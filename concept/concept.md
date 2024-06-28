~~~
RBAC
~~~

~~~
高并发 系统并行处理请求
 响应时间（Response Time)
 吞吐量（Throughput)
 每秒查询率QPS（Query Per Second)
 并发用户数
~~~

~~~
高可用HA（High Availability）减少系统不能提供服务的时间
 冗余 keepalived
 自动故障转移
~~~

~~~
QPS Queries Per Second 每秒查询率 QPS = req/sec = 请求数/秒
TPS Transactions Per Second 每秒处理的事务数目
~~~

~~~
Zab Zookeeper Atomic Broadcast Zookeeper原子广播

所有的写操作都必须通过Leader完成

Zab 协议的特性：
1）Zab 协议需要确保那些已经在 Leader 服务器上提交（Commit）的事务最终被所有的服务器提交。
2）Zab 协议需要确保丢弃那些只在 Leader 上被提出而没有被提交的事务


客户端向Leader发起写请求
Leader将写请求以Proposal的形式发给所有Follower并等待ACK
Follower收到Leader的Proposal后返回ACK
Leader得到过半数的ACK（Leader对自己默认有一个ACK）后向所有的Follower和Observer发送Commmit
Leader将处理结果返回给客户端

observer

第一次 myid最大 Leader
第二次 zxid最大 Leader
~~~

~~~
request -> nginx -> redis -> cache service -> service
mq -> pub/sub


~~~

Map<String, List<ClientLine>> groupClient = list.stream().collect(Collectors.groupingBy(ClientLine::getClientId));

        ArrayList<ClientLine> clientLines = new ArrayList<>();
        AtomicInteger index = new AtomicInteger();

        groupClient.forEach((clientId, item) -> {
            List<ClientLine> sortedItems = item.stream()
                .sorted(Comparator.comparingLong(ClientLine::getSortTime))
                    .collect(Collectors.toList());
            System.out.println("" + JSON.toJSONString(sortedItems));
            System.out.println(JSON.toJSONString(sortedItems.get(sortedItems.size()-1)));
            clientLines.add(sortedItems.get(sortedItems.size()-1));
            System.out.println(index.incrementAndGet());
        });
