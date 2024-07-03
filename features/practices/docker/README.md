# installation
docker, docker-composeなどの一式インストール
```
# curl -o /etc/yum.repos.d/docker-ce.repo https://download.docker.com/linux/centos/docker-ce.repo
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1919  100  1919    0     0  19383      0 --:--:-- --:--:-- --:--:-- 19581
# yum install -y docker-ce docker-ce-cli containerd.io
# docker -v
Docker version 27.0.2, build 912c1dd
# systemctl start docker
# systemctl enable docker
Created symlink /etc/systemd/system/multi-user.target.wants/docker.service → /usr/lib/systemd/system/docker.service.
# curl -L -o /usr/local/bin/docker-compose https://github.com/docker/compose/releases/download/1.29.2/docker-compose-Linux-x86_64
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
100 12.1M  100 12.1M    0     0  9958k      0  0:00:01  0:00:01 --:--:-- 29.9M
# chmod 755 /usr/local/bin/docker-compose
# docker-compose -v
[5691] Error loading Python lib '/tmp/_MEIwaVsAU/libpython3.7m.so.1.0': dlopen: libcrypt.so.1: cannot open shared object file: No such file or directory
# yum install libxcrypt-compat
# docker-compose -v
docker-compose version 1.29.2, build 5becea4c
```