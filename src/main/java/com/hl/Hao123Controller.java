package com.hl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@RequestMapping(value = "/baidu", method = RequestMethod.GET)
	public void baidu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("---------------~~~~~~~~~~~~baidu~~~~~" + getIpAddress(req));
		resp.sendRedirect("http://www.baidu.com");
		asyncSkipToPage("baidu");
	}

	@RequestMapping(value = "/gitchat", method = RequestMethod.GET)
	public void gitchat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("---------------~~~~~~~~~~~~gitchat~~~~~" + getIpAddress(req));
		resp.sendRedirect("https://gitbook.cn");
		asyncSkipToPage("gitchat");
	}

	@RequestMapping(value = "/cheshier", method = RequestMethod.GET)
	public void cheshier(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info(Thread.currentThread().getName() + "---------线程池,调用------~~~~~~~~~~~~cheshier~~~~~"
				+ getIpAddress(req));
		resp.sendRedirect(
				"https://so.youku.com/search_video/q_%E8%BD%A6%E4%BA%8B%E5%84%BF?spm=a2h0k.11417342.searcharea.dbutton&_t=1539874305401");
		asyncSkipToPage("cheshier");
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
	public void wangyi(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------asyncHandle~~~~~~~~~~~~wangyi~~~~~" + getIpAddress(req));
		asyncHandle(resp, "https://www.163.com/");
	}

	@RequestMapping(value = "/qq", method = RequestMethod.GET)
	public void tencentIndex(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.qq.com");
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/douyu", method = RequestMethod.GET)
	public void douyu(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~douyu~~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.douyu.com");
			asyncSkipToPage("douyu");
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/iqiyi", method = RequestMethod.GET)
	public void iqiyi(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~iqiyi~~~~~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.iqiyi.com");
		} catch (IOException e) {
			logger.info("跳转到爱奇艺门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/youku", method = RequestMethod.GET)
	public void youku(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~youku~~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.youku.com");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/imooc", method = RequestMethod.GET)
	public void imooc(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~imooc~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.imooc.com/");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/panda", method = RequestMethod.GET)
	public void panda(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("~~~panda~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.panda.tv");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/yizhibo", method = RequestMethod.GET)
	public void yizhibo(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~yizhibo~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://new.yizhibo.com/");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/38hao", method = RequestMethod.GET)
	public void hao38(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~38hao~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.hellozz.cn/");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/38haoBigFish", method = RequestMethod.GET)
	public void hao38BigFish(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~38hao~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://i.youku.com/u/UMTUxMTg3NjU0MA==?spm=a2h0k.11417342.soresults.dtitle");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/lilaoshuBigFish", method = RequestMethod.GET)
	public void lilaoshuBigFish(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~38hao~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://i.youku.com/u/UMzA3ODE3NjcxNg==?spm=a2h0k.11417342.soresults.dtitle");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/taobao", method = RequestMethod.GET)
	public void taobao(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~taobao~~~~~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.taobao.com/");
		} catch (IOException e) {
			logger.error("跳转到taobao门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/jd", method = RequestMethod.GET)
	public void jd(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~jd~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.jd.com/");
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/tianya", method = RequestMethod.GET)
	public void tianya(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~jd~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://focus.tianya.cn/");
		} catch (IOException e) {
			logger.info("跳转到tianya门户失败");
			e.printStackTrace();
		}
	}

	// http://edu.51cto.com/?wwwdh0
	@RequestMapping(value = "/cto51", method = RequestMethod.GET)
	public void cto51(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0   ~~~~~~"
				+ getIpAddress(req));
		try {
			resp.sendRedirect("http://edu.51cto.com/?wwwdh0");
		} catch (IOException e) {
			logger.info("跳转到51cto门户失败");
			e.printStackTrace();
		}
	}

	// http://edu.51cto.com/?wwwdh0
	@RequestMapping(value = "/bjsxt", method = RequestMethod.GET)
	public void bjsxt(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~bjsxt    http://edu.51cto.com/?wwwdh0  ~~~~"
				+ getIpAddress(req));
		try {
			resp.sendRedirect("http://www.bjsxt.com/");
		} catch (IOException e) {
			logger.info("跳转到北京尚学堂门户失败");
			e.printStackTrace();
		}
	}

	// http://study.163.com/
	@RequestMapping(value = "/study163", method = RequestMethod.GET)
	public void study163(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0  ~~~~"
				+ getIpAddress(req));
		try {
			resp.sendRedirect("http://study.163.com");
		} catch (IOException e) {
			logger.info("跳转到网易云课堂门户失败");
			e.printStackTrace();
		}
	}

	// https://www.tmall.com
	@RequestMapping(value = "/tmall", method = RequestMethod.GET)
	public void tmall(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "---------------~~~~~~~~~~~~tmall    https://www.tmall.com  ~~~~" + getIpAddress(req));
		RateLimiter limiter = RateLimiter.create(1); // 每秒不超过10个任务被提交
		for (int i = 0; i < 3; i++) {
			limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
			logger.info("call execute.." + i);
		}
		try {
			resp.sendRedirect("https://www.tmall.com");
		} catch (IOException e) {
			logger.info("跳转到网易云课堂门户失败");
			e.printStackTrace();
		}
	}

	// https://coding.net/user
	@RequestMapping(value = "/coding", method = RequestMethod.GET)
	public void coding(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(
				new Date() + "---------------~~~~~~~~~~~~coding    https://coding.net/user ~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://coding.net/user");
		} catch (IOException e) {
			logger.info("跳转到码云门户失败");
			e.printStackTrace();
		}
	}

	// http://finance.sina.com.cn/stock/usstock/
	@RequestMapping(value = "/usstock", method = RequestMethod.GET)
	public void usstock(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~usstock http://finance.sina.com.cn/stock/usstock/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://finance.sina.com.cn/stock/usstock/");
		} catch (IOException e) {
			logger.info("跳转到美道门户失败");
			e.printStackTrace();
		}
	}

	// http://www.ximalaya.com/explore/
	@RequestMapping(value = "/ximalaya", method = RequestMethod.GET)
	public void ximalaya(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~ximalaya http://www.ximalaya.com/explore/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.ximalaya.com/explore/");
		} catch (IOException e) {
			logger.info("跳转到ximalaya门户失败");
			e.printStackTrace();
		}
	}

	// http://www.qingting.fm/
	@RequestMapping(value = "/qingting", method = RequestMethod.GET)
	public void qingting(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~qingting http://www.qingting.fm/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.qingting.fm/");
		} catch (IOException e) {
			logger.info("跳转到qingting门户失败");
			e.printStackTrace();
		}
	}

	// https://www.lagou.com/
	@RequestMapping(value = "/lagou", method = RequestMethod.GET)
	public void lagou(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~拉勾    https://www.lagou.com/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.lagou.com/");
		} catch (IOException e) {
			logger.info("跳转到拉勾门户失败");
			e.printStackTrace();
		}
	}

	// https://www.lagou.com/
	@RequestMapping(value = "/recaihotline", method = RequestMethod.GET)
	public void recaihotline(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~recaihotline    http://www.cjol.com/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://www.cjol.com/");
		} catch (IOException e) {
			logger.info("跳转到人才热线门户失败");
			e.printStackTrace();
		}
	}

	// https://gitee.com/
	@RequestMapping(value = "/gitee", method = RequestMethod.GET)
	public void gitee(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~gitee    https://gitee.com/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://gitee.com/");
		} catch (IOException e) {
			logger.info("跳转到gitee门户失败");
			e.printStackTrace();
		}
	}

	// https://gitee.com/
	@RequestMapping(value = "/github", method = RequestMethod.GET)
	public void github(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~github    https://github.com/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://github.com/");
		} catch (IOException e) {
			logger.info("跳转到github门户失败");
			e.printStackTrace();
		}
	}

	// https://www.autohome.com.cn/
	@RequestMapping(value = "/autoHome", method = RequestMethod.GET)
	public void autohome(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~~~~autohome    https://www.autohome.com.cn/~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.autohome.com.cn/");
		} catch (IOException e) {
			logger.info("跳转到autohome门户失败");
			e.printStackTrace();
		}
	}

	//
	@RequestMapping(value = "/scBaidu", method = RequestMethod.GET)
	public void scBaidu(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~~~~~" + getIpAddress(req));
		String str = HttpClientUtil.doGet("http://116.85.22.96:8769/hello/baidu?name=2423&token=234234", null);
		try {
			resp.sendRedirect(str);
		} catch (IOException e) {
			logger.info("跳转到autohome门户失败");
			e.printStackTrace();
		}
	}

	//
	@RequestMapping(value = "/scQQ", method = RequestMethod.GET)
	public void scQQ(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~~~~~" + getIpAddress(req));
		String str = HttpClientUtil.doGet("http://116.85.22.96:8769/hello/qq?name=2423&token=234234", null);
		try {
			resp.sendRedirect(str);
		} catch (IOException e) {
			logger.info("跳转到autohome门户失败");
			e.printStackTrace();
		}
	}

	//
	@RequestMapping(value = "/sc163", method = RequestMethod.GET)
	public void sc163(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~~~~~" + getIpAddress(req));
		String str = HttpClientUtil.doGet("http://116.85.22.96:8769/hello/163?name=2423&token=234234", null);
		try {
			resp.sendRedirect(str);
		} catch (IOException e) {
			logger.info("跳转到autohome门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/scImooc", method = RequestMethod.GET)
	public void scImooc(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~scImooc~~~~~" + getIpAddress(req));
		String str = HttpClientUtil.doGet("http://116.85.22.96:8769/hello/imooc?name=2423&token=234234", null);
		try {
			resp.sendRedirect(str);
		} catch (IOException e) {
			logger.info("跳转到autohome门户失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/bilibili", method = RequestMethod.GET)
	public void bilibili(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~bilibili~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("https://www.bilibili.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/beitaiYouku", method = RequestMethod.GET)
	public void beitaiYouku(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~beitaiYouku~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://i.youku.com/u/UMzU4MjIwMTcy?spm=a2h0k.11417342.soresults.dtitle");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/zealerChina", method = RequestMethod.GET)
	public void zealerChina(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(new Date() + "~~~~通过调用springcloud~~~zealerChina~~~~" + getIpAddress(req));
		try {
			resp.sendRedirect("http://i.youku.com/u/UMjI0ODEwMzQ4?spm=a2h0k.11417342.soresults.dtitle");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
