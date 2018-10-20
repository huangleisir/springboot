package com.hl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;

import util.SimpleThreadPoolUtils;

@RestController
public class Hao123Controller {
	private static final Logger logger = LoggerFactory.getLogger(Hao123Controller.class);

	/**
	 * http://localhost:8080/中文 不错，是可以支持中文的
	 * 
	 * @param value
	 * @return
	 * @throws IOException
	 */
	/*
	 * @RequestMapping(value = "/home", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String demo(){
	 * logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
	 * return "welcome to us , demo"; }
	 */

	@RequestMapping(value = "/baidu", method = RequestMethod.GET)
	public Object baidu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("---------------~~~~~~~~~~~~baidu~~~~~" + getIpAddress(req));
		asyncSkipToPage("baidu");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.baidu.com");
		return map;
	}

	@RequestMapping(value = "/gitchat", method = RequestMethod.GET)
	public Object gitchat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("---------------~~~~~~~~~~~~gitchat~~~~~" + getIpAddress(req));
		asyncSkipToPage("gitchat");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.baidu.com");
		return map;

	}

	@RequestMapping(value = "/cheshier", method = RequestMethod.GET)
	public Object cheshier(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info(Thread.currentThread().getName() + "---------线程池,调用------~~~~~~~~~~~~cheshier~~~~~"
				+ getIpAddress(req));
		asyncSkipToPage("cheshier");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url",
				"https://so.youku.com/search_video/q_%E8%BD%A6%E4%BA%8B%E5%84%BF?spm=a2h0k.11417342.searcharea.dbutton&_t=1539874305401");
		return map;
	}

	private void asyncSkipToPage(String name) {
		SimpleThreadPoolUtils.getInstance().asyncThreadHandler(new Runnable() {
			@Override
			public void run() {
				logger.info("多线程，异步调用" + Thread.currentThread().getName() + "---跳转到 " + name);
			}
		});
	}

	void asyncHandle(HttpServletResponse resp, String url) {
		SimpleThreadPoolUtils.getInstance().asyncThreadHandler(new Runnable() {
			@Override
			public void run() {
				try {
					resp.sendRedirect(url);
				} catch (IOException e) {
					logger.info("跳转到网易门户失败");
					e.printStackTrace();
				}

			}
		});
	}

	@RequestMapping(value = "/wangyi", method = RequestMethod.GET)
	@ResponseBody
	public Object wangyi(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------asyncHandle~~~~~~~~~~~~wangyi~~~~~" + getIpAddress(req));
		asyncSkipToPage("网易门户");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.163.com");
		return map;
	}

	@RequestMapping(value = "/qq", method = RequestMethod.GET)
	public Object tencentIndex(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~qq~~~~~~~~~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("腾讯门户");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.qq.com");
		return map;
	}

	@RequestMapping(value = "/douyu", method = RequestMethod.GET)
	public Object douyu(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~douyu~~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("douyu");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.douyu.com");
		return map;
	}

	@RequestMapping(value = "/iqiyi", method = RequestMethod.GET)
	public Object iqiyi(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~iqiyi~~~~~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("aiqiyi");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.iqiyi.com");
		return map;
	}

	@RequestMapping(value = "/youku", method = RequestMethod.GET)
	public Object youku(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~youku~~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("youku");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.youku.com");
		return map;
	}

	@RequestMapping(value = "/imooc", method = RequestMethod.GET)
	public Object imooc(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~imooc~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("imooc");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.imooc.com/");
		return map;
	}

	@RequestMapping(value = "/panda", method = RequestMethod.GET)
	public Object panda(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("~~~panda~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("panda");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.panda.tv");
		return map;
	}

	@RequestMapping(value = "/yizhibo", method = RequestMethod.GET)
	public Object yizhibo(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~yizhibo~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("yizhibo");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://new.yizhibo.com/");
		return map;
	}

	@RequestMapping(value = "/ershouchezhishidajiangtang", method = RequestMethod.GET)
	public Object ershouchezhishidajiangtang(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~ershouchezhishidajiangtang~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("二手车知识大讲堂");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.iqiyi.com/u/1350641189");
		return map;
	}

	@RequestMapping(value = "/caijinglangxianping", method = RequestMethod.GET)
	public Object caijinglangxianping(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~caijinglangxianping~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("财经郎咸平");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.iqiyi.com/a_19rrgu9qmt.html");
		return map;
	}

	@RequestMapping(value = "/38hao", method = RequestMethod.GET)
	public Object hao38(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~38hao~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("38hao");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.hellozz.cn/");
		return map;
	}

	@RequestMapping(value = "/38haoBigFish", method = RequestMethod.GET)
	public Object hao38BigFish(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~38hao~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("38haoBigFish");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://i.youku.com/u/UMTUxMTg3NjU0MA==?spm=a2h0k.11417342.soresults.dtitle");
		return map;
	}

	@RequestMapping(value = "/lilaoshuBigFish", method = RequestMethod.GET)
	public Object lilaoshuBigFish(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~38hao~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("lilaoshuBigFish");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://i.youku.com/u/UMzA3ODE3NjcxNg==?spm=a2h0k.11417342.soresults.dtitle");
		return map;
	}

	@RequestMapping(value = "/taobao", method = RequestMethod.GET)
	public Object taobao(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~taobao~~~~~~~~" + getIpAddress(req));
		asyncSkipToPage("taobao");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.taobao.com/");
		return map;
	}

	@RequestMapping(value = "/jd", method = RequestMethod.GET)
	public Object jd(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~jd~~~~" + getIpAddress(req));
		asyncSkipToPage("京东");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.jd.com/");
		return map;
	}

	@RequestMapping(value = "/tianya", method = RequestMethod.GET)
	public Object tianya(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~jd~~~~" + getIpAddress(req));
		asyncSkipToPage("天涯");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://focus.tianya.cn/");
		return map;
	}

	// http://edu.51cto.com/?wwwdh0
	@RequestMapping(value = "/cto51", method = RequestMethod.GET)
	public Object cto51(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0   ~~~~~~"
				+ getIpAddress(req));
		asyncSkipToPage("cto51");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.hellozz.cn/");
		return map;
	}

	// http://edu.51cto.com/?wwwdh0
	@RequestMapping(value = "/bjsxt", method = RequestMethod.GET)
	public Object bjsxt(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~bjsxt    http://edu.51cto.com/?wwwdh0  ~~~~"
				+ getIpAddress(req));
		asyncSkipToPage("北京尚学堂");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.bjsxt.com/");
		return map;
	}

	// http://study.163.com/
	@RequestMapping(value = "/study163", method = RequestMethod.GET)
	public Object study163(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0  ~~~~"
				+ getIpAddress(req));
		asyncSkipToPage("网易课堂");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://study.163.com");
		return map;
	}

	// https://www.tmall.com
	@RequestMapping(value = "/tmall", method = RequestMethod.GET)
	public Object tmall(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~tmall    https://www.tmall.com  ~~~~" + getIpAddress(req));
		RateLimiter limiter = RateLimiter.create(1); // 每秒不超过10个任务被提交
		for (int i = 0; i < 3; i++) {
			limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
			logger.info("call execute.." + i);
		}
		asyncSkipToPage("天猫");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.tmall.com");
		return map;
	}

	// https://coding.net/user
	@RequestMapping(value = "/coding", method = RequestMethod.GET)
	public Object coding(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(
				new Date() + "---------------~~~~~~~~~~~~coding    https://coding.net/user ~~~~" + getIpAddress(req));
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://coding.net/user");
		return map;
	}

	// http://finance.sina.com.cn/stock/usstock/
	@RequestMapping(value = "/usstock", method = RequestMethod.GET)
	public Object usstock(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~usstock http://finance.sina.com.cn/stock/usstock/~~~~" + getIpAddress(req));
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://finance.sina.com.cn/stock/usstock/");
		return map;
	}

	// http://www.ximalaya.com/explore/
	@RequestMapping(value = "/ximalaya", method = RequestMethod.GET)
	public Object ximalaya(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~ximalaya http://www.ximalaya.com/explore/~~~~" + getIpAddress(req));
		asyncSkipToPage("ximalaya");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.ximalaya.com/explore/");
		return map;
	}

	// http://www.qingting.fm/
	@RequestMapping(value = "/qingting", method = RequestMethod.GET)
	public Object qingting(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~qingting http://www.qingting.fm/~~~~" + getIpAddress(req));
		asyncSkipToPage("qingting");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.qingting.fm/");
		return map;
	}

	// https://www.lagou.com/
	@RequestMapping(value = "/lagou", method = RequestMethod.GET)
	public Object lagou(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~拉勾    https://www.lagou.com/~~~~" + getIpAddress(req));
		asyncSkipToPage("lagou");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.lagou.com/");
		return map;
	}

	// https://www.lagou.com/
	@RequestMapping(value = "/recaihotline", method = RequestMethod.GET)
	public Object recaihotline(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~recaihotline    http://www.cjol.com/~~~~" + getIpAddress(req));
		asyncSkipToPage("人才热线门户");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.cjol.com/");
		return map;
	}

	// https://gitee.com/
	@RequestMapping(value = "/gitee", method = RequestMethod.GET)
	public Object gitee(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~gitee    https://gitee.com/~~~~" + getIpAddress(req));
		asyncSkipToPage("gitee");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://gitee.com/");
		return map;
	}

	// https://gitee.com/
	@RequestMapping(value = "/github", method = RequestMethod.GET)
	public Object github(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~github    https://github.com/~~~~" + getIpAddress(req));
		asyncSkipToPage("github");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://github.com/");
		return map;
	}

	// https://www.autohome.com.cn/
	@RequestMapping(value = "/autoHome", method = RequestMethod.GET)
	public Object autohome(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~autohome    https://www.autohome.com.cn/~~~~" + getIpAddress(req));
		asyncSkipToPage("autoHome");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.autohome.com.cn/");
		return map;
	}

	@RequestMapping(value = "/bilibili", method = RequestMethod.GET)
	public Object bilibili(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~bilibili~~~~" + getIpAddress(req));
		asyncSkipToPage("bilibili");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "https://www.bilibili.com/");
		return map;
	}

	@RequestMapping(value = "/beitaiYouku", method = RequestMethod.GET)
	public Object beitaiYouku(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~beitaiYouku~~~~" + getIpAddress(req));
		asyncSkipToPage("备胎说车-优酷");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://i.youku.com/u/UMzU4MjIwMTcy?spm=a2h0k.11417342.soresults.dtitle");
		return map;
	}

	@RequestMapping(value = "/zealerChina", method = RequestMethod.GET)
	public Object zealerChina(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~zealerChina~~~~" + getIpAddress(req));
		asyncSkipToPage("zealerChina");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://i.youku.com/u/UMjI0ODEwMzQ4?spm=a2h0k.11417342.soresults.dtitle");
		return map;
	}

	@RequestMapping(value = "/zhangxiaoxiangMultiThread", method = RequestMethod.GET)
	public Object zhangxiaoxiangMultiThread(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~zealerChina~~~~" + getIpAddress(req));
		asyncSkipToPage("张孝祥-多线程");
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.icoolxue.com/album/show/109");
		return map;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 从这里从springcloud拿到url

}
