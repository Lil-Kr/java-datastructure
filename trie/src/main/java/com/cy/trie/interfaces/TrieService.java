package com.cy.trie.interfaces;

/**
 * @Author: Lil-K
 * @Date: 2023/3/29
 * @Description:
 */
public interface TrieService {

    void add(String word);

    void remove(String word);

    boolean contains(String word);

    boolean isPrefix(String prefix);

    int getSize();

}
