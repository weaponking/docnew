节点|Desc
-|-
Provider|提供方
Consumer|消费者
Registry|注册中心
Monitor|统计服务的调用次数和调用时间的监控中心
Container|运行容器

~~~
<dubbo:application name="demo-provider"/>
<dubbo:registry address="zookeeper://127.0.0.1:2181"/>
<dubbo:protocol name="dubbo" port="20880"/>
~~~

~~~
<dubbo:application name="demo-consumer"/>
<dubbo:registry address="zookeeper://127.0.0.1:2181"/>
~~~

集群模式|Desc
-|-
FailoverCluster|失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟。可通过 retries="2" 来设置重试次数(不含第一次)
Failfast Cluster|快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作
Failsafe Cluster|失败安全，出现异常时，直接忽略。
Failback Cluster|失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作。
Forking Cluster|并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 forks="2" 来设置最大并行数。
Broadcast Cluster|广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息。

负载均衡模式|Desc
-|-
Random LoadBalance|随机 按权重设置随机概率	<dubbo:service loadbalance="xxx"/>
RoundRobin LoadBalance|轮询 按公约后的权重设置轮询比率。
LeastActive LoadBalance|最少活跃调用数 相同活跃数的随机，活跃数指调用前后计数差。
ConsistentHash LoadBalance|一致性 Hash 相同参数的请求总是发到同一提供者。 当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动
