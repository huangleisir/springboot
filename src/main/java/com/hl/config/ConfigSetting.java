package com.hl.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * 
 * @Package: com.jst.park.common.util  
 * @ClassName: ConfigUtil 
 * @Description: TODO
 *
 * @author: Administrator 
 * @date: 2017年6月5日 下午3:37:17 
 * @version V1.0
 */
@Component
public class ConfigSetting {

	 @Autowired
	 private Environment env;
    
    private static Environment localEnv;
    
    @PostConstruct
    public void init() {
    	localEnv = this.env;
    	
    }
    public static int getIntProperty(String key){
    	return Integer.parseInt(localEnv.getProperty(key)) ;
    }
    
    
    public static String getProperty(String key){
    	return localEnv.getProperty(key) ;
    }
   
    public static String getProperty(String key,String defaultValue){
    	if (StringUtils.isEmpty((localEnv.getProperty(key)))) {
			return defaultValue ;
		}
    	return localEnv.getProperty(key) ;
    }

}