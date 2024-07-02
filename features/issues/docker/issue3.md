# dlopen: libcrypt.so.1: cannot open shared object file
docker-composeインストール後コマンド使用(`docker-compose- v`など)するとエラーが出た

# error
```
[5691] Error loading Python lib '/tmp/_MEIwaVsAU/libpython3.7m.so.1.0': dlopen: libcrypt.so.1: cannot open shared object file: No such file or directory
```

# solution
libcryptのインストール
`yum install libxcrypt-compat`
