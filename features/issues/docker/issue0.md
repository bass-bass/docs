# executable file not found in $PATH
`docker-compose up`時にコマンドが見つからずエラーになる

# error
```
ERROR: for nginx  Cannot start service nginx: OCI runtime create failed: container_linux.go:380: starting container process caused: exec: "nginx": executable file not found in $PATH: unknown
Encountered errors while bringing up the project.
```

# cause
hostの`/usr/local/nginx`をマウントしていたため
nginxのinstall時に`mkdir -p /usr/local/nginx/sbin`が作成できていなかった

# solution
マウントディレクトリ位置を変更
before
```yml
    volumes:
      - "/usr/local/nginx:/usr/local/nginx"
```

after
```yml
    volumes:
      - "/usr/local/nginx/cache:/usr/local/nginx/cache"
      - "/usr/local/nginx/html:/usr/local/nginx/html"
```
