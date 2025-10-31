package com.cy.datastructure.graph.path;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PathTest {
  private Path path;

  @Test
  public void test1() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    path = new Path(new Graph(arr), 0, 6);
    Assertions.assertEquals(true, path.isConnectedTo());
    Assertions.assertEquals("[0,1,3,2,6]", JSONArray.toJSONString(path.path()));

    path = new Path(new Graph(arr), 0, 5);
    Assertions.assertEquals(false, path.isConnectedTo());
  }

}