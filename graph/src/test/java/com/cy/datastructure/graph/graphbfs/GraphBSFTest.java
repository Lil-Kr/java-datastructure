package com.cy.datastructure.graph.graphbfs;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphBSFTest {
  private GraphBSF graphBSF;

  @Test
  public void test1() {
    String[][] arr = {{"7", "7"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"5", "6"}};
    graphBSF = new GraphBSF(new Graph(arr));
    Assertions.assertEquals("[0,1,2,3,4,6,5]", JSONArray.toJSONString(graphBSF.getOrder()));
  }
}