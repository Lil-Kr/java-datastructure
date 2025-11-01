package com.cy.datastructure.graph.bipartitioncheck;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.Arrays;

/**
 * @Author: Lil-K
 * @Date: 2025/11/1
 * @Description: 二分图检测
 */
public class BipartitionCheckGraph {

  private Graph G;

  private boolean[] visited;

  private int[] colors;

  private boolean isBipartite = true;

  public BipartitionCheckGraph(Graph graph) {
    G = graph;
    visited = new boolean[G.getV()];

    // 0: green, 1: blue
    colors = new int[G.getV()];
    Arrays.fill(colors, -1);

    // each vertex will loop
    for (int v = 0; v < G.getV(); v ++) {
      if (!visited[v]) {
        // 初始 顶点 统一为 0: green
        if (!dfs(v, 0)) {
          isBipartite = false;
          break;
        }
      }
    }
  }

  private boolean dfs(int v, int color) {
    visited[v] = true;
    colors[v] = color;
    for (int w : G.adj(v)) {
      if (!visited[w]) {
        if (!dfs(w, 1 - color)) return false;
      } else if (colors[w] == colors[v]) {
        return false;
      }
    }
    return true;
  }

  public boolean isBipartite() {
    return isBipartite;
  }
}