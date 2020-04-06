```
CREATE TABLE master
(
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  code varchar(64),
  name varchar(64),
  create_time datetime
);

CREATE TABLE property
(
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  master_code varchar(64),
  property varchar(64),
  value varchar(64),
  create_time datetime
);

CREATE TABLE detail
(
 id bigint(20) PRIMARY KEY AUTO_INCREMENT,
 master_code varchar(64),
 info varchar(64),
 quantity int
);

```

```
INSERT INTO master(code, create_time)
VALUES("master", now());

INSERT INTO master(code, create_time)
SELECT code, now() FROM master;

INSERT INTO detail (master_code, info, quantity)
SELECT code, CONCAT("info",id), RAND()*200000 FROM master where id%2 = 0;

INSERT INTO detail (master_code, info, quantity)
SELECT code, CONCAT("info",id), RAND()*5000 FROM master where id%2 = 1;

INSERT INTO property (master_code, property, value)
SELECT code, "type", "private" FROM master where id%2 = 1;

INSERT INTO property (master_code, property, value)
SELECT code, "bussine", "cloud" FROM master where id%9 = 0;

INSERT INTO property (master_code, property, value)
SELECT code, "bussine", "factory" FROM master where id%7 = 0;

INSERT INTO property (master_code, property, value)
SELECT code, "region", "china" FROM master where id%3 = 0;

INSERT INTO property (master_code, property, value)
SELECT code, "supply", "true" FROM master where id%4 = 0;

update property set create_time = now()

```

```
SELECT m.* FROM master m
INNER JOIN (SELECT master_code FROM property WHERE property = 'type' AND value = 'private') p ON p.master_code = m.code
INNER JOIN (SELECT master_code FROM property WHERE property = 'bussine' AND value = 'cloud') p1 ON p1.master_code = m.code
INNER JOIN (SELECT master_code FROM property WHERE property = 'region' AND value = 'china') p2 ON p2.master_code = m.code
INNER JOIN (SELECT master_code FROM detail WHERE info = 'info4527') d ON d.master_code = m.code
INNER JOIN (SELECT master_code FROM detail WHERE quantity > 200) d1 ON d1.master_code = m.code


SELECT m.* FROM master m
WHERE EXISTS
(
SELECT 1 FROM property WHERE property = 'type' AND value = 'private' AND master_code = m.code
)
AND EXISTS
(
SELECT 1 FROM property WHERE property = 'bussine' AND value = 'cloud' AND master_code = m.code
)
AND EXISTS
(
SELECT 1 FROM detail WHERE info = 'info4527' AND master_code = m.code
)
AND EXISTS
(
SELECT 1 FROM detail WHERE quantity > 200 AND master_code = m.code
)

```

```
EXISTS在命中率高的情况下查询速度较快 主表数据量较大，而条件表的数据量较少时性能差

INNER JOIN相对稳定 不会随命中率的变化而影响性能

```
