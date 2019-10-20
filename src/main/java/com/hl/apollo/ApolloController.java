package com.hl.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/apollo")
public class ApolloController {
    @Value("${timeout:2354643}")
    private String value;


    @RequestMapping(value="/getValue", method = RequestMethod.GET)
    @ResponseBody
    public Object getValue(){

        return value;
    }
}
