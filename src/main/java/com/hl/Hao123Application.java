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
import java.util.concurrent.ConcurrentHashMap;
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

	static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	static String tokenShow = "";

	static String token_time = "";

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
				tokenShow = token;
				token_time = new Date() + "";
				log.info("更新access_token, {}", token);
				tokenMap.put("access_token", token);
				// tokenMap.put("token_time", new Date() + "");
			} catch (Exception e) {
				log.info("更新access_token failed");
				e.printStackTrace();
			}

		}, 3, 7199, TimeUnit.SECONDS);
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
				log.info("发送的内容,发送不易，且读且珍惜, {}", content);
				String str = HttpClientUtil.httpPostWithJSON(
						"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token,
						"{\"touser\": \"orR4l1rIdRi1-xsJxWJezAA2QrXE\", \"msgtype\": \"text\", \"text\": {\"content\": \" "
								+ content + " \" }}");
				log.info("2222222222result2222, {}", str);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}, 2, 61, TimeUnit.SECONDS);

	}

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	@ResponseBody
	public String demo() {
		return "hello world!";
	}

	static String randomShiti() {
		List<String> list = Arrays.asList(
				"1你能讲讲jvm内存模型吗，eden，S1,S2,年轻代，年老代，永久代，垃圾回收算法吗？   https://www.cnblogs.com/dingyingsi/p/3760447.html",
				"2你能说出几种设计模式  https://www.cnblogs.com/geek6/p/3951677.html",
				"3并发包下面的类  https://blog.csdn.net/sunyc1990/article/details/78084864",
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
				"7dubbo原理源码  \n  https://www.cnblogs.com/gotodsp/p/6532856.html",
				"8 shiro  https://www.cnblogs.com/ll409546297/p/7815409.html",
				"9oauth2  https://blog.csdn.net/qq1623299667/article/details/78448324",
				"10mybatis原理   https://blog.csdn.net/u014297148/article/details/78696096",
				"11mysql隔离级别  读未提交 读已提交  可重复读(锁行)  串行化(锁表)  1、脏读：事务A读取了事务B更新的数据，然后B回滚操作，那么A读取到的数据是脏数据\n" + "\n"
						+ "　　2、不可重复读：事务 A 多次读取同一数据，事务 B 在事务A多次读取的过程中，对数据作了更新并提交，导致事务A多次读取同一数据时，结果 不一致。（事务 A在这一次事务里面多次读了同一批数据）\n"
						+ "　　3、幻读：系统管理员A将数据库中所有学生的成绩从具体分数改为ABCDE等级，但是系统管理员B就在这个时候插入了一条具体分数的记录，当系统管理员A改结束后发现还有一条记录没有改过来，就好像发生了幻觉一样，这就叫幻读。\n"
						+ "　　小结：不可重复读的和幻读很容易混淆，不可重复读侧重于修改，幻读侧重于新增或删除。解决不可重复读的问题只需锁住满足条件的行，解决幻读需要锁表事务隔离级别 	脏读 	不可重复读 	幻读\n"
						+ "读未提交（read-uncommitted） 	是 	是 	是\n" + "不可重复读（read-committed） 	否 	是 	是\n"
						+ "可重复读（repeatable-read） 	否 	否 	是\n" + "串行化（serializable） 	否 	否 	否",
				"12mysql优化：sql优化；索引优化，执行计划，配置参数优化",
				"13分布式锁   https://www.cnblogs.com/austinspark-jessylu/p/8043726.html",
				"14zookeeper 实现分布式锁  https://blog.csdn.net/qiangcuo6087/article/details/79067136",
				"15redis,复制,分布式锁,哈希环   一致性hash  https://blog.csdn.net/bntX2jSQfEHy7/article/details/79549368",
				"16讲讲现公司的架构", "17数据结构和算法  冒择入希快归堆", "18dubbo", "19zookepper", "20分布式事务", "21敏捷开发  快速迭代 持续集成",
				"22linux命令，根据端口找进程", "23zookeeper选举原理", "24consul跟zk有什么区别", "25最新版JDK HashMap的结构", "26springmvc的原理",
				"27oracle sql实现递归", "28mysql优化", "29执行计划看什么,是否有全表扫描,索引是否生效",
				"30jvm的数据模型,年代划分,gc算法 \n   https://www.cnblogs.com/kingszelda/p/7226080.html",
				"32set是怎么实现不重复的   https://blog.csdn.net/u010698072/article/details/52802179   一句话，用hashMap存储，key值不会重复 所以set也不会重复",
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
				"46过滤器中如何重新整理请求头，请求体中的数据 用Servlet" + "RequestWrapper,因为用了new来创建Wrapper实例，所以不会影响原始的request，和response",
				"47数据库优化 https://ke.qq.com/course/255238 这个里面很简洁的讲了主从配置读写分离\n"
						+ "I  经常查询并且区分力度大的字段加索引 II 索引要有效，不能索引失效 III 去掉无必要关联 IV  去掉select *\n"
						+ "VI 必须使用InnoDB引擎   VII 禁止使用视图，触发器，存储过程 避免DB压力过大 IX 禁止使用外键约束，用程序控制关联性，增删改十分影响SQL性能，大并发场景下性能优先。 X 禁止使用小数存储货币金额，避免金额对不上 XI 尽量使用小类型字段 boolean char tinyint int bigint long XII sql优化最基本的原则，就是让数据库尽早、高效率的过滤数据，避免无效的运算，具体的手段比较多，需要根据不同的数据库来确定实现方案。 XIII 对大数据量的表尽量避免join，也就是不用表本身的join功能，而是自己单独查一张表，再去循环查询另一张表。（上亿条数据）\n"
						+ "6.1 分库分表，读写分离 ，主从复制怎么做\n" + "7.数据库索引，sql优化 布尔学院那里的总结比较好",
				"48分布式：dubbo架构和zk原理\n" + "\n"
						+ "dubbo 源码 ----------------------duboo底层核心知识点------------------------------ tcp的单工、半双工、全双工、编码、解码、粘包、拆包。网络通信netty nio 阻塞、非阻塞、异步、同步、线程池、boss线程、work线程。集群负载均衡directory、router、cluster、loadbalance。zookeeper与zkClient、持久节点 和 临时节点。服务的本地暴露和远程暴露、服务降级、服务灰度发布。 dubbo雪崩 https://www.jianshu.com/p/48d3b1926086   dubbo雪崩很有可能是服务端返回超时，导致重连，retries默认是3，导致后端连接时最高达到正常的3倍，很有可能超过最大连接数，另外导致不幂等，一是后端 优化，缩短响应时间，还有增加超时时间，另外将重试次数设置为0.\n"
						+ "\n" + "dubboX 以及各种PRC框架横向比较http://blog.csdn.net/i_will_try/article/details/76212234",
				"49事务  这个曾经是我最头疼的 http://blog.csdn.net/liaohaojian/article/details/68488150?locationNum=8&fps=1 事务有四大点 1.事务原则  ACID 原子性 一致性 隔离性 持久性 2. 事务类型  I 数据库事务分为本地事务和全局事务  II java事务  jdbc事务和JTA事务   III 声明式事务和编程式事务 3.spring五大事务隔离级别\n"
						+ "\n"
						+ ".Spring事务隔离级别：spring有五大隔离级别，其在TransactionDefinition接口中定义。看源码可知，其默isolation_default（底层数据库默认级别），其他四个隔离级别跟数据库隔离级别一致。\n"
						+ "ISOLATION_DEFAULT：用底层数据库的默认隔离级别，数据库管理员设置什么就是什么\n"
						+ "ISOLATION_READ_UNCOMMITTED（未提交读）：最低隔离级别、事务未提交前，就可被其他事务读取（会出现幻读、脏读、不可重复读）\n"
						+ "ISOLATION_READ_COMMITTED（提交读）：一个事务提交后才能被其他事务读取到（该隔离级别禁止其他事务读取到未提交事务的数据、所以还是会造成幻读、不可重复读）、sql server默认级别\n"
						+ "ISOLATION_REPEATABLE_READ（可重复读）：可重复读，保证多次读取同一个数据时，其值都和事务开始时候的内容是一致，禁止读取到别的事务未提交的数据（该隔离基本可防止脏读，不可重复读（重点在修改），但会出现幻读（重点在增加与删除））（MySql默认级别，更改可通过set transaction isolation level 级别）\n"
						+ "ISOLATION_SERIALIZABLE（序列化）：代价最高最可靠的隔离级别（该隔离级别能防止脏读、不可重复读、幻读）\n"
						+ "丢失更新：两个事务同时更新一行数据，最后一个事务的更新会覆盖掉第一个事务的更新，从而导致第一个事务更新的数据丢失，这是由于没有加锁造成的；\n"
						+ "幻读：同样的事务操作过程中，不同时间段多次（不同事务）读取同一数据，读取到的内容不一致（一般是行数变多或变少）。\n"
						+ "脏读：一个事务读取到另外一个未提及事务的内容，即为脏读。\n" + "不可重复读：同一事务中，多次读取内容不一致（一般行数不变，而内容变了）。\n"
						+ "幻读与不可重复读的区别：幻读的重点在于插入与删除，即第二次查询会发现比第一次查询数据变少或者变多了，以至于给人一种幻象一样，而不可重复读重点在于修改，即第二次查询会发现查询结果比第一次查询结果不一致，即第一次结果已经不可重现了。\n"
						+ "数据库隔离级别越高，执行代价越高，并发执行能力越差，因此在实际项目开发使用时要综合考虑，为了考虑并发性能一般使用提交读隔离级别，它能避免丢失更新和脏读，尽管不可重复读和幻读不能避免，但可以在可能出现的场合使用悲观锁或乐观锁来解决这些问题。\n"
						+ "悲观锁与乐观锁可参考：http://blog.csdn.net/liaohaojian/article/details/62416972\n" + "",
				"50分布式\n" + "\n"
						+ "10.1 分布式锁 分布式锁:利用db行锁 ， redis锁   https://www.cnblogs.com/linjiqin/p/8003838.html  把这里面的工程要背下来 关键点：1 用setnx加锁，key要唯一，要加有效时间，且不能太长，避免死锁；要加随机数，解锁的时候，根据这个随机数判断是否是自己加的锁； 2. 释放锁，要用lua脚本，根据key取value，比较value，然后删除key即释放锁，一定要保证原子型，所以用lua脚本\n"
						+ "和zookeeper锁\n" + "\n"
						+ "保证锁节点（lock root node）这个父根节点的存在，这个节点是每个要获取lock客户端共用的，这个节点是PERSISTENT的。 第一次需要创建本客户端要获取lock的节点，调用 create( )，并设置 节点为EPHEMERAL_SEQUENTIAL类型，表示该节点为临时的和顺序的。如果获取锁的节点挂掉，则该节点自动失效，可以让其他节点获取锁。\n"
						+ "\n" + "在父锁节点（lock root node）上调用 getChildren( ) ，不需要设置监视标志。 (为了避免“羊群效应”).\n" + "\n"
						+ "按照Fair竞争的原则，将步骤3中的子节点（要获取锁的节点）按照节点顺序的大小做排序，取出编号最小的一个节点做为lock的owner，判断自己的节点id 是否就为owner id，如果是则返回，lock成功。如果不是则调用 exists( )监听比自己小的前一位的id，关注它锁释放的操作（也就是exist watch）。",
				"51jvm运行时数据图 GC 内存溢出 CPU高等解决办法 图 理解这个图 对理解GC 以及多线程非常有好处。 特别是锁，锁的是对象还是类 http://blog.csdn.net/loveslmy/article/details/46820929  这篇博客总结的非常好，非常喜欢前面那张图，图旁边有箭头指向，并且加了注释，非常好。 另外非常喜欢他的GC描述，讲的调理非常清晰，年轻代使用标记清除算法，因为年轻代大多数对象都是要被清理掉的，只有少量会存活下来，所以用mark-sweep GC 算法，年老代则是只有少部分对象需要被清理，所以使用标记整理算法（不会产生内存碎片）。 还有他的收集器的总结真的是绝了，GC收集器，从来没有这么清晰的明白过收集器。特别是那张图，上次在那个TCL国际E城那里面试，那个视力不好的家伙估计也 不懂这块的知识，很不耐烦的直接问，IO密集型和运算密集型分别使用什么类型的收集器，这东西是汪文敏那种家伙怎么都不会懂的，IO密集场景下用并发， 高并发嘛，运算密集型用并行，oracle里面不是用parallel来并行执行sql，加快执行时间吗。 另外就是收集器是如何在java命令中去配置的。 http://blog.csdn.net/heyutao007/article/details/38151581  这一篇也是讲的不错的 -XX：survivalRatio=8（默认值） 画个图 eden与From与To区域比例8：1：1  如果设置10 证个young generation 1200M 那分别是1000M 100M 100M GC  -XX:NewRatio 默认为4  老年代与新生代的比值为4:1  就是说老年代占整个堆内存的80%，剩下的新生代占20%，这个比值是个反的，不要被New所 误导 VM -XX:NewRatio=4（老年代与新生代的比值）、-XX:SurvivorRatio=8（eden与一个survivor的比值）参数含义 https://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html 这个里面jvm参数来历讲的比较好\n"
						+ "\n" + "查看dump文件，，用IBM Analyzer打开.hprof文件\n" + "\n"
						+ " -XX:+HeapDumpOnOutOfMemoryError 此参数是帮助生成dump文件，程序启动后直到抛出OOM异常，异常抛出后，在程序的classpath下会生成一个以.hprof结尾的文件，如 java_pid4504.hprof文件， 这就是我们需要的dump文件。这个文件在实际生产环境中比较大，，打开这个文件就可以分析java内存溢出点。 获取到这个.hprof文件之后，使用IBM的heapAnalyzer，这个IBM开发的强大的内存dump分析工具，分析我们的.hprof文件来打开分析找到可能的内存泄漏点。\n"
						+ "\n" + "打开这个.hprof文件，就可以看到这个高亮显示的部分就是可能的内存泄漏点， 打开 tree viwer 就能看到一个占用内存排序的列表。\n" + "\n"
						+ "内存溢出 两种可能 有大对象，堆内存空间不够，2.死循环，往某个容器里面拼命加东西,死锁，   3.-xmx参数设置过小\n" + "\n"
						+ "CPU高的解决办法  https://www.cnblogs.com/guoyuqiangf8/p/3545687.html 终于知道那个鼓泡那个家伙一口气念了那么多都是什么了，jinfo,jps,jstack,jstat,jmap,jconsole j后面的ipss mc  还有 JProfiler、Optimizeit Profiler 查看内存泄漏的 http://blog.csdn.net/caihaijiang/article/details/6084325",
				"52oppo问到的几个新问题： 1.线程死锁怎么解决，回答的时候有点忘了，说是避免多个线程多共享资源的争用。 2.set是怎么实现避免重复的； 3.error和exception的区别， 4.9个线程读一个线程写的安全分析。 redis锁的缺陷\n"
						+ "HashSet：HashSet的实现原理 https://www.cnblogs.com/xwdreamer/archive/2012/06/03/2532999.html  昨天在oppo卓越后海中心面试，我当时就 傻了，set是怎么保证数据不重复的，今天看了源码恍然大悟，源码set add方法添加的元素，是作为key来存放在map里面的，这样就保证了元素的唯一性， 另外因为map支持key为null，所以set也支持添加null元素。\n"
						+ "死锁产生的原因及如何避免 http://blog.csdn.net/ls5718/article/details/51896159 死锁及解决方案 http://blog.csdn.net/liujianfei526/article/details/51933162   鸵鸟方案挺有一丝，鸵鸟方案就是啥都不干\n"
						+ "https://zhidao.baidu.com/question/1893799901594511140.html  鸵鸟心态\n"
						+ "oppo复试问到的一些问题：1.客户端自动更新是怎么做的。 2.大并发场景持续优化怎么做，分库分表忘了讲，redis进一步优化，增加redis节点，用多个key解决 key过热的问题。增加tomcat配置的线程数。架构图。 3.幂等 4.分布式锁   5.多线程处理请求（接口分离，每个接口用一个独立的线程来处理）\n"
						+ "2018-02-02 16:53 几天上午通知oppo复试通过，还是那句话，级别不到位，钱不给到位，入职没意义。 所以自己的水平也要不断提高。\n"
						+ "2018-02-01 8:55 昨天oppo复试 问了service层中用到的设计模式，这个有点意思。 最王道的问题还是终极优化的问题，问得我绝望了。发现张开涛的亿级网站架构核心技术，这本书要拿出来好好的提炼提炼，这些大型互联网企业最终的技术都是 统一的。高可用高并发系统 提炼总结一下开涛的京东经验，虽然没有阿里牛逼，但是还是可以的。 这好比是登顶珠穆朗玛，你要找一个盘山公路上去，如果没有路，你要先自己给自己铺路。这条盘山公路最终是给自己走的，而且是又双叒叕来回走的，所以务必修得好。修的像走电影院的地毯上一样。 扩容，一是集群节点可以扩，二是DB分库分表可以扩容，三是redis可以扩容，为了key过热，可以用多个key。单独节点里面tomcat的线程数可以进一步增加。",
				"53JVM运行时数据区\n"
						+ "--   纯文字描述，等同于面试时候的演讲 讲述JVM运行时数据区不同于 讲述JVM内存模型   这是两个不同的概念 ----http://blog.csdn.net/tjiyu/article/details/53915869---\n"
						+ "--------正式开始，清一清嗓子 首先说，jvm运行时的数据区分为两块，一个是所有线程公用的区域：1.堆内存，存放所有Java 对象，用jvm垃圾回收机制来管理。 2.方法区，存储包括jvm加载的每一个 类的信息，包括常量池，成员变量和方法。 另一个是每一个栈所私有的区域：   本地方法栈，虚拟机栈和程序计数器。这些是每个线程私有的，不会与其他线程共有。在新建线程时创建，在线程结束时销毁。\n"
						+ "先说说这个程序计数器 我们的jvm内部在执行多线程任务的时候，他用的是CPU时间片的轮流分配来决定哪一个线程作为当前线程来执行程序代码。 这个计数器就是骑存储该线程需要执行的字节码指令的地址，到了该线程来执行的时候，JVM字节码解释器会来取该程序计数器的字节码指令的地址来寻找要执行的程序。\n"
						+ "再说Java虚拟机栈\n"
						+ "他是为方法调用而服务的，存放的是线程的栈帧，当方法调用的时候，栈帧被创建，当方法调用结束的时候，栈帧被销毁， 栈帧是方法运行时的基础结构，它里面存放的是方法执行时候逇一些信息，局部变量表、操作数栈、动态连接、方法出口。\n"
						+ "HotSpot VM通过\"-Xss\"参数设置JVM栈内存空间大小\n"
						+ "本地方法栈    想起了昨天看Object类的时候，看到了他下面的一个本地方法hashCode，没有代码实现。 为什么会这样呢，因为这个本地方法指的是 用Java语言之外的语言来编写的方法，所以不需要用Java代码来实现。\n"
						+ "谈堆内存的话，那就是JVM内存模型的问题了，那是一个更具体的话题。\n" + "-----以上\n",
				"54请讲讲ThreadLocal下面的四个方法. ThreadLocal用法详解和原理\n" + "\n" + "一、用法\n"
						+ "ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量。\n"
						+ "\n" + "1、ThreadLocal.get: 获取ThreadLocal中当前线程共享变量的值。\n"
						+ "2、ThreadLocal.set: 设置ThreadLocal中当前线程共享变量的值。\n"
						+ "3、ThreadLocal.remove: 移除ThreadLocal中当前线程共享变量的值。\n"
						+ "4、ThreadLocal.initialValue: ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值。",
				"55mybatis中的namespace有什么作用你这样来记忆，mapper文件跟dao接口是一一对应的，dao接口完整名对应namespace，dao的方法名对应sql中的id，就行了\n" + "\n"
						+ "mybatis 中mapper 的namespace有什么用 5 我自己定义的一个sql语句配置 可是我不懂这里的namespace有什么用 SELECT t.* FROM T_article t WHERE t.flag = '1' ORDER BY t.createtime D... 展开 lanmo970 | 浏览 23220 次 推荐于2016-03-01 17:04:57 最佳答案 楼主： 在mybatis中，映射文件中的namespace是用于绑定Dao接口的，即面向接口编程。 当你的namespace绑定接口后，你可以不用写接口实现类，mybatis会通过该绑定自动 帮你找到对应要执行的SQL语句，如下： 假设定义了IArticeDAO接口 public interface IArticleDAO { List selectAllArticle(); } 对于映射文件如下： SELECT t.* FROM T_article t WHERE t.flag = '1' ORDER BY t.createtime DESC 请注意接口中的方法与映射文件中的SQL语句的ID一一对应 。 则在代码中可以直接使用IArticeDAO面向接口编程而不需要再编写实现类。 有问题欢迎提问，满意请采纳，谢谢！",
				"56linux内核参数优化* 修改最大文件句柄打开数&最大可开进程数调优\n" + "\n" + "修改最大文件句柄打开数&最大可开进程数调优\n" + "    ulimit –u 65535\n"
						+ "    ulimit –n 65535\n" + "查看： ulimit -a\n" + "\n"
						+ "lsof -n |awk '{print $2}'|sort|uniq -c |sort -nr|more   这个可以看最大可打开文件句柄数的统计结果。\n"
						+ "然后用 ps -ef|grep pid_num  查看到底是哪一个进程占用了这么多的打开文件数\n" + "\n" + "\n"
						+ "聊聊jps这个命令，这个命令可以查看当前活着的java进程，这个就有意思了，springboot进程是可以发现的。 以前都用\n"
						+ "ps -ef|grep member.jar  这个方式，太low 了，直接用jps -l,真好用。\n"
						+ "---------------------------------------------------------------------------\n"
						+ "注：jps命令有个地方很不好，似乎只能显示当前用户的java进程，要显示其他用户的还是只能用unix/linux的ps命令。\n" + "\n" + "\n"
						+ "TCP传输参数调优\n" + "执行命令(root用户) ：\n" + "         vi /etc/sysctl.conf \n" + "   修改内容：\n"
						+ "         net.ipv4.tcp_max_tw_buckets = 6000\n"
						+ "              net.ipv4.ip_local_port_range = 1024 65000\n"
						+ "              net.ipv4.tcp_tw_recycle = 1\n" + "              net.ipv4.tcp_tw_reuse = 1\n"
						+ "              net.ipv4.tcp_syncookies = 1\n" + "              net.core.somaxconn = 262144\n"
						+ "          以上解决，高并发下linux访问流量上不去的问题   \n",
				"57详细描述：\n" + "1.负责系统技术平台和产品整体的架构设计、规划和实施。技术架构研究选型,功能模块设计,数据库设计等；\n"
						+ "2.负责公司项目的重构规划和实施,采用分而治之的思想对整个系统基于springboot+dubbo(2.5.4/6/8)+zookeeper+Redis+rabbitMQ+druid 进行模块化,服务化。对于公共部分进行抽象隔离组件化。\n"
						+ "3.负责公司基础设施的建设和完善,包括团队协作和知识共享的知识协同管理平台 的搭建和整个公司的推行;基于项目对象模型的项目构建管理平台maven+nexus 的搭建、培训和推行实施;\n"
						+ "4.基于分布式源代码管理平台gitlab的搭建、培训和推行;基于自动化持续集成平台jenkins 的搭建、培训和推行实施。\n"
						+ "5.负责公司基础规范的制定,包括java 编码规范,关系型数据库设计使用规范和非关系型数据库设计使用规范等,并进行统一的培训和落实。 对于代码规范进行监控,完善代码持续集成和审核机制,基于maven集成findbug,pmd,checkstyle 等代码静态检测工具并定期组织代码检视；\n"
						+ "6.负责系统性能调优，基于Linux，JVM性能参数，各中间件，DataBase性能调试，代码重构及性能优化，压力测试脚本编写，压测报告输出。\n"
						+ "7.研究行业最新产品的技术发展方向,引进新的技术理念和技术模型,设计和落实高效稳定的互联网大型分布式,高可用, 高可靠,高并发的系统平台架构。",
				"58详细描述：\n" + "项目介绍：\n" + "该项目为生鲜电商平台，O2O模式，包括前端app电商以及后台运营管理系统。\n" + "工作职责：\n"
						+ "1.完成项目从立项，需求分析，框架选型，架构设计，后端数据表结构设计，接口规范设计和编码实施，测试用例库建立，测试实施及生产灰度发布与验证，交付使用，以及后期维护与新需求的实现；\n"
						+ "2.使用SpringMVC+hessian+Mybatis+Oracle+Tomca+ActiveMQ/kafka搭建电商平台的移动端和后台运营端的系统平台；\n"
						+ "3.前端采用android、ios嵌入html5的方式，后台使用JSP,easyUI,Jquery,Ajax等工具;\n"
						+ "3.承担架构设计，核心编码工作以及部分调优任务，如SQL优化，多线程使用，代码性能优化等等。",
				"59详细描述：\n" + "项目介绍：\n" + "第三方支付平台项目。\n" + "工作职责：\n"
						+ "1.银行卡绑定业务以及营销系统服务端接收接收移动客户端请求的接口文档设计和编写，业务需求分析，代码设计和编码实现。还包括数据的签名验证，业务数据解密，生成签名等报文接收，发送，后台核心模块调用，异常处理以及非业务数据持久化；\n"
						+ "2.移动客户端html5网页制作及各手机型号，浏览器的内容样式兼容性匹配；\n"
						+ "3.消息平台的搭建，目前使用极光推送以及其他第三方推送平台搭建本APP平台的推送模块，实现富客户端，多媒体推送；\n"
						+ "4.主要使用SpringMVC，myBatis,hessian，activeMQ，html5等工具。",
				"60详细描述：\n" + "项目介绍：\n" + "加拿大BMO银行信用卡运营管理，该项目完成该银行信用卡消费记录测试数据的导入，信用卡消费记录的查询。\n" + "工作职责：\n"
						+ "1.运用SAX解析XML数据文档，完成测试数据导入，并验证导入数据正确性；\n"
						+ "2.运用Java Web（Hibernate，Struts，Spring）技术完成信用卡消费记录的查询；\n"
						+ "3.完成优化查询性能，改善java，SQL代码，存储过程等提升数据库性能的工作。\n" + "\n" + "项目介绍：\n"
						+ "香港汇丰银行理财产品开发，完成汇丰银行理财产品的第一期开发，实现客户投资前向客户演示保险，基金等理财产品的购买，收益获取的演示功能。\n" + "工作职责：\n"
						+ "主要负责客户需求分析，代码实现以及单元测试，文档编写，完成理财演示功能中收益预估。主要技术：Hibernate3，struts2，Spring3，webService。",
				"61 jps -l 好东西",
				"62  Java多线程：线程安全和非线程安全的集合对象一、概念：\n"
						+ "    线程安全：就是当多线程访问时，采用了加锁的机制；即当一个线程访问该类的某个数据时，会对这个数据进行保护，其他线程不能对其访问，直到该线程读取完之后，其他线程才可以使用。防止出现数据不一致或者数据被污染的情况。\n"
						+ "    线程不安全：就是不提供数据访问时的数据保护，多个线程能够同时操作某个数据，从而出现数据不一致或者数据污染的情况。\n"
						+ "    对于线程不安全的问题，一般会使用synchronized关键字加锁同步控制。\n"
						+ "    线程安全 工作原理： jvm中有一个main memory对象，每一个线程也有自己的working memory，一个线程对于一个变量variable进行操作的时候， 都需要在自己的working memory里创建一个copy,操作完之后再写入main memory。\n"
						+ "    当多个线程操作同一个变量variable，就可能出现不可预知的结果。\n"
						+ "    而用synchronized的关键是建立一个监控monitor，这个monitor可以是要修改的变量，也可以是其他自己认为合适的对象(方法)，然后通过给这个monitor加锁来实现线程安全，每个线程在获得这个锁之后，要执行完加载load到working memory 到 use && 指派assign 到 存储store 再到 main memory的过程。才会释放它得到的锁。这样就实现了所谓的线程安全。\n"
						+ "二、线程安全(Thread-safe)的集合对象：\n" + "    Vector 线程安全：\n" + "    HashTable 线程安全：\n"
						+ "    StringBuffer 线程安全：\n" + "三、非线程安全的集合对象：\n" + "    ArrayList ：\n" + "    LinkedList：\n"
						+ "    HashMap：\n" + "    HashSet：\n" + "    TreeMap：\n" + "    TreeSet：\n"
						+ "    StringBulider：\n" + "https://blog.csdn.net/u011389474/article/details/54602812",
				"63String s1=”ab”, String s2=”a”+”b”, String s3=”a”, String s4=”b”, s5=s3+s4请问s5==s2返回什么？\n"
						+ "返回false。在编译过程中，编译器会将s2直接优化为”ab”，会将其放置在常量池当中，s5则是被创建在堆区，相当于s5=new String(“ab”);",
				"64java中==和eqauls()的区别,equals()和`hashcode的区别\n"
						+ "==是运算符，用于比较两个变量是否相等，而equals是Object类的方法，用于比较两个对象是否相等。默认Object类的equals方法是比较两个对象的地址，此时和==的结果一样。换句话说：基本类型比较用==，比较的是他们的值。默认下，对象用==比较时，比较的是内存地址，如果需要比较对象内容，需要重写equal方法。",
				"65equals()和hashcode()的联系\n" + "\n"
						+ "hashCode()是Object类的一个方法，返回一个哈希值。如果两个对象根据equal()方法比较相等，那么调用这两个对象中任意一个对象的hashCode()方法必须产生相同的哈希值。\n"
						+ "如果两个对象根据eqaul()方法比较不相等，那么产生的哈希值不一定相等(碰撞的情况下还是会相等的。)",
				"66a.hashCode()有什么用?与a.equals(b)有什么关系\n" + "\n"
						+ "hashCode() 方法是相应对象整型的 hash 值。它常用于基于 hash 的集合类，如 Hashtable、HashMap、LinkedHashMap等等。它与 equals() 方法关系特别紧密。根据 Java 规范，使用 equal() 方法来判断两个相等的对象，必须具有相同的 hashcode。\n"
						+ "将对象放入到集合中时，首先判断要放入对象的hashcode是否已经在集合中存在，不存在则直接放入集合。如果hashcode相等，然后通过equal()方法判断要放入对象与集合中的任意对象是否相等：如果equal()判断不相等，直接将该元素放入集合中，否则不放入。",
				"67有没有可能两个不相等的对象有相同的hashcode\n"
						+ "有可能，两个不相等的对象可能会有相同的 hashcode 值，这就是为什么在 hashmap 中会有冲突。如果两个对象相等，必须有相同的hashcode 值，反之不成立。",
				"68可以在hashcode中使用随机数字吗?\n" + "\n" + "不行，因为同一对象的 hashcode 值必须是相同的",
				"69   a==b与a.equals(b)有什么区别\n" + "\n"
						+ "如果a 和b 都是对象，则 a==b 是比较两个对象的引用，只有当 a 和 b 指向的是堆中的同一个对象才会返回 true，而 a.equals(b) 是进行逻辑比较，所以通常需要重写该方法来提供逻辑一致性的比较。例如，String 类重写 equals() 方法，所以可以用于两个不同对象，但是包含的字母相同的比较。",
				"70 String, StringBuffer和StringBuilder区别\n" + "\n" + "String是字符串常量，final修饰：StringBuffer字符串变量(线程安全)；\n"
						+ "StringBuilder 字符串变量(线程不安全)。\n" + "String和StringBuffer\n" + "\n"
						+ "String和StringBuffer主要区别是性能：String是不可变对象，每次对String类型进行操作都等同于产生了一个新的String对象，然后指向新的String对象。所以尽量不在对String进行大量的拼接操作，否则会产生很多临时对象，导致GC开始工作，影响系统性能。\n"
						+ "\n" + "StringBuffer是对对象本身操作，而不是产生新的对象，因此在有大量拼接的情况下，我们建议使用StringBuffer。\n" + "\n"
						+ "但是需要注意现在JVM会对String拼接做一定的优化：\n"
						+ "String s=“This is only ”+”simple”+”test”会被虚拟机直接优化成String s=“This is only simple test”，此时就不存在拼接过程。",
				"71    StringBuffer和StringBuilder\n" + "\n"
						+ "StringBuffer是线程安全的可变字符串，其内部实现是可变数组。StringBuilder是jdk 1.5新增的，其功能和StringBuffer类似，但是非线程安全。因此，在没有多线程问题的前提下，使用StringBuilder会取得更好的性能。",
				"72  java当中使用什么类型表示价格比较好?\n" + "\n" + "如果不是特别关心内存和性能的话，使用BigDecimal，否则使用预定义精度的 double 类型。",
				"73关于垃圾回收\n" + "你知道哪些垃圾回收算法?\n" + "\n" + "垃圾回收从理论上非常容易理解,具体的方法有以下几种: \n" + "1. 标记-清除 \n" + "2. 标记-复制 \n"
						+ "3. 标记-整理 \n" + "4. 分代回收 \n" + "更详细的内容参见深入理解垃圾回收算法：\n" + "\n"
						+ "http://blog.csdn.net/dd864140130/article/details/50084471\n" + "如何判断一个对象是否应该被回收\n" + "\n"
						+ "这就是所谓的对象存活性判断，常用的方法有两种：1.引用计数法;　2.对象可达性分析。由于引用计数法存在互相引用导致无法进行GC的问题，所以目前JVM虚拟机多使用对象可达性分析算法。\n"
						+ "简单的解释一下垃圾回收\n" + "\n"
						+ "Java 垃圾回收机制最基本的做法是分代回收。内存中的区域被划分成不同的世代，对象根据其存活的时间被保存在对应世代的区域中。一般的实现是划分成3个世代：年轻、年老和永久。内存的分配是发生在年轻世代中的。当一个对象存活时间足够长的时候，它就会被复制到年老世代中。对于不同的世代可以使用不同的垃圾回收算法。进行世代划分的出发点是对应用中对象存活时间进行研究之后得出的统计规律。一般来说，一个应用中的大部分对象的存活时间都很短。比如局部变量的存活时间就只在方法的执行过程中。基于这一点，对于年轻世代的垃圾回收算法就可以很有针对性。\n"
						+ "调用System.gc()会发生什么?\n" + "\n" + "通知GC开始工作，但是GC真正开始的时间不确定。",
				"74  链接给到这里吧  https://blog.csdn.net/linzhiqiang0316/article/details/80473906",
				"75    策略模式  今天看这个类图   感觉无比简单啊  https://www.cnblogs.com/java-my-life/archive/2012/05/10/2491891.html",
				"76 String.format(\" %s ￥ %s\", tornCake.getDesc(), tornCake.price()  这也是一种拼接字符串的方式  JAVA字符串格式化-String.format()和MessageFormat的使用   https://blog.csdn.net/candyguy242/article/details/80782244",
				"77 https://www.cnblogs.com/stonefeng/p/5693560.html   观察者模式",
				"78  https://www.cnblogs.com/stonefeng/p/5679638.html   装饰器模式",
				"79  https://blog.csdn.net/yeguxin/article/details/77337838   这个把桥接模式讲得通俗易懂",
				"80  https://www.cnblogs.com/V1haoge/p/6542449.html   这个把享元模式 讲得够简单了",
				"81 https://www.cnblogs.com/ysw-go/p/5384516.html  迭代器模式",
				"82  https://www.cnblogs.com/taosim/articles/4238674.html   redis集群 使用一致性哈希算法",
				"83  https://www.cnblogs.com/felixzh/p/5869212.html                       Zookeeper的功能以及工作原理",
				"84  https://www.cnblogs.com/yjd_hycf_space/p/7730690.html     Linux常用命令大全（非常全！！！）\n"
						+ "最近都在和Linux打交道，感觉还不错。我觉得Linux相比windows比较麻烦的就是很多东西都要用命令来控制，当然，这也是很多人喜欢linux的原因，比较短小但却功能强大。我将我了解到的命令列举一下，仅供大家参考：\n"
						+ "系统信息 \n" + "arch 显示机器的处理器架构(1) \n" + "uname -m 显示机器的处理器架构(2) \n" + "uname -r 显示正在使用的内核版本 \n"
						+ "dmidecode -q 显示硬件系统部件 - (SMBIOS / DMI) \n" + "hdparm -i /dev/hda 罗列一个磁盘的架构特性 \n"
						+ "hdparm -tT /dev/sda 在磁盘上执行测试性读取操作 \n" + "cat /proc/cpuinfo 显示CPU info的信息 \n"
						+ "cat /proc/interrupts 显示中断 \n" + "cat /proc/meminfo 校验内存使用 \n"
						+ "cat /proc/swaps 显示哪些swap被使用 \n" + "cat /proc/version 显示内核的版本 \n"
						+ "cat /proc/net/dev 显示网络适配器及统计 \n" + "cat /proc/mounts 显示已加载的文件系统 \n"
						+ "lspci -tv 罗列 PCI 设备 \n" + "lsusb -tv 显示 USB 设备 \n" + "date 显示系统日期 \n"
						+ "cal 2007 显示2007年的日历表 \n" + "date 041217002007.00 设置日期和时间 - 月日时分年.秒 \n"
						+ "clock -w 将时间修改保存到 BIOS \n" + "\n" + "\n" + "\n" + "关机 (系统的关机、重启以及登出 ) \n"
						+ "shutdown -h now 关闭系统(1) \n" + "init 0 关闭系统(2) \n" + "telinit 0 关闭系统(3) \n"
						+ "shutdown -h hours:minutes & 按预定时间关闭系统 \n" + "shutdown -c 取消按预定时间关闭系统 \n"
						+ "shutdown -r now 重启(1) \n" + "reboot 重启(2) \n" + "logout 注销 ",
				"85  https://blog.csdn.net/tanga842428/article/details/73822905    eureka简介与原理    ",
				"86 java 方法 methodA(int ... ids)\n" + "2017年07月26日 18:23:55 wide288 阅读数：704更多所属专栏： Java 编程技巧\n"
						+ "版权声明：本文为博主原创文章，未经博主允许不得转载。 https://blog.csdn.net/wide288/article/details/76158798\n"
						+ "ids 就是数组 int 类型的。\n" + "int ... ids 等价于 int[] ids",
				"87     @RequestMapping(value = \"/showCodes\", method = RequestMethod.POST)\n" + "    @ResponseBody\n"
						+ "    public Object showCodes(HttpServletRequest req, HttpServletResponse resp) throws IOException {\n"
						+ "        Map<String, String> map = new HashMap<String, String>();\n"
						+ "        map.put(\"code1\", apiCode900500RetNo);\n"
						+ "        map.put(\"code2\", apiCode900510RetNo);\n"
						+ "        // OutputStream out = resp.getOutputStream();\n"
						+ "        // out.write((\"{\\\"code2\\\":\" + apiCode900510RetNo + \",\\\"code1\\\":\" + apiCode900500RetNo + \"}\").getBytes());\n"
						+ "        // out.flush();\n" + "        // out.close();\n" + "        return map;\n"
						+ "    }    这两种传值给前端的方法是等效的",
				"88 能不能用一句话总结HTTPS？\n" + "答案是不能，因为HTTPS本身实在太复杂。但是我还是尝试使用一段话来总结HTTPS:\n"
						+ "HTTPS要使客户端与服务器端的通信过程得到安全保证，必须使用的对称加密算法，但是协商对称加密算法的过程，需要使用非对称加密算法来保证安全，然而直接使用非对称加密的过程本身也不安全，会有中间人篡改公钥的可能性，所以客户端与服务器不直接使用公钥，而是使用数字证书签发机构颁发的证书来保证非对称加密过程本身的安全。这样通过这些机制协商出一个对称加密算法，就此双方使用该算法进行加密解密。从而解决了客户端与服务器端之间的通信安全问题。\n"
						+ "\n" + "好长的一段话。",
				"89    排序算法  时间空间复杂度  https://blog.csdn.net/yushiyi6453/article/details/76407640 ",
				"90 o(1), o(n), o(logn), o(nlogn)\n"
						+ "2017年07月15日 21:32:09 Mars93 阅读数：20454 标签： o-1-o-n 更多个人分类： tomcat\n"
						+ "在描述算法复杂度时,经常用到o(1), o(n), o(logn), o(nlogn)来表示对应算法的时间复杂度, 这里进行归纳一下它们代表的含义: \n"
						+ "这是算法的时空复杂度的表示。不仅仅用于表示时间复杂度，也用于表示空间复杂度。 \n"
						+ "O后面的括号中有一个函数，指明某个算法的耗时/耗空间与数据增长量之间的关系。其中的n代表输入数据的量。 \n"
						+ "比如时间复杂度为O(n)，就代表数据量增大几倍，耗时也增大几倍。比如常见的遍历算法。 \n"
						+ "再比如时间复杂度O(n^2)，就代表数据量增大n倍时，耗时增大n的平方倍，这是比线性更高的时间复杂度。比如冒泡排序，就是典型的O(n^2)的算法，对n个数排序，需要扫描n×n次。 \n"
						+ "再比如O(logn)，当数据增大n倍时，耗时增大logn倍（这里的log是以2为底的，比如，当数据增大256倍时，耗时只增大8倍，是比线性还要低的时间复杂度）。二分查找就是O(logn)的算法，每找一次排除一半的可能，256个数据中查找只要找8次就可以找到目标。 \n"
						+ "O(nlogn)同理，就是n乘以logn，当数据增大256倍时，耗时增大256*8=2048倍。这个复杂度高于线性低于平方。归并排序就是O(nlogn)的时间复杂度。 \n"
						+ "O(1)就是最低的时空复杂度了，也就是耗时/耗空间与输入数据大小无关，无论输入数据增大多少倍，耗时/耗空间都不变。 哈希算法就是典型的O(1)时间复杂度，无论数据规模多大，都可以在一次计算后找到目标（不考虑冲突的话）  ",
				"90 LinkedList的是单向链表还是双向?\n" + "双向循环列表，具体实现自行查阅源码。\n" + "TreeMap是实现原理\n" + "采用红黑树实现，具体实现自行查阅源码。\n"
						+ "遍历ArrayList时如何正确移除一个元素\n"
						+ "该问题的关键在于面试者使用的是 ArrayList 的 remove() 还是 Iterator 的 remove()方法。这有一段示例代码，是使用正确的方式来实现在遍历的过程中移除元素，而不会出现 ConcurrentModificationException 异常的示例代码。\n"
						+ "什么是ArrayMap?它和HashMap有什么区别?\n" + "ArrayMap是Android SDK中提供的，非Android开发者可以略过。\n"
						+ "ArrayMap是用两个数组来模拟map，更少的内存占用空间,更高的效率。\n"
						+ "具体参考这篇文章：ArrayMap VS HashMap：http://lvable.com/?p=217%5D\n" + "HashMap的实现原理\n"
						+ "1. HashMap概述： HashMap是基于哈希表的Map接口的非同步实现。此实现提供所有可选的映射操作，并允许使用null值和null键。此类不保证映射的顺序，特别是它不保证该顺序恒久不变。 \n"
						+ "2. HashMap的数据结构： 在java编程语言中，最基本的结构就是两种，一个是数组，另外一个是模拟指针（引用），所有的数据结构都可以用这两个基本结构来构造的，HashMap也不例外。HashMap实际上是一个“链表散列”的数据结构，即数组和链表的结合体。\n"
						+ "当我们往Hashmap中put元素时,首先根据key的hashcode重新计算hash值,根绝hash值得到这个元素在数组中的位置(下标),如果该数组在该位置上已经存放了其他元素,那么在这个位置上的元素将以链表的形式存放,新加入的放在链头,最先加入的放入链尾.如果数组中该位置没有元素,就直接将该元素放到数组的该位置上.\n"
						+ "需要注意Jdk 1.8中对HashMap的实现做了优化,当链表中的节点数据超过八个之后,该链表会转为红黑树来提高查询效率,从原来的O(n)到O(logn)",
				"91 java中的++操作符线程安全么?\n" + "不是线程安全的操作。它涉及到多个指令，如读取变量值，增加，然后存储回内存，这个过程可能会出现多个线程交差。\n"
						+ "你有哪些多线程开发良好的实践?\n" + "给线程命名\n" + "最小化同步范围\n" + "优先使用volatile\n"
						+ "尽可能使用更高层次的并发工具而非wait和notify()来实现线程通信,如BlockingQueue,Semeaphore\n" + "优先使用并发容器而非同步容器.\n"
						+ "考虑使用线程池",
				"92 CyclicBarrier和CountDownLatch区别\n" + "这两个类非常类似，都在java.util.concurrent下，都可以用来表示代码运行到某个点上，二者的区别在于：\n"
						+ "CyclicBarrier的某个线程运行到某个点上之后，该线程即停止运行，直到所有的线程都到达了这个点，所有线程才重新运行；CountDownLatch则不是，某线程运行到某个点上之后，只是给某个数值-1而已，该线程继续运行。\n"
						+ "CyclicBarrier只能唤起一个任务，CountDownLatch可以唤起多个任务\n"
						+ "CyclicBarrier可重用，CountDownLatch不可重用，计数值为0该CountDownLatch就不可再用了。",
				"93 ConcurrentHashMap的并发度是什么?\n"
						+ "ConcurrentHashMap的并发度就是segment的大小，默认为16，这意味着最多同时可以有16条线程操作ConcurrentHashMap，这也是ConcurrentHashMap对Hashtable的最大优势，任何情况下，Hashtable能同时有两条线程获取Hashtable中的数据吗？\n"
						+ "ConcurrentHashMap的工作原理\n" + "ConcurrentHashMap在jdk 1.6和jdk 1.8实现原理是不同的。\n" + "jdk 1.6:\n"
						+ "ConcurrentHashMap是线程安全的，但是与Hashtablea相比，实现线程安全的方式不同。Hashtable是通过对hash表结构进行锁定，是阻塞式的，当一个线程占有这个锁时，其他线程必须阻塞等待其释放锁。ConcurrentHashMap是采用分离锁的方式，它并没有对整个hash表进行锁定，而是局部锁定，也就是说当一个线程占有这个局部锁时，不影响其他线程对hash表其他地方的访问。 \n"
						+ "具体实现:ConcurrentHashMap内部有一个Segment.\n" + "jdk 1.8\n"
						+ "在jdk 8中，ConcurrentHashMap不再使用Segment分离锁，而是采用一种乐观锁CAS算法来实现同步问题，但其底层还是“数组+链表->红黑树”的实现。",
				"94 什么是乐观锁和悲观锁\n"
						+ "乐观锁：乐观锁认为竞争不总是会发生，因此它不需要持有锁，将比较-替换这两个动作作为一个原子操作尝试去修改内存中的变量，如果失败则表示发生冲突，那么就应该有相应的重试逻辑。\n"
						+ "悲观锁：悲观锁认为竞争总是会发生，因此每次对某资源进行操作时，都会持有一个独占的锁，就像synchronized，不管三七二十一，直接上了锁就操作资源了。"
						+ "乐观锁是可以让你读，但是你先取版本号，update的时候版本号对不上,update语句后面的条件不满足，不让你update,这就是乐观锁；悲观锁，让你先取锁，拿到锁再来读，没拿到，读都不让你读，更不用说写了",
				"95 https://blog.csdn.net/u013305783/article/details/78563389   SpringBoot使用PageHelper进行分页   // 使用分页插件,核心代码就这一行\n"
						+ "        PageHelper.startPage(page, perCount);\n"
						+ "        List<AppNoticeDO> appNoticeDOList = appNoticeMapper.getAppNoticeDOList(appNoticeDTO);\n"
						+ "        return new PageInfo<>(appNoticeDOList);",
				"96  【深圳】【今日头条深圳研发中心】这真的是一封很认真的招聘贴~~~\n" + "liuyaqiu_150 · 3月之前 · 1595 次点击\n"
						+ "我们是谁？ Developing a company as a product 是我们的理念 公司是我们团队的产品，员工是我们的用户 我们的工作是打造强大的效率工具和系统，支撑和推动公司更快更好地发展 业务范围涉及企业沟通工具、在线文档、共享日历、人力资源系统等 未来我们会推向市场，帮助更多优秀企业成长 甚至推动全球企业办公模式的变革\n"
						+ "我们希望你来， 但我们希望吸引你来的不是免费的咖啡、漂亮的办公室和精美的下午茶 我们希望你和我们一样，最看重的是：『和优秀的人，做有挑战的事』 我们需要一群有才华、有志向的产品、技术、设计人才 能够像今日头条成立之初一样，从零开始打造一个团队\n"
						+ "目前效率工程团队已经有数百人的规模 我们的管理理念是 【和优秀的人做有挑战的事，保持简单灵活的机制。提高透明度和信息分享效率，基于愿景目标自我驱动】 我们需要大量的leader、前后端高级工程师、客户端工程师、产品经理和UI设计师 如果你认同我们的价值观 如果你也期望加入这个推动字节跳动高速发展的团队 请和我们联系：liuyaqiu@bytedance.com\n"
						+ "需求岗位： 服务端 /后台开发工程师 前端开发工程师 移动端（ Android 、 iOS ）开发工程师 产品经理 UI设计师\n" + "工作经验： 2 年以上\n"
						+ "工作地点：深圳市南山区南海大道 2163 号来福士广场 15 层\n" + "简历投递邮箱：liuyaqiu@bytedance.com",
				"97单例模式三种模式，饿汉、饱汉、双重锁模式，实例及优劣详解   https://blog.csdn.net/zhangliangzi/article/details/52438401\n" + " ",
				"98 面向对象三大特性 六大原则  https://www.cnblogs.com/xijie/p/6068786.html ",
				"99 https://www.cnblogs.com/butterfly100/p/9034281.html   分库分表思路",
				"100  浅谈我对DDD领域驱动设计的理解  https://blog.csdn.net/heweimingming/article/details/78661540" + new Date(),
				"101  Condition有点意思\n" + "Condition con1 = new Condition.newCondition();\n"
						+ "con1.await();   //让当前线程在这里wait();\n"
						+ "con1.signal();  //唤醒一个被con1.await()的线程，继续执行后面的代码   \n"
						+ "我很想写一个demo，按顺序让线程1，2，3分别执行1，2，3圈，循环往复，一个线程任务不能被其他线程断，可以用重入锁。或者synchronized" + new Date(),
				"102 JAVA并行框架学习之ForkJoin\\n\" + \"https://www.cnblogs.com/jiyuqi/p/4547082.html\" " + new Date(),
				"103  package concurrent;\n" + "public class JoinDemo implements Runnable {\n"
						+ "	public void run() {\n" + "		for (int i = 0; i < 100; i++) {\n" + "			try {\n"
						+ "				Thread.sleep(1);\n" + "			} catch (InterruptedException e) {\n"
						+ "				e.printStackTrace();\n" + "			}\n"
						+ "			System.out.println(\"join \"+i);\n" + "		}\n" + "	}\n"
						+ "	public static void main(String[] args) throws InterruptedException {\n"
						+ "		Thread t = new Thread(new JoinDemo());\n" + "		t.start();\n"
						+ "		for (int i = 0; i < 100; i++) {\n" + "			if(i==50){\n"
						+ "				t.join();\n" + "			}\n"
						+ "			System.out.println(\"main \"+i);\n" + "		}\n"
						+ "	}  你注意这里t.join()方法的出现一定是在t线程之外的另一个线程的代码中，就是让这个线程等一等，跟join的含义有什么区别，你可以把它理解为嫁接中的结合， 而且优先级比那个线程t.join()之后的代码执行优先级还要高。这个很有意思。 让我想起来了，那年过年在曹武大幺家里吃饭，张飞几个小家伙，菜都抢光。呵呵。 源码里面的注释 比较搞笑，一直要等到这个客人死亡，才能执行自己的代码，这么悲催。 /** * Waits for this thread to die. *     跟人家抢不赢了，就找个人去人家家里去说一声，等我执行完了，你再执行。 main 0 join 0 main 1 main 2 main 3 main 4 main 5 main 6 main 7 main 8 main 9 join 1 main 10 join 2 main 11 main 12 join 3 main 13 main 14 main 15 main 16 main 17 main 18 main 19 main 20 main 21 join 4 main 22 join 5 main 23 join 6 main 24 join 7 main 25 join 8 main 26 join 9 main 27 join 10 main 28 join 11 main 29 join 12 main 30 join 13 main 31 join 14 main 32 join 15 main 33 join 16 main 34 join 17 main 35 join 18 main 36 join 19 main 37 join 20 join 21 join 22 main 38 join 23 join 24 join 25 join 26 join 27 join 28 main 39 main 40 main 41 main 42 main 43 join 29 main 44 join 30 main 45 join 31 join 32 main 46 join 33 join 34 join 35 join 36 join 37 join 38 join 39 main 47 join 40 join 41 join 42 join 43 join 44 join 45 join 46 join 47 join 48 join 49 main 48 join 50 main 49 join 51 join 52 join 53 join 54 join 55 join 56 join 57 join 58 join 59 join 60 join 61 join 62 join 63 join 64 join 65 join 66 join 67 join 68 join 69 join 70 join 71 join 72 join 73 join 74 join 75 join 76 join 77 join 78 join 79 join 80 join 81 join 82 join 83 join 84 join 85 join 86 join 87 join 88 join 89 join 90 join 91 join 92 join 93 join 94 join 95 join 96 join 97 join 98 join 99 main 50 main 51 main 52 main 53 main 54 main 55 main 56 main 57 main 58 main 59 main 60 main 61 main 62 main 63 main 64 main 65 main 66 main 67 main 68 main 69 main 70 main 71 main 72 main 73 main 74 main 75 main 76 main 77 main 78 main 79 main 80 main 81 main 82 main 83 main 84 main 85 main 86 main 87 main 88 main 89 main 90 main 91 main 92 main 93 main 94 main 95 main 96 main 97 main 98 main 99"
						+ new Date(),
				"104 Synchronized\n"
						+ "这篇文章讲的特别好，还特别讲了为什么 “synchronized加持的方法或者代码块有可能多个线程可以同时执行，在锁对象的时候，而却对象非单例，而且多个线程并不是\n"
						+ "公用一个实例的时候，其实这时候多个线程之间没有竞争关系的，当然可以多个线程同时执行这段代码块啊”\n" + "http://www.importnew.com/20444.html"
						+ new Date(),
				"105 import java.util.concurrent.ExecutorService;\n" + "import java.util.concurrent.Executors;\n"
						+ "public class ThreadA {\n" + "	static int i = 1;\n"
						+ "	public static void main(String[] args) {\n" + "		fixedThreadPool();\n"
						+ "		singleThreadPool();\n" + "		cachedThreadPool();\n" + "	}\n"
						+ "	public static void fixedThreadPool() {\n"
						+ "		ExecutorService es = Executors.newFixedThreadPool(3);\n"
						+ "		for (int i = 1; i <= 10; i++) {\n" + "			final int task = i ;\n"
						+ "			es.execute(new Runnable() {\n" + "				@Override\n"
						+ "				public void run() {\n"
						+ "					for (int j = 1; j <= 5; j++) { //假设j代表绕操场跑几圈\n"
						+ "						System.out.println(Thread.currentThread().getName()+\"目前正在跑第\"+task+\"个任务中的第\"+j+\"圈\");\n"
						+ "					}\n" + "				}\n" + "			});\n" + "		}\n" + "	}\n"
						+ "	public static void singleThreadPool() {\n"
						+ "		ExecutorService es = Executors.newSingleThreadExecutor();\n"
						+ "		for (int i = 1; i <= 10; i++) {\n" + "			final int task = i ;\n"
						+ "			es.execute(new Runnable() {\n" + "				@Override\n"
						+ "				public void run() {\n"
						+ "					for (int j = 1; j <= 5; j++) { //假设j代表绕操场跑几圈\n"
						+ "						System.out.println(Thread.currentThread().getName()+\"目前正在跑第\"+task+\"个任务中的第\"+j+\"圈\");\n"
						+ "					}\n" + "				}\n" + "			});\n" + "		}\n" + "	}\n"
						+ "	public static void cachedThreadPool() {\n"
						+ "		ExecutorService es = Executors.newCachedThreadPool();\n"
						+ "		for (int i = 1; i <= 10; i++) {\n" + "			final int task = i ;\n"
						+ "			es.execute(new Runnable() {\n" + "				@Override\n"
						+ "				public void run() {\n"
						+ "					for (int j = 1; j <= 5; j++) { //假设j代表绕操场跑几圈\n"
						+ "						System.out.println(Thread.currentThread().getName()+\"目前正在跑第\"+task+\"个任务中的第\"+j+\"圈\");\n"
						+ "					}\n" + "				}\n" + "			});\n" + "		}\n" + "	}\n"
						+ "}\n" + "" + new Date(),
				"106 \n" + "\n" + "这种搞法太粗犷了，每次都新建一个线程，太浪费了，迟早会崩掉的。 看ThreadB1\n" + "\n" + "public class ThreadB {\n"
						+ "	static int i = 1;\n" + "	public static void main(String[] args) {\n"
						+ "		while(true) {\n" + "		 new Thread(new Runnable() {\n" + "			@Override\n"
						+ "			public void run() {\n" + "				synchronized (ThreadA.class) {\n"
						+ "					if(i<=0) {\n" + "						try {\n"
						+ "							Thread.currentThread();\n"
						+ "							Thread.sleep(200);\n"
						+ "						} catch (InterruptedException e) {\n"
						+ "							e.printStackTrace();\n" + "						}\n"
						+ "						System.out.println(\"生产者：\"+(++i));\n" + "					}\n"
						+ "				}\n" + "			}\n" + "		}).start();\n"
						+ "		 new Thread(new Runnable() {\n" + "				@Override\n"
						+ "				public void run() {\n" + "					synchronized (ThreadA.class) {\n"
						+ "						if(i>=1) {\n" + "							try {\n"
						+ "								Thread.currentThread();\n"
						+ "								Thread.sleep(200);\n"
						+ "							} catch (InterruptedException e) {\n"
						+ "								e.printStackTrace();\n" + "							}\n"
						+ "							System.out.println(\"消费者：\"+(--i));\n" + "						}\n"
						+ "					}\n" + "				}\n" + "			}).start();\n" + "		}\n"
						+ "	}\n" + "}\n" + "\n" + "" + new Date(),
				"107  ThreadLocal钻进去看原理钻进范冰冰也就这么回事.md\n"
						+ "https://github.com/huangleisir/captain_diary/blob/master/Concurrent/ThreadLocal%E9%92%BB%E8%BF%9B%E5%8E%BB%E7%9C%8B%E5%8E%9F%E7%90%86%E9%92%BB%E8%BF%9B%E8%8C%83%E5%86%B0%E5%86%B0%E4%B9%9F%E5%B0%B1%E8%BF%99%E4%B9%88%E5%9B%9E%E4%BA%8B.md"
						+ new Date(),
				"108  java 多线程同步工具这个讲的最好\n" + "http://blog.csdn.net/hejingyuan6/article/details/47070443\n"
						+ "可惜没有代码 跟上 这是个遗憾" + new Date(),
				"109 java并发编程读后感\n"
						+ "  http://blog.csdn.net/songxinjianqwe/article/details/72789899?locationNum=15&fps=1\n"
						+ "以后还是尽量别贴url, 文字贴到这里来。" + new Date(),
				"110 java5里面的读写锁跟mysql里面的读写锁是不是异曲同工之妙\n"
						+ "mysql里面的读写锁，昨天配置这个mysql主从配置的时候，还配置了这个读写锁，先给主库上read lock,从库配置完重启后，再释放主库的锁用unlock lock\n"
						+ "\n" + "命令吧好像。\n" + "\n"
						+ "java5的读写锁的妙用，用在缓存的读写里面。是怕出现脏读，幻读。仅仅是get，set操作倒还好，就是怕前面还有if条件判断，那就有可能出现中间wait（if判断完\n"
						+ "wait（），\n" + "不能马上执行后面的语句，这就完了）" + new Date(),
				"111 java并发编程读后感\n"
						+ "http://blog.csdn.net/songxinjianqwe/article/details/72789899?locationNum=15&fps=1\n"
						+ "以后还是尽量别贴url, 文字贴到这里来。" + new Date(),
				"112 notify()和notifyAll()区别\n" + " http://blog.csdn.net/cloudeagle_bupt/article/details/21378283\n"
						+ "想想yield()方法怎么用的。感觉这方法没啥用，notify()  notifyAll()还有点意思\n" + "改造一下 更新意思" + new Date(),
				"113 \n" + "volatile关键字解析\n"
						+ "http://www.cnblogs.com/dolphin0520/p/3920373.html 这一篇对volatile的解读算挖到根了  \n"
						+ "这里面引入了intel CPU 的一个高速缓存的概念\n" + "我的理解没错，这个高速缓存是属于CPU的一部分的。\n"
						+ "https://jingyan.baidu.com/article/d169e1866d0e6d436711d87d.html\n"
						+ "它如何保证它修饰的变量在线程之间的可见性。\n" + "缓存里面用的也是Lock机制，当一个线程修改该变量时候，其他等待若要读取该变量只能等待。知道update过程结束，锁释放。\n"
						+ "那既然加了锁为什么还是不能解决线程安全性呢，因为这个锁控制的力度太细并不是在业务代码上保证原子性。\n"
						+ "比如i++,这个命令的操作是分两部完成的，先读取i的值，然后加1，可是中间有可能被另一个线程干扰。\n"
						+ "你刚读完值，别的线程已经将该值修改了，你再加1并不是在新值上面加，为什么呢，如果你重新读取这个值你可以读到update 后的值，问题是你以及你给读过了，\n"
						+ "不可能重复读第二回。\n"
						+ "再举个例子，火车票多线程出售，if(count>0){count--;} if判断跟后面的count-- 操作有可能被截胡。被干扰。count被其他线程修改了。"
						+ new Date(),
				"114  dubbo 服务端用的注解@Service  客户端用的注解 @Refrence" + new Date(), "115" + new Date(),
				"116 //重定向，不会共享request  \n" + "        //以下写法错误,该 \"/\"代表了8080端口  \n"
						+ "        //response.sendRedirect(\"/student_list.jsp\");  \n"
						+ "        response.sendRedirect(request.getContextPath() + \"/student_list.jsp\");        //转发,转发是在服务器端转发的，客户端是不知道的  \n"
						+ "request.getRequestDispatcher(\"/student_list.jsp\").forward(request, response);   重定向 前一个request携带的参数就带不过去了"
						+ new Date(),
				"117 如何保证队列中消息的顺序  用同一个队列名  也就是将消息按顺序放进同一个队列中" + new Date(),
				"118  https://news.html5.qq.com/share/768397206588282402?ch=060000&tabId=0&tagId=0&docId=768397206588282402&url=http%3A%2F%2Fkuaibao.qq.com%2Fs%2F20180106G000P100&clientWidth=360&dataSrc=&sc_id=xkeHgtC  这篇是写的不错的 volatile 关键字只能保证共享变量对各线程的可见性，也就是各个线程都能读到这个变量的最新值，但是并不能保证线程安全性，这是两个概念。需要办证安全性 一是在读写这个变量的时候加锁，各种锁都可以，另外可以用原子类。 https://www.cnblogs.com/zhengbin/p/5657707.html   这篇文章里面讲的很好，备战阿里，不错，小伙子，阿里不要你要谁\n"
						+ "\n" + "关于原子类，张孝祥讲的很好，atomic下面的原子类可以操作基本数据，数组中的基本数据，以及类中的基本数据。\n" + "\n"
						+ "atomic原子类操作类中的成员变量" + new Date(),
				"119  我记得在oppo面试的时候，问了一个这样的问题，10个写线程，只有一个读线程，那么这个时候有必要加读锁吗。 其实是要加的，加读锁是为了跟写锁互斥。\n" + "\n"
						+ "类图是这样的， ReadWriteLock接口（这个接口跟Lock接口没有半毛钱关系）下面的实现类RenentrantReadWriteLock 张孝祥：读写锁卡农控制粒度比synchronized更加细致，且他只能在代码块上加锁，也就是说她锁的是对象，不能锁类。两个线程要达到互斥的效果，必须用同一个读写锁，那么用ReentrantLock行不行，达不到读写锁这么细颗粒度的控制。\n"
						+ "\n"
						+ "对象下面的读锁和写锁。锁是要上在共享的任务实例的内部方法中的代码块上的，而不是线程代码上。 读锁与读锁不互斥，读锁与写锁互斥，写锁与读锁互斥，写锁与写锁互斥。前提是都是同一个读写锁啊。面试题用读写锁写一个缓存类。 我在总结一下：一个线程读的时候，另一个线程不能写但可以读（正是因为这个特性使得写多读少的场景下提升了性能，因为面试经常问到）， 一个线程写的时候，另一个线程也不能读，夜不能写。欧拉。 从代码语句执行的角度来讲，就是一个线程执行了读锁语句，另一个线程还能执行读锁语句，但不能执行上写锁语句以及后面的代码，一个线程执行了上写锁语句， 另一个线程既不能执行上读锁语句以及后面的语句，也不能执行上写锁以及后面的语句。 我的一个疑问是，既然读锁与读锁不互斥，那么所线程都是读的情况下，还有加读锁的必要吗，那确实没必要，但是如果读的时候不希望有写锁包围的代码被执行， 那就必须要 加读锁，另外一块代码要加写锁。另外，我觉得不要被这个读写的名字局限了，凡是需要用到这个互斥特性的地方都可以使用读写锁。"
						+ new Date(),
				"120 \n" + "并发编程的目的是为了让程序运行得更快，但是，并不是启动更多的线程就能让程序最大限度地并发执行。在进行并发编程时，\n"
						+ "如果希望通过多线程执行任务让程序运行得更快，会面临非常多的挑战，比如上下文切换的问题、死锁的问题，以及受限于硬件和软件的资源限制问题，\n"
						+ "本章会介绍几种并发编程的挑战以及解决方案。\n" + "\n" + "对于多核CPU，多线程才起优化作用" + new Date(),
				"121 " + new Date(), "122" + new Date(), "123" + new Date(), "124" + new Date(), "125" + new Date(),
				"126  面试是一次双向的沟通过程，对求职者而言是找到心仪的东家，对公司而言是招揽合适的人才。面试官的目的是考察求职者能力，评估和岗位的匹配程度，\n" + "绝非用稀奇古怪的题目面倒求职者。\n"
						+ "\n" + "本人近两年面试大几十位求职者，深感作为一个面试者不易，做好一个面试官更难，如何在极其有限的时间内准确的考察出求职的技术能力，对面试官的考验很大。\n"
						+ "本文关注服务端技术能力的考察，包括考察的范围、方式和注意事项。性格、合作能力、抗压能力等的考察不在本文讨论范围内。\n" + "\n" + "态度指南\n" + "\n"
						+ "古有周公吐哺、三顾茅庐的佳话，优秀的人才从来不乏东家，所以请尊重求职者，纵然无缘招揽优秀的人才，也要赢得面试者的好评和名声：\n" + "\n"
						+ "求职者慕名而来至少出门迎接、给瓶解渴水吧\n" + "营造宽松良好的面试氛围，让求职者正常发挥\n" + "控制好时间，不予太长，亦不宜太短\n"
						+ "面试的过程请保持耐心，认真听取，切勿打断求职者，切勿过分争论和反驳\n" + "面试结束后尽快给予求职者反馈\n" + "技术考察\n" + "\n"
						+ "技术考察主要覆盖基础知识、专业知识和逻辑思维三方面。首先了解求职者经历的项目以及在该项目中承担的角色和工作内容，再以项目为切入点，由浅入深的提出和\n"
						+ "基础知识以及专业知识相关的问题，要特别注意这些问题的连贯性，从而考察面试者的功底、逻辑性和解决问题的思路。为了缓和氛围，还可以提一些开放的问题，\n"
						+ "那些钟情于技术的 geek 们，在这类问题往往能回答的深入和透彻。\n" + "\n" + "基础知识\n" + "熟练掌握一门语言\n" + "熟悉基本的数据结构和算法\n"
						+ "熟练使用 Linux(Windows)，对操作系统原理的理解\n" + "Optional: 数据库、Web Server 和 TCP/IP 等\n" + "专业 & 经验\n"
						+ "专业知识的考察因业务而异，求职者应该需要了解整个项目的功能，明白自己模块承担的角色，熟悉模块的业务流程以及针对该业务使用到的技术框架，\n" + "某些特殊的业务可能会用到算法。\n"
						+ "开放问题\n" + "你使用过哪些语言，对比它们的异同，谈谈你的感受？\n" + "你喜欢哪些技术书籍(博客)？\n" + "哪些软件你觉得很棒，为什么？\n"
						+ "你通过什么渠道学习新技术知识？\n" + "你解决过哪些令你难忘的 bug，并说说解决的过程？\n" + "谈谈个人计算机(互联网网络)的发展历史？\n"
						+ "谈谈你敬佩的工程师？\n" + "谈谈测试的重要性？\n" + "一点感受\n" + "\n" + "不推荐纯粹的数学题、智力题等考察题目\n"
						+ "不推荐压力面试，群面等面试方式\n" + "不推荐做题、现场写代码(时间成本太高，伪代码除外)，不推荐询问杂而无连贯的问题\n"
						+ "能给出 github 的求职者往往具有更强的竞争力\n"
						+ "面试的方式和考察重点因人而异，本人认为这种方式的面试效率较高，既适合现场面试、又适合电话面试，非常注重基础功底，基础功底扎实的工程师编程能力、\n"
						+ "学习能力和调试能力都比较强悍，因为外部各异的技术归根结底总是有诸多相通之处。" + new Date(),
				"127  https://github.com/huangleisir/captain_diary/blob/master/Concurrent/%E8%81%94%E6%98%93%E8%9E%8D%E9%9D%A2%E8%AF%95%E6%8C%91%E6%88%9830k.md"
						+ new Date(),
				"128 自己实现BlockingQueue\n" + "  张孝祥应该就是这么写阻塞队列的吧\n"
						+ "http://blog.csdn.net/chenchaofuck1/article/details/51660119\n"
						+ "其实也不难，用condition下面的await()和signal（）" + new Date(),
				"129 昨晚睡觉又做噩梦高考，没有背作文\n" + "没有背诵三五十篇作文，怎么敢去高考\n" + "这就跟现在面试前没有去背面试题一样\n" + "噩梦拯救行动    高考作文拯救行动\n"
						+ "这样 先把面试题背下来" + new Date(),
				"130 一门研究数据之间关系的学问，目的是为了提高插入，读取，更新，删除效率\n" + "\n" + "分为两种关系\n" + " 逻辑关系\n" + "\n"
						+ "    集合：同在一个范围内，数据之间没有关系\n" + "    线性：1对1 怎么举例子呢\n" + "    树形 1对多  比如文件夹就是树形结构\n" + "\n"
						+ "物理关系 物理存储" + new Date(),
				"131  一看就懂的模板方法模式  https://blog.csdn.net/u013565163/article/details/79285617" + new Date(),
				"132 一次 HashSet 所引起的并发问题  https://mp.weixin.qq.com/s/-_6fDP6OSse-tL-ptjcbJw    HashSet的本质还是HashMap  如何线程安全的使用HashSet 使用ConcurrentHashMap   value写死成null"
						+ new Date(),
				"133 局部性原理与磁盘预读\n" + "\n"
						+ "由于存储介质的特性，磁盘本身存取就比主存慢很多，再加上机械运动耗费，磁盘的存取速度往往是主存的几百分分之一，因此为了提高效率，要尽量减少磁盘I/O。为了达到这个目的，磁盘往往不是严格按需读取，而是每次都会预读，即使只需要一个字节，磁盘也会从这个位置开始，顺序向后读取一定长度的数据放入内存。这样做的理论依据是计算机科学中著名的局部性原理：当一个数据被用到时，其附近的数据也通常会马上被使用。程序运行期间所需要的数据通常比较集中。\n"
						+ "\n" + "由于磁盘顺序读取的效率很高（不需要寻道时间，只需很少的旋转时间），因此对于具有局部性的程序来说，预读可以提高I/O效率。\n" + "\n"
						+ "预读的长度一般为页（page）的整倍数。页是计算机管理存储器的逻辑块，硬件及操作系统往往将主存和磁盘存储区分割为连续的大小相等的块，每个存储块称为一页（在许多操作系统中，页得大小通常为4k），主存和磁盘以页为单位交换数据。当程序要读取的数据不在主存中时，会触发一个缺页异常，此时系统会向磁盘发出读盘信号，磁盘会找到数据的起始位置并向后连续读取一页或几页载入内存中，然后异常返回，程序继续运行。"
						+ new Date(),
				"134 B-/+Tree索引的性能分析\n" + "\n" + "到这里终于可以分析B-/+Tree索引的性能了。\n" + "\n"
						+ "上文说过一般使用磁盘I/O次数评价索引结构的优劣。先从B-Tree分析，根据B-Tree的定义，可知检索一次最多需要访问h个节点。数据库系统的设计者巧妙利用了磁盘预读原理，将一个节点的大小设为等于一个页，这样每个节点只需要一次I/O就可以完全载入。为了达到这个目的，在实际实现B-Tree还需要使用如下技巧：\n"
						+ "\n" + "每次新建节点时，直接申请一个页的空间，这样就保证一个节点物理上也存储在一个页里，加之计算机存储分配都是按页对齐的，就实现了一个node只需一次I/O。\n" + "\n"
						+ "B-Tree中一次检索最多需要h-1次I/O（根节点常驻内存），渐进复杂度为O(h)=O(logdN)。一般实际应用中，出度d是非常大的数字，通常超过100，因此h非常小（通常不超过3）。\n"
						+ "\n" + "而红黑树这种结构，h明显要深的多。由于逻辑上很近的节点（父子）物理上可能很远，无法利用局部性，所以红黑树的I/O渐进复杂度也为O(h)，效率明显比B-Tree差很多。\n"
						+ "\n" + "综上所述，用B-Tree作为索引结构效率是非常高的。" + new Date(),
				"135 根据数据库的功能，可以在数据库设计器中创建三种索引：唯一索引、主键索引和聚集索引。 唯一索引\n" + "\n" + "唯一索引是不允许其中任何两行具有相同索引值的索引。\n" + "\n"
						+ "当现有数据中存在重复的键值时，大多数数据库不允许将新创建的唯一索引与表一起保存。数据库还可能防止添加将在表中创建重复键值的新数据。例如，如果在employee表中职员的姓(lname)上创建了唯一索引，则任何两个员工都不能同姓。 主键索引 数据库表经常有一列或列组合，其值唯一标识表中的每一行。该列称为表的主键。 在数据库关系图中为表定义主键将自动创建主键索引，主键索引是唯一索引的特定类型。该索引要求主键中的每个值都唯一。当在查询中使用主键索引时，它还允许对数据的快速访问。 聚集索引 在聚集索引中，表中行的物理顺序与键值的逻辑（索引）顺序相同。一个表只能包含一个聚集索引。\n"
						+ "\n" + "如果某索引不是聚集索引，则表中行的物理顺序与键值的逻辑顺序不匹配。与非聚集索引相比，聚集索引通常提供更快的数据访问速度。" + new Date(),
				"136 5.索引的工作原理及其种类\n" + "\n" + "数据库索引，是数据库管理系统中一个排序的数据结构，以协助快速查询、更新数据库表中数据。索引的实现通常使用B树及其变种B+树。\n" + "\n"
						+ "在数据之外，数据库系统还维护着满足特定查找算法的数据结构，这些数据结构以某种方式引用（指向）数据，这样就可以在这些数据结构上实现高级查找算法。这种数据结构，就是索引。\n" + "\n"
						+ "为表设置索引要付出代价的：一是增加了数据库的存储空间，二是在插入和修改数据时要花费较多的时间(因为索引也要随之变动)。" + new Date(),
				"137 同样，对于有些列不应该创建索引。一般来说，不应该创建索引的的这些列具有下列特点：\n" + "\n"
						+ "第一，对于那些在查询中很少使用或者参考的列不应该创建索引。这是因为，既然这些列很少使用到，因此有索引或者无索引，并不能提高查询速度。相反，由于增加了索引，反而降低了系统的维护速度和增大了空间需求。\n"
						+ "\n"
						+ "第二，对于那些只有很少数据值的列也不应该增加索引。这是因为，由于这些列的取值很少，例如人事表的性别列，在查询的结果中，结果集的数据行占了表中数据行的很大比例，即需要在表中搜索的数据行的比例很大。增加索引，并不能明显加快检索速度。\n"
						+ "\n" + "第三，对于那些定义为text, image和bit数据类型的列不应该增加索引。这是因为，这些列的数据量要么相当大，要么取值很少。\n" + "\n"
						+ "第四，当修改性能远远大于检索性能时，不应该创建索引。这是因为，修改性能和检索性能是互相矛盾的。当增加索引时，会提高检索性能，但是会降低修改性能。当减少索引时，会提高修改性能，降低检索性能。因此，当修改性能远远大于检索性能时，不应该创建索引。"
						+ new Date(),
				"138 图展示了一种可能的索引方式。左边是数据表，一共有两列七条记录，最左边的是数据记录的物理地址（注意逻辑上相邻的记录在磁盘上也并不是一定物理相邻的）。为了加快Col2的查找，可以维护一个右边所示的二叉查找树，每个节点分别包含索引键值和一个指向对应数据记录物理地址的指针，这样就可以运用二叉查找在O(log2n)的复杂度内获取到相应数据。\n"
						+ "\n" + "创建索引可以大大提高系统的性能。\n" + "\n" + "第一，通过创建唯一性索引，可以保证数据库表中每一行数据的唯一性。\n" + "\n"
						+ "第二，可以大大加快数据的检索速度，这也是创建索引的最主要的原因。\n" + "\n" + "第三，可以加速表和表之间的连接，特别是在实现数据的参考完整性方面特别有意义。\n"
						+ "\n" + "第四，在使用分组和排序子句进行数据检索时，同样可以显著减少查询中分组和排序的时间。\n" + "\n"
						+ "第五，通过使用索引，可以在查询的过程中，使用优化隐藏器，提高系统的性能。" + new Date(),
				"139 也许会有人要问：增加索引有如此多的优点，为什么不对表中的每一个列创建一个索引呢？因为，增加索引也有许多不利的方面。\n" + "\n"
						+ "第一，创建索引和维护索引要耗费时间，这种时间随着数据量的增加而增加。\n" + "\n"
						+ "第二，索引需要占物理空间，除了数据表占数据空间之外，每一个索引还要占一定的物理空间，如果要建立聚簇索引，那么需要的空间就会更大。\n" + "\n"
						+ "第三，当对表中的数据进行增加、删除和修改的时候，索引也要动态的维护，这样就降低了数据的维护速度。\n" + "\n"
						+ "索引是建立在数据库表中的某些列的上面。在创建索引的时候，应该考虑在哪些列上可以创建索引，在哪些列上不能创建索引。一般来说，应该在这些列上创建索引：在经常需要搜索的列上，可以加快搜索的速度；在作为主键的列上，强制该列的唯一性和组织表中数据的排列结构；在经常用在连接的列上，这些列主要是一些外键，可以加快连接的速度；在经常需要根据范围进行搜索的列上创建索引，因为索引已经排序，其指定的范围是连续的；在经常需要排序的列上创建索引，因为索引已经排序，这样查询可以利用索引的排序，加快排序查询时间；在经常使用在WHERE子句中的列上面创建索引，加快条件的判断速度。\n"
						+ "\n" + "同样，对于有些列不应该创建索引。一般来说，不应该创建索引的的这些列具有下列特点：\n" + "\n"
						+ "第一，对于那些在查询中很少使用或者参考的列不应该创建索引。这是因为，既然这些列很少使用到，因此有索引或者无索引，并不能提高查询速度。相反，由于增加了索引，反而降低了系统的维护速度和增大了空间需求。\n"
						+ "\n"
						+ "第二，对于那些只有很少数据值的列也不应该增加索引。这是因为，由于这些列的取值很少，例如人事表的性别列，在查询的结果中，结果集的数据行占了表中数据行的很大比例，即需要在表中搜索的数据行的比例很大。增加索引，并不能明显加快检索速度。\n"
						+ "\n" + "第三，对于那些定义为text, image和bit数据类型的列不应该增加索引。这是因为，这些列的数据量要么相当大，要么取值很少。\n" + "\n"
						+ "第四，当修改性能远远大于检索性能时，不应该创建索引。这是因为，修改性能和检索性能是互相矛盾的。当增加索引时，会提高检索性能，但是会降低修改性能。当减少索引时，会提高修改性能，降低检索性能。因此，当修改性能远远大于检索性能时，不应该创建索引。"
						+ new Date(),
				"140 3.视图的作用，视图可以更改么？\n" + "\n"
						+ "视图是虚拟的表，与包含数据的表不一样，视图只包含使用时动态检索数据的查询；不包含任何列或数据。使用视图可以简化复杂的sql操作，隐藏具体的细节，保护数据；视图创建后，可以使用与表相同的方式利用它们。\n"
						+ "视图不能被索引，也不能有关联的触发器或默认值，如果视图本身内有order by 则对视图再次order by将被覆盖。\n"
						+ "创建视图：create view XXX as XXXXXXXXXXXXXX;\n"
						+ "对于某些视图比如未使用联结子查询分组聚集函数Distinct Union等，是可以对其更新的，对视图的更新将对基表进行更新；但是视图主要用于简化检索，保护数据，并不用于更新，而且大部分视图都不可以更新。"
						+ new Date(),
				"141 4.drop,delete与truncate的区别\n" + "\n"
						+ "drop直接删掉表 truncate删除表中数据，再插入时自增长id又从1开始 delete删除表中数据，可以加where字句。\n" + "\n"
						+ "（1） DELETE语句执行删除的过程是每次从表中删除一行，并且同时将该行的删除操作作为事务记录在日志中保存以便进行进行回滚操作。TRUNCATE TABLE 则一次性地从表中删除所有的数据并不把单独的删除操作记录记入日志保存，删除行是不能恢复的。并且在删除的过程中不会激活与表有关的删除触发器。执行速度快。\n"
						+ "\n"
						+ "（2） 表和索引所占空间。当表被TRUNCATE 后，这个表和索引所占用的空间会恢复到初始大小，而DELETE操作不会减少表或索引所占用的空间。drop语句将表所占用的空间全释放掉。\n"
						+ "\n" + "（3） 一般而言，drop > truncate > delete\n" + "\n"
						+ "（4） 应用范围。TRUNCATE 只能对TABLE；DELETE可以是table和view\n" + "\n"
						+ "（5） TRUNCATE 和DELETE只删除数据，而DROP则删除整个表（结构和数据）。\n" + "\n"
						+ "（6） truncate与不带where的delete ：只删除数据，而不删除表的结构（定义）drop语句将删除表的结构被依赖的约束（constrain),触发器（trigger)索引（index);依赖于该表的存储过程/函数将被保留，但其状态会变为：invalid。\n"
						+ "\n"
						+ "（7） delete语句为DML（data maintain Language),这个操作会被放到 rollback segment中,事务提交后才生效。如果有相应的 tigger,执行的时候将被触发。\n"
						+ "\n" + "（8） truncate、drop是DLL（data define language),操作立即生效，原数据不放到 rollback segment中，不能回滚\n"
						+ "\n"
						+ "（9） 在没有备份情况下，谨慎使用 drop 与 truncate。要删除部分数据行采用delete且注意结合where来约束影响范围。回滚段要足够大。要删除表用drop;若想保留表而将表中数据删除，如果于事务无关，用truncate即可实现。如果和事务有关，或老师想触发trigger,还是用delete。\n"
						+ "\n" + "（10） Truncate table 表名 速度快,而且效率高,因为:\n"
						+ "truncate table 在功能上与不带 WHERE 子句的 DELETE 语句相同：二者均删除表中的全部行。但 TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少。DELETE 语句每次删除一行，并在事务日志中为所删除的每行记录一项。TRUNCATE TABLE 通过释放存储表数据所用的数据页来删除数据，并且只在事务日志中记录页的释放。\n"
						+ "\n"
						+ "（11） TRUNCATE TABLE 删除表中的所有行，但表结构及其列、约束、索引等保持不变。新行标识所用的计数值重置为该列的种子。如果想保留标识计数值，请改用 DELETE。如果要删除表定义及其数据，请使用 DROP TABLE 语句。\n"
						+ "\n"
						+ "（12） 对于由 FOREIGN KEY 约束引用的表，不能使用 TRUNCATE TABLE，而应使用不带 WHERE 子句的 DELETE 语句。由于 TRUNCATE TABLE 不记录在日志中，所以它不能激活触发器。"
						+ new Date(),
				"142  MySQL经典面试题\n" + "1、MySQL的复制原理以及流程\n" + "\n" + "(1)、复制基本原理流程\n" + "\n"
						+ "1. 主：binlog线程——记录下所有改变了数据库数据的语句，放进master上的binlog中；\n"
						+ "2. 从：io线程——在使用start slave 之后，负责从master上拉取 binlog 内容，放进 自己的relay log中；\n"
						+ "3. 从：sql执行线程——执行relay log中的语句；\n" + "\n" + "" + new Date(),
				"143 (2)、MySQL复制的线程有几个及之间的关联\n" + "\n" + "MySQL 的复制是基于如下 3 个线程的交互（ 多线程复制里面应该是 4 类线程）：\n"
						+ "1. Master 上面的 binlog dump 线程，该线程负责将 master 的 binlog event 传到slave；\n"
						+ "2. Slave 上面的 IO 线程，该线程负责接收 Master 传过来的 binlog，并写入 relay log；\n"
						+ "3. Slave 上面的 SQL 线程，该线程负责读取 relay log 并执行；\n"
						+ "4. 如果是多线程复制，无论是 5.6 库级别的假多线程还是 MariaDB 或者 5.7 的真正的多线程复制， SQL 线程只做 coordinator，只负责把 relay log 中的 binlog读出来然后交给 worker 线程， woker 线程负责具体 binlog event 的执行；\n"
						+ "\n" + "" + new Date(),
				"144 (3)、MySQL如何保证复制过程中数据一致性及减少数据同步延时\n" + "\n" + "一致性主要有以下几个方面：\n" + "复制代码\n" + "\n"
						+ "1.在 MySQL5.5 以及之前， slave 的 SQL 线程执行的 relay log 的位置只能保存在文件（ relay-log.info）里面，并且该文件默认每执行 10000 次事务做一次同步到磁盘， 这意味着 slave 意外 crash 重启时， SQL 线程执行到的位置和数据库的数据是不一致的，将导致复制报错，如果不重搭复制，则有可能会\n"
						+ "导致数据不一致。 MySQL 5.6 引入参数 relay_log_info_repository，将该参数设置为 TABLE 时， MySQL 将 SQL 线程执行到的位置存到mysql.slave_relay_log_info 表，这样更新该表的位置和 SQL 线程执行的用户事务绑定成一个事务，这样 slave 意外宕机后， slave 通过 innodb 的崩溃\n"
						+ "恢复可以把 SQL 线程执行到的位置和用户事务恢复到一致性的状态。\n"
						+ "2. MySQL 5.6 引入 GTID 复制，每个 GTID 对应的事务在每个实例上面最多执行一次， 这极大地提高了复制的数据一致性；\n"
						+ "3. MySQL 5.5 引入半同步复制， 用户安装半同步复制插件并且开启参数后，设置超时时间，可保证在超时时间内如果 binlog 不传到 slave 上面，那么用户提交事务时不会返回，直到超时后切成异步复制，但是如果切成异步之前用户线程提交时在 master 上面等待的时候，事务已经提交，该事务对 master\n"
						+ "上面的其他 session 是可见的，如果这时 master 宕机，那么到 slave 上面该事务又不可见了，该问题直到 5.7 才解决；\n"
						+ "4. MySQL 5.7 引入无损半同步复制，引入参 rpl_semi_sync_master_wait_point，该参数默认为 after_sync，指的是在切成半同步之前，事务不提交，而是接收到 slave 的 ACK 确认之后才提交该事务，从此，复制真正可以做到无损的了。\n"
						+ "5.可以再说一下 5.7 的无损复制情况下， master 意外宕机，重启后发现有 binlog没传到 slave 上面，这部分 binlog 怎么办？？？分 2 种情况讨论， 1 宕机时已经切成异步了， 2 是宕机时还没切成异步？？？ 这个怎么判断宕机时有没有切成异步呢？？？ 分别怎么处理？？？"
						+ new Date(),
				"145 延时性：\n" + "\n"
						+ " 5.5 是单线程复制， 5.6 是多库复制（对于单库或者单表的并发操作是没用的）， 5.7 是真正意义的多线程复制，它的原理是基于 group commit， 只要\n"
						+ "master 上面的事务是 group commit 的，那 slave 上面也可以通过多个 worker线程去并发执行。 和 MairaDB10.0.0.5 引入多线程复制的原理基本一样。\n"
						+ "\n" + "" + new Date(),
				"146 2、MySQL中myisam与innodb的区别，至少5点\n" + "(1)、问5点不同\n" + "复制代码\n" + "\n" + "1.InnoDB支持事物，而MyISAM不支持事物\n"
						+ "2.InnoDB支持行级锁，而MyISAM支持表级锁\n" + "3.InnoDB支持MVCC, 而MyISAM不支持\n" + "4.InnoDB支持外键，而MyISAM不支持\n"
						+ "5.InnoDB不支持全文索引，而MyISAM支持。\n" + "6.InnoDB不能通过直接拷贝表文件的方法拷贝表到另外一台机器， myisam 支持\n"
						+ "7.InnoDB表支持多种行格式， myisam 不支持\n" + "8.InnoDB是索引组织表， myisam 是堆表" + new Date(),
				"147 (2)、innodb引擎的4大特性\n" + "\n" + "1.插入缓冲（insert buffer)\n" + "2.二次写(double write)\n"
						+ "3.自适应哈希索引(ahi)\n" + "4.预读(read ahead)\n" + "\n" + "" + new Date(),
				"148 (3)、各种不同 mysql 版本的Innodb的改进\n" + "复制代码\n" + "\n" + "MySQL5.6 下 Innodb 引擎的主要改进：\n"
						+ "（ 1） online DDL\n" + "（ 2） memcached NoSQL 接口\n"
						+ "（ 3） transportable tablespace（ alter table discard/import tablespace）\n"
						+ "（ 4） MySQL 正常关闭时，可以 dump 出 buffer pool 的（ space， page_no），重启时 reload，加快预热速度\n"
						+ "（ 5） 索引和表的统计信息持久化到 mysql.innodb_table_stats 和mysql.innodb_index_stats，可提供稳定的执行计划\n"
						+ "（ 6） Compressed row format 支持压缩表\n" + "\n" + "MySQL 5.7 innodb 引擎主要改进\n"
						+ "（ 1） 修改 varchar 字段长度有时可以使用 online DDL\n" + "（ 2） Buffer pool 支持在线改变大小\n"
						+ "（ 3） Buffer pool 支持导出部分比例\n" + "（ 4） 支持新建 innodb tablespace，并可以在其中创建多张表\n"
						+ "（ 5） 磁盘临时表采用 innodb 存储，并且存储在 innodb temp tablespace 里面，以前是 myisam 存储\n" + "（ 6） 透明表空间压缩功能"
						+ new Date(),
				"149 (4)、2者select  count(*)哪个更快，为什么\n" + "\n" + "myisam更快，因为myisam内部维护了一个计数器，可以直接调取。\n" + "\n"
						+ "(5)、2 者的索引的实现方式\n" + "\n" + "都是 B+树索引， Innodb 是索引组织表， myisam 是堆表， 索引组织表和堆表的区别要熟悉\n" + "\n"
						+ "" + new Date(),
				"150 3、MySQL中varchar与char的区别以及varchar(50)中的50代表的涵义\n" + "(1)、varchar与char的区别\n" + "\n"
						+ "在单字节字符集下， char（ N） 在内部存储的时候总是定长， 而且没有变长字段长度列表中。 在多字节字符集下面， char(N)如果存储的字节数超过 N，那么 char（ N）将和 varchar（ N）没有区别。在多字节字符集下面，如果存\n"
						+ "储的字节数少于 N，那么存储 N 个字节，后面补空格，补到 N 字节长度。 都存储变长的数据和变长字段长度列表。 varchar(N)无论是什么字节字符集，都是变长的，即都存储变长数据和变长字段长度列表。\n"
						+ "\n" + "(2)、varchar(50)中50的涵义\n" + "\n"
						+ "最多存放50个字符，varchar(50)和(200)存储hello所占空间一样，但后者在排序时会消耗更多内存，因为order by col采用fixed_length计算col长度(memory引擎也一样)。在早期 MySQL 版本中， 50 代表字节数，现在代表字符数。\n"
						+ "\n" + "(3)、int（20）中20的涵义\n" + "\n" + "是指显示字符的长度\n"
						+ "不影响内部存储，只是影响带 zerofill 定义的 int 时，前面补多少个 0，易于报表展示\n" + "\n" + "(4)、mysql为什么这么设计\n" + "\n"
						+ "对大多数应用没有意义，只是规定一些工具用来显示字符的个数；int(1)和int(20)存储和计算均一样；\n" + "\n" + "" + new Date(),
				"151 4、innodb的事务与日志的实现方式\n" + "\n" + "(1)、有多少种日志\n" + "\n" + "redo和undo\n" + "\n" + "(2)、日志的存放形式\n"
						+ "\n"
						+ "redo：在页修改的时候，先写到 redo log buffer 里面， 然后写到 redo log 的文件系统缓存里面(fwrite)，然后再同步到磁盘文件（ fsync）。\n"
						+ "Undo：在 MySQL5.5 之前， undo 只能存放在 ibdata*文件里面， 5.6 之后，可以通过设置 innodb_undo_tablespaces 参数把 undo log 存放在 ibdata*之外。\n"
						+ "\n" + "(3)、事务是如何通过日志来实现的，说得越深入越好\n" + "\n" + "基本流程如下：\n"
						+ "因为事务在修改页时，要先记 undo，在记 undo 之前要记 undo 的 redo， 然后修改数据页，再记数据页修改的 redo。 Redo（里面包括 undo 的修改） 一定要比数据页先持久化到磁盘。 当事务需要回滚时，因为有 undo，可以把数据页回滚到前镜像的\n"
						+ "状态，崩溃恢复时，如果 redo log 中事务没有对应的 commit 记录，那么需要用 undo把该事务的修改回滚到事务开始之前。 如果有 commit 记录，就用 redo 前滚到该事务完成时并提交掉。\n"
						+ "\n" + "" + new Date(),
				"152 5、MySQL binlog的几种日志录入格式以及区别\n" + "\n" + "(1)、 各种日志格式的涵义\n" + "复制代码\n" + "\n"
						+ "1.Statement：每一条会修改数据的sql都会记录在binlog中。\n"
						+ "优点：不需要记录每一行的变化，减少了binlog日志量，节约了IO，提高性能。(相比row能节约多少性能 与日志量，这个取决于应用的SQL情况，正常同一条记录修改或者插入row格式所产生的日志量还小于Statement产生的日志量，\n"
						+ "但是考虑到如果带条 件的update操作，以及整表删除，alter表等操作，ROW格式会产生大量日志，因此在考虑是否使用ROW格式日志时应该跟据应用的实际情况，其所 产生的日志量会增加多少，以及带来的IO性能问题。)\n"
						+ "缺点：由于记录的只是执行语句，为了这些语句能在slave上正确运行，因此还必须记录每条语句在执行的时候的 一些相关信息，以保证所有语句能在slave得到和在master端执行时候相同 的结果。另外mysql 的复制,\n"
						+ "像一些特定函数功能，slave可与master上要保持一致会有很多相关问题(如sleep()函数， last_insert_id()，以及user-defined functions(udf)会出现问题).\n"
						+ "使用以下函数的语句也无法被复制：\n" + "* LOAD_FILE()\n" + "* UUID()\n" + "* USER()\n" + "* FOUND_ROWS()\n"
						+ "* SYSDATE() (除非启动时启用了 --sysdate-is-now 选项)\n" + "同时在INSERT ...SELECT 会产生比 RBR 更多的行级锁\n"
						+ "\n" + "2.Row:不记录sql语句上下文相关信息，仅保存哪条记录被修改。\n"
						+ "优点： binlog中可以不记录执行的sql语句的上下文相关的信息，仅需要记录那一条记录被修改成什么了。所以rowlevel的日志内容会非常清楚的记录下 每一行数据修改的细节。而且不会出现某些特定情况下的存储过程，或function，以及trigger的调用和触发无法被正确复制的问题\n"
						+ "缺点:所有的执行的语句当记录到日志中的时候，都将以每行记录的修改来记录，这样可能会产生大量的日志内容,比 如一条update语句，修改多条记录，则binlog中每一条修改都会有记录，这样造成binlog日志量会很大，特别是当执行alter table之类的语句的时候，\n"
						+ "由于表结构修改，每条记录都发生改变，那么该表每一条记录都会记录到日志中。\n" + "\n"
						+ "3.Mixedlevel: 是以上两种level的混合使用，一般的语句修改使用statment格式保存binlog，如一些函数，statement无法完成主从复制的操作，则 采用row格式保存binlog,MySQL会根据执行的每一条具体的sql语句来区分对待记录的日志形式，\n"
						+ "也就是在Statement和Row之间选择 一种.新版本的MySQL中队row level模式也被做了优化，并不是所有的修改都会以row level来记录，像遇到表结构变更的时候就会以statement模式来记录。至于update或者delete等修改数据的语句，还是会记录所有行的变更。\n"
						+ "\n" + "复制代码\n" + "\n" + " (2)、适用场景\n" + "\n"
						+ "在一条 SQL 操作了多行数据时， statement 更节省空间， row 更占用空间。但是 row模式更可靠。\n" + "\n"
						+ "(3)、结合第一个问题，每一种日志格式在复制中的优劣\n" + "\n"
						+ "Statement 可能占用空间会相对小一些，传送到 slave 的时间可能也短，但是没有 row模式的可靠。 Row 模式在操作多行数据时更占用空间， 但是可靠。\n" + "\n"
						+ "" + new Date(),
				"153 6、下MySQL数据库cpu飙升到500%的话他怎么处理？\n" + "\n"
						+ "当 cpu 飙升到 500%时，先用操作系统命令 top 命令观察是不是 mysqld 占用导致的，如果不是，找出占用高的进程，并进行相关处理。如果是 mysqld 造成的， show processlist，看看里面跑的 session 情况，是不是有消耗资源的 sql 在运行。找出消耗高的 sql，\n"
						+ "看看执行计划是否准确， index 是否缺失，或者实在是数据量太大造成。一般来说，肯定要 kill 掉这些线程(同时观察 cpu 使用率是否下降)，等进行相应的调整(比如说加索引、改 sql、改内存参数)之后，再重新跑这些 SQL。也有可能是每个 sql 消耗资源并不多，但是突然之间，\n"
						+ "有大量的 session 连进来导致 cpu 飙升，这种情况就需要跟应用一起来分析为何连接数会激增，再做出相应的调整，比如说限制连接数等\n" + "\n" + ""
						+ new Date(),
				"154 7、sql优化\n" + "\n" + "(1)、explain出来的各种item的意义\n" + "复制代码\n" + "\n"
						+ "id:每个被独立执行的操作的标志，表示对象被操作的顺序。一般来说， id 值大，先被执行；如果 id 值相同，则顺序从上到下。\n"
						+ "select_type：查询中每个 select 子句的类型。\n" + "table:名字，被操作的对象名称，通常的表名(或者别名)，但是也有其他格式。\n"
						+ "partitions:匹配的分区信息。\n" + "type:join 类型。\n" + "possible_keys：列出可能会用到的索引。\n" + "key:实际用到的索引。\n"
						+ "key_len:用到的索引键的平均长度，单位为字节。\n" + "ref:表示本行被操作的对象的参照对象，可能是一个常量用 const 表示，也可能是其他表的\n"
						+ "key 指向的对象，比如说驱动表的连接列。\n" + "rows:估计每次需要扫描的行数。\n"
						+ "filtered:rows*filtered/100 表示该步骤最后得到的行数(估计值)。\n" + "extra:重要的补充信息。\n" + "\n" + "复制代码\n"
						+ "\n" + "(2)、profile的意义以及使用场景\n" + "\n"
						+ "Profile 用来分析 sql 性能的消耗分布情况。当用 explain 无法解决慢 SQL 的时候，需要用profile 来对 sql 进行更细致的分析，找出 sql 所花的时间大部分消耗在哪个部分，确认 sql的性能瓶颈。\n"
						+ "\n" + "(3)、explain 中的索引问题\n" + "\n"
						+ "Explain 结果中，一般来说，要看到尽量用 index(type 为 const、 ref 等， key 列有值)，避免使用全表扫描(type 显式为 ALL)。比如说有 where 条件且选择性不错的列，需要建立索引。\n"
						+ "被驱动表的连接列，也需要建立索引。被驱动表的连接列也可能会跟 where 条件列一起建立联合索引。当有排序或者 group by 的需求时，也可以考虑建立索引来达到直接排序和汇总的需求。\n"
						+ "\n" + "" + new Date(),
				"155 8、备份计划，mysqldump以及xtranbackup的实现原理\n" + "\n" + "(1)、备份计划\n" + "\n"
						+ "视库的大小来定，一般来说 100G 内的库，可以考虑使用 mysqldump 来做，因为 mysqldump更加轻巧灵活，备份时间选在业务低峰期，可以每天进行都进行全量备份(mysqldump 备份\n"
						+ "出来的文件比较小，压缩之后更小)。100G 以上的库，可以考虑用 xtranbackup 来做，备份速度明显要比 mysqldump 要快。一般是选择一周一个全备，其余每天进行增量备份，备份时间为业务低峰期。\n"
						+ "\n" + "(2)、备份恢复时间\n" + "复制代码\n" + "\n" + "物理备份恢复快，逻辑备份恢复慢\n"
						+ "这里跟机器，尤其是硬盘的速率有关系，以下列举几个仅供参考\n" + "20G的2分钟（mysqldump）\n" + "80G的30分钟(mysqldump)\n"
						+ "111G的30分钟（mysqldump)\n" + "288G的3小时（xtra)\n" + "3T的4小时（xtra)\n" + "逻辑导入时间一般是备份时间的5倍以上\n"
						+ "\n" + "复制代码\n" + "\n" + "(3)、备份恢复失败如何处理\n" + "\n"
						+ "首先在恢复之前就应该做足准备工作，避免恢复的时候出错。比如说备份之后的有效性检查、权限检查、空间检查等。如果万一报错，再根据报错的提示来进行相应的调整。\n" + "\n"
						+ "(4)、mysqldump和xtrabackup实现原理\n" + "\n" + "mysqldump\n" + "\n"
						+ "mysqldump 属于逻辑备份。加入--single-transaction 选项可以进行一致性备份。后台进程会先设置 session 的事务隔离级别为 RR(SET SESSION TRANSACTION ISOLATION LEVELREPEATABLE READ)，\n"
						+ "之后显式开启一个事务(START TRANSACTION /*!40100 WITH CONSISTENTSNAPSHOT */)，这样就保证了该事务里读到的数据都是事务事务时候的快照。之后再把表的数据读取出来。 如果加上--master-data=1 的话，在刚开始的时候还会加一个数据库的读锁\n"
						+ "(FLUSH TABLES WITH READ LOCK),等开启事务后，再记录下数据库此时 binlog 的位置(showmaster status)，马上解锁，再读取表的数据。等所有的数据都已经导完，就可以结束事务\n"
						+ "\n" + "Xtrabackup:\n" + "\n"
						+ "xtrabackup 属于物理备份，直接拷贝表空间文件，同时不断扫描产生的 redo 日志并保存下来。最后完成 innodb 的备份后，会做一个 flush engine logs 的操作(老版本在有 bug，在5.6 上不做此操作会丢数据)，确保所有的 redo log 都已经落盘(涉及到事务的两阶段提交\n"
						+ "概念，因为 xtrabackup 并不拷贝 binlog，所以必须保证所有的 redo log 都落盘，否则可能会丢最后一组提交事务的数据)。这个时间点就是 innodb 完成备份的时间点，数据文件虽然不是一致性的，但是有这段时间的 redo 就可以让数据文件达到一致性(恢复的时候做的事\n"
						+ "情)。然后还需要 flush tables with read lock，把 myisam 等其他引擎的表给备份出来，备份完后解锁。 这样就做到了完美的热备。\n" + "\n"
						+ "" + new Date(),
				"156 10、500台db，在最快时间之内重启\n" + "\n"
						+ "可以使用批量 ssh 工具 pssh 来对需要重启的机器执行重启命令。 也可以使用 salt（前提是客户端有安装 salt）或者 ansible（ ansible 只需要 ssh 免登通了就行）等多线程工具同时操作多台服务器\n"
						+ "\n" + "" + new Date(),
				"157 13、你是否做过主从一致性校验，如果有，怎么做的，如果没有，你打算怎么做？\n" + "\n"
						+ "主从一致性校验有多种工具 例如checksum、mysqldiff、pt-table-checksum等\n" + "\n" + "" + new Date(),
				"158 14、表中有大字段X(例如：text类型)，且字段X不会经常更新，以读为为主，请问您是选择拆成子表，还是继续放一起?写出您这样选择的理由\n" + "\n"
						+ "答：拆带来的问题：连接消耗 + 存储拆分空间；不拆可能带来的问题：查询性能；\n"
						+ "如果能容忍拆分带来的空间问题,拆的话最好和经常要查询的表的主键在物理结构上放置在一起(分区) 顺序IO,减少连接消耗,最后这是一个文本列再加上一个全文索引来尽量抵消连接消耗\n"
						+ "如果能容忍不拆分带来的查询性能损失的话:上面的方案在某个极致条件下肯定会出现问题,那么不拆就是最好的选择\n" + "\n" + "" + new Date(),
				"159 15、MySQL中InnoDB引擎的行锁是通过加在什么上完成(或称实现)的？为什么是这样子的？\n" + "\n" + "答：InnoDB是基于索引来完成行锁\n"
						+ "例: select * from tab_with_index where id = 1 for update;\n"
						+ "for update 可以根据条件来完成行锁锁定,并且 id 是有索引键的列,\n" + "如果 id 不是索引键那么InnoDB将完成表锁,,并发将无从谈起\n" + "\n"
						+ "" + new Date(),
				"160 16、如何从mysqldump产生的全库备份中只恢复某一个库、某一张表？\n" + "复制代码\n" + "\n" + "全库备份\n"
						+ "[root@HE1 ~]# mysqldump -uroot -p --single-transaction -A --master-data=2 >dump.sql\n"
						+ "只还原erp库的内容\n" + "[root@HE1 ~]# mysql -uroot -pMANAGER erp --one-database <dump.sql\n" + "\n"
						+ "可以看出这里主要用到的参数是--one-database简写-o的参数，极大方便了我们的恢复灵活性\n"
						+ "那么如何从全库备份中抽取某张表呢，全库恢复，再恢复某张表小库还可以，大库就很麻烦了，那我们可以利用正则表达式来进行快速抽取，具体实现方法如下：\n" + " \n"
						+ "从全库备份中抽取出t表的表结构\n"
						+ "[root@HE1 ~]# sed -e'/./{H;$!d;}' -e 'x;/CREATE TABLE `t`/!d;q' dump.sql\n" + " \n"
						+ "DROP TABLE IF EXISTS`t`;\n"
						+ "/*!40101 SET@saved_cs_client     =@@character_set_client */;\n"
						+ "/*!40101 SETcharacter_set_client = utf8 */;\n" + "CREATE TABLE `t` (\n"
						+ "  `id` int(10) NOT NULL AUTO_INCREMENT,\n" + "  `age` tinyint(4) NOT NULL DEFAULT '0',\n"
						+ "  `name` varchar(30) NOT NULL DEFAULT '',\n" + "  PRIMARY KEY (`id`)\n"
						+ ") ENGINE=InnoDBAUTO_INCREMENT=4 DEFAULT CHARSET=utf8;\n"
						+ "/*!40101 SETcharacter_set_client = @saved_cs_client */;\n" + " \n" + "从全库备份中抽取出t表的内容\n"
						+ "[root@HE1 ~]# grep'INSERT INTO `t`' dump.sql\n"
						+ "INSERT INTO `t`VALUES (0,0,''),(1,0,'aa'),(2,0,'bbb'),(3,25,'helei');\n" + "\n" + "复制代码"
						+ new Date(),
				"161 17、在当前的工作中，你碰到到的最大的 mysql db 问题以及如何解决的？\n" + "\n"
						+ "可以选择一个处理过的比较棘手的案例，或者选择一个老师在课程上讲过的死锁的案例;没有及时 Purge + insert 唯一索引造成的死锁：具体案例可以参考学委笔记。\n" + "\n"
						+ "" + new Date(),
				"162 18、请简洁地描述下 MySQL 中 InnoDB 支持的四种事务隔离级别名称，以及逐级之间的区别？\n" + "\n" + "(1)、事物的4种隔离级别\n" + "\n"
						+ "读未提交(read uncommitted)\n" + "读已提交(read committed)\n" + "可重复读(repeatable read)\n"
						+ "串行(serializable)\n" + "\n" + "(2)、不同级别的现象\n" + "\n"
						+ "Read Uncommitted:可以读取其他 session 未提交的脏数据。\n"
						+ "Read Committed:允许不可重复读取，但不允许脏读取。提交后，其他会话可以看到提交的数据。\n"
						+ "Repeatable Read: 禁止不可重复读取和脏读取、以及幻读(innodb 独有)。\n"
						+ "Serializable: 事务只能一个接着一个地执行，但不能并发执行。事务隔离级别最高。\n"
						+ "不同的隔离级别有不同的现象，并有不同的锁定/并发机制，隔离级别越高，数据库的并发性就越差。\n" + "\n" + "" + new Date(),
				"163 面试中其他的问题：\n" + "1、2 年 MySQL DBA 经验\n" + "复制代码\n" + "\n"
						+ "其中许多有水分，一看到简历自我介绍，说公司项目的时候，会写上 linux 系统维护，mssql server 项目，或者 oracle data gard 项目，一般如果有这些的话，工作在 3 年到 4年的话，他的 2 年 MySQL DBA 管理经验，是有很大的水分的。刚开始我跟领导说，这些\n"
						+ "不用去面试了，肯定 mysql dba 经验不足，领导说先面面看看，于是我就面了，结果很多人卡在基础知识这一环节之上，比如：\n" + "（ 1）有的卡在复制原理之上\n"
						+ "（ 2）有的卡在 binlog 的日志格式的种类和分别\n" + "（ 3）有的卡在 innodb 事务与日志的实现上。\n"
						+ "（ 4）有的卡在 innodb 与 myisam 的索引实现方式的理解上面。\n" + ".........\n"
						+ "个人觉得如果有过真正的 2 年 mysql 专职 dba 经验，那么肯定会在 mysql 的基本原理上有所研究，因为很多问题都不得不让你去仔细研究各种细节，而自 己研究过的细节肯定会记忆深刻，别人问起一定会说的头头是道，起码一些最基本的关键参数比如\n"
						+ "Seconds_Behind_Master 为 60 这个值 60 的准确涵义，面试了 10+的 mysql dba，没有一个说的准确，有的说不知道忘记了，有的说是差了 60 秒，有的说是与主上执行时间延后了 60 秒。"
						+ new Date(),
				"164 2 、对于简历中写有熟悉 mysql 高可用方案\n" + "\n"
						+ "我一般先问他现在管理的数据库架构是什么，如果他只说出了主从，而没有说任何 ha的方案，那么我就可以判断出他没有实际的 ha 经验。不过这时候也不能就是 断定他不懂mysql 高可用，也许是没有实际机会去使用，那么我就要问 mmm 以及 mha 以及mm+keepalived 等的原理\n"
						+ "实现方式以及它们之间的优 势和不足了，一般这种情况下，能说出这个的基本没有。mmm 那东西好像不靠谱，据说不稳定，但是有人在用的，我只在虚拟机上面用过，和mysql-router 比较像，都是指定可写的机器和只读机器。 MHA 的话一句话说不完，可以翻翻学委的笔记\n"
						+ "\n" + "" + new Date(),
				"165 3 、对于简历中写有批量 MySQL 数据库服务器的管理经验\n" + "\n"
						+ "这个如果他说有的话，我会先问他们现在实际线上的 mysql 数据库数量有多少，分多少个节点组，最后问这些节点组上面的 slow log 是如何组合在一起来统计分析的。如果这些他都答对了，那么我还有一问，就是现在手上有 600 台数据库，新来的机器， Mysql 都\n"
						+ "安装好了，那么你如 何在最快的时间里面把这 600 台 mysql 数据库的 mysqld 服务启动起来。这个重点在于最快的时间，而能准确回答出清晰思路的只有 2 个人。slow log 分析：可以通过一个管理服务器定时去各台 MySQL 服务器上面 mv 并且 cp slowlog，\n"
						+ "然后分析入库，页面展示。最快的时间里面启动 600 台服务器： 肯定是多线程。 可以用 pssh， ansible 等多线程批量管理服务器的工具\n" + "\n" + ""
						+ new Date(),
				"166 4 、对于有丰富的 SQL 优化的经验\n" + "\n"
						+ "首先问 mysql 中 sql 优化的思路，如果能准备说出来， ok，那么我就开始问 explain的各种参数了，重点是 select_type， type， possible_key, ref,rows,extra 等参数的各种\n"
						+ "值的含义，如果他都回答正确了，那么我再问 file sort 的含义以及什么时候会出现这个分析结果，如果这里他也回答对了，那么我就准备问 profile 分析了，如果这里他也答对了，那么我就会再问一个问 题，\n"
						+ "那是曾经 tx 问我的让我郁闷不已的问题，一个 6 亿的表 a，一个 3 亿的表 b，通过外间 tid 关联，你如何最快的查询出满足条件的第 50000 到第 50200中的这 200 条数据记录。\n"
						+ "Explain 在上面的题目中有了，这里就不说了。如何最快的查询出满足条件的第 50000 到第 50200 中的这 200 条数据记录？这个我想不出来！\n"
						+ "关于 explain 的各种参数，请参考： http://blog.csdn.net/mchdba/article/details/9190771\n" + "\n" + ""
						+ new Date(),
				"167 5、对于有丰富的数据库设计经验\n" + "\n"
						+ "这个对于数据库设计我真的没有太多的经验，我也就只能问问最基础的， mysql 中varchar(60) 60 是啥含义， int(30)中 30 是啥含义？ 如果他都回答对了，那么我就问 mysql中为什么要这么设计呢？\n"
						+ "如果他还回答对了，我就继续问 int(20)存储的数字的上限和下限是多少？这个问题难道了全部的 mysql dba 的应聘者，不得不佩服提出这个问题的金总的睿智啊，因为这个问题回答正确了，\n"
						+ "那么他确实认认真真地研究了 mysql 的设计中关于字段类型的细节。至 于丰富的设计数据库的经验，不用着急，这不我上面还有更加厉害的 dba吗，他会搞明白的，那就跟我无关了。\n"
						+ "varchar(60)的 60 表示最多可以存储 60 个字符。int(30)的 30 表示客户端显示这个字段的宽度。\n"
						+ "为何这么设计？说不清楚，请大家补充 。 int(20)的上限为 2147483647(signed)或者4294967295(unsigned)。\n" + "\n" + ""
						+ new Date(),
				"168 6 、关于 mysql 参数优化的经验\n" + "复制代码\n" + "\n"
						+ "首先问他它们线上 mysql 数据库是怎么安装的，如果说是 rpm 安装的，那么我就直接问调优参数了，如果是源码安装的，那么我就要问编译中的一些参数了，比如 my.cnf 以及存储引擎以及字符类型等等。然后从以下几个方面问起：\n"
						+ "（ 1） mysql 有哪些 global 内存参数，有哪些 local 内存参数。\n" + "Global:\n"
						+ "innodb_buffer_pool_size/innodb_additional_mem_pool_size/innodb_log_buffer_size/key_buffer_size/query_cache_size/table_open_cache/table_definition_cache/thread_cache_size\n"
						+ "Local:\n"
						+ "read_buffer_size/read_rnd_buffer_size/sort_buffer_size/join_buffer_size/binlog_cache_size/tmp_table_size/thread_stack/bulk_insert_buffer_size\n"
						+ "\n" + "（ 2） mysql 的写入参数需要调整哪些？重要的几个写参数的几个值得含义以及适用场景，\n"
						+ "比如 innodb_flush_log_at_trx_commit 等。 (求补充)\n" + "sync_binlog 设置为 1，保证 binlog 的安全性。\n"
						+ "innodb_flush_log_at_trx_commit：\n"
						+ "0：事务提交时不将 redo log buffer 写入磁盘(仅每秒进行 master thread 刷新，安全\n" + "性最差，性能最好)\n"
						+ "1：事务提交时将 redo log buffer 写入磁盘(安全性最好，性能最差， 推荐生产使用)\n"
						+ "2：事务提交时仅将 redo log buffer 写入操作系统缓存(安全性和性能都居中，当 mysql宕机但是操作系统不宕机则不丢数据，如果操作系统宕机，最多丢一秒数据)\n"
						+ "innodb_io_capacity/innodb_io_capacity_max：看磁盘的性能来定。如果是 HDD 可以设置为 200-几百不等。如果是 SSD，推荐为 4000 左右。 innodb_io_capacity_max 更大一些。\n"
						+ "innodb_flush_method 设置为 O_DIRECT。\n" + "\n"
						+ "（ 3） 读取的话，那几个全局的 pool 的值的设置，以及几个 local 的 buffer 的设置。\n" + "Global:\n"
						+ "innodb_buffer_pool_size:设置为可用内存的 50%-60%左右，如果不够，再慢慢上调。\n"
						+ "innodb_additional_mem_pool_size：采用默认值 8M 即可。\n" + "innodb_log_buffer_size:默认值 8M 即可。\n"
						+ "key_buffer_size:myisam 表需要的 buffer size，选择基本都用 innodb，所以采用默认的 8M 即可。\n" + "Local:\n"
						+ "join_buffer_size： 当 sql 有 BNL 和 BKA 的时候，需要用的 buffer_size(plain index\n"
						+ "scans, range index scans 的时候可能也会用到)。默认为 256k，建议设置为 16M-32M。\n"
						+ "read_rnd_buffer_size：当使用 mrr 时，用到的 buffer。默认为 256k，建议设置为16-32M。\n"
						+ "read_buffer_size:当顺序扫描一个 myisam 表，需要用到这个 buffer。或者用来决定memory table 的大小。或者所有的 engine 类型做如下操作：order by 的时候用 temporaryfile、 SELECT INTO … OUTFILE 'filename' 、 For caching results of nested queries。默认为 128K，建议为 16M。\n"
						+ "sort_buffer_size： sql 语句用来进行 sort 操作(order by,group by)的 buffer。如果 buffer 不够，则需要建立 temporary file。如果在 show global status 中发现有大量的 Sort_merge_passes 值，则需要考虑调大 sort_buffer_size。默认为 256k，建议设置为 16-32M。\n"
						+ "binlog_cache_size： 表示每个 session 中存放 transaction 的 binlog 的 cache size。默认 32K。一般使用默认值即可。如果有大事务，可以考虑调大。\n"
						+ "thread_stack： 每个进程都需要有，默认为 256K，使用默认值即可。\n" + "\n"
						+ "（ 4） 还有就是著名的 query cache 了，以及 query cache 的适用场景了，这里有一个陷阱，\n"
						+ "就是高并发的情况下，比如双十一的时候， query cache 开还是不开，开了怎么保证高并发，不开又有何别的考虑？建议关闭，上了性能反而更差。" + new Date(),
				"169 7、关于熟悉 mysql 的锁机制\n" + "\n" + "gap 锁， next-key 锁，以及 innodb 的行锁是怎么实现的，以及 myisam 的锁是怎么实现的等\n"
						+ "Innodb 的锁的策略为 next-key 锁，即 record lock+gap lock。是通过在 index 上加 lock 实现的，如果 index 为 unique index，则降级为 record lock,如果是普通 index，则为 next-key lock，如果没有 index，则直接锁住全表。 myisam 直接使用全表扫描。\n"
						+ "\n" + "" + new Date(),
				"170 8、 关于熟悉 mysql 集群的\n" + "\n"
						+ "我就问了 ndbd 的节点的启动先后顺序，再问配置参数中的内存配置几个重要的参数，再问 sql 节点中执行一个 join 表的 select 语句的实现流程是怎么走的？ ok，能回答的也只有一个。\n"
						+ "关于 mysql 集群入门资料，请参考： http://write.blog.csdn.net/postlist/1583151/all\n" + "\n" + ""
						+ new Date(),
				"171 9、 关于有丰富的备份经验的\n" + "复制代码\n" + "\n"
						+ "就问 mysqldump 中备份出来的 sql，如果我想 sql 文件中，一行只有一个 insert .... value()的话，怎么办？如果备份需要带上 master 的复制点信息怎么办？或者 xtrabackup 中如何\n"
						+ "做到实时在线备份的？以及 xtrabackup 是如何做到带上 master 的复制点的信息的？ 当前 xtrabackup 做增量备份的时候有何缺陷？能全部回答出来的没有一个，不过没有关系，只要回答出 mysqldump 或者xtrabackup 其中一个的也可以。\n"
						+ "1). --skip-extended-insert\n" + "2). --master-date=1\n"
						+ "3). 因为 xtrabackup 是多线程，一个线程不停地在拷贝新产生的 redo 文件，另外的线程去备份数据库，当所有表空间备份完成的时候，它会执行 flush table with read lock 操作\n"
						+ "锁住所有表，然后执行 show master status; 接着执行 flush engine logs; 最后解锁表。执行 show master status; 时就能获取到 mster 的复制点信息，执行 flush engine logs 强制把redo 文件刷新到磁盘。\n"
						+ "4). xtrabackup 增量备份的缺陷不了解，在线上用 xtrabackup 备份没有发现什么缺陷。" + new Date(),
				"172 10 、关于有丰富的线上恢复经验的\n" + "\n"
						+ "就问你现在线上数据量有多大，如果是 100G，你用 mysqldump 出来要多久，然后 mysql进去又要多久，如果互联网不允许延时的话，你又怎么做到 恢复单张表的时候保证 nagios不报警。如果有人说 mysqldump 出来 1 个小时就 ok 了，那么我就要问问他 db 服务器是\n"
						+ "啥配置了，如果他说 mysql 进去 50 分钟搞定了，那么我也要问问他 db 机器啥配置了，如果是普通的吊丝 pc server，那么真实性，大家懂得。然后如果你用 xtrabackup 备份要多久，恢复要多久，大家都知道 copy-back 这一步要很久，那么你有没有办法对这一块优化。\n"
						+ "\n" + "" + new Date(),
				"173 详解TCP的3次握手和4次挥手   https://blog.csdn.net/kingov/article/details/77155671" + new Date(),
				"174 sql语句面试   https://www.cnblogs.com/diffrent/p/8854995.html   " + new Date(), "" + new Date(),
				"175 jvm内存溢出分析\n" + "2016-11-07 13:47 by 钟绍威, 3689 阅读, 1 评论, 收藏, 编辑概述\n"
						+ "jvm中除了程序计数器，其他的区域都有可能会发生内存溢出\n" + "内存溢出是什么？\n"
						+ "当程序需要申请内存的时候，由于没有足够的内存，此时就会抛出OutOfMemoryError，这就是内存溢出\n" + "内存溢出和内存泄漏有什么区别？\n"
						+ "内存泄漏是由于使用不当，把一部分内存“丢掉了”，导致这部分内存不可用。\n"
						+ "当在堆中创建了对象，后来没有使用这个对象了，又没有把整个对象的相关引用设为null。此时垃圾收集器会认为这个对象是需要的，就不会清理这部分内存。这就会导致这部分内存不可用。\n"
						+ "所以内存泄漏会导致可用的内存减少，进而会导致内存溢出。\n" + "用到的jvm参数\n" + "下面为了说明溢出的情景，会执行一些实例代码，同时需要给jvm指定参数\n"
						+ "-Xms 堆最小容量(heap min size)\n" + "-Xmx 堆最大容量(heap max size)\n" + "-Xss 栈容量(stack size)\n"
						+ "-XX:PermSize=size 永生代最小容量\n" + "-XX:MaxPermSize=size 永生代最大容量\n" + "堆溢出\n"
						+ "堆是存放对象的地方，那么只要在堆中疯狂的创建对象，那么堆就会发生内存溢出。\n" + "https://www.cnblogs.com/wewill/p/6038528.html"
						+ new Date(),
				"176 https://www.cnblogs.com/panxuejun/p/8630779.html     JVM调优总结（7）：调优方法" + new Date(),
				"177 内存泄漏  无非是GC没起到该有的作用 或是加载了大对象   Java JVM 内存泄露——全解析和处理办法\n"
						+ "   https://www.cnblogs.com/ixenos/p/5674702.html?utm_source=itdadao&utm_medium=referral"
						+ new Date(),
				"178 内存泄漏  无非是GC没起到该有的作用 或是加载了大对象   Java JVM 内存泄露——全解析和处理办法\n"
						+ "   https://www.cnblogs.com/ixenos/p/5674702.html?utm_source=itdadao&utm_medium=referral"
						+ new Date(),
				"179 java stackoverflowerror与outofmemoryerror区别\n"
						+ "  https://blog.csdn.net/chenchaofuck1/article/details/51144223  记忆力不好的博客" + new Date(),
				"180 字符串作为值来传递，数组作为对象传递\n" + "\n" + "值传递是不改变原来属性的值的。\n" + "所以结果是：goodbbb\n" + "\n"
						+ "String 确定是值传递，。   https://zhidao.baidu.com/question/106796048.html" + new Date(),
				"181 \n" + "一次线上JVM调优实践，FullGC40次/天到10天一次的优化过程    https://blog.csdn.net/cml_blog/article/details/81057966"
						+ new Date(),
				"182 https://blog.csdn.net/varyall/article/details/80517977   性能案例-JVM频繁full GC 问题（JVM参数优化）"
						+ new Date(),
				"183 Minor GC、Major GC和Full GC之间的区别\n"
						+ "   https://www.cnblogs.com/yang-hao/p/5948207.html" + new Date(),
				"184 查看gc情况  stat -gc -t 4037 10s " + new Date(),
				"185 重入读写锁  这里重入有何含义   重入：此锁允许reader和writer按照 ReentrantLock 的样式重新获取读取锁或写入锁。在写入线程保持的所有写入锁都已经释放后，才允许重入reader使用读取锁。 重入 第二次进来  先进来发现资源锁住了，于是乎出去，等待锁释放再进来操作资源。"
						+ new Date(),
				"186 redis  面试总结  https://www.cnblogs.com/jiahaoJAVA/p/6244278.html" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(), "" + new Date(),
				"" + new Date(), "" + new Date(), "" + new Date());
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
