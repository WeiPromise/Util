package com.promise.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by leiwei on 2021/8/19 21:39
 */
public class HelloGC {

    public static void main(String[] args) {
        System.out.println("helloGC");
        List list = new LinkedList<>();
        for(;;){
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }
}
