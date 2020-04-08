## <aop:aspectj-autoproxy proxy-target-class="true"/>

~~~
默认为false使用JDK动态代理,如果目标类没有声明接口，则Spring将自动使用CGLIB动态代理
implements InvocationHandler

proxy-target-class="true"使用CGLIB动态代理
implements MethodInterceptor
~~~
