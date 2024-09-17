# docker-compose
ref : https://docs.docker.jp/compose/reference/docker-compose.html

* `docker-compose build` : imageの作成
* `docker-compose up` : containerのの作成と起動
* `docker-compose stop` : containerの停止
* `docker-compose down` : stop + container,networkの削除
  * `docker-compose down --rmi all` : down + imageの削除
  * `docker-compose down --rmi all --volumes` : down + image,volumeの削除
  * `docker-compose down --rmi all --volumes --remove-orphans` : + ファイルで定義していないサービス用のコンテナも削除

# docker
ref : https://docs.docker.jp/engine/reference/commandline/cli.html#id2

* `docekr ps` : 起動しているcontainer一覧
* `docekr ps -a` : container一覧
* `docker images` : image一覧
* `docker logs {container_id}` : containerのログ確認
* `docker stop {container_id}` : container停止
* `docker rm {container_id}` : container削除
* `docker rmi {image_id}` : image削除
* `docker inspect {image_id}` : image詳細
* `docker exec -it {container_name} bash` : containerに入る
* `docker image prune` : dangling imageの削除
  * `docker image prune -a` : dangling + unused imageの削除
* `docker system prune` : 未使用container,image,network
  * `docker system prune -a` : + buildキャッシュ,中間imageの削除
* `docker system df` : 容量確認
