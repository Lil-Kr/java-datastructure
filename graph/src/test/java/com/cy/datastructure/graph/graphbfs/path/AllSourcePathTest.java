package com.cy.datastructure.graph.graphbfs.path;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AllSourcePathTest {

  private AllSourcePath sourcePath;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    sourcePath = new AllSourcePath(new Graph(arr));
    boolean res = sourcePath.isConnected(0, 6);
    Assertions.assertEquals(true, res);

    res = sourcePath.isConnected(0, 5);
    Assertions.assertEquals(false, res);
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    sourcePath = new AllSourcePath(new Graph(arr));
    Assertions.assertEquals("[0,2,6]", JSONArray.toJSONString(sourcePath.path(0, 6)));
  }
}