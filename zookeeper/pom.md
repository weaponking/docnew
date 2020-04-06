~~~
<zookeeper.version>3.4.13</zookeeper.version>
<curator.version>4.1.0</curator.version>
<zkclient.version>0.10</zkclient.version>

<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>${zookeeper.version}</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
        <exclusion>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>${zkclient.version}</version>
</dependency>

<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>${curator.version}</version>
</dependency>

<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>${curator.version}</version>
</dependency>
~~~

~~~
@Slf4j
public class TestCase {

    public static void main(String[] args) {
        String zkServers = "localhost:21810";
        String nodePath = "/zk_test";

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkServers)  // zk server ip
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                // addauth digest admin:admin
                .authorization("digest","admin:admin".getBytes())
                //zk 根节点 /testspace
                .namespace("")
                .build();
        client.start();

        log.info("zk client state : {}", client.getState().toString());

        Stat statExist = null;
        try {
            statExist = client.checkExists().forPath(nodePath);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            client.setData()
                    //.withVersion(0)
                    .forPath("/zk_test", "tdata1".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            byte [] data = client.getData().forPath("/zk_test");
            log.info("data:{}", new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            client.delete()
//                    .guaranteed()  // 删除失败 继续删除 直到成功
//                    .deletingChildrenIfNeeded()
//                    //.withVersion(0)
//                    .forPath(nodePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        client.close();
    }
}
~~~
