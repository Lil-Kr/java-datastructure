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
 * 找到目标顶点后, 提前结束递归
 */
public class Path {

  private Graph G;

  private boolean[] visited;

  private int s;
  private int t;

  private int[] pre;

  public Path(Graph graph, int source, int target) {
    this.G = graph;

    graph.validateVertex(source);
    graph.validateVertex(target);
    this.s = source;
    this.t = target;

    visited = new boolean[G.getV()];
    this.pre = new int[G.getV()];
    Arrays.fill(pre, -1);

    dfs(source, source);
  }

  /**
   * prev order
   * @param v
   */
  private boolean dfs(int v, int parent) {
    visited[v] = true;
    pre[v] = parent;
    // 找到目标后, 提前终止递归
    if (v == t) return true;

    for (int w : G.adj(v)) {
      if (visited[w]) continue;
      if (dfs(w, v)) return true;
    }
    return false;
  }

  /**
   * 目标 t 对于 源头 s, 是否可达?
   * @return
   */
  public boolean isConnectedTo() {
    return visited[t];
  }

  /**
   * 从 t 到 源头 s 的全路径
   * @return
   */
  public List<Integer> path() {
    List<Integer> res = new ArrayList<>();
    if (!isConnectedTo()) {
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