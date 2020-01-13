
*Stream API*
```
Stream不存储元素
Stream不改变源对象,返回一个持有结果的新Stream
Stream操作是延迟执行的,等到需要结果的时候才执行
Stream只能使用一次 再次使用需要再次打开
```

```
.distinct() 去重
```

```
.skip(2) 跳过前2条
.limit(5) 只返回前5条
```

```
.map() 一对一映射 对一级元素操作
.flatmap() 对二级元素操作

@Test
public void mapAndFlatMap() {
    List<String> list = Arrays.asList("1 2","3 4", "5 6", "7 8");
    list.stream().map(item -> {
       return Arrays.stream(item.split(" "));
    }).forEach(System.out::println);
    System.out.println("-------------");
    list.stream().map(item ->
        Arrays.stream(item.split(" "))).forEach(subItem -> {
            subItem.forEach(System.out::println);
    });
    System.out.println("-------------");
    list.stream().flatMap(item ->
        Arrays.stream(item.split(" ")))
            .forEach(System.out::println);
}

```

```
.sorted() 排序
@Test
public void sort() {
    List<TestVo> list = generator(5);
    list.stream().map(TestVo::getId).sorted().forEach(System.out::println);
    System.out.println("-------------");
    //降序
    list.stream().sorted((one, other) -> {
        return other.getId().compareTo(one.getId());
    }).forEach(System.out::println);
}
```

```
@Test
public void match() {
   List<TestVo> list = generator(6);
   //全部匹配
   boolean b1 = list.stream().allMatch(item -> item.getId()>2);
   Assert.assertEquals(b1, false);
   b1 = list.stream().limit(4).allMatch(item -> item.getId()>2);
   Assert.assertEquals(b1, true);
   //至少一个
   b1 = list.stream().anyMatch(item -> item.getId()>5);
   Assert.assertEquals(b1, true);
   //没有ID>5的项
   b1 = list.stream().skip(1).noneMatch(item -> item.getId()>5);
   Assert.assertEquals(b1, true);
}
```
