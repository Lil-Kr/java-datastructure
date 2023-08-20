package com.cy.test.twodimensional.segmenttree;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class SegmentTreeTest {

    private static Integer[] nums = {-2, 0, 3, -5, 2, -1};

    @BeforeEach
    public void init() {
    }

    @Test
    public void testCreateSegmentTree() {
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segmentTree);
    }

    @Test
    public void testQuery() {
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);

        System.out.println(segmentTree.query(2, 3));
    }
}