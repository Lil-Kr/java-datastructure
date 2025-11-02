package com.cy.datastructure.graph.graphbfs.shortestpath;

import com.cy.datastructure.graph.adjlist.Graph;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description: all source shortest path distance
 * 无权图: 随机 两点 之间的最短距离
 */
public class AllShortestPath {

  private Graph G;

  private SingleShortestPath[] shortestPath;

  public AllShortestPath(Graph graph) {
    this.G = graph;
    this.shortestPath = new SingleShortestPath[G.getV()];

    for (int v = 0; v < G.getV(); v ++) {
      shortestPath[v] = new SingleShortestPath(G, v);
    }
  }

  public int distance(int s, int t) {
    return shortestPath[s].distance(s, t);
  }
}