package com.cy.datastructure.test.onedimensional.queue;

import com.cy.datastructure.test.onedimensional.serviec.Queue;
import com.cy.datastructure.test.onedimensional.array.Arrays;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description: 数组实现队列
 */
public class ArrayQueue<E extends Comparable<E>> implements Queue<E> {

    private Arrays<E> arrays;

    public ArrayQueue() {
        arrays = new Arrays<>();
    }

    @Override
    public void enqueue(E e) {
        arrays.addLast(e);
    }

    @Override
    public E dequeue() {
        return arrays.removeFirst();
    }

    @Override
    public E getFront() {
        return arrays.getFirst();
    }

    @Override
    public E getTail() {
        return arrays.getLast();
    }

    @Override
    public boolean isEmpty() {
        return arrays.isEmpty();
    }

    @Override
    public int getSize() {
        return arrays.getSize();
    }
}
