package com.example.canademo.Strategy;

import com.example.canademo.factory.StrategyFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: liuchang
 * @CreateTime: 2022-10-14  14:55
 */


@Component
public class BaseInfoVehicleStrategy extends IStrategy{



    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("baseInfoVehicle", this);
    }

    @Override
    String handleData(String sourceData) {
        return null;
    }
}
