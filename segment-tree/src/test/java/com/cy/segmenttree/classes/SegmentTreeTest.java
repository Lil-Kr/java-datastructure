package com.cy.segmenttree.classes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class SegmentTreeTest {

    private SegmentTree<Integer> segmentTree;

    // [5, 8, 7, 2, 10, 2, 2]
//    private static Integer[] nums = {-2, 0, 3, -5, 2, -1};
    private static Integer[] nums = {5, 8, 7, 2, 10, 2, 2};

    @BeforeEach
    public void init() {
        segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
//        System.out.println(segmentTree);
    }

    @Test
    public void test1() {
        Integer query = segmentTree.query(3, 3);
        System.out.println(query);
    }

}