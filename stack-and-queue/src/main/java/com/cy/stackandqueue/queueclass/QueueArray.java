package com.cy.stackandqueue.queueclass;

import com.cy.array.arrayclass.Array;

/**
 * @Author: Lil-K
 * @Date: 2023/2/22
 * @Description: 数组实现 队列, 具有局限性, 出队操作 O(n)
 */
public class QueueArray<E extends Comparable<E>> implements Queue<E> {

    private Array<E> array;

    public QueueArray() {
        this.array = new Array();
    }

    public QueueArray(int capacity) {
        this.array = new Array(capacity);
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        res.append("]");

        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}