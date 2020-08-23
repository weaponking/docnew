*Least Recently Used \ random \ nerver*

~~~

volatile-lru:从设置过期时间的数据集中选择最近最久未使用的数据释放

allkeys-lru:从所有数据集中选择最近最久未使用的数据释放

volatile-random:从设置过期时间的数据集中随机选择一个数据进行释放

allkeys-random:从数据集中随机选择一个数据进行入释放

volatile-ttl:从设置过期时间的数据集中选择马上就要过期的数据进行释放

noeviction:不删除数据

~~~
