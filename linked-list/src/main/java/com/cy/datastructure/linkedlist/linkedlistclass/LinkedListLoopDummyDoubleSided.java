package com.cy.datastructure.linkedlist.linkedlistclass;

import com.cy.datastructure.linkedlist.service.LinkedListService;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description: 循环双向链表
 */
public class LinkedListLoopDummyDoubleSided<E> implements LinkedListService<E> {


    private class Node {
        public E e;
        public Node next, prev;

        public Node() {
            this(null,null,null);
        }

        public Node(E e){
            this(e,null,null);
        }

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedListLoopDummyDoubleSided() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 循环双向链表添加元素
     * dummyHead.next -> 首
     * dummyHead.perv -> 尾
     * @param index
     * @param e
     */
    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("double loop linked list index is error");
        }

        /**
         * 计算index与首尾距离大小,
         * 判断从头部开始还是尾部开始
         */
        Node prev = dummyHead;
        Node node = new Node(e);
        if (size == 0) {
            prev.next = node;
            prev.prev = node;

            node.prev = prev;
            node.next = prev;
        } else if (index <= (size >> 1)) {

            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            node.next = prev.next;
            node.prev = prev;

            dummyHead.prev = prev.next;
//            prev.next;
        }else {
        }

        size++;
    }

    public void addFirst(E e){
        this.add(0, e);
    }

    public void addLast(E e){
        this.add(size - 1, e);
    }

    @Override
    public E remove(int index) {
        return null;
    }

    public E removeFirst() {
        return this.remove(0);
    }

    public E removeLast() {
        return this.remove(size-1);
    }

    @Override
    public E get(int index) {
        return null;
    }

    public E getLast(){
        if (isEmpty()) {
            return null;
        }
        return dummyHead.prev.e;
    }

    public E getFirst(){
        if (isEmpty()) {
            return null;
        }
        return dummyHead.next.e;
    }

    @Override
    public void set(int index, E e) {

    }

    @Override
    public boolean contain() {
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E[] toArray() {
        return null;
    }
}
