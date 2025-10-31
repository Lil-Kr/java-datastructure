package com.cy.datastructure.graph.graphdfs;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2025/10/31
 * @Description:
 * depth-first search algorithm implement
 *
 * O(V + E)
 */
public class GraphDFS {

  private Graph G;
  private boolean[] visited;
  // 联通分量: connected component
  private int count = 0;
  private List<Integer> pre = new ArrayList<>();
  private List<Integer> post = new ArrayList<>();

  public GraphDFS(Graph graph) {
    G = graph;
    visited = new boolean[G.getV()];
    // each vertex will loop
    for (int v = 0; v < G.getV(); v ++) {
      if (!visited[v]) {
        dfs(v);
        count++;
      }
    }
  }

  /**
   * prev order
   * @param v
   */
  private void dfs(int v) {
    visited[v] = true;
    pre.add(v);

    for (int w : G.adj(v)) {
      if (visited[w]) continue;
      dfs(w);
    }
    // post order
     post.add(v);
  }

  public Iterable<Integer> pre() {
    return pre;
  }

  public Iterable<Integer> post() {
    return post;
  }

  public int getCount() {
    return count;
  }

  @Override
  public String toString() {
    return JSONArray.toJSONString(pre);
  }
}