# ローカルでhtmlをhttpで簡単に表示する
https://www.php.net/manual/ja/features.commandline.webserver.php

1. 適当なディレクトリにhtmlファイル作成
   1. `/hoge/huga/index.html`
2. `cd /hoge/huga`
3. `php -S localhost:8888`
```
# php -S localhost:8888
[Fri Sep 13 17:23:27 2024] PHP 8.2.7 Development Server (http://localhost:8888) started
[Fri Sep 13 17:23:40 2024] [::1]:65110 Accepted
[Fri Sep 13 17:23:40 2024] [::1]:65110 [404]: GET / - No such file or directory
[Fri Sep 13 17:23:40 2024] [::1]:65111 Accepted
[Fri Sep 13 17:23:40 2024] [::1]:65110 Closing
[Fri Sep 13 17:23:40 2024] [::1]:65111 [404]: GET /favicon.ico - No such file or directory
[Fri Sep 13 17:23:40 2024] [::1]:65111 Closing
[Fri Sep 13 17:23:45 2024] [::1]:65112 Accepted
[Fri Sep 13 17:23:45 2024] [::1]:65112 [200]: GET /index.html
...
```
4. Access `http://localhost:8888/index.html`
5. 