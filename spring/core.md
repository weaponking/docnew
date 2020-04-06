~~~
BeanFactory/ApplicationContext
~~~

*事务*

隔离级别|Desc
-|-
REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED|支持当前事务，如果没有事务会创建一个新的事务
SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS)|支持当前事务，如果没有事务的话以非事务方式执行
MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY)|支持当前事务，如果没有事务抛出异常
REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW)|创建一个新的事务并挂起当前事务
NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED)|以非事务方式执行，如果当前存在事务则将当前事务挂起
NEVER(TransactionDefinition.PROPAGATION_NEVER)|以非事务方式进行，如果存在事务则抛出异常
NESTED(TransactionDefinition.PROPAGATION_NESTED)|如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作
