package com.cy.datastructure.graph.path;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import com.cy.datastructure.graph.path.SingleSourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SingleSourcePathTest {

  private SingleSourcePath singleSourcePath;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    singleSourcePath = new SingleSourcePath(new Graph(arr), 0);
    List<Integer> res = singleSourcePath.path(6);
    Assertions.assertEquals("[0,1,3,2,6]", JSONArray.toJSONString(res));
  }
}