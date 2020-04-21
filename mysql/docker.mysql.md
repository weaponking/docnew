~~~
sudo docker network create mysql-net

sudo docker pull mysql

sudo docker inspect mysql | grep "MYSQL_VERSION"

mkdir -p mysql/{master,slave}/conf.d/data

#my.cnf
[mysqld]
log-bin=master-bin
log-bin-index=master-bin.index
server-id=1

[mysqld]
log-bin=slave-bin
log-bin-index=slave-bin.index
server-id=2

sudo docker run -d --name mysql-master \
  --net=mysql-net \
  -p 3307:3306 \
  -v /home/weapon/docker/mysql/master:/etc/mysql/conf.d \
  -e MYSQL_ROOT_PASSWORD=1234 \
  -d mysql

sudo docker run -d --name mysql-slave \
  --net=mysql-net \
  -p 3308:3306 \
  -v /home/weapon/docker/mysql/slave:/etc/mysql/conf.d \
  -e MYSQL_ROOT_PASSWORD=1234 \
  -d mysql

sudo docker exec -it mysql-master /bin/sh
mysql -u root -p
SHOW VARIABLES LIKE 'validate_password%';

CREATE USER 'slave_user'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
FLUSH PRIVILEGES;
GRANT REPLICATION SLAVE ON *.* TO 'slave_user'@'%';
show master status;

sudo docker exec -it mysql-slave /bin/sh
CHANGE MASTER TO MASTER_HOST='172.21.0.2',MASTER_USER='slave_user',MASTER_PASSWORD='123456',master_log_file='master-bin.000003',master_log_pos=837;
start slave;
show slave status\G

sudo docker stop mysql-slave mysql-master;
sudo docker rm mysql-slave mysql-master;
~~~

~~~
CREATE TABLE test
(
 id bigint(20) PRIMARY KEY AUTO_INCREMENT,
 name varchar(255)
);
INSERT INTO test (name) VALUES("test");
INSERT INTO test (name) SELECT name FROM test;
~~~

~~~
Authentication plugin 'caching_sha2_password' reported error: Authentication requires secure connection.

Can't connect to local MySQL server through socket '/var/run/mysqld/mysqld.sock'
-v /home/weapon/docker/mysql/master/data:/var/run/mysqld \
~~~
