package com.cy.linkedlist.linkedlistclass;

/**
 * @Author: Lil-K
 * @Date: 2023/2/22
 * @Description: 双向链表
 */
public class LinkedListDummyDoubleSided<E> {

    private class Node {
        public E e;

        public Node prev, next;

        public Node(){
            this(null,null,null);
        }

        public Node(E e){
            this(e,null,null);
        }

        public Node(Node node){
            this(node.e,node.prev,node.next);
        }

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node dummyHead;
    private Node dummyTail;
    private Node head;
    private Node tail;

    private int size;

    public LinkedListDummyDoubleSided() {
        this.dummyHead = new Node();
        this.dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        this.size = 0;
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

        Node node;
        if (isEmpty()) {
            node = new Node(e, dummyHead, dummyTail);
            dummyHead.next = head = node;
            dummyTail.prev = tail = node;
            size++;
            return;
        }

        if(index == 0) {
            node = new Node(e, dummyHead, head);
            dummyHead.next = node;
            head.prev = node;
            head = node;
        }else if(index == size) {
            node = new Node(e, tail, dummyTail);
            dummyTail.prev = node;
            tail.next = node;
            tail = node;
        }else {
            if (index <= getSize() >> 1) {
                Node prev = dummyHead;
                for (int i = 0; i < index; i++) {
                    prev = prev.next;
                }
                node = new Node(e, prev, prev.next);
                prev.next.prev = node;
                prev.next = node;
            }else {
                Node prev = dummyTail;
                for (int i = index; i < size; i++) {
                    prev = prev.prev;
                }
                node = new Node(e, prev.prev, prev);
                prev.prev.next = node;
                prev.prev = node;
            }
        }
        size++;
    }

    public void addFirst(E e) {
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E get(int index) {
        checkIndex(index);
        if (index < getSize() >> 1) {
            Node cur = dummyHead.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.e;
        }else {
            Node cur = dummyTail.prev;
            for (int i = size; i > index; i--) {
                cur = cur.prev;
            }
            return cur.e;
        }
    }

    public E getFirst() {
        return head.e;
    }

    public E getLast() {
        return tail.e;
    }

    public void update(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("dummyHead linkedlist update method index was error");
        }

        if (index <= getSize() >> 1) {
            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            prev.next.e = e;
        }else {
            Node prev = dummyTail;
            for (int i = index; i < size; i++) {
                prev = prev.prev;
            }
            prev.prev.e = e;
        }
    }

    public void updateFrist(E e) {
        head.e = e;
    }

    public void updateLast(E e) {
        tail.e = e;
    }

    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("dummyHead linkedlist add method index was error");
        }

        if (index <= getSize() >> 1) {
            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node del = prev.next;
            prev.next = del.next;
            del.next.prev = prev;
            size--;
            return del.e;
        }else {
            Node prev = dummyTail;
            for (int i = size; i > index+1; i--) {
                prev = prev.prev;
            }

            Node del = prev.prev;
            del.prev.next = prev;
            prev.prev = del.prev;
            size--;
            return del.e;
        }
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(cur.e + " -> ");
            cur = cur.next;
        }
        res.append("NULL");
        res.append("]");
        return res.toString();
    }

}
