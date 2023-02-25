package com.cy.hashtable.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Lil-K
 * @Date: 2023/2/25
 * @Description:
 */
public class Demo {


    public static void main(String[] args) {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("TT",1);
        map1.put("89",2);
        map1.put("-=",3);
        map1.put("%",4);

        for (String s : map1.keySet()) {
            System.out.println(s);
        }

        System.out.println();

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("TT",1);
        map2.put("89",2);
        map2.put("-=",3);
        map2.put("%",4);
        for (String s : map2.keySet()) {
            System.out.println(s);
        }
    }


}
