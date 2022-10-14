package com.example.canademo.Strategy;

import com.example.canademo.factory.StrategyFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: liuchang
 * @CreateTime: 2022-10-14  14:55
 */


@Component
public class BaseInfoVehicleStrategy implements IStrategy{

    @Override
    public String handleData(String sourceData) {
        System.out.println("baseInfoVehicle的handleData");
        return null;
    }

    @Override
    public void sendData(String sourceData) {
        System.out.println("baseInfoVehicle的发送数据方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("baseInfoVehicle", this);
    }
}
