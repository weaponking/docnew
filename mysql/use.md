*忘记root密码*
~~~
sudo vi mysqld.cnf

my.cnf 添加skip-grant-tables

mysql -u root

flush privileges;

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
~~~
