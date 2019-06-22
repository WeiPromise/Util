package com.promise.util;

import com.promise.util.dataUtil.ArabicToChineseUtils;

import java.util.Scanner;

/**
 * Created by leiwei on 2019/6/19 11:18
 */
public class MainTest {

    public static void main(String[] args) throws Exception {

        while (true){
            Scanner in=new Scanner(System.in);
            System.out.println("请输入INT类整数：");
            System.out.println(ArabicToChineseUtils.foematInteger(in.nextInt()));
        }
    }
}
