package com.hl.config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hl.interceptor.MyInterceptor;

@Component
@Configuration
@EnableWebMvc
public class InterceptorConfig extends WebMvcConfigurerAdapter {

/*	@Autowired
	MyInterceptor myInterceptor  ;*/
	
	

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new MyInterceptor()).addPathPatterns("/*");
        super.addInterceptors(registry);

    }
}
/*
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new HandlerInterceptorAdapter() {

          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                   Object handler) throws Exception {
              System.out.println("interceptor====222");
              return true;
          }
      }).addPathPatterns("/demo/**");
  }
}*/

