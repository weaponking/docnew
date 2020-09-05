```sequence
participant 车机
participant 注册server
participant ZKservice
participant tcpserver
车机->注册server: 启动时请求
Note right of 车机: 注册车机号/ip
注册server-->>ZKservice : dubbo
Note left of ZKservice: 获取tcp长连server
tcpserver->ZKservice : 启动注册节点
ZKservice-->>tcpserver : 监听节点
tcpserver-->车机: tcp长连
```

~~~
key:vin, value:ip

key:ip, value:tcpserver
~~~


```sequence
participant app
participant service
participant redis
participant tcpserver
participant car

app->service : vin
service->redis: vin->ip->tcpserver
service-->tcpserver: send command?
tcpserver-->>service : message?
app-->car:?
```
~~~
获取tcp长连server既是netty又是dubbo又是wcontroller?
~~~
