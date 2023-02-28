package com.cy.stackandqueue.stackclass;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
class StackArrayTest {

    @Test
    void push_1() {
        // 期望值
        Object[] except1 = {1,2,3,4};

        // 实际值
        StackArray<Integer> stackArray = new StackArray<>();
        stackArray.push(1);
        stackArray.push(2);
        stackArray.push(3);
        stackArray.push(4);
        List<Object> actual1 = Arrays.asList(stackArray.getStackArray());
        actual1 = actual1.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(except1));
        log.info("actual value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(except1, actual1.toArray());
    }

    @Test
    void push_2() {
        // 期望值
        Object[] except1 = {1};

        // 实际值
        StackArray<Integer> stackArray = new StackArray<>();
        stackArray.push(1);
        List<Object> actual1 = Arrays.asList(stackArray.getStackArray());
        actual1 = actual1.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(except1));
        log.info("actual value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(except1, actual1.toArray());
    }

    @Test
    void pop_success_1() {
        // 期望值
        Integer except1 = 4;

        // 实际值
        StackArray<Integer> stackArray = new StackArray<>();
        stackArray.push(1);
        stackArray.push(2);
        stackArray.push(3);
        stackArray.push(4);
        Integer actual1 = (Integer) stackArray.pop();

        Assertions.assertEquals(except1,actual1);
    }

    @Test
    void pop_success_2() {
        // 期望值
        Integer except1 = 4;

        // 实际值
        StackArray<Integer> stackArray = new StackArray<>();
        stackArray.push(1);
        stackArray.push(4);
        Integer actual1 = (Integer) stackArray.pop();

        Assertions.assertEquals(except1,actual1);
    }

    @Test
    void getSize_success_1() {
        // 期望值
        Integer except1 = 2;

        // 实际值
        StackArray<Integer> stackArray = new StackArray<>();
        stackArray.push(1);
        stackArray.push(4);
        int size = stackArray.getSize();

        Assertions.assertEquals(except1,size);
    }

    @Test
    void getSize_error_1() {
        // 期望值
        Integer except1 = 1;

        // 实际值
        StackArray<Integer> stackArray = new StackArray<>();
        stackArray.push(1);
        stackArray.push(4);
        int size = stackArray.getSize();

        Assertions.assertTrue(except1 != size);
    }

    @Test
    void peek() {
    }
}