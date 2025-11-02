package com.cy.datastructure.graph.graphbfs;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GraphBSF2Test {

  private GraphBSF2 graph;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graph = new GraphBSF2(new Graph(arr));
    // test component
    Assertions.assertEquals("[0,0,0,0,0,1,0]", JSONArray.toJSONString(graph.getVisited()));

    // test source and target is connected or not
    Assertions.assertEquals(true, graph.isConnected(0, 6));
    Assertions.assertEquals(false, graph.isConnected(0, 5));

    // test component count
    Assertions.assertEquals(2, graph.getCount());

    // test each component order
    List<Integer>[] components = graph.component();
    Assertions.assertEquals("[[0,1,2,3,4,6],[5]]", JSONArray.toJSONString(components));
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graph = new GraphBSF2(new Graph(arr));
    Assertions.assertEquals(true, graph.isHasCycle());
  }

  @Test
  public void test3() {
    String[][] arr = {{"7", "5"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "6"}};
    graph = new GraphBSF2(new Graph(arr));
    Assertions.assertEquals(false, graph.isHasCycle());
  }

  @Test
  public void test4() {
    String[][] arr = {{"7", "7"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"5", "6"}};
    graph = new GraphBSF2(new Graph(arr));
    Assertions.assertEquals(true, graph.isBipartite());
  }

  @Test
  public void test5() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"5", "6"}};
    graph = new GraphBSF2(new Graph(arr));
    Assertions.assertEquals("[0,2,6]", JSONArray.toJSONString(graph.path(0, 6)));
  }
}