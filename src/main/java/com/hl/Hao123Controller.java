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
@RestController
public class Hao123Controller {
	 private static final Logger logger = LoggerFactory.getLogger(Hao123Controller.class);
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * @param value
	 * @return
	 */
	/*@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String demo(){
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return "welcome to us , demo";
	}*/
	
	
	@RequestMapping(value = "/baidu", method = RequestMethod.GET)
	public void baidu(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~baidu~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.baidu.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/wangyi", method = RequestMethod.GET)
	public void wangyi(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~wangyi~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.163.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/qq", method = RequestMethod.GET)
	public void tencentIndex(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.qq.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/douyu", method = RequestMethod.GET)
	public void douyu(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~douyu~~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.douyu.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/iqiyi", method = RequestMethod.GET)
	public void iqiyi(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~iqiyi~~~~~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.iqiyi.com"); 
		} catch (IOException e) {
			logger.info("跳转到爱奇艺门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/youku", method = RequestMethod.GET)
	public void youku(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~youku~~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.youku.com"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/imooc", method = RequestMethod.GET)
	public void imooc(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"~~~~~~imooc~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("https://www.imooc.com/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/panda", method = RequestMethod.GET)
	public void panda(HttpServletRequest req,HttpServletResponse resp){
		logger.info("~~~panda~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("https://www.panda.tv"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/yizhibo", method = RequestMethod.GET)
	public void yizhibo(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"~~~~yizhibo~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://new.yizhibo.com/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/38hao", method = RequestMethod.GET)
	public void hao38(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"~~~~~38hao~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.hellozz.cn/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/taobao", method = RequestMethod.GET)
	public void taobao(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~taobao~~~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("https://www.taobao.com/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/jd", method = RequestMethod.GET)
	public void jd(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~jd~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("https://www.jd.com/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	//  http://edu.51cto.com/?wwwdh0
	@RequestMapping(value = "/cto51", method = RequestMethod.GET)
	public void cto51(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0   ~~~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://edu.51cto.com/?wwwdh0"); 
		} catch (IOException e) {
			logger.info("跳转到51cto门户失败");
			e.printStackTrace();
		} 
	}	
	
//  http://edu.51cto.com/?wwwdh0
	@RequestMapping(value = "/bjsxt", method = RequestMethod.GET)
	public void bjsxt(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0  ~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.bjsxt.com/"); 
		} catch (IOException e) {
			logger.info("跳转到北京尚学堂门户失败");
			e.printStackTrace();
		} 
	}
	
//	http://study.163.com/
	@RequestMapping(value = "/study163", method = RequestMethod.GET)
	public void study163(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~cto51    http://edu.51cto.com/?wwwdh0  ~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://study.163.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易云课堂门户失败");
			e.printStackTrace();
		} 
	}
	
//	https://www.tmall.com
	@RequestMapping(value = "/tmall", method = RequestMethod.GET)
	public void tmall(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~tmall    https://www.tmall.com  ~~~~"+getIpAddress(req));
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
	//  https://coding.net/user
	@RequestMapping(value = "/coding", method = RequestMethod.GET)
	public void coding(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"---------------~~~~~~~~~~~~coding    https://coding.net/user ~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("https://coding.net/user"); 
		} catch (IOException e) {
			logger.info("跳转到码云门户失败");
			e.printStackTrace();
		} 
	}	
//	http://finance.sina.com.cn/stock/usstock/
	@RequestMapping(value = "/usstock", method = RequestMethod.GET)
	public void usstock(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"~~~~~~~usstock http://finance.sina.com.cn/stock/usstock/~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://finance.sina.com.cn/stock/usstock/"); 
		} catch (IOException e) {
			logger.info("跳转到美道门户失败");
			e.printStackTrace();
		} 
	}	
	//http://www.ximalaya.com/explore/
	@RequestMapping(value = "/ximalaya", method = RequestMethod.GET)
	public void ximalaya(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"~~~~~~~ximalaya http://www.ximalaya.com/explore/~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.ximalaya.com/explore/"); 
		} catch (IOException e) {
			logger.info("跳转到美道门户失败");
			e.printStackTrace();
		} 
	}	
	//http://www.qingting.fm/
	@RequestMapping(value = "/qingting", method = RequestMethod.GET)
	public void qingting(HttpServletRequest req,HttpServletResponse resp){
		logger.info(new Date()+"~~~~~~~qingting http://www.qingting.fm/~~~~"+getIpAddress(req));
		try {
			resp.sendRedirect("http://www.qingting.fm/"); 
		} catch (IOException e) {
			logger.info("跳转到qingting门户失败");
			e.printStackTrace();
		} 
	}	
	
	
    /** 
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
*  
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     *  
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
     * 192.168.1.100 
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
	
}
