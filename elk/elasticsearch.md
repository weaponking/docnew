~~~
sudo docker pull docker.elastic.co/elasticsearch/elasticsearch:7.6.2
sudo docker run -p 9200:9200 -p 9300:9300 --name es01 docker.elastic.co/elasticsearch/elasticsearch:7.6.2
~~~

*sudo vi /etc/security/limits.conf*
~~~
* -  nofile   65536
* -  noproc   65536
*  soft   core     unlimited
*  soft   hard     unlimited
weapon soft   nofile   65536
weapon hard   nofile   65536
weapon soft   nproc    4096
weapon hard   nproc    4096
weapon soft   core     unlimited
weapon soft   hard     unlimited

ulimit -SHn 65536
~~~

*sudo vi /etc/sysctl.conf*
~~~
vm.max_map_count=262144
~~~

~~~
curl http://localhost:9200
curl -X GET "localhost:9200/_cat/health?v"
curl -X GET "localhost:9200/_cat/nodes?v"
curl -X GET "localhost:9200/_cat/indices?v"
~~~

~~~
curl -X PUT "localhost:9200/test?pretty"
curl -X PUT "localhost:9200/test/_doc/1?pretty" -H 'Content-Type: application/json' -d'{"name": "test"}'
curl -X GET "localhost:9200/test/_doc/1?pretty"
curl -X DELETE "localhost:9200/test?pretty"
curl -X POST "localhost:9200/test/_doc/1/_update?pretty" -H 'Content-Type: application/json' -d'
{
  "doc": { "name": "test", "age": 40 }
}'
curl -X POST "localhost:9200/test/_doc/1/_update?pretty" -H 'Content-Type: application/json' -d'
{
  "script" : "ctx._source.age += 5"
}
'
~~~

~~~
sudo docker pull docker.elastic.co/logstash/logstash:7.6.2

path.logs: /home/weapon/docker/logstash/log/

input{
  file{
    path => "/home/weapon/tmp/logs/*.log"
  }
}
output{
  stdout{
    codec => rubydebug
  }
  elasticsearch{
    hosts => "http://172.19.0.2:9200"
    index => "testindex"
  }
}

sudo docker run -d -p 5044:5044 --name logstash --net=elk_elastic -v /home/weapon/docker/logstash/file.conf:/usr/share/logstash/config/logstash.conf docker.elastic.co/logstash/logstash:7.6.2

~~~

~~~
sudo docker pull docker.elastic.co/kibana/kibana:7.6.2
sudo docker run --link es01:elasticsearch --net=single_elastic -p 5601:5601 --name kibana docker.elastic.co/kibana/kibana:7.6.2
~~~
