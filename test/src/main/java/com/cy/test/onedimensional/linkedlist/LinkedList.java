package com.cy.test.onedimensional.linkedlist;

import com.cy.test.onedimensional.serviec.LinkedArrayService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description: 链表
 */
public class LinkedList<E extends Comparable<E>> implements LinkedArrayService<E> {

    private class Node {
        public E e;
        public Node next;

        public Node() {
            this(null,null);
        }

        public Node(E e) {
            this(e,null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private int size;

    private Node dummyHead;

    public LinkedList() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("linkedlist add method index is error");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        this.add(0,e);
    }

    public void addLast(E e) {
        this.add(size,e);
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("linkedlist remove method index is error");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node del = prev.next;
        prev.next = del.next;
        del.next = null;
        size--;
        return del.e;
    }

    public E removeFirst() {
        return this.remove(0);
    }

    public E removeLast() {
        return this.remove(size-1);
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size || isEmpty()) {
            throw new IllegalArgumentException("linkedlist get method index is error");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return this.get(0);
    }

    public E getLast() {
        return this.get(size-1);
    }


    @Override
    public void set(int index, E e) {
        if(index < 0 || index >= size || isEmpty()) {
            throw new IllegalArgumentException("linkedlist set method index is error");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    @Override
    public boolean contain(E e) {

        Node prev = dummyHead;
        for (int i = 0; i < size-1; i++) {
            prev = prev.next;
            if (prev.e.compareTo(e) == 0) {
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
        Comparable[] newArray = new Comparable[size];

        Node prev = dummyHead;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
            newArray[i] = prev.e;
        }
        return newArray;
    }
}
