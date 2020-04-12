~~~
1.字符串常量池：
java字符串不可变,为了减少创建字符串的数量,创建字符串时先从常量池检索,找到返回引用.找不到实例化一个返回
Integer 缓存返回127 -》 -128之间的int
2.字符串不可变：
String final 类
private final char value[]
3.CAS(compare and swap)
无锁的非阻塞 乐观锁
保证同时访问时只有一个线程能进来，当多个线程同时并发访问操作共享数据的时候，有且只有一个能够成功，其他的线程都会失败
4.Java中throw和throws的区别
5.线程的生命周期包括哪几个阶段
new runnable running blocked dead
6.synchronized与Lock的区别
synchronized是java内置关键字 Lock是个java类
Lock可以判断是否获取到锁
Lock需在finally中手工释放锁
Lock锁可重入、可判断、可公平
7.AbstractQueuedSynchronizer（AQS）
8.CountDownLatch/CyclicBarrier
CountDownLatch减计数，CyclicBarrier加计数。
CountDownLatch是一次性的，CyclicBarrier可以重用。
9.线程池启动线程submit和execute
submit(Runnable x) 返回一个future
10.线程池reject
AbortPolicy 丢弃任务，抛运行时异常
CallerRunsPolicy 执行任务
DiscardPolicy 忽视，什么都不会发生
DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
11.类的加载过程
类加载阶段
  Java虚拟机将.class文件读入内存，并为之创建一个Class对象。
  任何类被使用时系统都会为其创建一个且仅有一个Class对象。
  这个Class对象描述了这个类创建出来的对象的所有信息，比如有哪些构造方法，都有哪些成员方法，都有哪些成员变量等。
链接
  验证阶段。主要的目的是确保被加载的类（.class文件的字节流）满足Java虚拟机规范，不会造成安全错误。
  准备阶段。负责为类的静态成员分配内存，并设置默认初始值。
  解析阶段。将类的二进制数据中的符号引用替换为直接引用。
初始化
12.哪些情况会导致Full GC
13.拦截器与过滤器的区别
过滤器依赖servlet容器
14.数据库三大范式
确保每列的原子性
确保数据库表中的每一列都和主键相关
确保每列都和主键列直接相关,而不是间接相关
15.依赖注入的方式有几种
set 构造器 静态工厂 实例工厂
16.Spring容器对Bean组件是如何管理的
默认随容器创建,通过lazy-init可以改变对象实例化时机
17.springmvc执行流程
request -> dispatcherservlet -> HandlerMapping -> HandlerAdapter -> controller -> viewResolver
18.spring 事务
PROPAGATION_REQUIRED：
如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
PROPAGATION_SUPPORTS：
支持当前事务，如果当前没有事务，就以非事务方式执行。
PROPAGATION_MANDATORY：
使用当前的事务，如果当前没有事务，就抛出异常。
PROPAGATION_REQUIRES_NEW：
新建事务，如果当前存在事务，把当前事务挂起。
PROPAGATION_NOT_SUPPORTED：
以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
PROPAGATION_NEVER：
以非事务方式执行，如果当前存在事务，则抛出异常。
PROPAGATION_NESTED：
如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类 似的操作。
PROPAGATION_REQUIRES_NEW 和 PROPAGATION_NESTED 的最大区别在于, PROPAGATION_REQUIRES_NEW 完全是一个新的事务, 而 PROPAGATION_NESTED 则是外部事务的子事务, 如果外部事务 commit, 潜套事务也会被 commit, 这个规则同样适用于 roll back
19.事务隔离级别
DEFAULT 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.
未提交读（read uncommited） :脏读，不可重复读，虚读都有可能发生
已提交读 （read commited）:避免脏读。但是不可重复读和虚读有可能发生
可重复读 （repeatable read） :避免脏读和不可重复读.但是虚读有可能发生.
串行化的 （serializable） :避免以上所有读问题.
20.SpringMVC如何处理JSON数据
21.redis zookeeper分布式锁
22.PAXOS
  Paxos算法解决的问题是在一个可能发生消息可能会延迟、丢失、重复的分布式系统中如何就某个值达成一致，保证不论发生以上任何异常，都不会破坏决议的一致性
23.hbase
24.注解
java.lang.annotation.Annotation
@Target：注解的作用目标
@Retention：注解的生命周期
@Documented：注解是否应当被包含在 JavaDoc 文档中
@Inherited：是否允许子类继承该注解
25.volatile
可见性 :一个线程修改的状态对另一个线程是可见
禁止指令重排序优化
26.spring init bean
@PostConstruct
@InitializingBean
BeanPostProcessor
27.zookeeper
分布式协调服务
Zookeeper文件系统:Zookeeper为了保证高吞吐和低延迟，在内存中维护了这个树状的目录结构，每个节点的存放数据上限为1M
ZAB协议
28.CGLIB：字节码技术为一个类创建子类
29.HTTP1.0、HTTP1.1 和 HTTP2.0 区别
在HTTP1.1中新增了24个错误状态响应码
HTTP 1.1支持长连接（PersistentConnection）和请求的流水线（Pipelining）处理，在一个TCP连接上可以传送多个HTTP请求和响应，减少了建立和关闭连接的消耗和延迟，在HTTP1.1中默认开启Connection： keep-alive，一定程度上弥补了HTTP1.0每次请求都要创建连接的缺点
HTTP 1.1新增缓存处理策略
30.redis
master server 先dump出内存快照文件，然后将rdb文件传给slave server，slave server 根据rdb文件重建内存表
~~~
