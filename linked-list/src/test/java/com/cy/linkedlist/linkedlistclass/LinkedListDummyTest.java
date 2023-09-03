package com.cy.linkedlist.linkedlistclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedListDummyTest<E> {

    private LinkedListDummy<Integer> linkedListDummy;

    private LinkedListDummy<Integer> linkedListDummy2 = new LinkedListDummy<>();

    private static int[] nums = {2,1,6,0,3,4};

    private static String numsStr = "[2 -> 1 -> 6 -> 0 -> 3 -> 4 -> NULL]";

    @BeforeEach
    public void init() {
        this.linkedListDummy = new LinkedListDummy<>();
        for (int i = 0; i < nums.length; i++) {
            linkedListDummy.add(i, nums[i]);
        }
//        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length, linkedListDummy.getSize());
    }

    @Test
    public void addFirst() {
        linkedListDummy.addFirst(41);
        linkedListDummy.addFirst(42);

        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length + 2, linkedListDummy.getSize());
        Assertions.assertEquals("[42 -> 41 -> 2 -> 1 -> 6 -> 0 -> 3 -> 4 -> NULL]", linkedListDummy.toString());
    }

    @Test
    public void addLast() {
        linkedListDummy.addLast(41);
        linkedListDummy.addLast(42);

        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length + 2, linkedListDummy.getSize());
        Assertions.assertEquals("[2 -> 1 -> 6 -> 0 -> 3 -> 4 -> 41 -> 42 -> NULL]", linkedListDummy.toString());
    }

    @Test
    public void set() {
        linkedListDummy.set(0,100);
        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length, linkedListDummy.getSize());
        Assertions.assertEquals("[100 -> 1 -> 6 -> 0 -> 3 -> 4 -> NULL]", linkedListDummy.toString());

        linkedListDummy.set(linkedListDummy.getSize() - 1,99);
        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length, linkedListDummy.getSize());
        Assertions.assertEquals("[100 -> 1 -> 6 -> 0 -> 3 -> 99 -> NULL]", linkedListDummy.toString());
    }

    @Test
    void remove() {
        linkedListDummy.remove(3);
        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length - 1, linkedListDummy.getSize());
        Assertions.assertEquals("[2 -> 1 -> 6 -> 3 -> 4 -> NULL]", linkedListDummy.toString());
    }

    @Test
    void removeFirst() {
        linkedListDummy.removeFirst();
        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length - 1, linkedListDummy.getSize());
        Assertions.assertEquals("[1 -> 6 -> 0 -> 3 -> 4 -> NULL]", linkedListDummy.toString());
    }

    @Test
    void removeLast() {
        linkedListDummy.removeLast();
        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length - 1, linkedListDummy.getSize());
        Assertions.assertEquals("[2 -> 1 -> 6 -> 0 -> 3 -> NULL]", linkedListDummy.toString());
    }

    @Test
    void get() {
        Integer e1 = linkedListDummy.get(2);
        System.out.println(linkedListDummy);
        Assertions.assertEquals(nums.length, linkedListDummy.getSize());
        Assertions.assertEquals(numsStr, linkedListDummy.toString());
        Assertions.assertEquals(e1, nums[2]);
    }
}