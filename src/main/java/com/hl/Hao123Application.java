/*******************************************************************************
 * Licensed to the OKChem
 *
 * http://www.okchem.com
 *
 *******************************************************************************/
package com.hl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import util.GsonUtil;

/**
 * @author moss
 */
// @ComponentScan({"com.hl","config","entity"})
@ServletComponentScan
@SpringBootApplication
@RestController
public class Hao123Application {
	static Logger log = LoggerFactory.getLogger(Hao123Application.class);

	static Map<String, String> tokenMap = new HashMap<String, String>();

	public static void main(String[] args) {
		SpringApplication.run(Hao123Application.class, args);
		/////////////////////////////////////////////////////////////////////
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 参数：1、任务体 2、首次执行的延时时间
		// 3、任务执行间隔 4、间隔时间单位
		service.scheduleAtFixedRate(() -> {
			System.out.println("更新access_token, " + new Date());
			try {
				String str = HttpClientUtil.doGet(
						"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx63e923c37a90e24e&secret=58e8da3e42d3e3b04db36af5dae8fdb1",
						null);
				log.info("-----------" + str);
				Map<String, Object> retMap = GsonUtil.GsonToMaps(str);
				String token = (String) retMap.get("access_token");
				log.info("更新access_token, {}", token);
				tokenMap.put("access_token", token);
			} catch (Exception e) {
				log.info("更新access_token failed");
				e.printStackTrace();
			}

		}, 0, 7, TimeUnit.MINUTES);
		ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
		// 参数：1、任务体 2、首次执行的延时时间
		// 3、任务执行间隔 4、间隔时间单位

		service2.scheduleAtFixedRate(() -> {
			/*
			 * "{\"touser\": \\", \"msgtype\": \"text\", \"text\": {\"content\": \" " +
			 * content + " \" }}"
			 */
			String content = randomShiti();
			try {
				log.info("22222222222222, " + new Date());
				String token = tokenMap.get("access_token");
				Map<String, String> map = new HashMap<String, String>();
				map.put("touser", "orR4l1sSeLPOQRpLkCC57sBU1fE0");
				map.put("msgtype", "text");
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("content", "123");
				map.put("text", GsonUtil.GsonString(map2));
				log.info("11111111111111, {}", GsonUtil.GsonString(map));
				String str = HttpClientUtil.httpPostWithJSON(
						"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token,
						"{\"touser\": \"orR4l1rIdRi1-xsJxWJezAA2QrXE\", \"msgtype\": \"text\", \"text\": {\"content\": \" "
								+ content + " \" }}");
				log.info("2222222222result2222, {}" + str);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}, 2, 51, TimeUnit.MINUTES);

	}

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	@ResponseBody
	public String demo() {
		return "hello world!";
	}

	static String randomShiti() {
		List<String> list = Arrays.asList("1你能讲讲jvm内存模型吗，eden，S1,S2,年轻代，年老代，永久代，垃圾回收算法吗？", "2你能说出几种设计模式", "3并发包下面的类",
				"4集合框架1.java集合 集合的好处：1是可变容量 2.是可以存放不同类型的元素 3.增删改查算法优化 4.部分支持线程安全 Collections - List接口 -Map接口 -Queue接口 HashMap 源码解读  http://blog.csdn.net/mrb1289798400/article/details/76761423  第一层是数组，第二层是Node元素的单向链表 ConcurrentHashMap源码解读  http://www.importnew.com/16142.html 用到了segment，然后更重要的是在segment里面用到了重入锁，用读写所，在 读多写少的情况下，性能有显著提升，如果写多读少到不见得比synchrnized能好到哪里去。\n"
						+ "List接口 ： 有序可重复 Arraylist 查快 增删改慢（用挪动其他元素） LinkedList 查比较慢 增删改快（不用挪动其他元素） Vector 3个 可以用List的特性做队列和栈容器 用addLast/First pollFirst/Last removeFirst/Last（）在在集合为空的时候会报空指针异常。 List接口里面有contains()方法，不要傻逼比再去用for循环一个元素一个元素在那里比较了了。 set接口 : 无序不可重复 HashSet treeSet 2个\n"
						+ "\n" + "Map 接口 ： treeMap HashMap HashTable 3个\n"
						+ "也很重要，能装逼的类：ArrayQueue， Stack（Verctor子类） WeakHashMap() SortedMap() SortedSet() EnumHashMap EnumHashSet LinkedHashMap LinkedHashSet NavigableMap NavigableSet RegularEnumSet 还有并发包下面的集合\n"
						+ "BlockingQueue BlockingDequeue ArrayBlockingQueue 还有SynchronousQueue （用在缓冲线程池里面） ConcurrentHashMap ConcurrentLinkedDequeue ConcurrentLinkedQueue ConcurrentSkipListMap ConcurrentSkipListSet CopyOnWriteArrayList CopyOnWriteArraySet DelayQueue",
				"5SpringMVC原理1、  用户发送请求至前端控制器DispatcherServlet。\n"
						+ "2、  DispatcherServlet收到请求调用HandlerMapping处理器映射器。\n"
						+ "3、  处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。\n"
						+ "4、  DispatcherServlet调用HandlerAdapter处理器适配器。\n"
						+ "5、  HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。\n"
						+ "6、  Controller执行完成返回ModelAndView。\n"
						+ "7、  HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。\n"
						+ "8、  DispatcherServlet将ModelAndView传给ViewReslover视图解析器。\n" + "9、  ViewReslover解析后返回具体View。\n"
						+ "10、DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。\n" + "11、 DispatcherServlet响应用户。",
				"6多线程几种实现方式  继承Thread接口，实现Runnable,使用线程池",
				"7dubbo原理源码  \n  https://www.cnblogs.com/gotodsp/p/6532856.html", "8shiro", "9oauth2", "10mybatis原理",
				"11mysql隔离级别  读未提交 读已提交  可重复读(锁行)  串行化(锁表)  1、脏读：事务A读取了事务B更新的数据，然后B回滚操作，那么A读取到的数据是脏数据\n" + "\n"
						+ "　　2、不可重复读：事务 A 多次读取同一数据，事务 B 在事务A多次读取的过程中，对数据作了更新并提交，导致事务A多次读取同一数据时，结果 不一致。（事务 A在这一次事务里面多次读了同一批数据）\n"
						+ "　　3、幻读：系统管理员A将数据库中所有学生的成绩从具体分数改为ABCDE等级，但是系统管理员B就在这个时候插入了一条具体分数的记录，当系统管理员A改结束后发现还有一条记录没有改过来，就好像发生了幻觉一样，这就叫幻读。\n"
						+ "　　小结：不可重复读的和幻读很容易混淆，不可重复读侧重于修改，幻读侧重于新增或删除。解决不可重复读的问题只需锁住满足条件的行，解决幻读需要锁表事务隔离级别 	脏读 	不可重复读 	幻读\n"
						+ "读未提交（read-uncommitted） 	是 	是 	是\n" + "不可重复读（read-committed） 	否 	是 	是\n"
						+ "可重复读（repeatable-read） 	否 	否 	是\n" + "串行化（serializable） 	否 	否 	否",
				"12mysql优化：sql优化；索引优化，执行计划，配置参数优化", "13分布式锁", "15redis,复制,分布式锁,哈希环", "16讲讲现公司的架构", "17数据结构和算法  冒择入希快归堆",
				"18dubbo", "19zookepper", "20分布式事务", "21敏捷开发", "22linux命令，根据端口找进程", "23zookeeper选举原理",
				"24consul跟zk有什么区别", "25最新版JDK HashMap的结构", "26springmvc的原理", "27oracle sql实现递归", "28mysql优化",
				"29执行计划看什么,是否有全表扫描,索引是否生效",
				"30jvm的数据模型,年代划分,gc算法 \n   https://www.cnblogs.com/kingszelda/p/7226080.html", "32set是怎么实现不重复的",
				"33lock锁与synchronize的差别", "34ConcurrentHashMap实现原理", "35一致性hash",
				"36RabbitMQ:ACK机制 MQ三个作用，异步，解耦，削峰，\n    Consumer可能需要一段时间才能处理完收到的数据。如果在这个过程中，Consumer出错了，异常退出了，而数据还没有处理完成，这段数据就丢失了。如果我们采用no-ack的方式进行确认，也就是说，每次Consumer接到数据后，而不管是否处理完成，RabbitMQ Server会立即把这个Message标记为完成，然后从queue中删除了。\n"
						+ "为了保证数据不被丢失，RabbitMQ支持消息确认机制，即ack。为了保证数据能被正确处理而不仅仅是被Consumer收到，我们就不能采用no-ack或者auto-ack，我们需要手动ack(manual-ack)。在数据处理完成后手动发送ack，这个时候Server才将Message删除。"
						+ "如何设置？",
				"37zookeeper实现服务注册与发现原理", "38工厂模式如何实现",
				"39单例模式如何实现？ 饱汉模式，饥饿模式，1构造器私有化，2持有一个私有的该对象的实例作为成员变量3.获取实例时判断当前成员变量实例是否为空",
				"40实现动态代理有几种方式,cglib1. 动态代理是指在运行时，动态生成代理类。代理类的字节码将在运行时生成并载入当前的ClassLoader.\n"
						+ "生成动态代理类的方法很多，如JDK自带的动态代理、CGLIB、Javassist或者ASM库。\n"
						+ "JDK动态代理使用简单，它内置在JDK中，因此不需要引入第三方Jar包，但相对功能比较弱。CGLIB和Javassist都是高级的字节码生成库，总体性能比JDK自带的动态代理好，而且功能十分强大。ASM是低级的字节码生成工具，使用ASM已经近乎在于使用Javabytecode编程，对开发人员要求较高，也是性能最好的一种动态代理生辰工具。但ASM的使用是在过于繁琐，而且性能也没有数量级的提升，与CGLIB等高级字节码生成工具相比，ASM程序的可维护性也较差。\n"
						+ "JDK实现\n" + "1、步骤\n" + "1）通过实现InvocationHandler接口创建自己的调用处理器\n"
						+ "2）通过为Proxy类指定ClassLoader对象和一组interface来创建动态代理类\n"
						+ "3）通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型\n" + "4）通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入",
				"41适配器模式如何实现", "42桥接模式如何实现", "43策略模式如何实现",
				"44门面模式如何实现 \n  https://blog.csdn.net/xingjiarong/article/details/50066133", "45命令行模式如何实现",
				"46过滤器中如何重新整理请求头，请求体中的数据 用Servlet" + "RequestWrapper,因为用了new来创建Wrapper实例，所以不会影响原始的request，和response");
		int i = RandomUtils.nextInt(list.size());
		return list.get(i);
	}
}

/**
 * 一个最简单的Web应用
 * 
 * 使用Spring Boot框架可以大大加速Web应用的开发过程，首先在Maven项目依赖中引入spring-boot-starter-web：
 * 
 * pom.xml
 * 
 * <?xml version="1.0" encoding="UTF-8"?>
 * <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi=
 * "http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=
 * "http://maven.apache.org/POM/4.0.0
 * http://maven.apache.org/xsd/maven-4.0.0.xsd">
 * <modelVersion>4.0.0</modelVersion>
 * 
 * <groupId>com.tianmaying</groupId> <artifactId>spring-web-demo</artifactId>
 * <version>0.0.1-SNAPSHOT</version> <packaging>jar</packaging>
 * 
 * <name>spring-web-demo</name> <description>Demo project for Spring
 * WebMvc</description>
 * 
 * <parent> <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-parent</artifactId>
 * <version>1.2.5.RELEASE</version> <relativePath/> </parent>
 * 
 * <properties>
 * <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 * <java.version>1.8</java.version> </properties>
 * 
 * <dependencies> <dependency> <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-web</artifactId> </dependency>
 * </dependencies>
 * 
 * <build> <plugins> <plugin> <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-maven-plugin</artifactId> </plugin> </plugins>
 * </build>
 * 
 * 
 * </project> 接下来创建src/main/Java/Application.java:
 * 
 * import org.springframework.boot.SpringApplication; import
 * org.springframework.boot.autoconfigure.SpringBootApplication; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * @SpringBootApplication
 * @RestController public class Application {
 * 
 * 				@RequestMapping("/") public String greeting() { return "Hello
 *                 World!"; }
 * 
 *                 public static void main(String[] args) {
 *                 SpringApplication.run(Application.class, args); } } 运行应用：mvn
 *                 spring-boot:run或在IDE中运行main()方法，在浏览器中访问http://localhost:8080，Hello
 *                 World!就出现在了页面中。只用了区区十几行Java代码，一个Hello
 *                 World应用就可以正确运行了，那么这段代码究竟做了什么呢？我们从程序的入口SpringApplication.run(Application.class,
 *                 args);开始分析：
 * 
 *                 SpringApplication是Spring
 *                 Boot框架中描述Spring应用的类，它的run()方法会创建一个Spring应用上下文（Application
 *                 Context）。另一方面它会扫描当前应用类路径上的依赖，例如本例中发现spring-webmvc（由
 *                 spring-boot-starter-web传递引入）在类路径中，那么Spring
 *                 Boot会判断这是一个Web应用，并启动一个内嵌的Servlet容器（默认是Tomcat）用于处理HTTP请求。
 * 
 *                 Spring
 *                 WebMvc框架会将Servlet容器里收到的HTTP请求根据路径分发给对应的@Controller类进行处理，@RestController是一类特殊的@Controller，它的返回值直接作为HTTP
 *                 Response的Body部分返回给浏览器。
 * 
 * @RequestMapping注解表明该方法处理那些URL对应的HTTP请求，也就是我们常说的URL路由（routing)，请求的分发工作是有Spring完成的。例如上面的代码中http://localhost:8080/根路径就被路由至greeting()方法进行处理。如果访问http://localhost:8080/hello，则会出现404 Not
 *                                                                                                                                                                                 Found错误，因为我们并没有编写任何方法来处理/hello请求。
 * 
 *                                                                                                                                                                                 使用@Controller实现URL路由
 * 
 *                                                                                                                                                                                 现代Web应用往往包括很多页面，不同的页面也对应着不同的URL。对于不同的URL，通常需要不同的方法进行处理并返回不同的内容。
 * 
 *                                                                                                                                                                                 匹配多个URL
 * 
 * @RestController public class Application {
 * 
 * 				@RequestMapping("/") public String index() { return "Index
 *                 Page"; }
 * 
 * 				@RequestMapping("/hello") public String hello() { return
 *                 "Hello World!"; } }
 * @RequestMapping可以注解@Controller类：
 * 
 * 									@RestController @RequestMapping("/classPath")
 *                                  public class Application
 *                                  { @RequestMapping("/methodPath") public
 *                                  String method() { return "mapping url is
 *                                  /classPath/methodPath"; } }
 *                                  method方法匹配的URL是/classPath/methodPath"。
 * 
 *                                  提示
 * 
 *                                  可以定义多个@Controller将不同URL的处理方法分散在不同的类中
 * 
 *                                  URL中的变量——PathVariable
 * 
 *                                  在Web应用中URL通常不是一成不变的，例如微博两个不同用户的个人主页对应两个不同的URL:http://weibo.com/user1，http://weibo.com/user2。我们不可能对于每一个用户都编写一个被@RequestMapping注解的方法来处理其请求，Spring
 *                                  MVC提供了一套机制来处理这种情况：
 * 
 * 									@RequestMapping("/users/{username}") public
 *                                  String userProfile(@PathVariable("username")
 *                                  String username) { return
 *                                  String.format("user %s", username); }
 * 
 * 									@RequestMapping("/posts/{id}") public String
 *                                  post(@PathVariable("id") int id) { return
 *                                  String.format("post %d", id); }
 *                                  在上述例子中，URL中的变量可以用{variableName}来表示，同时在方法的参数中加上@PathVariable("variableName")，那么当请求被转发给该方法处理时，对应的URL中的变量会被自动赋值给被@PathVariable注解的参数（能够自动根据参数类型赋值，例如上例中的int）。
 * 
 *                                  支持HTTP方法
 * 
 *                                  对于HTTP请求除了其URL，还需要注意它的方法（Method）。例如我们在浏览器中访问一个页面通常是GET方法，而表单的提交一般是POST方法。@Controller中的方法同样需要对其进行区分：
 * 
 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
 *                       loginGet() { return "Login Page"; }
 * 
 * @RequestMapping(value = "/login", method = RequestMethod.POST) public String
 *                       loginPost() { return "Login Post Request"; } 模板渲染
 * 
 *                       在之前所有的@RequestMapping注解的方法中，返回值字符串都被直接传送到浏览器端并显示给用户。但是为了能够呈现更加丰富、美观的页面，我们需要将HTML代码返回给浏览器，浏览器再进行页面的渲染、显示。
 * 
 *                       一种很直观的方法是在处理请求的方法中，直接返回HTML代码，但是这样做的问题在于——一个复杂的页面HTML代码往往也非常复杂，并且嵌入在Java代码中十分不利于维护。更好的做法是将页面的HTML代码写在模板文件中，渲染后再返回给用户。为了能够进行模板渲染，需要将@RestController改成@Controller：
 * 
 *                       import org.springframework.ui.Model;
 * 
 * @Controller public class HelloController {
 * 
 * 			@RequestMapping("/hello/{name}") public String
 *             hello(@PathVariable("name") String name, Model model) {
 *             model.addAttribute("name", name); return "hello" } }
 *             在上述例子中，返回值"hello"并非直接将字符串返回给浏览器，而是寻找名字为hello的模板进行渲染，我们使用Thymeleaf模板引擎进行模板渲染，需要引入依赖：
 * 
 *             <dependency> <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-thymeleaf</artifactId>
 *             </dependency>
 *             接下来需要在默认的模板文件夹src/main/resources/templates/目录下添加一个模板文件hello.html：
 * 
 *             <!DOCTYPE HTML> <html xmlns:th="http://www.thymeleaf.org"> <head>
 *             <title>Getting Started: Serving Web Content</title>
 *             <meta http-equiv="Content-Type" content="text/html;
 *             charset=UTF-8" /> </head> <body>
 *             <p th:text="'Hello, ' + ${name} + '!'" />
 *             </body> </html> th:text="'Hello, ' + ${name} +
 *             '!'"也就是将我们之前在@Controller方法里添加至Model的属性name进行渲染，并放入
 *             <p>
 *             标签中（因为th:text是
 *             <p>
 *             标签的属性）。模板渲染还有更多的用法，请参考Thymeleaf官方文档。
 * 
 *             处理静态文件
 * 
 *             浏览器页面使用HTML作为描述语言，那么必然也脱离不了CSS以及JavaScript。为了能够浏览器能够正确加载类似/css/style.css,
 *             /js/main.js等资源，默认情况下我们只需要在src/main/resources/static目录下添加css/style.css和js/main.js文件后，Spring
 *             MVC能够自动将他们发布，通过访问/css/style.css, /js/main.js也就可以正确加载这些资源。
 * 
 * 
 * 
 * 
 * 
 */
