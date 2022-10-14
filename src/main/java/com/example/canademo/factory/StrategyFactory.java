package com.example.canademo.factory;

import com.example.canademo.Strategy.IStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: liuchang
 * @CreateTime: 2022-10-14  14:50
 */

public class StrategyFactory {


    private static final Map<String, IStrategy> strategies = new HashMap<>();

//    static {
//        strategies.put("baseInfoCompanyFare", new BaseInfoCompanyFareStrategy());
//        strategies.put("baseInfoVehicleStrategy", new baseInfoVehicleStrategy());
//    }

    public static IStrategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return strategies.get(type);
    }

    public static  void register(String name,IStrategy iStrategy){
        if (StringUtils.isEmpty(name) || null == iStrategy) {
           return;
        }
        strategies.put(name,iStrategy);

    }



}
