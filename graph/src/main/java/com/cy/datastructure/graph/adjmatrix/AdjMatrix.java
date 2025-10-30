package com.cy.datastructure.graph.adjmatrix;

import com.alibaba.fastjson2.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2025/10/30
 * @Description:
 * 图的表示: 邻接矩阵
 */
public class AdjMatrix {

  // how many vertex
  private int V;
  // how many edge
  private int E;

  private int[][] adj;

  public AdjMatrix() {}

  public AdjMatrix(String filename) {

  }

  public AdjMatrix(String[][] arr) {
    String[] head = arr[0];
    V = Integer.parseInt(head[0]);
    E = Integer.parseInt(head[1]);
    adj = new int[V][V];
    for (int i = 0; i < E; i ++) {
      int a = Integer.parseInt(arr[i + 1][0]);
      validateVertex(a);
      int b = Integer.parseInt(arr[i + 1][1]);
      validateVertex(b);

      // 判断是否为自环边
      if (a == b) throw new IllegalArgumentException("Self Loop is Detected!");

      // 判断是否为平行边
      if (adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected!");

      adj[a][b] = 1;
      adj[b][a] = 1;
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
    return adj[v][w] == 1;
  }

  public List<Integer> adj(int v) {
    validateVertex(v);
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < V; i ++) {
      if (adj[v][i] == 1) {
        res.add(i);
      }
    }
    return res;
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