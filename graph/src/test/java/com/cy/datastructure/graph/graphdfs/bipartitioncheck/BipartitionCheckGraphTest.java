package com.cy.datastructure.graph.graphdfs.bipartitioncheck;

import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BipartitionCheckGraphTest {

  private BipartitionCheckGraph graph;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"6", "5"}};
    graph = new BipartitionCheckGraph(new Graph(arr));
    Assertions.assertEquals(true, graph.isBipartite());
  }

  @Test
  public void test2() {
    String[][] arr = {{"4", "6"}, {"0", "1"}, {"0", "2"}, {"0", "3"}, {"1", "2"}, {"1", "3"}, {"2", "3"}};
    graph = new BipartitionCheckGraph(new Graph(arr));
    Assertions.assertEquals(false, graph.isBipartite());
  }
}