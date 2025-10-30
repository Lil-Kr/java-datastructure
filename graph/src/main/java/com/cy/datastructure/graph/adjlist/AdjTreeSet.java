package com.cy.datastructure.graph.adjlist;

import com.alibaba.fastjson2.JSONArray;

import java.util.TreeSet;

/**
 * @Author: Lil-K
 * @Date: 2025/10/30
 * @Description:
 * 图的表示: 改进邻接表
 */
public class AdjTreeSet {
  // how many vertex
  private int V;
  // how many edge
  private int E;

  private TreeSet<Integer>[] adj;

  public AdjTreeSet() {}

  public AdjTreeSet(String filename) {

  }

  public AdjTreeSet(String[][] arr) {
    String[] head = arr[0];
    V = Integer.parseInt(head[0]);
    E = Integer.parseInt(head[1]);
    adj = new TreeSet[V];

    for (int i = 0; i < V; i ++) {
      adj[i] = new TreeSet<>();
    }

    for (int i = 0; i < E; i ++) {
      int a = Integer.parseInt(arr[i + 1][0]);
      validateVertex(a);
      int b = Integer.parseInt(arr[i + 1][1]);
      validateVertex(b);

      // 判断是否为自环边
      if (a == b) throw new IllegalArgumentException("Self Loop is Detected!");

      // 判断是否为平行边
      if (adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

      adj[a].add(b);
      adj[b].add(a);
    }
  }

  private void validateVertex(int v) {
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + "is invalid");
    }
  }

  public int getV() {
    return V;
  }

  public int getE() {
    return E;
  }

  public boolean hasEdge(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    return adj[v].contains(w);
  }

  public TreeSet<Integer> adj(int v) {
    validateVertex(v);
    return adj[v];
  }

  /**
   * 求一个顶点的度
   * @param v
   * @return
   */
  public int degree(int v) {
    return adj(v).size();
  }

  @Override
  public String toString() {
    return JSONArray.toJSONString(adj);
  }
}