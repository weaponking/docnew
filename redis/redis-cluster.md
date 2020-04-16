~~~
sudo docker pull ruby

sudo docker network create redis-network

sudo docker network ls

sudo docker network inspect redis-network

sudo docker network inspect redis-network |grep "Gateway"

sudo mkdir -p /docker/redis/{6379,6380,6381}

sudo passwd root

chown -R weapon:weapon docker/

sudo docker network inspect redis-network

~~~

*主从*
~~~
sudo docker run --name redis-6379 -p 6379:6379 --net=redis-network --ip 172.20.0.2 -d redis
sudo docker run --name redis-6380 -p 6380:6380 --net=redis-network --ip 172.20.0.3 -d redis
sudo docker run --name redis-6381 -p 6381:6380 --net=redis-network --ip 172.20.0.4 -d redis

sudo docker inspect

sudo docker start -i redis-6379
sudo docker start -i redis-6380
sudo docker start -i redis-6381

sudo docker exec -it redis-6381 redis-cli
SLAVEOF 172.17.0.2 6379

sudo docker exec -it redis-6380 redis-cli
SLAVEOF 172.17.0.2 6379

info replication
~~~

~~~
sudo docker stop redis-6381 redis-6380 redis-6379
sudo docker rm redis-6381 redis-6380 redis-6379
sudo docker start redis-6381 redis-6380 redis-6379
~~~

*redis-6379.conf*
~~~
port 6379

appendonly no

requirepass 123456
~~~
*redis-6380.conf*
~~~
port 6380

appendonly no

slaveof 172.20.0.2 6379

masterauth 123456

requirepass 123456
~~~
*主从 run conf*
~~~
sudo docker run --net=redis-network --ip 172.20.0.2 --restart=always --name redis-6379 -v /home/weapon/docker/redis/6379/redis-6379.conf:/etc/redis/redis-6379.conf -v /home/weapon/docker/redis/6379/data:/data -d redis redis-server /etc/redis/redis-6379.conf

sudo docker run --net=redis-network --ip 172.20.0.3 --restart=always --name redis-6380 -v /home/weapon/docker/redis/6380/redis-6380.conf:/etc/redis/redis-6380.conf -v /home/weapon/docker/redis/6380/data:/data -d redis redis-server /etc/redis/redis-6380.conf

sudo docker run --net=redis-network --ip 172.20.0.4 --restart=always --name redis-6381 -v /home/weapon/docker/redis/6381/redis-6381.conf:/etc/redis/redis-6381.conf -v /home/weapon/docker/redis/6381/data:/data -d redis redis-server /etc/redis/redis-6381.conf

sudo docker exec -it redis-6380 redis-cli -p 6380
~~~

*哨兵*
~~~

~~~

*集群*
~~~

~~~
~~~
sudo docker build -t redis-trib .

sudo docker run -i --rm --net redis-network redis-trib ruby redis-trib.rb create --replicas 1 172.20.0.2:6379 172.18.0.3:6380 172.18.0.4:6381
~~~
*redis.conf*
~~~
port 6380
cluster-enabled yes         
cluster-config-file nodes.conf     
cluster-node-timeout 5000         
cluster-announce-ip 172.20.0.2
cluster-announce-port 6380    
cluster-announce-bus-port 17000 
appendonly yes                     
protected-mode no
~~~
~~~
sudo docker run -p 6379:6379 -p 17000:17000 --restart always --name redis-6379 --net redis-network --privileged=true -v /docker/redis/6379/redis.conf:/etc/redis/redis.conf -v /docker/redis/6379/data:/data -d redis redis-server /etc/redis/redis.conf

sudo docker run -p 6380:6380 -p 17001:17001 --restart always --name redis-6380 --net redis-network --privileged=true -v /docker/redis/6380/redis.conf:/etc/redis/redis.conf -v /docker/redis6380/data:/data -d redis redis-server /etc/redis/redis.conf

sudo docker run -p 6381:6381 -p 17002:17002 --restart always --name redis-6381 --net redis-network --privileged=true -v /docker/redis/6381/redis.conf:/etc/redis/redis.conf -v /docker/redis/6381/data:/data -d redis redis-server /etc/redis/redis.conf
~~~
*ruby*
~~~
FROM ruby

MAINTAINER weapon<619981990@qq.com>

RUN gem install redis

RUN mkdir /redis

WORKDIR /redis

COPY redis-trib.rb /redis/redis-trib.rb 
~~~
