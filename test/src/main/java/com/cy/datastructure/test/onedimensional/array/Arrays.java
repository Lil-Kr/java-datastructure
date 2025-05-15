package com.cy.datastructure.test.onedimensional.array;

import com.alibaba.fastjson2.JSON;
import com.cy.datastructure.test.onedimensional.serviec.ArrayService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/31
 * @Description: 动态数组
 */
public class Arrays<E extends Comparable<E>> implements ArrayService<E> {

  private Comparable<E>[] data;

  private int size;

  private int DEFAULT_CAPACITY = 10;

  public Arrays() {
    this.data = new Comparable[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public Arrays(int capacity) {
    this.data = new Comparable[capacity];
    this.size = 0;
  }

  private void resize(int newCapacity) {
    Comparable<E>[] newData = new Comparable[newCapacity];
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i];
    }
    data = newData;
  }

  @Override
  public void add(int index, E e) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("array add method index is error");
    }

    /**
     * resize()
     */
    if (size == getCapacity()) {
      resize(getCapacity() + (getCapacity() >> 1));
    }

    if (index == size) {
      data[size] = e;
    }else {
      for (int i = size; i > index; i--) {
        data[i] = data[i - 1];
      }
      data[index] = e;
    }
    size++;
  }

  public void addFirst(E e) {
    add(0, e);
  }

  public void addLast(E e) {
    add(size, e);
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("array remove method index is error");
    }
    Comparable<E> ret = data[index];

    if (index == size-1) {
      data[size-1] = null;
    }else {
      for (int i = index; i < size - 1; i++) {
        data[i] = data[i + 1];
      }
      data[size - 1] = null;
    }
    size--;

    /**
     * 缩容
     */
    if (size >= DEFAULT_CAPACITY && size <= getCapacity() >> 2) {
      resize(getCapacity());
    }

    return (E) ret;
  }

  public E removeFirst() {
    return remove(0);
  }

  public E removeLast() {
    return remove(size - 1);
  }

  @Override
  public boolean contains(E e) {
    if (isEmpty()) {
      throw new IllegalArgumentException("array contains method is empty");
    }

    for (int i = 0; i < size; i++) {
      if (data[i].compareTo(e) == 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("array get method index is error");
    }
    return (E) data[index];
  }

  public E getFirst() {
    return get(0);
  }

  public E getLast() {
    return get(size-1);
  }

  @Override
  public void set(int index, E e) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("array get method index is error");
    }
    data[index] = e;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int getCapacity() {
    return data.length;
  }

  @Override
  public E[] toArray() {
    E[] newData = (E[]) new Comparable[size];
    for (int i = 0; i < size; i++) {
      newData[i] = (E) data[i];
    }
    return newData;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(toArray());
  }
}
