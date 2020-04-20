~~~
sudo docker exec -it zookeeper /bin/sh
sudo docker exec -it kafka /bin/sh

./kafka-topics.sh --list --zookeeper localhost:2181

sudo docker stop zookeeper kafka
sudo docker container rm zookeeper kafka


sudo docker stop zookeeper kafka
sudo docker rm zookeeper kafka
~~~
