*扩展不改变原有功能*
~~~
public interface ISource {

    String test();
}
~~~
~~~
@Slf4j
public class SourceImpl implements ISource {

    @Override
    public String test() {
        log.info("sourceimpl test");
        return "test";
    }
}
~~~
~~~
public class ImplProxy {

    public String test() {
        return "ImplProxy test";
    }
}
~~~
*JDK动态代理*
~~~
@Slf4j
public class SourceHandler<ISource> implements InvocationHandler {

    private ISource source;

    public SourceHandler(ISource source) {
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoke before");
        Object ret = method.invoke(source, args);
        log.info("invoke after");
        return ret;
    }

}
~~~
*CGLIB*
~~~
@Slf4j
public class SourceInterceptor implements MethodInterceptor {

    private ISource source;

    public SourceInterceptor(ISource source) {
        this.source = source;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("intercept before");
        Object ret = method.invoke(source, objects);
        log.info("intercept after");
        return ret;
    }
}
~~~
*静态代理*
~~~
@Slf4j
public class StaticProxy {

    private ISource source;

    public StaticProxy(ISource source) {
        this.source = source;
    }

    public String test() {
        log.info("test before");
        String ret = source.test();
        log.info("test after");
        return ret;
    }
}
~~~
*Test*
~~~
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Proxy;
@Slf4j
public class ProxyTestCase {

    private ISource source = null;
    private StaticProxy staticProxy;
    private SourceHandler sourceProxy;
    private SourceInterceptor sourceInterceptor;

    @Before
    public void before() {
        source = new SourceImpl();
        staticProxy = new StaticProxy(source);
        sourceProxy = new SourceHandler(source);
        sourceInterceptor = new SourceInterceptor(source);
    }

    @Test
    public void test() {
        String ret = staticProxy.test();
        log.info(ret);
    }

    @Test
    public void test1() {
        ISource hanlderSource = (ISource) Proxy.newProxyInstance(sourceProxy.getClass().getClassLoader(),
            source.getClass().getInterfaces(), sourceProxy);
        String ret = hanlderSource.test();
        log.info(ret);
    }

    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(source.getClass());
        enhancer.setCallback(sourceInterceptor);
        ISource InterceptSource = (ISource) enhancer.create();
        String ret = InterceptSource.test();
        log.info(ret);
    }

    //不需要代理类继承接口
    @Test
    public void test3() {
        ImplProxy implProxy = new ImplProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ImplProxy.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                log.info("ImplProxy before");
                Object ret = method.invoke(implProxy, objects);
                log.info("ImplProxy after");
                return ret;
            }
        });

        ImplProxy proxy = (ImplProxy) enhancer.create();
        log.info(proxy.test());
    }
}
~~~
