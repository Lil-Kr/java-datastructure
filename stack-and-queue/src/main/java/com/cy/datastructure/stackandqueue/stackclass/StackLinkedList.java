package com.cy.datastructure.stackandqueue.stackclass;

import com.cy.datastructure.linkedlist.linkedlistclass.LinkedListDummy;

/**
 * @Author: Lil-K
 * @Date: 2023/2/21
 * @Description: 链表实现栈结构
 */
public class StackLinkedList<E> implements Stack<E> {

  private LinkedListDummy list;

  public StackLinkedList() {
    list = new LinkedListDummy();
  }

  @Override
  public void push(E e) {
    list.addLast(e);
  }

  @Override
  public E pop() {
    return (E) list.removeLast();
  }

  @Override
  public int getSize() {
    return list.getSize();
  }

  @Override
  public E peek() {
    return (E) list.get(list.getSize() - 1);
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }
}
