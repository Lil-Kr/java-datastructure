package com.cy.datastructure.graph.graphdfs.cyclecheck;

import com.cy.datastructure.graph.adjlist.Graph;

/**
 * @Author: Lil-K
 * @Date: 2025/11/1
 * @Description: 无向图的环检测
 */
public class CycleCheckGraph {

  private Graph G;

  private boolean[] visited;

  private boolean hasCycle = false;

  public CycleCheckGraph(Graph graph) {
    G = graph;
    visited = new boolean[G.getV()];
    // each vertex will loop
    for (int v = 0; v < G.getV(); v ++) {
      if (!visited[v]) {
        if (dfs(v, v)) {
          hasCycle = true;
          break;
        }
      }
    }
  }

  private boolean dfs(int v, int parent) {
    visited[v] = true;

    for (int w : G.adj(v)) {
      if (!visited[w]) {
        if (dfs(w, v)) return true;
      } else if (w != parent) {
        // 图中找到了一个环
        return true;
      }
    }
    return false;
  }

  public boolean isHasCycle() {
    return hasCycle;
  }
}