package com.cy.trie.classes;

import com.cy.trie.interfaces.TrieService;

import java.util.TreeMap;

/**
 * @Author: Lil-K
 * @Date: 2023/3/29
 * @Description: 基于 java 的TreeMap 实现Trie
 */
public class Trie implements TrieService {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node () {
            this(false);
        }

        public Node(boolean isWord) {
            this(isWord, new TreeMap<>());
        }

        public Node(boolean isWord, TreeMap<Character, Node> next) {
            this.isWord = isWord;
            this.next = next;
        }
    }

    private Node root;

    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * 向 Trie 中添加要给新的单词 word
     * @param word
     */
    @Override
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词word是否在Trie中
     * @param word
     * @return
     */
    @Override
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询是否在Trie中有单词以isPrefix为前缀的
     * @return
     */
    @Override
    public boolean isPrefix(String prefix) {
        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    /**
     * 删除操作
     * @param word
     */
    @Override
    public void remove(String word) {

    }

    @Override
    public int getSize() {
        return size;
    }

}