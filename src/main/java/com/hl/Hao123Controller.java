package com.hl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/skip/{name}", method = RequestMethod.GET)
	public Object skip(@PathVariable("name") String name, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		logger.info("---------------~~~~~~~~~~~~name~~~~~" + name);
		Map<String, String> map = new HashMap<String, String>();
		switch (name) {
		case "baidu":
			asyncSkipToPage("baidu");
			map.put("url", "http://www.baidu.com");
			break;
		case "github":
			asyncSkipToPage("github");
			map.put("url", "https://github.com/huangleisir");
			break;
		case "panda":
			asyncSkipToPage("panda");
			map.put("url", "https://www.panda.tv");
			break;
		case "beitaiYouku":
			asyncSkipToPage("panda");
			map.put("url", "http://i.youku.com/u/UMzU4MjIwMTcy?spm=a2h0k.11417342.soresults.dtitle");
			break;
		case "ershouchezhishidajiangtang":
			asyncSkipToPage("二手车知识大讲堂");
			map.put("url", "http://www.iqiyi.com/u/1350641189");
			break;
		case "kaikeba":
			asyncSkipToPage("开课吧");
			map.put("url", "https://www.kaikeba.com/opencourses");
			break;
		case "bilibili":
			asyncSkipToPage("bilibili");
			map.put("url", "https://www.bilibili.com/");
			break;
		case "cheshier":
			asyncSkipToPage("车事儿");
			map.put("url",
					"https://so.youku.com/search_video/q_%E8%BD%A6%E4%BA%8B%E5%84%BF?spm=a2h0k.11417342.searcharea.dbutton&_t=1539874305401");
			break;
		case "38haoBigFish":
			asyncSkipToPage("38haoBigFish");
			map.put("url", "http://i.youku.com/u/UMTUxMTg3NjU0MA==?spm=a2h0k.11417342.soresults.dtitle");
			break;
		case "gitchat":
			asyncSkipToPage("gitchat");
			map.put("url", "http://www.gitbook.cn");
			break;
		case "wangyi":
			asyncSkipToPage("wangyi");
			map.put("url", "http://www.163.com");
			break;
		case "qq":
			asyncSkipToPage("qq");
			map.put("url", "http://www.qq.com");
			break;
		case "douyu":
			asyncSkipToPage("douyu");
			map.put("url", "http://www.douyu.com");
			break;
		case "iqiyi":
			asyncSkipToPage("douyu");
			map.put("url", "http://www.iqiyi.com");
			break;
		case "youku":
			asyncSkipToPage("youku");
			map.put("url", "http://www.youku.com");
			break;
		case "imooc":
			asyncSkipToPage("imooc");
			map.put("url", "https://www.imooc.com/");
			break;
		case "yizhibo":
			asyncSkipToPage("yizhibo");
			map.put("url", "http://new.yizhibo.com/");
			break;
		case "caijinglangxianping":
			asyncSkipToPage("caijinglangxianping");
			map.put("url", "http://www.iqiyi.com/a_19rrgu9qmt.html");
			break;
		case "zhangxiaoxiangMultiThread":
			asyncSkipToPage("zhangxiaoxiangMultiThread");
			map.put("url", "http://www.icoolxue.com/album/show/109");
			break;
		case "tianya":
			asyncSkipToPage("tianya");
			map.put("url", "http://focus.tianya.cn/");
			break;
		case "38hao":
			asyncSkipToPage("tianya");
			map.put("url", "http://www.hellozz.cn/");
			break;
		case "lilaoshuBigFish":
			asyncSkipToPage("lilaoshuBigFish");
			map.put("url", "http://i.youku.com/u/UMzA3ODE3NjcxNg==?spm=a2h0k.11417342.soresults.dtitle");
			break;
		case "taobao":
			asyncSkipToPage("taobao");
			map.put("url", "https://www.taobao.com/");
			break;
		case "jd":
			asyncSkipToPage("jd");
			map.put("url", "https://www.jd.com/");
			break;
		case "51cto":
			asyncSkipToPage("51cto");
			map.put("url", "http://edu.51cto.com/?wwwdh0");
			break;
		case "bjsxt":
			asyncSkipToPage("bjsxt");
			map.put("url", "http://www.bjsxt.com/");
			break;
		case "study163":
			asyncSkipToPage("study163");
			map.put("url", "http://www.bjsxt.com/");
			break;
		case "tmall":
			asyncSkipToPage("tmall");
			map.put("url", "https://www.tmall.com");
			break;
		case "coding":
			asyncSkipToPage("coding");
			map.put("url", "https://coding.net/user");
			break;
		case "usstock":
			asyncSkipToPage("usstock");
			map.put("url", "http://finance.sina.com.cn/stock/usstock/");
			break;
		case "ximalaya":
			asyncSkipToPage("ximalaya");
			map.put("url", "http://www.ximalaya.com/explore/");
			break;
		case "qingting":
			asyncSkipToPage("qingting");
			map.put("url", "http://www.qingting.fm/");
			break;
		case "lagou":
			asyncSkipToPage("lagou");
			map.put("url", "https://www.lagou.com/");
			break;
		case "gitee":
			asyncSkipToPage("lagou");
			map.put("url", "https://gitee.com/");
			break;
		case "recaihotline":
			asyncSkipToPage("lagou");
			map.put("url", "http://www.cjol.com/");
			break;
		case "autoHome":
			asyncSkipToPage("lagou");
			map.put("url", "https://www.autohome.com.cn/");
			break;
		case "zealerChina":
			asyncSkipToPage("lagou");
			map.put("url", "http://i.youku.com/u/UMjI0ODEwMzQ4?spm=a2h0k.11417342.soresults.dtitle");
			break;
		default:
			asyncSkipToPage("gitbook");
			map.put("url", "http://www.gitbook.cn");
			break;
		}
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
		map.put("url", "");
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

	private void asyncSkipToPage(String name) {
		SimpleThreadPoolUtils.getInstance().asyncThreadHandler(new Runnable() {
			@Override
			public void run() {
				logger.info("多线程，异步调用 日志打印方法" + Thread.currentThread().getName() + "---跳转到 " + name);
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

	// 从这里从springcloud拿到url

}
