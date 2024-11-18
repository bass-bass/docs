## `uptime`
reboot確認
e.g. rebootしてから1:34経過
```
# uptime
 18:38:24 up  1:34,  1 user,  load average: 0.04, 0.05, 0.09
```

## `vmstat`
memory, cpuなどの使用率
```
# vmstat
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0      0 47111372   2076 15081064    0    0    35     0   23   37  0  0 100  0  0
```

## `iostat`
I/O状況確認

## `lscpu`
cpuコア数など確認

## `find`
ファイルなど探す
e.g. `find / -name nginx.conf`

## `df -h`
サーバー容量確認
ディレクトリごとに確認する場合
1. `cd /path/to/some/dir`
2. `du -alh --max-depth=1`

## `cat /etc/redhat-release`
osバージョンなど確認

## `arp`
IPとMAcアドレスの対応確認
https://wa3.i-3-i.info/word11340.html
* `arp -a {ip_address}` : 確認
* `arp -d {ip_address}` : 削除（更新の際もこれでキャッシュ削除できる）
* `arp -s {ip_address} {mac_adddress}` : 登録

## `ip`
https://access.redhat.com/sites/default/files/attachments/rh_ip_command_cheatsheet_1214_jcs_print_ja4.pdf
* `ip addr` : ipアドレスとプロパティ情報の表示
* `ip link` : ネットワークインターフェイスの管理および表示
* `ip route` : ルーティングテーブルの変更や表示
