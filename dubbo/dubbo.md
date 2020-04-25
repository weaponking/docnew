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
FailoverCluster|失败自动切换
Failfast Cluster|快速失败
Failsafe Cluster|失败安全
Failback Cluster|失败自动恢复
Forking Cluster|并行调用多个服务提供者

负载均衡模式|Desc
-|-
Random LoadBalance|随机 按权重设置随机概率	<dubbo:service loadbalance="xxx"/>
RoundRobin LoadBalance|轮询 按公约后的权重设置轮询比率。
LeastActive LoadBalance|最少活跃调用数 相同活跃数的随机，活跃数指调用前后计数差。
ConsistentHash LoadBalance|一致性 Hash 相同参数的请求总是发到同一提供者。 当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动

*服务调用过程*
~~~
0. 服务容器启动运行服务提供者
1. 服务提供者向注册中心注册提供的服务
2. 服务消费者向注册中心订阅所需的服务
3. 注册中心返回服务提供者地址列表给消费者
4. 服务消费者从提供者地址列表中选择提供者进行调用
5. 服务提供者和消费者定时发送统计数据到监控中心

默认情况下，Dubbo 使用 Netty 作为底层的通信框架

//官网源码导读
http://dubbo.apache.org/zh-cn/docs/source_code_guide/service-invoking-process.html
~~~
