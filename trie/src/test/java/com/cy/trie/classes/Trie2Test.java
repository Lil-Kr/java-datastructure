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


    @BeforeEach
    public void init() {
        trie2 = new Trie2();
    }

    @Test
    void add() {
        List<String> stringList = new ArrayList<>();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("deer");
        stringList.add("panda");

        for (String s : stringList) {
            trie2.add(s);
        }

        Set<String> strSet = new HashSet<>(stringList);

        Assertions.assertEquals(strSet.size(), trie2.getSize());

        System.out.println();
    }

    @Test
    void query() {
    }
}