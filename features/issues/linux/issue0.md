# Could not resolve host: mirrorlist.centos.org; Unknown error
docker image構築時のyum installでmirrorlistが存在しないエラーが発生(20240701)

### Dockerfile
```Dockerfile
FROM centos:7

RUN yum -y update && yum clean all && \
    yum -y install sudo vim wget make patch gcc openssl-devel pcre-devel zlib-devel && \
    ...
```

# error
```
0.532 Loaded plugins: fastestmirror, ovl
0.710 Determining fastest mirrors
6.226 Could not retrieve mirrorlist http://mirrorlist.centos.org/?release=7&arch=x86_64&repo=os&infra=container error was
6.226 14: curl#6 - "Could not resolve host: mirrorlist.centos.org; Unknown error"
6.230 
6.230 
6.230  One of the configured repositories failed (Unknown),
6.230  and yum doesn't have enough cached data to continue. At this point the only
6.230  safe thing yum can do is fail. There are a few ways to work "fix" this:
6.230 
6.230      1. Contact the upstream for the repository and get them to fix the problem.
6.230 
6.230      2. Reconfigure the baseurl/etc. for the repository, to point to a working
6.230         upstream. This is most often useful if you are using a newer
6.230         distribution release than is supported by the repository (and the
6.230         packages for the previous distribution release still work).
6.230 
6.230      3. Run the command with the repository temporarily disabled
6.230             yum --disablerepo=<repoid> ...
6.230 
6.230      4. Disable the repository permanently, so yum won't use it by default. Yum
6.230         will then just ignore the repository until you permanently enable it
6.230         again or use --enablerepo for temporary usage:
6.230 
6.230             yum-config-manager --disable <repoid>
6.230         or
6.230             subscription-manager repos --disable=<repoid>
6.230 
6.230      5. Configure the failing repository to be skipped, if it is unavailable.
6.230         Note that yum will try to contact the repo. when it runs most commands,
6.230         so will have to try and fail each time (and thus. yum will be be much
6.230         slower). If it is a very temporary problem though, this is often a nice
6.230         compromise:
6.230 
6.230             yum-config-manager --save --setopt=<repoid>.skip_if_unavailable=true
6.230 
6.230 Cannot find a valid baseurl for repo: base/7/x86_64
```

# solution
`/etc/yum.repos.d/CentOS-*.repo`からmirrorlistのコメントアウトとbaseurlの書き換え

e.g. `/etc/yum.repos.d/CentOS-Base.repo`
* before
```
[base]
name=CentOS-$releasever - Base
mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=os&infra=$infra
#baseurl=http://mirror.centos.org/centos/$releasever/os/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
...
```

* after
```
[base]
name=CentOS-$releasever - Base
#mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=os&infra=$infra
baseurl=http://vault.centos.org/centos/$releasever/os/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
...
```

```Dockerfile
FROM centos:7

RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-* && \
    sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-* && \
    yum -y update && yum clean all && \
    yum -y install sudo vim wget make patch gcc openssl-devel pcre-devel zlib-devel && \
    ...
```

ref: https://stackoverflow.com/questions/78692851/could-not-retrieve-mirrorlist-http-mirrorlist-centos-org-release-7arch-x86-6
