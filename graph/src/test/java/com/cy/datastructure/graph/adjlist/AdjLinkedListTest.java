package com.cy.datastructure.graph.adjlist;

import org.junit.jupiter.api.Test;

class AdjLinkedListTest {
  private AdjLinkedList adjList;

  @Test
  public void test1() {
    String[][] arr = {{"7", "9"},{"0","1"}, {"0","3"}, {"1","2"}, {"1","6"}, {"2","3"}, {"2","5"}, {"3","4"}, {"4","5"}, {"5","6"}};
    adjList = new AdjLinkedList(arr);
    String res = adjList.toString();
    System.out.println(res);
  }
}