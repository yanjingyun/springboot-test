springboot-test1： 测试spring4的泛型注入
	封装BaseRepository、BaseService两个模板父类，把通用的crud等代码放在其中，然后子类继承后，只需要添加额外的实现。
	如:
		@Repository class UserRepository extends BaseRepository<User> {...}
		@Service class UserService extends BaseService<User> {...}
		controller层调用： @Autowired private UserService userService;


springboot-test2： 测试SpringDataJpa使用
	主要为复杂查询方法的使用，详情参考“jpa查询方法.txt”文档
	1、Specification查询，返回实体、列表、分页
	2、entityManager的原生sql查询，返回实体、列表、分页
	3、@Version乐观锁使用



springboot-test3： 测试统一数据格式返回
	1.http日志输出
		通过AOP切入：@Aspect @Pointcut @Before @After @AfterReturning @Around
	2.统一数据格式返回，格式：{code: xxx, msg: xxx, data: []|{} }
	3.统一异常处理
		--封装一个业务异常类MyException，使用ResultEnum存放各种异常信息，并使用@ExceptionHandler注解处理捕获的异常。
		一共分三类异常：
			1、自定义的异常 new MyException(...)
			2、访问链接不存在异常（需要在application.properties配置）。格式：{"code":404,"msg":"找不到系统资源","data":null}
			3、系统异常 格式：{"code":-1,"msg":"未知错误","data":null}

	泛型：
		public static <T> Result<T> success(T object) {...} //声明
		List<User> list = new ArrayList<>();
		Result<List<User>> resultData = ResultUtil.success(list); //使用


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
	--后续整合部分：1、会话管理器SessionManager，用于处理集群的session共享问题（使用redis） 2、缓存管理器CacheManager（使用redis）




springboot-test8： 测试redis整合
	1、测试使用redisTemplate。详情查看UserTest和ListUserTest两个类
	2、使用springCache缓存注解，注入具体缓存方案是redis。测试详情查看UserController类（目前使用注解存入redis的数据不是json格式）

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
	主键类(IdEntity)、乐观锁(VersionEntity)
	AuditEntity类：使用@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy自动生成时间和修改者
	ApplicationUtils类：获取ApplicationContext和bean方法
