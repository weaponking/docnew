sudo service cassandra start
sudo service cassandra stop

nodetool status

The default location of log and data directories is
  /var/log/cassandra/ and /var/lib/cassandra.

Start-up options (heap size, etc) can be configured in
  /etc/default/cassandra.

pkill -f Cassandra

/etc/cassandra
配置文件:cassandra.yaml

cluster_name: the name of your cluster.
seeds: a comma separated list of the IP addresses of your cluster seeds.
storage_port: you don’t necessarily need to change this but make sure that there are no firewalls blocking this port.
listen_address: the IP address of your node, this is what allows other nodes to communicate with this node so it is important that you change it. Alternatively, you can set listen_interface to tell Cassandra which interface to use, and consecutively which address to use. Set only one, not both.
native_transport_port: as for storage_port, make sure this port is not blocked by firewalls as clients will communicate with Cassandra on this port.

data_file_directories: one or more directories where data files are located.
commitlog_directory: the directory where commitlog files are located.
saved_caches_directory: the directory where saved caches are located.
hints_directory: the directory where hints are located.

cqlsh localhost
SELECT cluster_name, listen_address FROM system.local;

//查询所有表
describe tables;

drop table ;

/**
 *OperationTimedOut: errors={'127.0.0.1': 'Client request timeout. See Session.execute[_async](timeout)'}, last_host=127.0.0.1
 *
 *  cqlsh.py  DEFAULT_REQUEST_TIMEOUT_SECONDS
 *
 * Cassandra timeout during SIMPLE write query at consistency LOCAL_ONE (1 replica were required but only 0 acknowledged the write)
 */

message="ORDER BY is only supported when the partition key is restricted by an EQ or an
ReadTimeout: Error from server: code=1200


-Xms2G
-Xmx2G
-Xmn800M

Unexpected error deserializing mutation; saved to /tmp/mutation5107493228466419113dat.  This may be caused by replaying a mutation against a table with the same name but incompatible schema.  Exception follows: org.apache.cassandra.serializers.MarshalException: A tombstone should not have a value
qingkong commit log
