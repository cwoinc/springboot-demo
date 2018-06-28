package com.example.demo.init;

import java.util.HashMap;
import java.util.Map;

public class SystemInit {

    /**
     * 记录用户每次请求时间:key为userId，value为请求时间毫秒值
     */
    private static Map<String, Object> requestTime = new HashMap<String, Object>();

    public static Map<String, Object> getRequestTime() {
        return requestTime;
    }

    /**
     * 系统初始化
     */
    public static void init() {
//        //清空本地用户登陆token缓存
//        RedisUtils.delete(ERedisDomain.TOKEN_LOGIN_USER);
//
//        //初始化ES连接池
//        ESUtils.initClient();
//
//        //启动系统主线程
//        SystemThread thread = new SystemThread();
//        thread.setDaemon(true);
//        thread.start();
    }

}
