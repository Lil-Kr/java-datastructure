package com.cy.test.onedimensional.queue;

import com.cy.test.onedimensional.linkedlist.LinkedList;
import com.cy.test.onedimensional.serviec.Queue;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description: 链表实现队列
 */
public class LinkedListQueue<E extends Comparable<E>> implements Queue<E> {

    private LinkedList<E> linkedList;

    public LinkedListQueue() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void enqueue(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public E getFront() {
        return linkedList.getFirst();
    }

    @Override
    public E getTail() {
        return linkedList.getLast();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }
}
