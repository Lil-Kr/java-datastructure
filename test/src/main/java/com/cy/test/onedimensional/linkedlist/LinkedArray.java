package com.cy.test.onedimensional.linkedlist;

/**
 * @Author: Lil-K
 * @Date: 2023/2/28
 * @Description:
 */
public class LinkedArray<E extends Comparable<E>> implements LinkedArrayService<E> {

    private class Node {
        public E e;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private int size;

    /**
     * 虚拟头节点
     */
    private Node dummyHead;

    public LinkedArray() {
        dummyHead = new Node();
        size = 0;
    }

    /**================== linked list add operation ================== **/

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("linked list add operation index is error");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        this.add(0, e);
    }

    public void addLast(E e) {
        this.add(size, e);
    }

    /**================== linked list remove operation ================== **/
    @Override
    public E remove(int index) {
        if (index < 0 || index > size || isEmpty()) {
            throw new IllegalArgumentException("linked list is empty");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node del = prev.next;
        prev.next = del.next;
        del.next = null;// help for gc
        size--;
        return del.e;
    }

    public E removeFirst(){
        return this.remove(0);
    }

    public E removeLast(){
        return this.remove(size-1);
    }

    /**================== linked list get operation ================== **/
    @Override
    public E get(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            throw new IllegalArgumentException("linked list get operation index is error");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    /**================== linked list set operation ================== **/
    @Override
    public void set(int index, E e) {
        if (index < 0 || index >= size || isEmpty()) {
            throw new IllegalArgumentException("linked list get operation index is error");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public boolean contains(E e) {
        return this.indexOf(e) != -1;
    }

    private int indexOf(E e) {
        int count = 0;

        Node prev = dummyHead;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
            if(prev.e.equals(e)){
                return count;
            }
            count++;
        }
        return -1;
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
        if (isEmpty()) {
            return null;
        }

        Comparable[] arr = (E[]) new Comparable[size];
        Node prev = dummyHead;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
            arr[i] = prev.e;
        }
        return arr;
    }
}
