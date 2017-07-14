package config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hl.interceptor.MyInterceptor;


@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	MyInterceptor myInterceptor  ;
	
	

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        
        InterceptorRegistration csrfInterceptorRegistration = registry.addInterceptor(myInterceptor);
        csrfInterceptorRegistration.addPathPatterns("/**");
        super.addInterceptors(registry);

    }
}
