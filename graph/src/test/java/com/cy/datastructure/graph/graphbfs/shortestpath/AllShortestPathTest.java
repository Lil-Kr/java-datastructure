package com.cy.datastructure.graph.graphbfs.shortestpath;

import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AllShortestPathTest {
  private AllShortestPath graph;

  @Test
  public void test1() {
    String[][] arr = {{"7", "7"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"5", "6"}};
    graph = new AllShortestPath(new Graph(arr));

    Assertions.assertEquals(2, graph.distance(0, 6));
    Assertions.assertEquals(3, graph.distance(1, 6));
    Assertions.assertEquals(1, graph.distance(1, 4));
    Assertions.assertEquals(4, graph.distance(1, 5));
    Assertions.assertEquals(5, graph.distance(4, 5));
    Assertions.assertEquals(1, graph.distance(5, 6));
  }


  @Test
  public void test2() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graph = new AllShortestPath(new Graph(arr));

    Assertions.assertEquals(2, graph.distance(0, 6));
    Assertions.assertEquals(4, graph.distance(4, 6));
    Assertions.assertEquals(-1, graph.distance(5, 6));
  }
}