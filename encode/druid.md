~~~
java -cp druid-1.1.18.jar com.alibaba.druid.filter.config.ConfigTools 123456

<property name="filters" value="${filters}" /> 	
<property name="connectionProperties" value="druid.stat.slowSqlMillis=5000;config.decrypt=true;config.decrypt.key=${publicKey}"/>

password:
publicKey:
filters:config
~~~

~~~
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
    username: root
    password: eQP+XsSwZCQKkZFo30LAGsH4i3ptl24FhiKmn6azc/oOcifiWwm2MclrmTPXgTQd99zAfi4THMK5Ls7dyIoXqg==
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    initialization-mode: always
    continue-on-error: true
    druid:
      connection-properties: config.decrypt=true;config.decrypt.key=${public-key}
      filter:
        config:
          enabled: true

public-key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIjfPA/Jd+DV32ftd5i9Px2UdwXHdNPblR8Sd00L/u2fF30oxzdCjk4sKBZMq1GpHWkv3IGeFyyNFxvIsXWSwDkCAwEAAQ==
~~~
