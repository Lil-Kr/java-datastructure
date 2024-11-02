package com.cy.datastructure.linkedlist.linkedlistclass;

import com.alibaba.fastjson2.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoubleLinkedListTest {

	private DoubleLinkedList solution;
	Integer[] nums = {2, 3, 5, 6, 1, 4, 9};

	@BeforeEach
	public void init() {
		solution = new DoubleLinkedList<Integer>(nums);
	}

	@Test
	public void test1() {
		Object[] array = solution.getArray();
		System.out.println(JSONArray.toJSONString(array));
		Assertions.assertEquals(JSONArray.toJSONString(nums), JSONArray.toJSONString(array));
	}

	@Test
	public void test2() {
		Integer[] nums = {2, 3, 5, 6, 1, 4, 9};
		solution = new DoubleLinkedList<Integer>(nums);
		Assertions.assertEquals(2, solution.getFirst());
		Assertions.assertEquals(9, solution.getLast());

//		solution.removeFirst();
//		Assertions.assertEquals(3, solution.getFirst());
//
//		solution.removeLast();
//		Assertions.assertEquals(4, solution.getLast());
	}
}