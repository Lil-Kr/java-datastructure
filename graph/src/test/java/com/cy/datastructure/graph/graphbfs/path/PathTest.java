package com.cy.datastructure.graph.graphbfs.path;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

  private Path path;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    path = new Path(new Graph(arr), 0, 6);
    Assertions.assertEquals(true, path.isConnected());
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    path = new Path(new Graph(arr), 0, 5);
    Assertions.assertEquals(false, path.isConnected());
  }

  @Test
  public void test3() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    int source = 0, target = 6;
    path = new Path(new Graph(arr), source, target);
    Assertions.assertEquals("[0,2,6]", JSONArray.toJSONString(path.path(source, target)));
  }
}