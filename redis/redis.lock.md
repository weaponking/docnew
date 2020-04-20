~~~
<properties>
  <spring-redis.version>2.1.16.RELEASE</spring-redis.version>
  <jedis.version>2.9.3</jedis.version>
</properties>

<dependencies>
  <dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-redis</artifactId>
    <version>${spring-redis.version}</version>
  </dependency>
  <dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>${jedis.version}</version>
  </dependency>
</dependencies>
~~~
~~~
@Configuration
@PropertySource(value = {"classpath:redis-config.properties"})
public class RedisConfig {

    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.maxIdle}")
    private Integer maxIdle;
    @Value("${redis.maxWaitMillis}")
    private Long maxWaitMillis;
    @Value("${redis.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.timeout}")
    private Integer timeout;
    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setTimeout(timeout);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisScript<String> lockLuaScript() {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("lua/lock.lua"));
        redisScript.setResultType(String.class);
        return redisScript;
    }

    @Bean
    public RedisScript<Boolean> unlockLuaScript() {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("lua/unlock.lua"));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
}
~~~
~~~
@Autowired
RedisTemplate<String, String> redisTemplate;

@Autowired
@Qualifier("lockLuaScript")
private RedisScript<String> lockLuaScript;

@Autowired
@Qualifier("unlockLuaScript")
private RedisScript<Boolean> unlockLuaScript;

private static final String LOCK_KEY = "LOCK_KEY";

private static final String LOCK_VALUE = "LOCK_VALUE";

private static final String NEW_LOCK_VALUE = "NEW_LOCK_VALUE";

private static final String TIME_OUT = "30";

@Test
public void test1() {
    String result = redisTemplate.execute(lockLuaScript, Arrays.asList(LOCK_KEY), LOCK_VALUE, TIME_OUT);
    Assert.assertEquals(LOCK_VALUE, result);
    boolean result1 = redisTemplate.execute(unlockLuaScript, Arrays.asList(LOCK_KEY), LOCK_VALUE);
    Assert.assertEquals(Boolean.TRUE, result1);
    result = redisTemplate.execute(lockLuaScript, Arrays.asList(LOCK_KEY), NEW_LOCK_VALUE, TIME_OUT);
    Assert.assertEquals(NEW_LOCK_VALUE, result);
}
~~~
