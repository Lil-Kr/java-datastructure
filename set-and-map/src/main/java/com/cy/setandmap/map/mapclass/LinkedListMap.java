package com.cy.setandmap.map.mapclass;

import com.cy.setandmap.service.MapService;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description: 基于链表实现的Map
 */
public class LinkedListMap<K extends Comparable<K>, V> implements MapService<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(){
            this(null,null, null);
        }

        public Node(K key) {
            this(key, null,null);
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString() + " -> " + value.toString();
        }
    }

    /**
     * 虚拟头节点
     */
    private Node dummyHead;

    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 添加元素, 头插法
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        // 如果存在就覆盖原来的值
        if (node != null) {
            node.value = value;
        }else {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
    }

    /**
     * 删除元素
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedListMap is empty!");
        }

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node del = prev.next;
            prev.next = del.next;
            del.next = null;
            size--;
            return del.value;
        }else {
            return null;
        }

    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn`t exist !");
        }

        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 辅助方法
     * @param key
     * @return
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node prev = dummyHead;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
            res.append(prev.key + " -> " + prev.value + "\n");
        }
        return "LinkedListMap{" +
                " \nlinkedlistMap=\n" + res +
                ", size= " + size +
                '}';
    }
}
