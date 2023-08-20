package com.cy.test.twodimensional.segmenttree.impl;

import com.cy.test.twodimensional.segmenttree.Merger;

import java.util.Objects;

/**
 * @Author: Lil-K
 * @Date: 2023/8/20
 * @Description:
 */
public class SumMergerImpl<E> implements Merger<Integer> {
    @Override
    public Integer merge(Integer a, Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return 0;
        }
        return a + b;
    }
}
