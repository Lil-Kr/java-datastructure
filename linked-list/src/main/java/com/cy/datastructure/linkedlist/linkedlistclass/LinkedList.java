package com.cy.datastructure.linkedlist.linkedlistclass;

/**
 * @Author: Lil-K
 * @Date: 2023/2/21
 * @Description: 链表的CRUD
 */
public class LinkedList<E> {

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
        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

    private Node head;

    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("get element index was error");
        }

        if(index == 0) {
            head = new Node(e, head);
            size++;
            return true;
        }

        Node prev = head;
        for (int i = 0; i < index-1; i++) {
            prev = prev.next;
        }
        // 写法1
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;

        // 写法2
        Node node = new Node(e, prev.next);
        prev.next = node;
        size ++;
        return true;
    }

    public boolean addFirst(E e) {
        /**
         * 写法1
         */
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        /**
         * 写法2
         */
//        head = new Node(e, head);
//        size ++;

        return add(e, 0);
    }

    public boolean addLast(E e) {
        return add(e, size);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get element index was error");
        }

        if(index == 0) {
            return head.e;
        }

        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        return prev.e;
    }

    public E[] getLinkedList() {
        E[] res = (E[]) new Object[size];

        Node perv = head;
        for (int i = 0; i < size; i++) {
            res[i] = perv.e;
            perv = perv.next;
        }

        return res;
    }

    public boolean update(E e, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("update element index was error");
        }

        if (index == 0) {
            head.e = e;
            return true;
        }

        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.e = e;
        return true;
    }

    public E remove(int index) {
        if (index < 0 || size < 0) {
            throw new IllegalArgumentException("remove element index was error");
        }

        if (index == 0) {
            Node remove = head;
            head = head.next;
            size--;
            return remove.e;
        }

        Node prev = head;
        for (int i = 0; i < index-1; i++) {
            prev = prev.next;
        }

        Node del = prev.next;
        prev.next = del.next;
        size--;
        return del.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node prev = head;
        for (int i = 0; i < size; i++) {
            sb.append(prev.e + " -> ");
            prev = prev.next;
        }

        sb.append(prev);

        return "LinkedList{" +
                "linked-list= [" + sb + "\n" +
                "], size=" + size +
                '}';
    }
}
