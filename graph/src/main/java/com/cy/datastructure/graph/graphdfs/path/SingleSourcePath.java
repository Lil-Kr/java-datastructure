package com.cy.datastructure.graph.graphdfs.path;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2025/10/31
 * @Description:
 * 求单源路径: 固定源头
 */
public class SingleSourcePath {
  private Graph G;

  private boolean[] visited;

  private int s;

  private int[] pre;

  public SingleSourcePath(Graph graph, int source) {
    this.G = graph;

    graph.validateVertex(source);
    this.s = source;

    this.pre = new int[G.getV()];
    Arrays.fill(pre, -1);

    visited = new boolean[G.getV()];
    dfs(source, source);
  }

  /**
   * prev order
   * @param v
   */
  private void dfs(int v, int parent) {
    visited[v] = true;
    pre[v] = parent;
    for (int w : G.adj(v)) {
      if (visited[w]) continue;
      dfs(w, v);
    }
  }

  /**
   * 目标 t 对于 源头 s, 是否可达?
   * @param t
   * @return
   */
  public boolean isConnectedTo(int t) {
    G.validateVertex(t);
    return visited[t];
  }

  /**
   * 从 t 到 源头 s 的全路径
   * @param t
   * @return
   */
  public List<Integer> path(int t) {
    List<Integer> res = new ArrayList<>();
    if (!isConnectedTo(t)) {
      return res;
    }

    int cur = t;
    while (cur != s) {
      res.add(cur);
      cur = pre[cur];
    }
    res.add(s);

    Collections.reverse(res);
    return res;
  }
}