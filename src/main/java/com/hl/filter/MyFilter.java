package com.hl.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@WebFilter(filterName = "myFilter", urlPatterns = "/*")
@Order(Integer.MAX_VALUE)
public class MyFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("MyFilter init............");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("MyFilter doFilter.........before");
		/*
		 * String name = request.getParameter("name"); if (StringUtils.isNotEmpty(name)
		 * && (name.startsWith("yingxiong") || name.startsWith("wangzhe"))) {
		 * logger.info("游戏生活丰富多彩啊"); }
		 */

		logger.info("request.getContentLength():\t" + "\t" + request.getContentLength());
		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}
		System.out.println("====================" + wholeStr);
		chain.doFilter(request, response);
		System.out.println("MyFilter doFilter.........after");
		logger.info("request.getContentLength():\t" + "\t" + request.getContentLength());
		// 打印请求方法，路径
		logger.info("request url:\t" + "\t" + request.getRemoteAddr());
		Map<String, String[]> map = request.getParameterMap();
		// 打印请求url参数
		if (map != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("request parameters:\t");
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				sb.append("[" + entry.getKey() + "=" + printArray(entry.getValue()) + "]");
			}
			logger.info(sb.toString());
		}

		// // 打印response
		// InputStream out = request.getInputStream();
		// String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
		// if (outBody != null) {
		// logger.info("response body:\t" + outBody);
		// }

		// response.setResponseBody(outBody);// 重要！！！
	}

	String printArray(String[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i < arr.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	@Override
	public void destroy() {
		System.out.println("MyFilter destroy..........");
	}
}