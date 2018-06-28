package com.example.demo.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerImpl implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 执行操作
        System.out.println("ApplicationListenerImpl");
    }
}
