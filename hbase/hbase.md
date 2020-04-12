export HBASE_HOME=/opt/hbase-2.1.3
export PATH=$PATH:$HBASE_HOME/bin
export JAVA_HOME=/opt/jdk1.8.0_201
 hbase -version

./start-hbase.sh

./hbase shell

create 'test', 'cf'
list
put 'test', 'row1', 'cf:a', 'value1'
put 'test', 'row1', 'cf:b', 'val2'
key为row1, 列为 cf:a， 值是 value1。HBase中的列是由 列族前缀和列的名字组成的，以冒号间隔
scan 'test'
get 'test', 'row1'
disable 'test'
drop 'test'

Ubuntu上设置ulimit
/etc/security/limits.conf

//使用外部zookeeper
hbase-env.sh
export HBASE_MANAGES_ZK=flase

1.Could not start ZK at requested port of 2181.
hbase-site.xml
<configuration>
	 <property>
	    <name>hbase.rootdir</name>
	    <value>hdfs://ip:9000/hbase</value>
	</property>
	<property>
		<name>hbase.zookeeper.property.dataDir</name>
		<value>/opt/zookeeper-3.4.13/data</value>
	</property>
	<property>
		<name>hbase.zookeeper.quorum</name>
		<value>localhost</value>
	</property>
	<property>
		<name>hbase.zookeeper.property.clientPort</name>
		<value>2181</value>
	</property>
	<property>
		<name>zookeeper.znode.parent</name>
		<value>/hbase</value>
	</property>
	<property>
		<name>hbase.cluster.distributed</name>
		<value>true</value>
	</property>
</configuration>

2.org.apache.hadoop.hbase.PleaseHoldException: Master is initializing
cat /etc/hosts
localhost 127.0.0.1

Table       (HBase table)
    Region       (Regions for the table)
         Store          (Store per ColumnFamily for each Region for the table)
              MemStore        (MemStore for each Store for each Region for the table)
              StoreFile       (StoreFiles for each Store for each Region for the table)
                    Block     (Blocks within a StoreFile within a Store for each Region for the table)


Region是HBase中分布式存储和负载均衡的最小单元。不同Region分布到不同RegionServer上，但并不是存储的最小单元。
Region由一个或者多个Store组成，每个store保存一个columns family，每个Strore又由一个memStore和0至多个StoreFile 组成。memStore存储在内存中， StoreFile存储在HDFS上。
HBase通过将region切分在许多机器上实现分布式。也就是说，你如果有16GB的数据，只分了2个region， 你却有20台机器，有18台就浪费了。
region数目太多就会造成性能下降，现在比以前好多了。但是对于同样大小的数据，700个region比3000个要好。
region数目太少就会妨碍可扩展性，降低并行能力。有的时候导致压力不够分散。这就是为什么，你向一个10节点的HBase集群导入200MB的数据，大部分的节点是idle的。
RegionServer中1个region和10个region索引需要的内存量没有太多的差别。

region的最大大小在hbase配置文件中定义
<property>
  <name>hbase.hregion.max.filesize</name>
  <value>10 * 1024 * 1024 * 1024</value>
</property>

Region 拆分策略:
IncreasingToUpperBoundRegionSplitPolicy，0.94.0默认region split策略。根据公式min(r^2*flushSize，maxFileSize)确定split的maxFileSize，其中r为在线region个数，maxFileSize由hbase.hregion.max.filesize指定。
ConstantSizeRegionSplitPolicy，仅仅当region大小超过常量值（hbase.hregion.max.filesize大小）时，才进行拆分。
DelimitedKeyPrefixRegionSplitPolicy，保证以分隔符前面的前缀为splitPoint，保证相同RowKey前缀的数据在一个Region中
KeyPrefixRegionSplitPolicy，保证具有相同前缀的row在一个region中（要求设计中前缀具有同样长度）。指定rowkey前缀位数划分region，通过读取table的prefix_split_key_policy.prefix_length属性，该属性为数字类型，表示前缀长度，在进行split时，按此长度对splitPoint进行截取。此种策略比较适合固定前缀的rowkey。当table中没有设置该属性，或其属性不为Integer类型时，指定此策略效果等同与使用IncreasingToUpperBoundRegionSplitPolicy。

The procedure WAL relies on the ability to hsync for proper operation during component failures, but the underlying filesystem does not support doing so. Please check the config value of 'hbase.procedure.store.wal.use.hsync' to set the desired level of robustness and ensure the config value of 'hbase.wal.dir' points to a FileSystem mount that can provide it.
<property>
  <name>hbase.unsafe.stream.capability.enforce</name>
  <value>false</value>
</property>


Invalid Trailer version. got 16 expected 1

mapreduce job failed. exited with exitCode 1
/opt/hadoop-3.1.2/libexec/hadoop-functions.sh添加JAVA_HOME=/opt/jdk1.8.0_201
