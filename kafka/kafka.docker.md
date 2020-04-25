~~~
sudo docker exec -it zookeeper /bin/sh
sudo docker exec -it kafka /bin/sh

./kafka-topics.sh --list --zookeeper localhost:2181

sudo docker stop zookeeper kafka
sudo docker container rm zookeeper kafka


sudo docker stop zookeeper kafka
sudo docker rm zookeeper kafka
~~~

~~~
./kafka-topics.sh --create --zookeeper 172.19.0.2:2181 --replication-factor 2 --partitions 3 --topic test2

./kafka-console-producer.sh --broker-list 172.19.0.5:9092 --topic test2

./kafka-console-consumer.sh --bootstrap-server 172.19.0.6:9093 --topic test2 --from-beginning

./kafka-topics.sh --list --zookeeper 172.19.0.2:2181
~~~

~~~
cd /opt/kafka_2.12-2.4.1/bin
sudo docker stop zookeeper zookeeper1 zookeeper2 kafka kafka1
sudo docker rm zookeeper zookeeper1 zookeeper2 kafka kafka1

sudo docker network create --subnet=172.19.0.0/24 kafka-zoo-net
~~~
