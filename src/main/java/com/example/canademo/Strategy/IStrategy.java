package com.example.canademo.Strategy;

import org.springframework.beans.factory.InitializingBean;

public interface IStrategy extends InitializingBean {

    //数据进一步组装
    String handleData(String sourceData);

    //数据上报
    void sendData(String sourceData);
}
