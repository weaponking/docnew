## <aop:aspectj-autoproxy proxy-target-class="true"/>

~~~
默认为false使用JDK动态代理,如果目标类没有声明接口，则Spring将自动使用CGLIB动态代理
implements InvocationHandler

proxy-target-class="true"使用CGLIB动态代理
implements MethodInterceptor
~~~

~~~
<bean id="settersAndAbsquatulatePointcut"
        class="org.springframework.aop.support.JdkRegexpMethodPointcut">
    <property name="patterns">
        <list>
            <value>.*set.*</value>
            <value>.*absquatulate</value>
        </list>
    </property>
</bean>

org.springframework.aop.framework.ProxyFactoryBean

org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator

<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>


~~~
