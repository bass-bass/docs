# you cannot remove a running container
docker-composeで立ち上げたcontainerのstopやdownが急に効かなくなった

# error
```
Error response from daemon: You cannot remove a running container 1710ee4fb30e9c5df3193dd37c3aa66bccbdd56ec9fcba2d7e6449a00b84d90c. Stop the container before attempting removal or force remove
```

# solution
docker desktopの再起動
1. `docker-compose stop` : NG
2. `docker-compsoe down` : NG
3. `docker-compose kill` : NG
4. restart docker desktop : OK
