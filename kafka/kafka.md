*链接*
~~~
配置:
http://kafka.apache.org/23/documentation.html#configuration
~~~

*kafka ack 机制 :*
~~~
producer的消息发送确认机制 影响到Kafka集群的吞吐量和消息可靠性

ack可选值 1，0，-1 默认值1

props.put(ProducerConfig.ACKS_CONFIG, "1");

ack=1, producer只要收到leader副本成功写入的通知就认为推送消息成功

ack=0, producer发送一次 不管是否发送成功

ack=-1, producer只有收到分区内所有副本的成功写入的通知才认为推送消息成功
~~~

*kafka 消息顺序消费*
~~~
  同topic 同partition 有序
~~~

*kafka不丢失数据*
~~~
同步模式:
producer.type=sync
request.required.acks=-1(写入所有副本)

异步模式:
producer.type=async
#阻塞生产
queue.enqueue.timeout.ms = -1

The amount of time to block before dropping messages when running in async mode and the buffer has reached queue.buffering.max.messages. If set to 0 events will be enqueued immediately or dropped if the queue is full (the producer send call will never block). If set to -1 the producer will block indefinitely and never willingly drop a send.
~~~

~~~
分区中的所有副本统称为AR（Assigned Repllicas）
所有与leader副本保持一定程度同步的副本（包括Leader）组成ISR（In-Sync Replicas）
与leader副本同步滞后过多的副本（不包括leader）副本，组成OSR(Out-Sync Relipcas)
AR=ISR+OSR

Kafka在启动的时候会开启两个与ISR相关的定时任务，名称分别为“isr-expiration"和”isr-change-propagation"。

/brokers/topics/partition/state
~~~

~~~
HW | High Watermak
LEO | Log End Offset
LSO | LastStableOffset
LW | Low Watermark
~~~

~~~
分区器:根据键值确定消息应该处于哪个分区中，默认情况下使用轮询分区
序列化器:键序列化器和值序列化器，将键和值都转为二进制流 还有反序列化器 将二进制流转为指定类型数据
拦截器:两个方法 doSend()方法会在序列化之前完成 onAcknowledgement()方法在消息确认或失败时调用 可以添加多个拦截器按顺序执行
~~~

~~~
kafka消费者分配分区
 分区数除以消费者数
~~~
