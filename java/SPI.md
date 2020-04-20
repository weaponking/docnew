## SPI(Service Provider Interface)
```mermaid
graph LR
  SPI-->classpath下/创建META-INF/services文件夹;
  SPI-->常见文件接口全名package.spiservice;
  SPI-->ServiceLoader.load/spiservice
```
## 用例
~~~
//spi接口
@FunctionalInterface
public interface SpiService {
    String testSpi();
}

//spi实现类
public class SpiServiceBImpl implements SpiService {
    @Override
    public String testSpi() {
        String result = "SpiServiceBImpl testSpi";
        System.out.println(result);
        return result;
    }
}

//测试
@Slf4j
public class TestCase {
    @Test
    public void test() {
        ServiceLoader<SpiService> spiLoader = ServiceLoader.load(SpiService.class);
        for(SpiService spiService : spiLoader) {
            spiService.testSpi();
        }
    }
}
~~~
