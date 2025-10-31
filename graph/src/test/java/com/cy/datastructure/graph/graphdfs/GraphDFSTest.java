package com.cy.datastructure.graph.graphdfs;

import com.cy.datastructure.graph.adjlist.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphDFSTest {

  private GraphDFS graphDFS;

  @Test
  public void test1() {
    String[][] arr = {{"7", "9"}, {"0", "1"}, {"0", "3"}, {"1", "2"}, {"1", "6"}, {"2", "3"}, {"2", "5"}, {"3", "4"}, {"4", "5"}, {"5", "6"}};
    graphDFS = new GraphDFS(new Graph(arr));
    Assertions.assertEquals("[0,1,2,3,4,5,6]", graphDFS.toString());
  }

  @Test
  public void test2() {
    String[][] arr = {{"7", "8"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}, {"3", "5"}, {"5", "6"}};
    graphDFS = new GraphDFS(new Graph(arr));
    Assertions.assertEquals("[0,1,3,2,6,5,4]", graphDFS.toString());
  }

  @Test
  public void test3() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graphDFS = new GraphDFS(new Graph(arr));
    Assertions.assertEquals("[0,1,3,2,6,4,5]", graphDFS.toString());
  }

  // connected component count
  @Test
  public void test4() {
    String[][] arr = {{"7", "6"}, {"0", "1"}, {"0", "2"}, {"1", "3"}, {"1", "4"}, {"2", "3"}, {"2", "6"}};
    graphDFS = new GraphDFS(new Graph(arr));
    Assertions.assertEquals(2, graphDFS.getCount());
  }
}