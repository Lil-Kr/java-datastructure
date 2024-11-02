package com.cy.datastructure.test.onedimensional.linkedlist;

import com.cy.datastructure.test.onedimensional.serviec.LinkedArrayService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/31
 * @Description: 单向链表 带尾节点
 */
public class LinkedListSingle<E extends Comparable<E>> implements LinkedArrayService<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node dummyHead;
    private Node tail;
    private int size;

    public LinkedListSingle() {
        dummyHead = new Node(null);
        tail = dummyHead;
        size = 0;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("linked list add method index is out of bounds");
        }

        Node pre = dummyHead;
        Node newData = new Node(e, pre.next);
        if (index == size) {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = newData;
            tail = newData;
        }else {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = newData;
        }
        size++;

    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("linked list remove method index is out of bounds");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node del = pre.next;
        pre.next = del.next;
        del.next = null;
        size--;
        return del.e;
    }

    public E removeFirst() {
        return remove(0);
    }


    public E removeLast() {
        return remove(size-1);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("linked list get method index is out of bounds");
        }

        if (index == size - 1) {
            return tail.e;
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        return pre.next.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size-1);
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("linked list set method index is out of bounds");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next.e = e;
    }

    @Override
    public boolean contain(E e) {
        if (isEmpty()) {
            throw new IllegalArgumentException("linked list is empty");
        }

        Node pre = dummyHead;
        for (int i = 0; i < size; i++) {
            pre = pre.next;
            if (pre.e.compareTo(e) == 0) {
                return true;
            }
        }
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
    public Comparable[] toArray() {
        Comparable[] comparables = new Comparable[size];
        Node pre = dummyHead;
        for (int i = 0; i < comparables.length; i++) {
            pre = pre.next;
            comparables[i] = pre.e;
        }
        return comparables;
    }
}