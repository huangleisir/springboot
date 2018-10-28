package com.hl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;
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
		String name = request.getParameter("name");
		// Map pathVars = (Map)
		// request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		// String name = null;
		/*
		 * if (null != pathVariables) name = (String) pathVariables.get("name");
		 */
		if (StringUtils.isNotEmpty(name) && (name.startsWith("yingxiong") || name.startsWith("wangzhe"))) {
			logger.info("游戏生活丰富多彩啊");
		}
		chain.doFilter(request, response);
		System.out.println("MyFilter doFilter.........after");
	}

	@Override
	public void destroy() {
		System.out.println("MyFilter destroy..........");
	}
}