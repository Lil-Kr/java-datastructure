package com.cy.setandmap.set.setclass;

import com.alibaba.fastjson2.JSON;
import com.cy.binarysearchtree.bst.BST;
import com.cy.setandmap.service.SetService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description: 基于 BST 实现的 Set
 */
@Slf4j
public class SetBST<E extends Comparable<E>> implements SetService<E> {

    private BST<E> bst;

    public SetBST(){
        bst = new BST();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public List<E> getSetBSTs() {
        return bst.getBSTListByInOrder();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public String toString() {
        return "SetBST{" +
                "size=" + getSize() +
                ", set-bst-list=" + JSON.toJSONString(getSetBSTs()) +
                '}';
    }
}
