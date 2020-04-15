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

*redis.conf*
~~~
port 6380
cluster-enabled yes         
cluster-config-file nodes.conf     
cluster-node-timeout 5000         
cluster-announce-ip 172.20.0.1    
cluster-announce-port 6380    
cluster-announce-bus-port 17000 
appendonly yes                     
protected-mode no
~~~

~~~
sudo docker run -p 6379:6379 -p 17000:17000 --restart always --name redis-6379 --net redis-network --privileged=true -v /docker/redis/6379/redis.conf:/etc/redis/redis.conf -v /docker/redis/6379/data:/data -d redis redis-server /etc/redis/redis.conf

sudo docker run -p 6380:6380 -p 17001:17001 --restart always --name redis-6380 --net redis-network --privileged=true -v /docker/redis/6380/redis.conf:/etc/redis/redis.conf -v /docker/redis/6380/data:/data -d redis redis-server /etc/redis/redis.conf

sudo docker run -p 6381:6381 -p 17002:17002 --restart always --name redis-6381 --net redis-network --privileged=true -v /docker/redis/6381/redis.conf:/etc/redis/redis.conf -v /docker/redis/6381/data:/data -d redis redis-server /etc/redis/redis.conf

~~~
