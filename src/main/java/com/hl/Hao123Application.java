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
				String str = HttpClientUtil.httpPostWithJSON(
						"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token,
						"{\"touser\": \"orR4l1rIdRi1-xsJxWJezAA2QrXE\", \"msgtype\": \"text\", \"text\": {\"content\": \" "
								+ content + " \" }}");
				log.info("2222222222result2222, {}" + str);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}, 2, 19, TimeUnit.MINUTES);

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
				"76 String.format(\"%s ￥%s\", tornCake.getDesc(), tornCake.price()  这也是一种拼接字符串的方式  JAVA字符串格式化-String.format()和MessageFormat的使用 https://blog.csdn.net/candyguy242/article/details/80782244",
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
						+ "ids 就是数组 int 类型的。\n" + "int ... ids 等价于 int[] ids");
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
