package com.cy.stackandqueue.queueclass;

import com.cy.linkedlist.linkedlistclass.LinkedListDummy;

/**
 * @Author: Lil-K
 * @Date: 2023/2/22
 * @Description:
 */
public class QueueLinkedlist<E> implements Queue<E> {

    private LinkedListDummy linkedList;

    public QueueLinkedlist() {
        linkedList = new LinkedListDummy();
    }

    @Override
    public void enqueue(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E dequeue() {
        return (E) linkedList.removeFirst();
    }

    @Override
    public E getFront() {
        return (E) linkedList.get(0);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
