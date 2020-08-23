```
jmap -histo:live
```

~~~
TreeMap LinkedHashMap
CyclicBarrier
类加载机制

~~~

~~~
java -XX:+PrintCommandLineFlags -version

-Xms750m -Xmx750m -XX:NewSize=200m -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -XX:+PrintGCDateStamps -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -Xloggc:./gclogs

java -XX:+PrintFlagsWithComments //BEBUG

java -XX:+PrintFlagsFinal

arthas
~~~
