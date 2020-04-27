~~~
//下载
http://httpd.apache.org/download.cgi

1. cd httpd-2.4.43

2. ./configure --prefix=/opt/apache
//APR Apache Portable Runtime
configure: error: APR not found

3. 下载 安装apr
http://archive.apache.org/dist/apr/
./configure --prefix=/opt/apache/apr --with-apr=/opt/apache/apr
~~~
