package com.example.canademo.Strategy;

import org.springframework.beans.factory.InitializingBean;

public abstract class IStrategy implements InitializingBean {


    //数据上报
    public void sendData(String sourceData) {
        //处理数据
        String s = handleData(sourceData);

        //发送kafka
    }

    //数据进一步组装
    public abstract String handleData(String sourceData);
}
