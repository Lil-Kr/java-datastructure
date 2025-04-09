package com.cy.datastructure.hashtable.hashtableclass;

import java.util.TreeMap;

/**
 * @Author: Lil-K
 * @Date: 2023/2/25
 * @Description:
 */
public class HashTableTreeMap<K, V> {

  private TreeMap<K,V>[] hashtable;

  private int M;

  private int size;

  private static final int DEFAULT_CAPACITY = 7;

  // 扩容的上限
  private static final int upperTol = 10;
  // 缩容的下限
  private static final int lowerTol = 2;

  public HashTableTreeMap() {
    this(DEFAULT_CAPACITY);
  }

  public HashTableTreeMap(int m) {
    M = m;
    size = 0;
    hashtable = new TreeMap[M];
    // 实例化每个TreeMap
    for (int i = 0; i < M; i++) {
      hashtable[i] = new TreeMap<>();
    }
  }

  /**
   * 将key 转成 hashcode 整数
   * & 0x7fffffff -> 表示消除最高位的符号(正负符号)
   * @param key
   * @return 转换为一个真实的数组索引
   */
  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /** ============================== 主要方法实现 ============================== **/

  /**
   * 添加操作
   * @param key
   * @param value
   */
  public void add(K key, V value){
    int hashIndex = hash(key);
    TreeMap<K, V> map = hashtable[hashIndex];
    if (map.containsKey(key)) {
      map.put(key, value);
    }else {
      map.put(key, value);
      size++;

      if (size >= upperTol * M) {
        resize(M << 1);
      }
    }
  }

  /**
   * 删除操作
   * @param key
   * @return
   */
  public V remove(K key) {
    int hashIndex = hash(key);
    TreeMap<K, V> map = hashtable[hashIndex];
    V ret = null;
    if (map.containsKey(key)) {
      ret = map.remove(key);
      size--;

      if (size < (lowerTol * M)
        && (M >> 1) >= DEFAULT_CAPACITY) {
        resize(M >> 1);
      }
    }
    return ret;
  }

  /**
   * 调整数组(哈希表)的容量
   * @param newM
   */
  private void resize(int newM) {
    TreeMap<K, V>[] newHashTable = new TreeMap[newM];
    for (int i = 0; i < newM; i++) {
      newHashTable[i] = new TreeMap<>();
    }

    int oldM = M;
    // 重新赋值, 以便计算正确的哈希值
    this.M = newM;

    for (int i = 0; i < oldM; i++) {
      TreeMap<K, V> map = hashtable[i];
      for (K key : map.keySet()) {
        int hashIndex = hash(key);
        newHashTable[hashIndex].put(key, map.get(key));
      }
    }

    this.hashtable = newHashTable;
  }

  /**
   * 修改元素
   * @param key
   */
  public void set(K key, V value) {
    int hashIndex = hash(key);
    TreeMap<K, V> map = hashtable[hashIndex];
    if (!map.containsKey(key)) {
      throw new IllegalArgumentException("key: "+key+" doesn`t exit!");
    }
    map.put(key, value);
  }

  public V get(K key) {
    int hashIndex = hash(key);
    TreeMap<K, V> map = hashtable[hashIndex];
    if (!map.containsKey(key)) {
      throw new IllegalArgumentException("key: "+key+" doesn`t exit!");
    }

    return map.get(key);
  }

  public boolean contains(K key) {
    int hashIndex = hash(key);
    TreeMap<K, V> map = hashtable[hashIndex];
    return map.containsKey(key);
  }
}