package com.example.canademo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.canademo.Strategy.IStrategy;
import com.example.canademo.factory.StrategyFactory;
import com.example.canademo.utils.CanalConfig;
import com.example.canademo.utils.DataInfo;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class CanalDemoApplication {

    static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws InterruptedException, InvalidProtocolBufferException {
        SpringApplication.run(CanalDemoApplication.class, args);
        CanalConfig canalConfig = new CanalConfig();
        service.execute(canalConfig);

        while (true) {
            Thread.sleep(5000);
//            List<String> data1 = canalConfig.getData();
            ConcurrentLinkedQueue<String> clq = DataInfo.getClq();
            String poll = clq.poll();
            System.out.println("取出的数据为" + poll);

            if (StringUtils.isEmpty(poll)) {
                continue;
            }

            JSONObject jsonObject = JSONObject.parseObject(poll);
            if (jsonObject.isEmpty()) {
                continue;
            }

            String type = (String) jsonObject.get("sex");
            System.out.println("sex为" + type);
            IStrategy strategy = StrategyFactory.getStrategy("baseInfoVehicle");
            String s = strategy.handleData(poll);
            strategy.sendData(s);
            System.out.println(Thread.currentThread().getName() + "-----------------");
        }


    }

}
