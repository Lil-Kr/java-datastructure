package com.cy.trie.interfaces;

/**
 * @Author: Lil-K
 * @Date: 2023/3/29
 * @Description: Trie interface
 */
public interface TrieService {

    void addWord(String word);

    void remove(String word);

    boolean search(String word);

    boolean isPrefix(String prefix);



    int getSize();

    boolean isEmpty();

}
