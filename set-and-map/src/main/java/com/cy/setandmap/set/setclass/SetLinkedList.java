package com.cy.setandmap.set.setclass;

import com.alibaba.fastjson2.JSON;
import com.cy.linkedlist.linkedlistclass.LinkedListDummy;
import com.cy.setandmap.service.SetService;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description: 基于链表实现的Set
 */
public class SetLinkedList<E> implements SetService<E> {

    private LinkedListDummy<E> linkedListDummy;

    public SetLinkedList() {
        this.linkedListDummy = new LinkedListDummy<>();
    }

    @Override
    public void add(E e) {
        linkedListDummy.addDeduplicationLast(e);
    }

    @Override
    public void remove(E e) {
        linkedListDummy.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedListDummy.contain(e);
    }

    @Override
    public List<E> getSetBSTs() {
        return Arrays.asList(linkedListDummy.toArray());
    }

    @Override
    public int getSize() {
        return linkedListDummy.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedListDummy.isEmpty();
    }

    @Override
    public String toString() {
        return "SetLinkedList{" +
                "size=" + getSize() + ", " +
                "linkedListDummy=" + JSON.toJSONString(getSetBSTs()) +
                '}';
    }
}
