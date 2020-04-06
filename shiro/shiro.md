1.java.io.IOException: Server returned HTTP response code: 403 for URL: http://www.terracotta.org/kit/reflector?pageID=update.properties&kitID=ehcache.default&id=2130706689&os-name=Linux&jvm-name=Java+HotSpot%28TM%29+64-Bit+Server+VM&jvm-version=1.8.0_221&platform=amd64&tc-version=2.6.11&tc-product=Ehcache+Core+2.6.11&source=Ehcache+Core&uptime-secs=1&patch=UNKNOWN
<ehcache name="es" updateCheck="false">

2.去掉URL中的JSESSIONID
<bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
<property name="sessionIdUrlRewritingEnabled" value="false" />
</bean>

3.org.apache.shiro.session.UnknownSessionException: There is no session with id
