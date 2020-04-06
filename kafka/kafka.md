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

*kafka不消费重复数据*
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
