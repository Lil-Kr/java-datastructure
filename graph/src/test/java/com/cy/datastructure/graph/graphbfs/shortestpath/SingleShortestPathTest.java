package com.cy.datastructure.graph.graphbfs.shortestpath;

import com.cy.datastructure.graph.adjlist.Graph;
import com.cy.datastructure.graph.graphbfs.shortestpath.SingleShortestPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SingleShortestPathTest {

  private SingleShortestPath graph;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"5", "6"}};
    graph = new SingleShortestPath(new Graph(arr), 0);

    // 两个顶点在同一个联通分量
    Assertions.assertEquals(2, graph.distance(0, 6));

    // 两个顶点不在同一个联通分量
    Assertions.assertEquals(-1, graph.distance(0, 5));
  }
}