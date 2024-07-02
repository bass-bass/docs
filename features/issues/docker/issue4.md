# KeyError: 'ContainerConfig'
`docker-compose up -d --build`などで再ビルドしようとした際にエラーが発生

### version
```
* os : AlmaLinux release 9.4 (Seafoam Ocelot)
* docker : Docker version 27.0.2, build 912c1dd
* docker-compose : docker-compose version 1.29.2, build 5becea4c
```

# error
```
KeyError: 'ContainerConfig'
```

# solution
`docker-compose down`実行後再度`docker-compose up -d --build`を実行

containerを停止せずにupしようとすることで発生している感じ

ref : https://askubuntu.com/questions/1508129/docker-compose-giving-containerconfig-errors-after-update-today
