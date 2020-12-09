springboot-test1： 测试spring4的泛型注入
	封装BaseRepository、BaseService两个模板父类，把通用的crud等代码放在其中，然后子类继承后，只需要添加额外的实现。
	如:
		@Repository class UserRepository extends BaseRepository<User> {...}
		@Service class UserService extends BaseService<User> {...}
		controller层调用： @Autowired private UserService userService;


springboot-test2： 测试SpringDataJpa使用
	主要为复杂查询方法的使用，详情参考“jpa查询方法.txt”文档
	1、Specification查询，返回实体、列表、分页
		添加exists子查询测试，详见CustomerTest类（注意一对多映射写法）
	2、entityManager的原生sql查询，返回实体、列表、分页
	3、@Version乐观锁使用



springboot-test3： 测试统一数据格式返回
	--测试详情参考《测试用例.postman_collection.json》
	1.http日志输出
		通过AOP切入：@Aspect @Pointcut @Before @After @AfterReturning @Around
	2.统一数据格式返回，格式：{code: xxx, msg: xxx, data: []|{} }
	3.统一异常处理
		--封装一个业务异常类MyException，使用ResultEnum存放各种异常信息，并使用@ExceptionHandler注解处理捕获的异常。
		1、自定义的异常 new MyException(...)
		2、访问链接不存在异常（需要在application.properties配置）。
			格式：{"code":404,"msg":"找不到系统资源","data":null}
		3、系统异常 格式：{"code":-1,"msg":"未知错误","data":null}
		4、测试校验异常(MethodArgumentNotValidException)（参考PersonController类）
	4.测试多个实体返回（详情参考UserRoleController类）
		描述1：统一数据格式里面只能返回一个泛型T的对象，但是我们有时候需要返回两个以上的对象（如有时候会把User对象和Rle对象一起返回）就无能为力了，因此需要处理能返回多个对象的情况。
		描述2：有时候我们返回User对象，但是该对象中的password字段不能给，因此需要将User对象转换成vo对象，然后将vo对象返回给前端。
	5.entity/list裁剪(详情参考Test4Controller类)
		描述：查询DB得到的entity或list是数据库表的所有字段，如果直接将所有字段全部返回给前端，会增加传输压力，而且有些敏感字段也不允许给到前端，因此需要裁剪部分字段。
			entity裁剪：User -> UserVo
			list裁剪： List<User> -> List<UserVo>
	6.测试DB更新实体(详情参考Test5Controller类)
		描述：前端传部分字段到后端，然后后端通过查询数据库最新记录，再通过反射得到数据库记录与前台实体结合后的实体，jpa直接保存该实体即可
		--我们数据库的字段可能很多，但是前端页面能修改的字段可能就几个，jpa的保存机制直接保存整个实体，因此需要保存的应是将前端实体和后端实体结合后得到的实体。如：用户表有用户名、密码、出生日期，但前端仅允许修改用户名、出生日期，因此在保存时将前端传过来的用户名、出生日期，与数据库得到的密码结合，得到新的实体，再保存该新实体。
		作用：
			1、保证恶意修改其它不必要的字段。保证前端仅能修改的是用户名和出生日期，密码从前端传入无效。
			2、部分字段为公共字段，如id、versionNumber、createUser、createTime、lastUpdateUser、lastUpdateTime等，公共字段会抽象成一个类，具体数据库实体继承这些类，从而添加了这些公共字段。在set属性时需要对这一部分字段的赋值。
			--附：若仅更新几个字段，可以使用jdbcTemplate进行更新，但这样不能保证jpa的乐观锁是否一致。
	7.测试配置文件内容注入(详情参考Test6Controller类)
		方式1：Environment形式
		方式2：@Value形式
		方式3：@ConfigurationProperties形式
	8.actuator测试
		查看项目状态：http://localhost:8080/actuator/health
		查看所有用户：http://localhost:8080/actuator/user

	--aop版log日志
	作用：用于记录当前请求的开始时间、结束时间、token、请求参数、返回参数、异常信息等数据。
	实现：统一对Controller层进行增强，针对某些在filter层抛异常返回的可以直接调用一个controller方法也能够进行aop代理，如SpringSecurity的filter可能会不经过controller层。
	日志记录：startTime,endTime,url,token,username
	日志详细记录：requestObj（由于所有controller方法参数都只有一个，json格式）,responseObj,exceptionStack


