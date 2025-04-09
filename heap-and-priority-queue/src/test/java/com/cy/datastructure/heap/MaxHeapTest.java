package com.cy.datastructure.heap;

import com.alibaba.fastjson2.JSON;
import com.cy.datastructure.heapandpriorityqueue.heap.MaxHeap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
class MaxHeapTest {

  private MaxHeap<Integer> maxHeap;

  private MaxHeap<Integer> maxHeapHeapify;

  private Integer[] arr = {3,22,1,0,4,70,54,21};

  @BeforeEach
  public void init() {
    maxHeap = new MaxHeap<>();
    maxHeapHeapify = new MaxHeap<>(arr);
  }

  /**
   * 添加一个元素
   */
  @Test
  public void add_success_1() {
    // 期望值
    Comparable[] except1 = {555};

    // 实际值
    maxHeap.add(555);
    List<Object> arr = Arrays.asList(maxHeap.getArray());
    List<Object> actualList = arr.stream()
      .filter(item -> Objects.nonNull(item))
      .collect(Collectors.toList());
    Assertions.assertArrayEquals(except1,actualList.toArray());

    /**
     * 获取堆顶元素
     */
    Integer actual1 = maxHeap.extractMax();
    Assertions.assertEquals(except1[0], actual1);

    Assertions.assertTrue(maxHeap.isEmpty());
  }

  /**
   * 添加多个元素
   * 校验元素个数是否相等
   */
  @Test
  public void add_success_2() {
    // 期望值 {3,22,1,0,4,70,54,21};
    Comparable[] except1 = arr;

    // 实际值
    maxHeap.add(3);
    maxHeap.add(22);
    maxHeap.add(1);
    maxHeap.add(0);
    maxHeap.add(4);
    maxHeap.add(70);
    maxHeap.add(54);
    maxHeap.add(21);
    /**
     * 元素个数相等
     */
    Assertions.assertEquals(except1.length, maxHeap.getSize());
  }

  /**
   * 添加的元素按从大到小排列取出来
   */
  @Test
  void get_add_array_success_1() {
    // 期望值 {3,22,1,0,4,70,54,21};
    Comparable[] except1 = arr;
    Comparable[] except2 = new Integer[arr.length];
    Boolean except3 = true;

    // 实际值
    maxHeap.add(3);
    maxHeap.add(22);
    maxHeap.add(1);
    maxHeap.add(0);
    maxHeap.add(4);
    maxHeap.add(70);
    maxHeap.add(54);
    maxHeap.add(21);

    int size = maxHeap.getSize();
    /**
     * 元素个数相等
     */
    Assertions.assertEquals(except1.length, size);

    for (int i = 0; i < size; i++) {
      except2[i] = maxHeap.extractMax();
    }

    for (int i = 0; i < except2.length-1; i++) {
      if (except2[i].compareTo(except2[i+1]) < 0) {
        except3 = false;
      }
    }

    Assertions.assertTrue(except3);
  }

  /**
   * 替换堆的元素之后(堆顶), 看是否还能满足堆的性质
   * 堆顶是否还是最大值
   */
  @Test
  void replace_success_1() {
    // 期望值 {3,22,1,0,4,70,54,21};
    Integer[] except1 = arr;
    Integer[] except2 = {};

    // 实际值
    maxHeap.add(3);
    maxHeap.add(22);
    maxHeap.add(1);
    maxHeap.add(0);
    maxHeap.add(4);
    maxHeap.add(70);
    maxHeap.add(54);
    maxHeap.add(21);

    Comparable[] array = maxHeap.getArray();
    System.out.println(JSON.toJSONString(array));

    maxHeap.replace(69);
    Comparable[] array1 = maxHeap.getArray();
    System.out.println(JSON.toJSONString(array1));

    Assertions.assertEquals(array1[0], maxHeap.findMax());
  }

  /**
   * 给定一个任意数组, 用堆排序后,
   * 校验取出来的元素是否是顺序的
   */
  @Test
  void heapify_success_1() {
    // {3,22,1,0,4,70,54,21};
    Comparable[] array = maxHeapHeapify.getArray();
    System.out.println(JSON.toJSONString(array));
  }

}