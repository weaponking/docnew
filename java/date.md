~~~
SimpleDateFormat 所有的格式化和解析都需要通过一个中间对象Calendar进行转换

Instant——它代表的是时间戳
LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
LocalTime——它代表的是不含日期的时间
LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。


public static String formatTime(LocalDateTime time, String pattern) {
    return time.format(DateTimeFormatter.ofPattern(pattern));
}

public static void main(String[] args) {

    System.out.println(formatTime(LocalDateTime.now(), "yyyy-MM-dd"));

    Date date = new Date();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    System.out.println(formatTime(localDateTime, "yyyy-MM-dd"));

    LocalDateTime localDateTime1 = LocalDateTime.of(2016, 2, 1, 12, 10);
    System.out.println(formatTime(localDateTime1, "yyyy-MM-dd"));
}
~~~