springboot-test4： 测试SpringDataJpa 一对多、多对多关联映射
	一对多映射：
		Customer： //被维护方
			@OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
			private List<Order> orders = new ArrayList<Order>();
		Order： //维护方
			@ManyToOne
			@JoinColumn(name="customer_id")
			private Customer customer;
	多对多映射：
		User： //维护方
			@ManyToMany(cascade = CascadeType.ALL)
			@JoinTable(name = "t_user_role", 
				joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
			private List<Role> roles = new ArrayList<Role>();
		Role： //被维护方
			@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="roles")
			private List<User> users = new ArrayList<User>();
		多对多映射操作说明：
			若添加维护方记录时，会把被维护方记录及关联表记录一并添加；若添加被维护方记录时，能添加本身记录及维护方记录，不产生关联表记录。
			若删除维护方记录时，会把被维护方记录及关联表记录一并删除；若删除被维护方记录时，仅删除本身记录。


springboot-test5： 测试SpringDataJpa 自定义Repository
	前言：
		项目中删除可能仅是逻辑删除（不删除数据库记录），因此需要重写delete()方法，一般都会自己实现RepositoryFactoryBean和Repository
		项目中的实体表可能存在公共属性，如主键id、版本号、创建时间、创建人、最后更新时间、最后更新人等。
	
	自定Repository步骤：
		1、创建存放公共属性的父类实体
		2、创建自定义Repository接口(BaseDao)，继承JpaRepository和JpaSpecificationExecutor接口
		3、创建BaseDao接口实现类(BaseDaoImpl)，继承SimpleJpaRepository类
		4、创建自定义BaseDaoFactoryBean类，继承JpaRepositoryFactoryBean类，用来代替默认的RepositoryFactoryBean
		5、在SpringBoot启动类注入BaseDaoFactoryBean类




simple-shiro-test：
	shiro简单认证和授权
	realm的用法：SimpleAccountRealm、IniRealm、JdbcRealm、自定义Realm
	详情参考“shiro笔记.txt”文档

springboot-test6： 测试shiro整合
	对应实体：用户表User、角色表Role、权限表Permission、菜单表Module。user与role关联，role与permission关联，role与module关联，都是多对多关系。
	测试账号(在static/db_shiro.sql导入)：admin/admin user1/user1 user2/user 分别对这三个账号进行登录，能查看到对应的权限
	--整合EhCache

springboot-test7： 测试shiro整合
	--项目来源于springboot-test6，新整合了密码加密(CredentialsMatcher)、记住我(RememberMeManager)、自定义filter
	配置admin账号拥有所有角色及权限。
	--后续整合部分：1、会话管理器SessionManager，用于处理集群的session共享问题（使用redis） 2、缓存管理器CacheManager（使用redis）
		重写SessionManager时，需要注意retrieveSession方法会执行多少次。



springboot-test8： 测试redis整合
	1、测试使用redisTemplate。详情查看UserTest和ListUserTest两个类
	2、使用springCache缓存注解，注入具体缓存方案是redis。测试详情查看UserController类。目前设置有效时间为10秒，可设置从配置文件写入

springboot-test9: 测试springCache缓存注解
	测试springCache的@Cacheable、@CachePut、@CacheEvict注解
	Spring的cache注解说明：
		--详情查看org.springframework.cache.annotation包。
		@EnableCaching	--开启缓存（在启动类或配置类添加）。这样才能使用@Cacheable、@CachePut、@CacheEvict等注解
		@Cacheable 	--缓存查询。先从缓存中查询有无数据，有则直接返回，无则执行方法并将返回值存入缓存
		@CachePut	--缓存添加。先执行方法，后将返回值存入缓存
		@CacheEvict	--缓存清除。先执行方法，后通过key将缓存里的数据删除
		@Caching	--构建复杂缓存。



springboot-test10： 测试传多个参数情况
	--前言：@RequestBody只能是一个参数，且不适应user.name这样的传值
	自定义HandlerMethodArgumentResolver，使得从页面传进来的user.id, user.name这些字段自动赋值到user对象中
		
	使用：addUser(@FormBean("user") User user, @FormBean("role") Role role)
	输入：http://localhost:8080user?user.id=111&user.name=AAA&user.type=type1&role.id=222&role.name=BBB
	输出：User [id=111, name=AAA, type=type1]  Role [id=222, name=BBB]


springboot-test11： 测试jpa添加基础字段
	数据库基础字段：
		主键Id	--IdEntity类
		乐观锁	--VersionEntity类
		创建时间/创建用户/最后更新时间/最后更新用户	--AuditEntity类
			@CreateDate、@CreateBy、@LastModifiedDate、@LastModifiedBy四个注解
			创建用户字段自动赋值需要实现AuditorAware接口
			@EntityListeners(AuditingListener.class) 和 @EnableJpaAuditing 注解
			问题：怎么添加预留字段自动赋值，比如创建机构/最后更新机构等字段？
		附：这些创建机构/最后更新机构预留字段可用其它方式实现
			@PrePersist/@PreRemove/@PostPersist/@PostRemove/@PreUpdate/@PostUpdate/@PostLoad 注解
	ApplicationUtils类：获取ApplicationContext和bean方法

