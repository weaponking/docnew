#elasticsearch(分布式搜索引擎 负责数据的存储检索分析 RESTFUL):
./bin/elasticsearch
curl http://localhost:9200/
查询所有index
curl -X GET 'http://localhost:9200/_cat/indices?v'
查询index type
all: curl 'localhost:9200/_mapping?pretty=true'
single: curl 'localhost:9200/error-2018.07.17/_mapping?pretty=true'
查询记录
curl 'localhost:9200/error-2018.07.17/doc/_search'
(total：返回记录数 max_score：最高的匹配程度 hits：返回的记录组成的数组)

curl 'localhost:9200/error-2018.07.17/doc/_search' -d '
{
  "query" : { "match" : { "message" : "1" }}
}'
{"error":"Content-Type header [application/x-www-form-urlencoded] is not supported"

curl -H "Content-Type: application/json" 'localhost:9200/error-2018.07.17/doc/_search' -d '
{
  "query" : { "match" : { "message" : "9" }}
}'
#logstash(数据收集处理):
./bin/logstash -e 'input { stdin { } } output { stdout {} }'

vi logstash-simple.conf
#日志输入输出
input {
	file {
  		path => ["/home/weapon/ELK/testlog/test.log"]
  		type => "test"
  	}
}
output {
  	#stdout {
  		#codec => rubydebug
  		#codec => json
  	#}
  	elasticsearch {
        hosts => ["localhost:9200"]
        index => "error-%{+YYYY.MM.dd}"
    }

}
./logstash -f logstash-simple.conf

#kibana(可视化界面):
config/kibana.yml
Set elasticsearch.url to point at your Elasticsearch instance
./bin/kibana
http://localhost:5601

error:
max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
su root
/etc/security/limits.conf
*               soft    nofile          65536
*               hard    nofile          65536
*               soft    nproc           4096
*               hard    nproc           4096

max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
/etc/sysctl.conf
vm.max_map_count=262144
