propagation 事物的传播性一共有7种，常用两种
REQUIRED
SUPPORTS
MANDATORY
REQUIRES_NEW
NOT_SUPPORTED
NEVER
NESTED

@Transactional(propagation=Propagation.REQUIRED)        //代表当前方法执行如果有现成的事物就用现成的，不会开启新事物，是默认的
@Transactional(propagation=Propagation.REQUIRES_NEW)    //代表当前方法执行开启新事物,所以该事物的失败对上级事物不会构成影响

isolation  事物的隔离性
DEFAULT
READ_UNCOMMITTED  读取未提交
READ_COMMITTED    读取已提交 常用
REPEATABLE_READ
SERIALIZABLE
@Transactional(isolation=Isolation.READ_COMMITTED)

readOnly 只读属性，一般find操作可以设置为readOnly=true可以加快数据库操作
@Transactional(readOnly=true)

timeout 超时回滚，单位秒
@Transactional(timeout=1)  //1秒后事物未完成就回滚

接收到异常回不回滚
@Transactional(
	rollbackFor={UserAccountException.class},
	rollbackForClassName={"xxx.xxx.xxException"},
	noRollbackFor={UserAccountException.class},
	noRollbackForClassName={"xxx.xxx.xxException"})