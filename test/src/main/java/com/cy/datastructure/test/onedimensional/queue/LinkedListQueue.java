package com.cy.datastructure.test.onedimensional.queue;

import com.cy.datastructure.test.onedimensional.linkedlist.LinkedListSingle;
import com.cy.datastructure.test.onedimensional.serviec.Queue;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description: 链表实现队列
 */
public class LinkedListQueue<E extends Comparable<E>> implements Queue<E> {

    private LinkedListSingle<E> linkedListSingle;

    public LinkedListQueue() {
        linkedListSingle = new LinkedListSingle<>();
    }

    @Override
    public void enqueue(E e) {
        linkedListSingle.addLast(e);
    }

    @Override
    public E dequeue() {
        return linkedListSingle.removeFirst();
    }

    @Override
    public E getFront() {
        return linkedListSingle.getFirst();
    }

    @Override
    public E getTail() {
        return linkedListSingle.getLast();
    }

    @Override
    public boolean isEmpty() {
        return linkedListSingle.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedListSingle.getSize();
    }
}
