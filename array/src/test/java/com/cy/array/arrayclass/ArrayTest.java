package com.cy.array.arrayclass;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ArrayTest {

    @Test
    void addLast() {
        Array array = new Array<>();
        List<Object> initList = Arrays.asList(1,22,4,34);
        for (Object o : initList) {
            array.addLast((Comparable) o);
        }

        List<Object> testAddList = Arrays.asList(100,44);

        for (Object o : testAddList) {
            array.addLast((Comparable) o);
        }

        // 期望值
        List<Object> expectList = new ArrayList<>();
        expectList.addAll(initList);
        expectList.addAll(testAddList);

        // 实际值
        List<Object> arr = Arrays.asList(array.getArray());
        List<Object> actualList = arr.stream()
                .filter(item -> Objects.nonNull(item))
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(expectList));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expectList.toArray(), actualList.toArray());
    }

    @Test
    void addFirst() {
        Array array = new Array<>();
        List<Object> initList = Arrays.asList(1,22,4,34);
        // 期望值
        List<Object> expectList = new ArrayList<>();
        expectList.addAll(Arrays.asList(33, 100, 54, 99));
        expectList.addAll(initList);

        for (Object o : initList) {
            array.addLast((Comparable) o);
        }

        List<Object> addList = Arrays.asList(99, 54, 100, 33);
        for (Object o : addList) {
            array.addFirst((Comparable) o);
        }

        // 实际值
        List<Object> arr = Arrays.asList(array.getArray());
        List<Object> actualList = arr.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(expectList));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expectList.toArray(), actualList.toArray());
    }

    /**
     * �������м期望值
     */
    @Test
    void add_1() {
        Array array = new Array<>();
        List<Object> initList = Arrays.asList(1,22,4,34);
        // 期望值
        List<Object> expectList = Arrays.asList(1, 22, 66 , 4, 34);

        // 实际值
        for (Object o : initList) {
            array.addLast((Comparable)o);
        }

        array.add(2,66);
        List<Object> arr = Arrays.asList(array.getArray());
        List<Object> actualList = arr.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(expectList));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expectList.toArray(), actualList.toArray());
    }

    /**
     *
     */
    @Test
    void add_2() {
        Array array = new Array<>();
        List<Object> initList = Arrays.asList(1,22,4,34);
        // 期望值
        List<Object> expectList = Arrays.asList(1, 22, 4, 34, 66);

        // 实际值
        for (Object o : initList) {
            array.addLast((Comparable)o);
        }

        array.add(4,66);
        List<Object> arr = Arrays.asList(array.getArray());
        List<Object> actualList = arr.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(expectList));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expectList.toArray(), actualList.toArray());
    }

    @Test
    void removeLast() {
        Array array = new Array<>();
        List<Object> initList = Arrays.asList(1,22,4,34);
        // 期望值
        List<Object> expectList = Arrays.asList(1, 22, 4);

        // 实际值
        for (Object o : initList) {
            array.addLast((Comparable) o);
        }

        Object o = array.removeLast();
        List<Object> arr = Arrays.asList(array.getArray());
        List<Object> actualList = arr.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(expectList));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expectList.toArray(), actualList.toArray());
    }

    @Test
    void removeFirst() {
        Array array = new Array<>();
        List<Object> initList = Arrays.asList(1,22,4,34);
        // 期望值
        List<Object> expectList = Arrays.asList(22, 4, 34);

        // 实际值
        for (Object o : initList) {
            array.addLast((Comparable) o);
        }

        Object o = array.removeFirst();
        List<Object> arr = Arrays.asList(array.getArray());
        List<Object> actualList = arr.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("expect value: {}", JSON.toJSONString(expectList));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expectList.toArray(), actualList.toArray());
    }

    @Test
    void removeFirst_exception() {
        Array array = new Array<>();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            array.removeFirst();
        });
        String expectedMessage = "remove index was error";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}