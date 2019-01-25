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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;

import util.GsonUtil;
import util.SimpleThreadPoolUtils;

@RestController
public class Hao123Controller {
    private static final Logger logger = LoggerFactory.getLogger(Hao123Controller.class);

    /**
     * http://localhost:8080/&#x4e2d;&#x6587; &#x4e0d;&#x9519;&#xff0c;&#x662f;&#x53ef;&#x4ee5;&#x652f;&#x6301;&#x4e2d;&#x6587;&#x7684;
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

    @RequestMapping(value = "/wechat/portal", method = RequestMethod.GET)
    @ResponseBody
    public String demo(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("---------------~~~~~~~~~~~~/wechat/portal~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // echostr
        String echostr = req.getParameter("echostr");
        return echostr;
    }

    @RequestMapping(value = "/skip/{name}", method = RequestMethod.POST)
    public Object skip(@PathVariable("name") String name, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("---------------~~~~~~~~~~~~name~~~~~" + name);
        Map<String, Object> map = new HashMap<String, Object>();
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
                asyncSkipToPage("beitaiYouku");
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
                map.put("url", "https://so.youku.com/search_video/q_%E8%BD%A6%E4%BA%8B%E5%84%BF?spm=a2h0k.11417342.searcharea.dbutton&_t=1539874305401");
                break;
            case "38haoBigFish":
                asyncSkipToPage("38haoBigFish");
                map.put("url", "http://i.youku.com/u/UMTUxMTg3NjU0MA==?spm=a2h0k.11417342.soresults.dtitle");
                break;
            case "daVkai8":
                asyncSkipToPage("daVkai8");
                map.put("url", "https://www.iqiyi.com/v_19rr3l9sac.html");
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
                map.put("url", "https://study.163.com/");
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
            case "pconline":
                asyncSkipToPage("pconline");
                map.put("url", "https://www.pconline.com.cn/");
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
                asyncSkipToPage("gitee");
                map.put("url", "https://gitee.com/");
                break;
            case "recaihotline":
                asyncSkipToPage("recaihotline");
                map.put("url", "http://www.cjol.com/");
                break;
            case "dangdang":
                asyncSkipToPage("dangdang");
                map.put("url", "http://book.dangdang.com/");
                break;
            case "autoHome":
                asyncSkipToPage("autoHome");
                map.put("url", "https://www.autohome.com.cn/");
                break;
            case "zealerChina":
                asyncSkipToPage("zealerChina");
                map.put("url", "http://i.youku.com/u/UMjI0ODEwMzQ4?spm=a2h0k.11417342.soresults.dtitle");
                break;
            case "springDotIO":
                asyncSkipToPage("springDotIO");
                map.put("url", "https://spring.io/");
                break;
            case "startSpringIO":
                asyncSkipToPage("startSpringIO");
                map.put("url", "https://start.spring.io/");
                break;
            case "redisDotIO":
                asyncSkipToPage("redisDotIO");
                map.put("url", "https://redis.io/");
                break;
            case "dubboOfficial":
                asyncSkipToPage("dubboOfficial");
                map.put("url", "http://dubbo.apache.org/en-us/index.html");
                break;
            case "mybatis":
                asyncSkipToPage("mybatis");
                map.put("url", "http://www.mybatis.org/mybatis-3/zh/source-repository.html");
                break;
            case "huangyeshengyan":
                asyncSkipToPage("荒野盛宴");
                map.put("url", "https://v.qq.com/x/cover/uwhaofqt6r06hvp.html");
                break;
            case "zk":
                asyncSkipToPage("zookeeper");
                map.put("url", "https://zookeeper.apache.org/");
                break;
            case "wangzherongyao":
                asyncSkipToPage("王者荣耀职业赛");
                map.put("url", "http://pvp.qq.com/match/center.shtml?ADTAG=pvp.index.nav.matchcenter");
                break;
            case "yingxionglianmeng":
                asyncSkipToPage("英雄联盟职业赛");
                map.put("url", "http://lpl.qq.com/es/worlds/2018/index.html");
                break;
            case "baihuaqiche":
                asyncSkipToPage("白话汽车");
                map.put("url", "https://i.youku.com/u/UMTMzMDc3NjgyOA==?spm=a2h0k.11417342.soresults.dname");
                break;
            case "ceshihao":
                asyncSkipToPage("微信测试号");
                map.put("url", "https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index");
                break;
            case "sina":
                asyncSkipToPage("sina");
                map.put("url", "https://www.sina.com.cn/");
                break;
            case "yeshengchufang":
                asyncSkipToPage("sina");
                map.put("url", "https://www.mgtv.com/b/326658/4667728.html?cxid=95kqkw8n6");
                break;
            case "toutiao":
                asyncSkipToPage("今日头条");
                map.put("url", "https://www.toutiao.com/");
                break;
            case "wechatDevDoc":
                asyncSkipToPage("微信支付开发文档");
                map.put("url", "https://pay.weixin.qq.com/wiki/doc/api/index.html");
                break;
            case "aliPayDevDoc":
                asyncSkipToPage("支付宝支付开发文档");
                map.put("url", "https://openhome.alipay.com/developmentDocument.htm");
                break;
            case "token":
                asyncSkipToPage("测试号access_token");
                map.put("url", Hao123Application.tokenShow);
                map.put("token_time", Hao123Application.token_time);
                break;
            case "shenzhenzhujianju":
                asyncSkipToPage("shenzhenzhujianju");
                map.put("url", "http://www.szjs.gov.cn/zfbzfw/");
                break;
            case "xinwen1plus1":
                asyncSkipToPage("xinwen1plus1");
                map.put("url", "http://tv.cctv.com/lm/xinwen1j1/");
                break;
            case "fandengdushu":
                asyncSkipToPage("樊登读书会");
                map.put("url", "http://www.szdushu.com/index.html");
                break;
            case "weather":
                asyncSkipToPage("weather");
                String str = HttpClientUtil.doGet("http://api.k780.com/?app=weather.today&weaid=1&appkey=37513&sign=03ece6e534315f18bb18cca05465217f&format=json", null);
                Map<String, String> map2 = GsonUtil.GsonToMaps(str);
                Map<String, String> resultMap = GsonUtil.GsonToMaps(GsonUtil.GsonString(map2.get("result")));
                logger.info(GsonUtil.GsonString(resultMap));
                map.put("resultMap", resultMap);
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
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
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
