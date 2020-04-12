~~~
sudo apt-get update

sudo dpkg --configure -a

sudo apt --fix-broken install

sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io
~~~

*command :*
~~~
//查看所有container
  sudo docker container ls -a  

//删除container
  sudo docker container rm a645b716c1e9

  sudo docker container ls

  sudo docker image ls

~~~

*zookeeper kafka :*
~~~
sudo docker pull wurstmeister/kafka
sudo docker pull zookeeper

//创建网络  查看明细
sudo docker network create zk_kfk
sudo docker network inspect zk_kfk

sudo docker run --net=zk_kfk --name zookeeper -p 21810:2181 -d zookeeper

sudo docker exec -it zookeeper /bin/sh

sudo docker stop zookeeper


~~~
