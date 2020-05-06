*Least Recently Used \ random \ nerver*
~~~
volatile-lru:从设置了过期时间的数据集中，选择最近最久未使用的数据释放；
allkeys-lru:从数据集中(包括设置过期时间以及未设置过期时间的数据集中)，选择最近最久未使用的数据释放；
volatile-random:从设置了过期时间的数据集中，随机选择一个数据进行释放；
allkeys-random:从数据集中(包括了设置过期时间以及未设置过期时间)随机选择一个数据进行入释放；
volatile-ttl：从设置了过期时间的数据集中，选择马上就要过期的数据进行释放操作；
noeviction：不删除任意数据(但redis还会根据引用计数器进行释放),这时如果内存不够时，会直接返回错误。
~~~
