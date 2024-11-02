package com.cy.datastructure.redblacktree.cls;

import com.cy.datastructure.common.helper.FileOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
class RBTree2Test {

    private static ClassPathResource resource = new ClassPathResource("pride-and-prejudice.txt");

    @Test
    public void test_1() throws IOException {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String path = resource.getURL().getPath();

        if(FileOperation.readFile(path, words)) {
            System.out.println("Total words: " + words.size());
            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}