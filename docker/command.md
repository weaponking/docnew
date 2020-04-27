~~~
//所有镜像
sudo docker images
sudo docker image ls -a

//镜像明细
sudo docker inspect busybox

//历史记录
sudo docker history busybox

//删除镜像
sudo docker rmi busybox
sudo docker image rm busybox
sudo docker image prune

//所有容器
sudo docker ps -a

//删除容器
sudo docker container rm id

//创建容器
sudo docker create --name busybox -i busybox

//启动
sudo docker start  busybox

//进入容器
sudo docker exec -it busybox /bin/sh

//查看
sudo docker top busybox
sudo docker container inspect busybox
sudo docker container port busybox

//数据卷
/var/lib/docker/volumes

sudo docker-compose up

sudo docker port container


~~~
