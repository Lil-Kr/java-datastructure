package com.cy.datastructure.graph.graphbfs.path;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SingleSourcePathTest {

  private SingleSourcePath singleSourcePath;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    singleSourcePath = new SingleSourcePath(new Graph(arr), 0);
    Assertions.assertEquals(true, singleSourcePath.isConnectedTo(3));
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    singleSourcePath = new SingleSourcePath(new Graph(arr), 0);
    Assertions.assertEquals(false, singleSourcePath.isConnectedTo(5));
  }

  @Test
  public void test3() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    singleSourcePath = new SingleSourcePath(new Graph(arr), 0);
    Assertions.assertEquals("[0,2,6]", JSONArray.toJSONString(singleSourcePath.path(6)));
  }
}