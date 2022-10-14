package com.example.canademo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: liuchang
 * @CreateTime: 2022-10-14  11:04
 */

public class CanalConfig implements Runnable {


    @Override
    public void run() {

        //TODO 获取连接
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("192.168.202.102", 11111), "example", "", "");


        System.out.println(Thread.currentThread().getName());
        while (true) {

            //TODO 连接
            canalConnector.connect();

            //TODO 订阅数据库
            canalConnector.subscribe("haikou2022.user_info");

            //TODO 获取数据
            Message message = canalConnector.get(100);

            //TODO 获取Entry集合
            List<CanalEntry.Entry> entries = message.getEntries();

            //TODO 判断集合是否为空,如果为空,则等待一会继续拉取数据
            if (entries.size() <= 0) {
                System.out.println("当次抓取没有数据，休息一会。。。。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {

                //TODO 遍历entries，单条解析
                for (CanalEntry.Entry entry : entries) {

                    //1.获取表名
                    String tableName = entry.getHeader().getTableName();

                    //2.获取类型
                    CanalEntry.EntryType entryType = entry.getEntryType();

                    //3.获取序列化后的数据
                    ByteString storeValue = entry.getStoreValue();

                    //4.判断当前entryType类型是否为ROWDATA
                    if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {

                        //5.反序列化数据
                        CanalEntry.RowChange rowChange = null;
                        try {
                            rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                        } catch (InvalidProtocolBufferException e) {
                            e.printStackTrace();
                        }

                        //6.获取当前事件的操作类型
                        CanalEntry.EventType eventType = rowChange.getEventType();

                        //7.获取数据集
                        List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();

                        //8.遍历rowDataList，并打印数据集
                        for (CanalEntry.RowData rowData : rowDataList) {

                            JSONObject beforeData = new JSONObject();
                            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                            for (CanalEntry.Column column : beforeColumnsList) {
                                beforeData.put(column.getName(), column.getValue());
                            }

                            JSONObject afterData = new JSONObject();
                            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                            for (CanalEntry.Column column : afterColumnsList) {
                                afterData.put(column.getName(), column.getValue());
                            }


                            //数据打印
                            System.out.println("Table:" + tableName + ",EventType:" + eventType + ",Before:" + beforeData + ",After:" + afterData);

                            if (afterData != null || afterData.isEmpty()) {
//                                list.add(JSON.toJSONString(afterData));
                                DataInfo.setClq(JSON.toJSONString(afterData));
                            }
                            if (beforeData != null || beforeData.isEmpty()) {
//                                list.add(JSON.toJSONString(beforeData));
                                DataInfo.setClq(JSON.toJSONString(beforeData));
                            }


                        }
                    } else {
                        System.out.println("当前操作类型为：" + entryType);
                    }
                }
            }
        }
    }
}
