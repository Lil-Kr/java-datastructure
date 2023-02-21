package com.cy.linkedlist.linkedlistclass;

/**
 * @Author: Lil-K
 * @Date: 2023/2/21
 * @Description:
 */
public class LinkedListDummy<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(){
            this(null, null);
        }

        public Node(E e) {
            this.e = e;
            this.next = null;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedListDummy(){
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("dummyHead linkedlist add method index was error");
        }
    }

    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("dummyHead linkedlist add method index was error");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node node = new Node(e, prev.next);
        prev.next = node;
        size++;
    }

    public void addFirst(E e) {
        add(0,e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void update(int index, E e) {
        checkIndex(index);

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public E remove(int index) {
        checkIndex(index);

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node del = prev.next;
        prev.next = del.next;
        del.next = null; // help for GC
        size--;
        return del.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    public E get(int index) {
        checkIndex(index);

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E[] getArray(){
        E[] arr = (E[]) new Object[size];

        Node prev = dummyHead;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
            arr[i] = prev.e;
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        for (int i = 0; i < size; i++) {
            sb.append(cur.e + " -> ");
            cur = cur.next;
        }
        sb.append("NULL");
        return "LinkedListDummy{" +
                "linkedlist= " + sb +
                ", size=" + size +
                '}';
    }
}
