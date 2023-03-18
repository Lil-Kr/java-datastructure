package com.cy.test.twodimensional.heap.base;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class MinHeapTest {

    private MinHeap<Integer> minHeap;

    private int maxV = 567;

    private int minV = -1;

    private Integer[] initNums = {3, 6, minV, 11, 2, maxV, 9, 0};

    @BeforeEach
    public void init() {
        minHeap = new MinHeap<>();
    }

    @Test
    void add_1() {
        for (int i = 0; i < initNums.length; i++) {
            minHeap.add(initNums[i]);
        }
        Assertions.assertEquals(minHeap.getSize(), initNums.length);
    }

    @Test
    void add_2() {
        minHeap.add(2);
        Assertions.assertEquals(minHeap.getSize(), 1);
    }

    @Test
    void extractMin_1() {
        for (int i = 0; i < initNums.length; i++) {
            minHeap.add(initNums[i]);
        }
        Assertions.assertEquals(minHeap.extractMin(), minV);
        Assertions.assertEquals(minHeap.getSize(), initNums.length-1);
    }

    @Test
    void extractMin_2() {
        for (int i = 0; i < initNums.length; i++) {
            minHeap.add(initNums[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < initNums.length; i++) {
            list.add(minHeap.extractMin());
        }

        boolean sign = true;
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(i) > list.get(i+1)) {
                sign = false;
                break;
            }
        }
        Assertions.assertTrue(sign);
        Assertions.assertEquals(list.size(), initNums.length);
    }

    @Test
    void findMin_1() {
        for (int i = 0; i < initNums.length; i++) {
            minHeap.add(initNums[i]);
        }

        Assertions.assertEquals(minHeap.findMin(),minV);
    }

    @Test
    void findMin_2() {
        minHeap.add(2);
        Assertions.assertEquals(minHeap.findMin(),2);
    }

    @Test
    void replace() {
        for (int i = 0; i < initNums.length; i++) {
            minHeap.add(initNums[i]);
        }
        System.out.println(minHeap.toString());
        minHeap.replace(4);
        Assertions.assertEquals(minHeap.findMin(), 0);
        System.out.println(minHeap.toString());

        Assertions.assertEquals(minHeap.getSize(), initNums.length);
    }

    @Test
    void heapify() {
        minHeap = new MinHeap<>(initNums);
        Assertions.assertEquals(minHeap.findMin(), minV);
        Assertions.assertTrue(minHeap.getSize() == initNums.length);

        //
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < initNums.length; i++) {
            list.add(minHeap.extractMin());
        }

        boolean b = true;
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(i) > list.get(i + 1)) {
                throw new IllegalArgumentException("元素有逆序");
            }
        }
        Assertions.assertTrue(b);
        System.out.println(JSON.toJSONString(list));
    }
}