package com.promise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by leiwei on 2021/4/26 11:37
 */
public class JDK8Map {

    public static void main(String[] args) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> set=new HashSet<>();
        set.add(4);
        map.put(1,set);

        for (int i=0;i<=5;i++){

            Set<Integer> orDefault = map.getOrDefault(i, new HashSet<>());
            orDefault.add(i);
            map.put(i,orDefault);

           /* int finalI = i;
            map.compute(i,(k, v)->{
                if(finalI==0)
                if(v==null)v=0;
                v+= finalI;
                return v;
            });*/
        }

        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"===="+entry.getValue().size());
        }



    }


}