springboot-test11-v2： 测试jpa添加基础字段(v2版本)
	描述：
		除了添加创建时间/创建用户/最后更新时间/最后更新用户这些基础字段外，可能还存在其它基础字段，如创建机构/最后更新机构等，在save时同样需要自动赋值。
	解决：
		使用@PrePersist/@PreUpdate等注解


springboot-test12-part1 && springboot-test12-part2 测试RestTemplate的使用
	RestTemplate是Spring提供的用于访问Rest服务的客户端，RestTemplate提供了多种便捷访问远程http服务的方法，能够大大提高客户端的编写效率。
	有时候项目中要建立多个微服务，或者把一个大的项目拆分成多个微服务进行解耦，为了方便完成各个微服务之间进行互相调用，Spring封装了http请求模板类RestTemplate
	示例：
		普通http测试（数字占位符传参）
		普通http测试（map传参）
		Object类型传参，针对请求参数较少
		Map类型传参，针对请求参数很多
		Map类型传参，针对请求参数很多
		Post请求，模拟新增用户-User类型传参
		Post请求，模拟新增用户-Map传参
		测试连接超时
		测试请求超时



springboot-test13： 测试生成日志文件


springboot-test14： 测试thymeleaf模板的高级特性
	1.公共模块引入 --> 替换原来jsp的taglib引入
	2.页面调用Java的静态变量和静态方法


springboot-test15： 测试jpa封装QueryVo和SaveVo！！！
	封装成Vo的作用：仅接收需要操作的字段，防止在前端传入其它非操作字段。
	1.QueryVo
		直接在Vo类生成Specification类，因此不用在service层写。QueryVo参数条件通过@Filter动态织入。详情查询Controller层方法
	2、SaveVo
		在Vo类接收仅需要修改的字段，不需要修改的字段不会更新。详情查看UserController的save()方法

springboot-test16： 测试表单提交后相关操作
	1、表单提交成功：弹出提示语、并关闭当前页面
	2、表单提交成功：弹出提示语、并关闭当前页面和刷新父页面


springboot-test17： 测试使用druid数据库连接池
	描述：Druid是一个非常好用的数据库连接池，自身带有一个强大的监控工具：DruidMonitor，不仅可以监控数据源和慢查询，还可以监控web应用、url监控、Session监控、Spring监控。
	测试：浏览器输入“http://localhost:8080/druid/login.html”，登录用户“druid/druid”，能进入监控工具。
	数据库连接池选择：
		性能方面：HikariCP > Druid > Tomcat-jdbc > Dbcp > C3p0
		1）HikariCP是springboot的默认连接池，目前性能最好。hikariCP的高性能得益于最大限度的避免锁竞争
		2）Druid功能最全面，sql拦截、统计数据等，具有良好的扩展性。
			开启prepareStatement缓存，即PSCache，对性能会有大概20%的提升
	参考文章：https://blog.csdn.net/justlpf/article/details/80728529
	官方文档：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

druid遇到的坑：
	生产环境连接池中，将testOnBorrow=false，由于不检查连接是否可用，应用通过getConnection()获取的连接可能不可用。

	统计SQL中是用map保存的，sql太多时会导致oom异常。详情：https://github.com/alibaba/druid/issues/1664


springboot-test18-part1 & springboot-test18-part2 ： 测试spring session共享(redis缓存)
	参考：
		https://www.cnblogs.com/SimpleWu/p/10118674.html
		--补充：可使用nginx配置负载均衡，通过请求同一地址进到不同的应用程序中，查看返回数据（未完成）
	测试如下：
	http://localhost:8080/addSession?username=admin //当前端口号：8080 当前sessionId:17973600-84e1-457b-84ba-9adcc6ab3b67 存入Session的值：admin
	http://localhost:8080/getSession //当前端口号：8080 当前sessionId:17973600-84e1-457b-84ba-9adcc6ab3b67 获取用户名：admin
	http://localhost:8081/getSession //当前端口号：8081 当前sessionId:17973600-84e1-457b-84ba-9adcc6ab3b67 获取用户名：admin


springboot-test19： 测试jdbcTemplate和NamedParameterJdbcTemplate的使用
	--适用于仅执行几个字段的写操作、读操作。高效批量、高效更新
	--1.jdbcTemplate测试
		update() --执行insert、update、delete语句。参数可写成:new Object[] {"testAA", 2}
		batchUpdate() --执行批量写操作语句。
		queryForObject() --1、单列结果查询 2、封装成对象RowMapper<User>
		query()	--执行批量读操作语句
	--2.NamedParameterJdbcTemplate测试
		namedParameterJdbcTemplate.update(sql, paramMap);
	配置springboot默认日志：
		# logging.level.root=info
		logging.file=./logs/log.log
		logging.pattern.file=%d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger- %msg%n


