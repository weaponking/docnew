~~~
docker build

.dockerignore 排除当前目录或者子目录的指定文件

${variable:-word} 变量为空显示word

${variable:+word}  变量不为空显示word 为空显示空

set name=test
echo ${name:-test1}
unset name

sudo docker build -t busybox-test .

sudo docker run --name t1 --rm busybox-test cat /data/web/index.html

sudo docker run --name t1 --rm busybox-test ls /data/tmp

sudo docker run --name t1 --rm busybox-test mount

sudo docker run --name t1 --rm busybox-test /bin/httpd -f -h /data/web

sudo docker run --name t1 --rm -P busybox-test printenv
~~~

~~~
FROM busybox:latest

MAINTAINER "weapon<619981990@qq.com>"

#LABEL maintainer="weapon<619981990@qq.com>"

ENV DOC_ROOT=/data/web/ \
    TMP_WORKDIR=/data/

COPY index.html $DOC_ROOT:-/data/web/
WORKDIR $TMP_WORKDIR

COPY sentinel ./tmp/

VOLUME /home/weapon/tmp

EXPOSE 80
~~~
