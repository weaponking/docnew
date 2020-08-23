*忘记root密码*
~~~
sudo vi mysqld.cnf

my.cnf 添加skip-grant-tables

mysql -u root

flush privileges;

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
~~~

~~~
show tables;

select * from supplier_pp

show index from supplier_pp

alter table supplier_pp drop index ppcode_index

alter table supplier_pp drop index code_index

explain select * from supplier_pp

复合索引 左前缀 a,b,c = a a,b a,b,c
~~~
