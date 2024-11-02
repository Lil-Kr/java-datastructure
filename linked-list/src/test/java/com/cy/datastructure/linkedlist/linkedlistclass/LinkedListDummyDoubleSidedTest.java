package com.cy.datastructure.linkedlist.linkedlistclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedListDummyDoubleSidedTest<E> {

    private LinkedListDummyDoubleSided<Integer> linkedListDummyDoubleSided;
    private LinkedListDummyDoubleSided<Integer> linkedListDummyDoubleSided2 = new LinkedListDummyDoubleSided<>();

    private static int[] nums = {2,1,6,0,3,4};

    private static String numsStr = "[2 -> 1 -> 6 -> 0 -> 3 -> 4 -> NULL]";

    @BeforeEach
    public void init() {
        linkedListDummyDoubleSided = new LinkedListDummyDoubleSided<>();
        for (int i = 0; i < nums.length; i++) {
            linkedListDummyDoubleSided.add(i, nums[i]);
        }
//        System.out.println(linkedListDummyDoubleSided);
        Assertions.assertEquals(nums.length, linkedListDummyDoubleSided.getSize());
    }

    @Test
    void addFirst() {
        linkedListDummyDoubleSided.addFirst(41);
        linkedListDummyDoubleSided.addFirst(42);

        System.out.println(linkedListDummyDoubleSided);
        Assertions.assertEquals(nums.length + 2, linkedListDummyDoubleSided.getSize());
        Assertions.assertEquals("[42 -> 41 -> 2 -> 1 -> 6 -> 0 -> 3 -> 4 -> NULL]", linkedListDummyDoubleSided.toString());
    }

    @Test
    public void addLast() {
        linkedListDummyDoubleSided.addLast(41);
        linkedListDummyDoubleSided.addLast(42);

        System.out.println(linkedListDummyDoubleSided);
        Assertions.assertEquals(nums.length + 2, linkedListDummyDoubleSided.getSize());
        Assertions.assertEquals("[2 -> 1 -> 6 -> 0 -> 3 -> 4 -> 41 -> 42 -> NULL]", linkedListDummyDoubleSided.toString());
    }

    @Test
    public void getFirst() {
        Integer first = linkedListDummyDoubleSided.getFirst();
        Assertions.assertEquals(nums.length, linkedListDummyDoubleSided.getSize());
        Assertions.assertEquals(numsStr, linkedListDummyDoubleSided.toString());
        Assertions.assertEquals(first, nums[0]);
    }

    @Test
    void getLast() {
        Integer last = linkedListDummyDoubleSided.getLast();
        Assertions.assertEquals(nums.length, linkedListDummyDoubleSided.getSize());
        Assertions.assertEquals(numsStr, linkedListDummyDoubleSided.toString());
        Assertions.assertEquals(last, nums[nums.length - 1]);
    }

    @Test
    public void remove_success_1() {
        Integer remove = linkedListDummyDoubleSided.remove(2);
        Assertions.assertEquals(remove, nums[2]);
        Assertions.assertEquals("[2 -> 1 -> 0 -> 3 -> 4 -> NULL]", linkedListDummyDoubleSided.toString());
        Assertions.assertEquals(nums.length - 1, linkedListDummyDoubleSided.getSize());
    }

    @Test
    public void removeFirst_success_1() {
        for (int i = 0; i < nums.length; i++) {
            linkedListDummyDoubleSided.removeFirst();
        }
        Assertions.assertEquals(0, linkedListDummyDoubleSided.getSize());
        Assertions.assertEquals("[NULL]", linkedListDummyDoubleSided.toString());
    }

    @Test
    void removeLast() {
        for (int i = 0; i < nums.length; i++) {
            linkedListDummyDoubleSided.removeLast();
        }
        Assertions.assertEquals(0, linkedListDummyDoubleSided.getSize());
        Assertions.assertEquals("[NULL]", linkedListDummyDoubleSided.toString());
    }

}