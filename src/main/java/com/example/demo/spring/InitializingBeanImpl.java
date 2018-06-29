package com.example.demo.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanImpl implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBeanImpl");
    }
    
    
}
