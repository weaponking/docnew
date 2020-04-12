~~~
Tomcat Connector三种运行模式（BIO, NIO, APR）

  BIO: 一个线程处理一个请求。缺点：并发量高时，线程数较多，浪费资源。

  NIO:
    <Connector port="8080"
         protocol="org.apache.coyote.http11.Http11NioProtocol"
         connectionTimeout="20000"
  		   redirectPort="8443"/>

    Tomcat8+：
    <Connector port="8080"
         protocol="org.apache.coyote.http11.Http11Nio2Protocol"
         connectionTimeout="20000"
         redirectPort="8443"/>

    参数：
        protocol，Tomcat 8 设置 nio2 更好：org.apache.coyote.http11.Http11Nio2Protocol
        protocol，Tomcat 6、7 设置 nio 更好：org.apache.coyote.http11.Http11NioProtocol
        enableLookups，禁用DNS查询
        acceptCount，指定当所有可以使用的处理请求的线程数都被使用时，可以放到处理队列中的请求数，超过这个数的请求将不予处理，默认设置 100
        maxPostSize，以 FORM URL 参数方式的 POST 提交方式，限制提交最大的大小，默认是 2097152(2兆)，它使用的单位是字节。10485760 为 10M。如果要禁用限制，则可以设置为 -1
        acceptorThreadCount，用于接收连接的线程的数量，默认值是1。一般这个指需要改动的时候是因为该服务器是一个多核CPU，如果是多核 CPU 一般配置为 2

  APR: Apache Portable Runtime，从操作系统层面解决io阻塞问题
~~~

~~~
连接池

    default:
    <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
        maxThreads="150" minSpareThreads="4"/>

    <Executor
        name="tomcatThreadPool"
        namePrefix="catalina-exec-"
        maxThreads="500"
        minSpareThreads="100"
        prestartminSpareThreads = "true"
        maxQueueSize = "100"/>

    参数：
      maxThreads，最大并发数，默认设置 200，一般建议在 500 ~ 800，根据硬件设施和业务来判断
      minSpareThreads，Tomcat 初始化时创建的线程数，默认设置 25
      prestartminSpareThreads，在 Tomcat 初始化的时候就初始化 minSpareThreads 的参数值，如果不等于 true，minSpareThreads 的值就没啥效果了
      maxQueueSize，最大的等待队列数，超过则拒绝请求
~~~
