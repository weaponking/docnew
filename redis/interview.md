~~~
（key-value）内存数据库
 数据结构 : String Hash List Set Zset
 ACID
 CAP BASE
~~~

~~~
redis支持 将内存中的数据持久化到磁盘中,在下次启动redis时可以将磁盘中的数据加载到内存中
~~~

## RDB 快照(某时刻数据备份)
~~~
fork 原进程

dump save默认策略
15min 1 change
5min 10 change
60sec 10000 change
~~~
## AOF (记录日志)
~~~
记录写操作
redis-check-aof --fix
rewrite 默认 100% 64MB
~~~
*transcation*
~~~
multi exec discard watch
部分支持
multi
set k1 aa
incr k1
set k2 v2
exec
~~~
*other*
~~~
slaveof no one
~~~