springboot-test20： 测试jwt使用
	jwt工具类(JwtUtils)：生成token、解析token
	token生成：String token = HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret);
	/login --输入username&password，验证通过后返回生成token，在前端本地保存token
	校验jwt拦截器：
		/hello1	--忽略jwt拦截器
		/hello2	--测试jwt拦截器，在header中添加token属性


springboot-test21： 测试前端传参实体与数据库实体结合
	--该项目引入了commons-beanutils包（已移除，commons-beanutils 1.9.3不安全）
	--描述：
		从前端传入部分字段，从数据库获取部分字段，将上面两个结合后得到新实体，jpa再保存该新实体。
		我们数据库的字段可能很多，但是前端页面能修改的字段可能就几个，jpa的保存机制直接保存整个实体，因此需要保存的应是将前端实体和后端实体结合后得到的实体。如：用户表有用户名、密码、出生日期，但前端仅允许修改用户名、出生日期，因此在保存时将前端传过来的用户名、出生日期，与数据库得到的密码结合，得到新的实体，再保存该新实体。
	--测试详情参考UserController类
	作用：
		1、保证恶意修改其它不必要的字段。保证前端仅能修改的是用户名和出生日期，密码从前端传入无效。
		2、部分字段为公共字段，如id、versionNumber、createUser、createTime、lastUpdateUser、lastUpdateTime等，公共字段会抽象成一个类，具体数据库实体继承这些类，从而添加了这些公共字段。在set属性时需要对这一部分字段的赋值。
		--附：若仅更新几个字段，可以使用jdbcTemplate进行更新，但这样不能保证jpa的乐观锁是否一致。


springboot-test22： 测试WebSocket，向前端推送消息
	测试：
		--1.两个客户端连接
		http://localhost:8080/websocket/socket/20
		http://localhost:8080/websocket/socket/21

		--2.群发推送消息
		http://localhost:8080/websocket/socket/push?message=测试群发推送
		--3.推送给某人
		http://localhost:8080/websocket/socket/push/21?message=推送给21客户
		--4.客户端发送消息
		浏览器Console中输入:ws.send("测试客户端20");


springboot-test23: 测试定时任务
	@EnableScheduling开启任务调度，@Scheduled创建定时任务。
	注：这些任务都是使用同一线程。



springboot-test24： 测试不同数据库插入解决分布式事务问题
	使用jta来解决多数据源的分布式事务问题（2pc实现）
	缺点：不能解决微服务的分布式事务（远程调用失败后处理不了回滚问题）

springboot-test25： 测试springboot整合mybatis框架
	user表的增删改查操作，exists查询，动态sql查询
	根据id查询order信息，附带部分用户信息，最后封装到vo实体里面
	打印sql日志

springboot-test25-v2: 测试mybatis-plus
	测试相关api方法
	测试插入/更新操作时的基础字段自动赋值
	测试乐观锁版本控制
	测试分页查询


springboot-test26： 测试Api接口文档生成工具
	运行项目并打开浏览器输入：http://localhost:8080/swagger-ui.html


springboot-test27： 测试RabbitMQ的广播模型
	举例：公众号一样，我每天推文章后，会推送给每个关注用户，他们都可以看到这条消息
	广播模型：1、可以有多个队列；2、每个队列都需要绑定交换机；3、每个消费者有自己的队列；4、交换机把消息发送给绑定的所有队列
	http://localhost:8080/send --生产消息

springboot-test27-v2： 测试RabbitMQ的5种消息模式（详情参考RabbitMqTest类）
	简单模式
	工作模式
	广播模式
	路由模式
	主题模式

springboot-test27-v3： 测试死信与延时队列
	


springboot-test28： 测试Validator校验工具
	嵌套属性、集合对象校验


springboot-test30： 测试自定义Starter



springboot-test31： 测试整合ElasticSearch6.2.2
	规范跟springDataJpa一样。
	测试参考ArticleTest类


springboot-test31-v2 整合elasticsearch7.6.1
	描述：（出自狂神说的教程）模拟爬取京东搜索数据，并放入es中，后续通过es获取对应搜索数据
	测试：
		http://localhost:9090/parse/java
		http://localhost:9090/ --在输入框中输入“java”，点击搜索，能获取到分页后的10条数据

		http://localhost:9090/parse/kafka --爬取京东数据并放入es中
		http://localhost:9090/ --在输入框中输入“kafka”，点击搜索，能获取到分页后的10条数据

		http://localhost:9090/parse/文
		http://localhost:9090/ --在输入框中输入“文”，点击搜索，能获取到分页后的10条数据

springboot-test31-v3 整合elasticsearch7.6.1
	测试参考：springboot整合elasticsearch7.postman_collection.json
	




