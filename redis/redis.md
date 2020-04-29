*启用密码redis.conf*
  ```
  CONFIG get requirepass

  CONFIG set requirepass "pass123"

  auth pass123

  //获取配置信息
  CONFIG GET *

  ```

配置项|说明
-|-
daemonize no | Redis 默认不是以守护进程的方式运行，可以通过该配置项修改，使用 yes 启用守护进程（Windows 不支持守护线程的配置为 no ）
pidfile /var/run/redis.pid | 当 Redis 以守护进程方式运行时，Redis 默认会把 pid 写入 /var/run/redis.pid 文件，可以通过 pidfile 指定
port 6379 | 默认端口为 6379
bind 127.0.0.1 | 绑定的主机地址
timeout 300 | 	当客户端闲置多长时间后关闭连接，如果指定为 0，表示关闭该功能
loglevel notice | 指定日志记录级别，Redis 总共支持四个级别：debug、verbose、notice、warning，默认为 notice
logfile stdout | 日志记录方式，默认为标准输出，如果配置 Redis 为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给 /dev/null
databases 0 | 设置数据库的数量，默认数据库为0，可以使用SELECT 命令在连接上指定数据库id
save | 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合 save 900 1 save 300 10 save 60 10000 分别表示 900 秒（15 分钟）内有 1 个更改，300 秒（5 分钟）内有 10 个更改以及 60 秒内有 10000 个更改。
rdbcompression yes | 指定存储至本地数据库时是否压缩数据，默认为 yes，Redis 采用 LZF 压缩，如果为了节省 CPU 时间，可以关闭该选项，但会导致数据库文件变的巨大
dbfilename dump.rdb | 指定本地数据库文件名，默认值为 dump.rdb
dir ./ | 指定本地数据库存放目录
slaveof | 设置当本机为 slav 服务时，设置 master 服务的 IP 地址及端口，在 Redis 启动时，它会自动从 master 进行数据同步
masterauth | 当 master 服务设置了密码保护时，slav 服务连接 master 的密码
requirepass foobared | Redis 连接密码
maxclients 128 | 设置同一时间最大客户端连接数，默认无限制，Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis 会关闭新的连接并向客户端返回 max number of clients reached 错误信息
maxmemory | 指定 Redis 最大内存限制，Redis 在启动时会把数据加载到内存中，达到最大内存后，Redis 会先尝试清除已到期或即将到期的 Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis 新的 vm 机制，会把 Key 存放内存，Value 会存放在 swap 区
appendonly no | 指定是否在每次更新操作后进行日志记录，Redis 在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis 本身同步数据文件是按上面 save 条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为 no
appendfilename appendonly.aof | 指定更新日志文件名，默认为 appendonly.aof
appendfsync everysec | 指定更新日志条件，共有 3 个可选值：no：表示等操作系统进行数据缓存同步到磁盘（快）always：表示每次更新操作后手动调用 fsync() 将数据写到磁盘（慢，安全）everysec：表示每秒同步一次（折中，默认值）
vm-enabled no | 指定是否启用虚拟内存机制，默认值为 no，简单的介绍一下，VM 机制将数据分页存放，由 Redis 将访问量较少的页即冷数据 swap 到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析 Redis 的 VM 机制）
vm-swap-file /tmp/redis.swap | 虚拟内存文件路径，默认值为 /tmp/redis.swap，不可多个 Redis 实例共享
vm-max-memory 0 | 将所有大于 vm-max-memory 的数据存入虚拟内存，无论 vm-max-memory 设置多小，所有索引数据都是内存存储的(Redis 的索引数据 就是 keys)，也就是说，当 vm-max-memory 设置为 0 的时候，其实是所有 value 都存在于磁盘。默认值为 0
vm-page-size 32 | Redis swap 文件分成了很多的 page，一个对象可以保存在多个 page 上面，但一个 page 上不能被多个对象共享，vm-page-size 是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page 大小最好设置为 32 或者 64bytes；如果存储很大大对象，则可以使用更大的 page，如果不确定，就使用默认值
vm-pages 134217728 |设置 swap 文件中的 page 数量，由于页表（一种表示页面空闲或使用的 bitmap）是在放在内存中的，，在磁盘上每 8 个 pages 将消耗 1byte 的内存。
vm-max-threads 4 | 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4
glueoutputbuf yes | 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启
hash-max-zipmap-entries 64 hash-max-zipmap-value 512| 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法
activerehashing yes | 指定是否激活重置哈希，默认为开启


数据类型|添加|枚举|说明
-|-|-|-|
String | set test val1 |get test|最大能存储512M
Hash | hmset settest "key1" "val1" "key2" "val2" "key3" "val3"| hmget settest "key1" "key2" "key3"|存储对象
List | lpush list 1 2 3 |lrange list 0 1|增删快
Set | sadd testset 1 |smembers testset|
zset | zadd testzset 2 1 zadd testzset 1 1| zrangebyscore testzset1 0 3|排序

*command*

```
redis-cli -h 127.0.0.1 -p 6379 -a "password"

redis-cli --raw(中文乱码)

ping

del key

exists key

dump key (序列化key)

expire key 3 (设置过期时间以秒计)

expireat key timestamp

pexpire key3 3000 (设置过期时间以毫秒计)

keys *

move key dbindex (将当前数据库的 key 移动到给定的数据库 db 当中)

persist key (移除 key 的过期时间，key 将持久保持)

pttl key (以毫秒为单位返回 key 的剩余的过期时间)

ttl key (以秒为单位，返回给定 key 的剩余生存时间)

randomkey (从当前数据库中随机返回一个 key)

rename key newkey

renamenx key newkey (仅当 newkey 不存在时，将 key 改名为 newkey)

type key3 (返回 key 所储存的值的类型)
```

*String command*
```
getrange key 0 2 (返回 key 中字符串值的子字符)

getset key val (将给定 key 的值设为 value ，并返回 key 的旧值)

mget key key1 (获取所有(一个或多个)给定 key 的值)

setnx key value (只有在 key 不存在时设置 key 的值)

append key val (如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾)

incr key 递增

decr key 递减

incrby key setp

decrby key setp
```
*list command*
~~~
lpush key 1 2 3 4 5 //取反
lrange 0 -1
rpush key 1 2 3 4 5 //怎么进怎么出
lpop key//正序
rpop key//反序
llen key
lrem key count value
ltrim key start stop
rpoplpush list1 list2 //list1 末尾加到 list2头上
linsert list01 before/after val1 val2
~~~
*set command*
~~~
sadd key val val1
smembers s1
sismember s1 val
scard s1
srandmember key count
spop key
smove key1 key2 val
sdiff 差集 在第一个里不在第二里
sinter 交集
sunion 并集
~~~
*hash command*
~~~
hset key
hget
hmset
hmget
hgetall
hdel
hlen
hexists
hkeys
hvals
hsetnx
~~~
