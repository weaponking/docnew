//1)IO是面向流的，NIO是面向缓冲区的；
//2)IO流是阻塞的，NIO流是不阻塞的;
//3)NIO有选择器，而IO没有。

//从通道进行数据读取 ：创建一个缓冲区，然后请求通道读取数据。
//从通道进行数据写入 ：创建一个缓冲区，填充数据，并要求通道写入数据。

//	Buffers:
//	Java NIO Buffers用于和NIO Channel交互。 我们从Channel中读取数据到buffers里，从Buffer把数据写入到Channels；
//	Buffer本质上就是一块内存区；
//	一个Buffer有三个属性是必须掌握的，分别是：capacity容量、position位置、limit限制

//  Channels

//  Selectors
//	用于检查一个或多个NIO Channel（通道）的状态是否处于可读、可写。如此可以实现单线程管理多个channels,也就是可以管理多个网络链接
//	使用Selector的好处在于： 使用更少的线程来就可以来处理通道了， 相比使用多个线程，避免了线程上下文切换带来的开销。	
