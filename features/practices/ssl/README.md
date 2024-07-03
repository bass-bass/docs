# letsencrypt

## installation
```
# yum install epel-release
# yum install certbot
```
## setup
[acme challenge](https://letsencrypt.org/ja/docs/challenge-types/#http-01-%E3%83%81%E3%83%A3%E3%83%AC%E3%83%B3%E3%82%B8)用の公開ディレクトリを用意し、webroot方式で証明書を発行する
```
# mkdir -m 755 -p /usr/local/nginx/html/.well-known/acme-challenge
# certbot certonly --webroot -w /usr/local/nginx/html -d sample.com -m hoge@example.com --agree-tos -n
Saving debug log to /var/log/letsencrypt/letsencrypt.log
Requesting a certificate for sample.com

Successfully received certificate.
Certificate is saved at: /etc/letsencrypt/live/sample.com/fullchain.pem
Key is saved at:         /etc/letsencrypt/live/sample.com/privkey.pem
This certificate expires on 2024-07-30.
These files will be updated when the certificate renews.
Certbot has set up a scheduled task to automatically renew this certificate in the background.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
If you like Certbot, please consider supporting our work by:
 * Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
 * Donating to EFF:                    https://eff.org/donate-le
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
```
* `--webroot` : ACME challengeの方式 (or `--standalone`)
   * >1: Runs an HTTP server locally which serves the necessary validation files under
the /.well-known/acme-challenge/ request path. Suitable if there is no HTTP
server already running. HTTP challenge only (wildcards not supported).
(standalone)
   * >2: Saves the necessary validation files to a .well-known/acme-challenge/
directory within the nominated webroot path. A seperate HTTP server must be
running and serving files from the webroot path. HTTP challenge only (wildcards
not supported). (webroot)
   * standalone方式だとport80で立ち上がるため別プロセスが動いている場合は注意
   ```
   Could not bind TCP port 80 because it is already in use by another process on this system (such as a web server). Please stop the program in question and then try again.
   ```
* `-w {path}` : `{path}/.well-known/acme-challenge/`を見に行く
* `-d {domain}` : 設定するドメイン
* `-m {mail}` : 通知用メールアドレス
## nginx
nginxのconfから参照するディレクトリにcertificationファイル配置
```
# cp /etc/letsencrypt/live/sample.com/fullchain.pem /etc/nginx/cert/sample.com.crt
# cp /etc/letsencrypt/live/sample.com/privkey.pem /etc/nginx/cert/sample.com.key
```

### nginx.conf
```conf
server {
    listen      80 backlog=10240;
    server_name sample.com;

    proxy_set_header Host $host;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Host $host;
    proxy_set_header X-Forwarded-Server $host;
    proxy_set_header X-Real-IP $remote_addr;

    server_name_in_redirect on;

    # http://sample.com/.well-known/acme-challenge -> /usr/local/nginx/html/.well-known/acme-challenge
    location / {
        root       /usr/local/nginx/html;
    }
}

server {
    listen      443 backlog=10240 ssl http2;
    server_name sample.com;

    ssl                       on;
    ssl_prefer_server_ciphers on;
    ssl_protocols             TLSv1 TLSv1.1 TLSv1.2;

    ssl_ciphers 'kEECDH+ECDSA+AES128 kEECDH+ECDSA+AES256 kEECDH+AES128 kEECDH+AES256 kEDH+AES128 kEDH+AES256 DES-CBC3-SHA +SHA !aNULL !eNULL !LOW !kECDH !DSS !MD5 !EXP !PSK !SRP !CAMELLIA !SEED';

    ssl_certificate     /etc/nginx/cert/sample.com.crt;
    ssl_certificate_key /etc/nginx/cert/sample.com.key;

    proxy_set_header Host               $host;
    proxy_set_header X-Forwarded-For    $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Host   $host;
    proxy_set_header X-Forwarded-Server $host;
    proxy_set_header X-Real-IP          $remote_addr;

    server_name_in_redirect on;

    location /favicon.ico {
        log_not_found off;
    }

    location / {
        root       /usr/local/nginx/html;
    }
}
```

# 証明書更新
```
# certbot renew
# cp /etc/letsencrypt/live/sample.com/fullchain.pem /etc/nginx/cert/sample.com.crt
# cp /etc/letsencrypt/live/sample.com/privkey.pem /etc/nginx/cert/sample.com.key
# nginx -s reload
```
