package com.cy.test.twodimensional.heap.base;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class MaxHeapTest {

    private MaxHeap2<Integer> maxHeap;

    private int maxV = 567;

    private int min = -1;

    private Integer [] initNums = {3, 6, min, 11, 2, maxV, 9, 0};

    @BeforeEach
    public void init() {
        maxHeap = new MaxHeap2<>();
    }

    @Test
    void add_1() {
        for (int i = 0; i < initNums.length; i++) {
            maxHeap.add(initNums[i]);
        }
        Assertions.assertEquals(maxHeap.getSize(), initNums.length);
    }

    @Test
    void add_2() {
        maxHeap.add(33);
        Assertions.assertEquals(maxHeap.getSize(), 1);
        Assertions.assertEquals(maxHeap.extractMax(),33);
        Assertions.assertEquals(maxHeap.getSize(), 0);
    }

    @Test
    void extractMax_1() {
        for (int i = 0; i < initNums.length; i++) {
            maxHeap.add(initNums[i]);
        }
        Assertions.assertEquals(maxHeap.extractMax(),maxV);
        Assertions.assertEquals(maxHeap.getSize(), initNums.length - 1);
    }

    @Test
    void extractMax_2() {
        for (int i = 0; i < initNums.length; i++) {
            maxHeap.add(initNums[i]);
        }
        Assertions.assertEquals(maxHeap.extractMax(), maxV);
        Assertions.assertEquals(maxHeap.getSize(), initNums.length - 1);

    }

    @Test
    void extractMax_3() {
        for (int i = 0; i < initNums.length; i++) {
            maxHeap.add(initNums[i]);
        }
        /**
         * 检查是否按从大到小排序
         */
        List<Integer> list = new ArrayList<>(initNums.length);
        for (int i = 0; i < initNums.length; i++) {
            list.add(maxHeap.extractMax());
        }
        Assertions.assertEquals(maxHeap.getSize(), 0);

        boolean b = true;
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(i) < list.get(i + 1)) {
                throw new IllegalArgumentException("元素有逆序");
            }
        }
        Assertions.assertTrue(b);
        System.out.println(JSON.toJSONString(list));
    }


    @Test
    void findMax_1() {
        for (int i = 0; i < initNums.length; i++) {
            maxHeap.add(initNums[i]);
        }
        Assertions.assertEquals(maxHeap.findMax(), maxV);
    }

    @Test
    void findMax_2() {
        maxHeap.add(null);
        maxHeap.add(33);
        Assertions.assertEquals(maxHeap.findMax(), 33);
    }

    @Test
    void replace() {
        for (int i = 0; i < initNums.length; i++) {
            maxHeap.add(initNums[i]);
        }

        System.out.println(maxHeap.toString());
        maxHeap.replace(4);
        Assertions.assertEquals(maxHeap.findMax(), 11);
        System.out.println(maxHeap.toString());

        Assertions.assertEquals(maxHeap.getSize(), initNums.length);
    }

    @Test
    void heapify() {
        maxHeap = new MaxHeap2<>(initNums);
        Assertions.assertEquals(maxHeap.findMax(), maxV);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < initNums.length; i++) {
            list.add(maxHeap.extractMax());
        }

        boolean b = true;
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(i) < list.get(i + 1)) {
                throw new IllegalArgumentException("元素有逆序");
            }
        }
        Assertions.assertTrue(b);
        System.out.println(JSON.toJSONString(list));
    }
}