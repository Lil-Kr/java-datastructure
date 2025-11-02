package com.cy.datastructure.graph.graphdfs.cyclecheck;

import com.cy.datastructure.graph.adjlist.Graph;
import com.cy.datastructure.graph.graphdfs.cyclecheck.CycleCheckGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CycleCheckGraphTest {
  private CycleCheckGraph graph;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "6"}, {"3", "4"}};
    graph = new CycleCheckGraph(new Graph(arr));
    Assertions.assertEquals(true, graph.isHasCycle());
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "5"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "6"}, {"3", "1"}};
    graph = new CycleCheckGraph(new Graph(arr));
    Assertions.assertEquals(false, graph.isHasCycle());
  }
}