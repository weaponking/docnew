*nginx keepalived*
~~~
http 反向代理 服务器

正向代理:
客户端非常明确要访问的服务器地址；服务器只清楚请求来自哪个代理服务器，而不清楚来自哪个具体的客户端；正向代理模式屏蔽或者隐藏了真实客户端信息

反向代理:
保护和隐藏原始资源服务器，虽然访问的域名地址指向的是服务器但是真实资源不一定在服务器上，服务器可以设置反向代理,即使访问服务器A但是是从服务器B拿的资源再返回给客户端的
~~~
*install*
~~~
PCRE 作用是让 Nginx 支持 Rewrite 功能
ftp://ftp.pcre.org/pub/pcre/
./configure
make && make install
sudo apt install libpcre3-dev
pcre-config --version

http://www.zlib.net/
sudo apt-get install zlib1g.dev

./configure --prefix=/opt/nginx-server

./nginx -h

sudo ./nginx -s reload
~~~
