package com.cy.trie.classes;

import java.util.TreeMap;

/**
 * @Author: Lil-K
 * @Date: 2023/8/21
 * @Description: Trie: 字典树
 */
public class Trie2 {

    private class Node {
        /**
         * 描述当访问到当前node的时候, 是否就已经找到了一个单词
         */
        public boolean isWord;

        /**
         * 使用 Character 作为Key, 是为了兼容实现任何语言类的问题, 比如中文, 韩文等等...
         * 使用Map表示每个node节点面下有多少个枝杈是不确定的
         */
        public TreeMap<Character, Node> next;

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * 表示在Trie中有多少个单词
     */
    private int size;

    public Trie2() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * 添加操作
     * 向Trie中添加一个新的单词word
     * todo: 实现一边使用递归的方式来完成 add 操作
     * @param word
     */
    public void add(String word) {
        Node cur = root;

        /**
         * 通过for循环添加单词字符
         * 当跳出循环之后, 表示这个单词按字符的形式已经添加完成了,
         */
        for(int i = 0; i < word.length(); i++) {
            /**
             * 每次取出一个字符
             */
            char c = word.charAt(i);

            /**
             * 判断这个 单个字符 是否已经有了指向 c 这个字符相应的节点了
             */
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }

            /**
             * 移动指针, 指向下一个元素
             */
            cur = cur.next.get(c);
        }

        /**
         * 此时 cur 是这个单词添加完毕之后的node
         * 判断最后一个节点是否是一个完整的新单词, 如果是添加重复的单词, 此时 cur.isWord == true, size也不会增加
         * 添加一个单词完成之后, isWord 为 false
         */
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 查询单词 word是否存在于 Trie 中
     * todo: 使用递归的方式实现一次 [查询] 的操作
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            /**
             * 如果当前字符 不包含下一个元素的映射
             * 说明在这个Trie中根本没有这个word
             */
            if (cur.next.get(c) == null) {
                return false;
            }

            /**
             * 一直往下查询
             */
            cur = cur.next.get(c);
        }

        /**
         * 整个单词按字符循环完毕之后, 返回最后一个node的isWord
         */
        return cur.isWord;
    }

    /**
     * 前缀匹配
     * 查询单词是否是是前缀
     * @param prefixWord
     * @return
     */
    public boolean isPrefix(String prefixWord) {
        Node cur = root;

        for (int i = 0; i < prefixWord.length(); i++) {
            char c = prefixWord.charAt(i);

            if(cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    /**
     * 简单模式匹配
     */


    /**
     * 获取 Trie 中存储的单词数量
     * @return
     */
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
