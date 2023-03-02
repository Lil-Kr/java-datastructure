package com.cy.test.onedimensional.array;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class ArrayTest {

    private Arrays<Integer> array;

    private int initnums = 10;

    @BeforeEach
    public void init() {
        array = new Arrays<>();
        for (int i = 0; i < initnums; i++) {
            array.add(i,i+1);
        }
    }

    @Test
    void add() {
        System.out.println(JSON.toJSONString(array.toArray()));
        Assertions.assertEquals(initnums,array.getSize());
    }

    @Test
    void addFirst() {
        // 期望值
        Comparable[] except1 = {888,1,2,3,4,5,6,7,8,9,10};

        // 实际值
        array.addFirst(888);
        Comparable[] actual1 = array.toArray();
        Assertions.assertEquals(initnums+1, array.getSize());
        Assertions.assertArrayEquals(except1,actual1);
    }

    @Test
    void addLast() {
        // 期望值
        Comparable[] except1 = {1,2,3,4,5,6,7,8,9,10,888};

        // 实际值
        array.addLast(888);
        Comparable[] actual1 = array.toArray();
        Assertions.assertArrayEquals(except1,actual1);
        Assertions.assertEquals(initnums+1, array.getSize());
    }

    @Test
    void resize() {
        Assertions.assertTrue(array.getSize() == 10);
        array.addLast(100);

        Assertions.assertTrue(array.getSize() == 11);
        Assertions.assertTrue(array.getCapacity() == 15);
    }

    @Test
    void remove_1() {
        Comparable[] except1 = {1,2,4,5,6,7,8,9,10,99};

        System.out.println(array.getSize());
        System.out.println(array.getCapacity());
        System.out.println(JSON.toJSONString(array.toArray()));
        Assertions.assertTrue(array.getCapacity() == array.getSize());

        System.out.println("---------------------------------");

        array.addLast(99);
        System.out.println(array.getSize());
        System.out.println(array.getCapacity());
        System.out.println(JSON.toJSONString(array.toArray()));
        Assertions.assertFalse(array.getCapacity() == array.getSize());

        System.out.println("---------------------------------");

        array.remove(2);
        Comparable[] actual1 = array.toArray();
        Assertions.assertArrayEquals(except1,actual1);
    }

    @Test
    void remove_2() {
        //
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            for (int i = 0; i < initnums; i++) {
                array.remove(i);
            }
        });

        Assertions.assertEquals(exception.getMessage(),"remove method index is error");
    }

    @Test
    void removeLast() {
        // 期望值 {1,2,3,4,5,6,7,8,9,10}
        Comparable[] except1 = {1,2,3,4,5,6,7,8,9};

        // 实际值
        array.removeLast();
        Comparable[] actual1 = array.toArray();
        Assertions.assertArrayEquals(except1,actual1);
    }

    @Test
    void removeFirst() {
        // 期望值 {1,2,3,4,5,6,7,8,9,10}
        Comparable[] except1 = {2,3,4,5,6,7,8,9,10};

        // 实际值
        array.removeFirst();
        Comparable[] actual1 = array.toArray();
        Assertions.assertArrayEquals(except1,actual1);
    }

}