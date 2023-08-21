package com.cy.trie.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Trie2Test {

    private static Trie2 trie2;
    private static List<String> strList;

    @BeforeEach
    public void init() {
        trie2 = new Trie2();
        strList = new ArrayList<>();
        strList.add("cat");
        strList.add("dog");
        strList.add("deer");
        strList.add("panda");

        for (String s : strList) {
            trie2.add(s);
        }

        Assertions.assertEquals(strList.size(), trie2.getSize());
    }

    @Test
    public void add() {
        strList.add("cat");
        Set<String> strSet = new HashSet<>(strList);
        Assertions.assertEquals(strSet.size(), trie2.getSize());
        System.out.println();
    }

    @Test
    public void contains() {
        Assertions.assertFalse(trie2.contains("c"));
    }


    @Test
    public void isPrefix() {
        Assertions.assertTrue(trie2.isPrefix("c"));
        Assertions.assertFalse(trie2.isPrefix("cay"));
        Assertions.assertTrue(trie2.isPrefix("cat"));
        Assertions.assertTrue(trie2.isPrefix("pan"));
    }
}