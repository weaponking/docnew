~~~
Netty基于NIO（Nonblocking I/O，非阻塞IO）开发的网络通信框架

ByteBuf零拷贝

Channel，表示一个连接，可以理解为每一个请求，就是一个Channel。
ChannelHandler，核心处理业务就在这里，用于处理业务请求。
ChannelHandlerContext，用于传输业务数据。
ChannelPipeline，用于保存处理过程需要用到的ChannelHandler和ChannelHandlerContext
~~~
