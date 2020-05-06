~~~
inner join
left join
right join
full join
~~~

~~~
show variables like '%buffer%'

join_buffer_size属性

show variables like '%datadir%'

~~~
*explain*
~~~
type中包含的值：
system、const： 可以将查询的变量转为常量. 如id=1; id为 主键或唯一键.
eq_ref： 访问索引,返回某单一行的数据.(通常在联接时出现，查询使用的索引为主键或惟一键)
ref： 访问索引,返回某个值的数据.(可以返回多行) 通常使用=时发生
range： 这个连接类型使用索引返回一个范围中的行，比如使用>或<查找东西，并且该字段上建有索引时发生的情况(注:不一定好于index)
index： 以索引的顺序进行全表扫描，优点是不用排序,缺点是还要全表扫描
ALL： 全表扫描，应该尽量避免

Extra中包含的值：
using index： 只用到索引,可以避免访问表，性能很高。
using where： 使用到where来过滤数据， 不是所有的where clause都要显示using where. 如以=方式访问索引。
using tmporary： 用到临时表去处理当前的查询。
using filesort： 用到额外的排序，此时mysql会根据联接类型浏览所有符合条件的记录，并保存排序关键字和行指针，然后排序关键字并按顺序检索行。(当使用order by v1,而没用到索引时,就会使用额外的排序)。
range checked for eache record(index map:N)： 没有好的索引可以使用。

show variables like '%sort_buffer_size%';
~~~

*自带库*
~~~
information_schema 数据库的名、数据库的表、访问权限、数据库表的数据类型，数据库索引
mysql 存储数据库的用户、权限设置、关键字
performance_schema 收集数据库服务器性能参数
sys 降低performance_schema复杂度
~~~

~~~
show engines;

SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA ='performance_schema' and engine='performance_schema';
~~~

~~~
一个表最多可有 16 个索引。最大索引长度是 256 个字节
MySQL 能在多个列上创建索引。一个索引可以由最多 15 个列组成
SHOW INDEX FROM
~~~
