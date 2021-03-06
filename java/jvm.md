~~~
  堆 栈 计数器 元空间

  1.元空间不在虚拟机 在本地内存
  2.堆: 常量 静态变量 类信息

  jvm full gc 触发条件
~~~

参数|Desc|备注
-|-|-
-Xms|初始堆大小|默认是物理内存的1/64
-Xmx|最大堆大小|默认是物理内存的1/4 堆最大初始设置相同,fullgc重新申请内存
-Xmn|新生代大小|
-XX:+UseCompressedOops|压缩指针
-XX:MetaspaceSize|元空间
-XX:MaxMetaspaceSize|元空间

*OOM*
~~~
jmap -dump:format=b,file=dump.hprof pid
~~~

*GC*
~~~
Minor GC触发条件：当Eden区满时，触发Minor GC。

Full GC触发条件：
（1）调用System.gc时，系统建议执行Full GC，但是不必然执行
（2）老年代空间不足
（4）通过Minor GC后进入老年代的平均大小大于老年代的可用内存

（5）由Eden区、From Space区向To Space区复制时，对象大小大于To Space可用内存，则把该对象转存到老年代，且老年代的可用内存小于该对象大小。
~~~

~~~
串行收集器/并行收集器/并发标记清除
内存占用及并发开销最小化/应用吞吐量最大化/应用GC暂停时间最小化


~~~
