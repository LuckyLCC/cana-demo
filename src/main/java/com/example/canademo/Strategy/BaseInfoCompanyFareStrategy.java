package com.example.canademo.Strategy;

import com.example.canademo.factory.StrategyFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: liuchang
 * @CreateTime: 2022-10-14  14:53
 */

@Component
public class BaseInfoCompanyFareStrategy extends IStrategy{
    @Override
    public String handleData(String sourceData) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("baseInfoCompanyFare", this);
    }
}
