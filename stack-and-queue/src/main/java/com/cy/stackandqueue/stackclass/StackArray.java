package com.cy.stackandqueue.stackclass;

import com.cy.array.arrayclass.Array;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: Lil-K
 * @Date: 2023/2/21
 * @Description: 自实现栈结构
 */
@Component
@Service

public class StackArray<E extends Comparable<E>> implements Stack<E>{

    private Array<E> array;

    public StackArray() {
        array = new Array();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return (E) array.removeLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public E peek() {
        return (E) array.get(array.getSize()-1);
    }

    @Override
    public boolean isEmpty() {
        return array.getSize() == 0;
    }

    public E[] getStackArray() {
        return (E[]) array.getArray();
    }
}
