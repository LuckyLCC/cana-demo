package com.example.canademo.utils;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: TODO
 * @Author: liuchang
 * @CreateTime: 2022-10-14  12:29
 */
public class DataInfo {


    private static List<String> list = new ArrayList<>();

    private static ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();


    public static void setData(String s) {
        list.add(s);
    }

    public static List<String> getData() {
        return list;
    }


    public static void setClq(String s) {
        clq.add(s);
    }

    public static ConcurrentLinkedQueue<String> getClq() {
        return clq;
    }
}
