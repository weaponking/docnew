*redis :*
~~~
sudo docker run --name redis-test -p 6380:6379  -d redis --requirepass "123456"

sudo docker start -i redis-test

sudo docker exec -it redis-test redis-cli

cat /home/weapon/docdemo/redis/redis-spring/src/test/java/resources/lock.lua | sudo docker exec -i redis-test redis-cli -a 123456 script load --pipe

the input device is not a TTY (-it 去掉t参数)

script flush

KEYS[1] 键值参数占位

ARGV[1] 参数占位
~~~

*script load*
~~~
local lock = redis.call("setnx",KEYS[1],ARGV[1]);
if(lock > 0)
then
	redis.call("expire",KEYS[1],tonumber(ARGV[2]));
	return redis.call("get",KEYS[1]);
else
	return "-1";
end

local lockKey = redis.call("get",KEYS[1]);
local lockVal = ARGV[1];
if(lockKey == lockVal) then
	redis.call("del",KEYS[1]);
	return true;
else
	return false;
end
~~~

*redis config*
~~~
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
~~~

*test*
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

private static final String TIME_OUT = "30";

@Test
public void test1() {
    String result = redisTemplate.execute(lockLuaScript, Arrays.asList(LOCK_KEY),LOCK_VALUE, TIME_OUT);
    Assert.assertEquals(result, LOCK_VALUE);
}

@Test
public void test2() {
    boolean result = redisTemplate.execute(unlockLuaScript, Arrays.asList(LOCK_KEY), LOCK_VALUE);
    Assert.assertEquals(result, Boolean.TRUE);
}
~~~
