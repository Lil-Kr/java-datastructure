package com.cy.datastructure.graph.graphdfs;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphDFS2Test {
  private GraphDFS2 graphDFS2;


  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graphDFS2 = new GraphDFS2(new Graph(arr));
    Assertions.assertEquals(2, graphDFS2.getCount());
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graphDFS2 = new GraphDFS2(new Graph(arr));
    boolean res = graphDFS2.isConnected(0, 5);
    Assertions.assertEquals(false, res);

    res = graphDFS2.isConnected(0, 6);
    Assertions.assertEquals(true, res);
  }

  @Test
  public void test3() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graphDFS2 = new GraphDFS2(new Graph(arr));
    System.out.println(JSONArray.toJSONString(graphDFS2.component()));
  }
}