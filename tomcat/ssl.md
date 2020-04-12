单向
cd jdk/bin keytool -genkeypair -alias "tomcat" -keyalg "RSA" -keystore "ssl.keystore" 36500(100年默认90天)

cd tomcat mkdir ssl copy ssl.keystore

<Connector port="8087" protocol="HTTP/1.1" URIEncoding="UTF-8"
      connectionTimeout="20000"
      redirectPort="8443" />

<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol" maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
    clientAuth="false" sslProtocol="TLS"
   	keystoreFile="/home/weapon/develop/apache-tomcat-ssl/ssl/ssl.keystore"
    keystorePass="weapon">
</Connector>

强制ssl访问,tomcat/conf/web.xml添加
<login-config>  
    <auth-method>CLIENT-CERT</auth-method>  
    <realm-name>Client Cert Users-only Area</realm-name>  
</login-config>  
<security-constraint>  
    <web-resource-collection >  
        <web-resource-name >SSL</web-resource-name>  
        <url-pattern>/*</url-pattern>  
    </web-resource-collection>  
    <user-data-constraint>  
        <transport-guarantee>CONFIDENTIAL</transport-guarantee>  
    </user-data-constraint>  
</security-constraint> 

双向
keytool -genkeypair -alias "clientkey" -keyalg "RSA" -storetype "PKCS12" -keystore "client.key.p12" 36500