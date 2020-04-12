## 原子 可见 有序

## 线程之间的通信机制

### 共享内存 :

```
线程之间共享程序的公共状态,线程之间通过写-读内存中的公共状态来隐式进行通信
同步是显式进行的,必须显式指定某个方法或某段代码需要在线程之间互斥执行

```

### 消息传递

```
线程之间没有公共状态,线程之间必须通过明确的发送消息来显式进行通信
由于消息的发送必须在消息的接收之前同步是隐式进行的

```

## Java的并发采用的是共享内存模型,线程之间的通信总是隐式进行

```
实例域、静态域和数组元素存储在堆内存中,堆内存在线程之间共享

happens-before

 * 监视器锁规则:对一个监视器的解锁,happens- before 于随后对这个监视器的加锁
 * volatile 变量规则:对一个 volatile 域的写,happens- before 于任意后续对这个 volatile 域的读
 * 传递性:如果 A happens- before B,且 B happens- before C,那么 A happens- before C


as-if-serial

 * 不管怎么重排序程序的执行结果不能被改变

volatile

 * 可见性。对一个 volatile 变量的读,总是能看到(任意线程)对这个 volatile变量最后的写入
 * 原子性:对任意单个 volatile 变量的读/写具有原子性,但类似于 volatile++这种复合操作不具有原子性
```
~~~
public enum State {
      NEW,

      RUNNABLE,

      BLOCKED,

      WAITING,

      TIMED_WAITING,

      TERMINATED;
  }
~~~
