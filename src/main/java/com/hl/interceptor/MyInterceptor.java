package com.hl.interceptor;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class MyInterceptor implements HandlerInterceptor {
	static Logger log = Logger.getLogger(MyInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle MyInterceptor HandlerInterceptor(MyInterceptor.preHandle) !!!");
		System.out.println("____________________pre_________________________");
		return true;
		}
		 
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		log.info("postHandle !!!");
		System.out.println("____________________post_________________________");
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		log.info("afterCompletion HandlerInterceptor(TokenInterceptor.preHandle) excute success !!!");
		System.out.println("____________________afterCompletion_________________________");
	}
}

