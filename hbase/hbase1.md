master regionserver namenode datanode

rowkey
一行记录唯一主键
每一行中，列的组成都是灵活的，行与行之间并不需要遵循相同的列定义， 也就是HBase数据表schema-less
region
将Key的完整区间切割成一个个的"Key Range" ，每一个"Key Range"称之为一个Region
将HBase中拥有数亿行的一个大表，横向切割成一个个"子表"，这一个个"子表"就是Region
Region是HBase中负载均衡的基本单元，当一个Region增长到一定大小以后，会自动分裂成两个
Column Family
如果将Region看成是一个表的横向切割，那么，一个Region中的数据列的纵向切割，称之为一个Column Family
每一个列，都必须归属于一个Column Family，这个归属关系是在写数据时指定的，而不是建表时预先定义

HBase的管理节点，通常在一个集群中设置一个主Master，一个备Master，主备角色的"仲裁"由ZooKeeper实现。 Master主要职责：
    负责管理所有的RegionServer
    建表/修改表/删除表等DDL操作请求的服务端执行主体
    管理所有的数据分片(Region)到RegionServer的分配
    如果一个RegionServer宕机或进程故障，由Master负责将它原来所负责的Regions转移到其它的RegionServer上继续提供服务
    Master自身也可以作为一个RegionServer提供服务，该能力是可配置的

HBase中数据是按照RowKey的字典顺序排列的，为了能够划分出合理的Region分割点，需要依据如下几点信息：
    Key的组成结构
    Key的数据分布预估
    如果不能基于Key的组成结构来预估数据分布的话，可能会导致数据在Region间的分布不均匀
    读写并发度需求
    依据读写并发度需求，设置合理的Region数量
