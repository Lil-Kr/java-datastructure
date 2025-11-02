package com.cy.datastructure.graph.graphbfs.path;

import com.cy.datastructure.graph.adjlist.Graph;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description:
 * BFS: all source path
 */
public class AllSourcePath {

  private Graph G;

  private SingleSourcePath[] paths;

  public AllSourcePath(Graph graph) {
    this.G = graph;

    this.paths = new SingleSourcePath[G.getV()];

    for (int v = 0; v < G.getV(); v ++) {
      paths[v] = new SingleSourcePath(G, v);
    }
  }

  public boolean isConnected(int s, int t) {
    G.validateVertex(s);
    return paths[s].isConnectedTo(t);
  }

  public Iterable<Integer> path(int s, int t) {
    G.validateVertex(s);
    return paths[s].path(t);
  }
}