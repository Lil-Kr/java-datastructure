package com.cy.datastructure.graph.graphdfs;

import com.alibaba.fastjson2.JSONArray;
import com.cy.datastructure.graph.adjlist.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2025/10/31
 * @Description:
 * 改进:
 *  1. 标记某个顶点属于哪个联通分量
 *  2. 判断两个顶点是否属于同一个联通分量
 */
public class GraphDFS2 {
  private Graph G;
  //  private boolean[] visited;
  // 记录某个顶点属于哪个联通分量
  private int[] visited;

  // 联通分量: connected component
  private int count = 0;

  private List<Integer> pre = new ArrayList<>();
  private List<Integer> post = new ArrayList<>();

  public GraphDFS2(Graph graph) {
    G = graph;

    visited = new int[G.getV()];
    Arrays.fill(visited, -1);

    // each vertex will loop
    for (int v = 0; v < G.getV(); v ++) {
      if (visited[v] == -1) {
        dfs(v, count);
        count ++;
      }
    }
  }

  /**
   * prev order
   * @param v
   */
  private void dfs(int v, int ccid) {
    visited[v] = ccid;
    pre.add(v);

    for (int w : G.adj(v)) {
      if (visited[w] != -1) continue;
      dfs(w, ccid);
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

  public int[] getVisited() {
    return visited;
  }

  public boolean isConnected(int v, int w) {
    G.validateVertex(v);
    G.validateVertex(w);
    return visited[v] == visited[w];
  }

  public List<Integer>[] component() {
    List<Integer>[] res = new ArrayList[count];
    for (int i = 0; i < count; i++) {
      res[i] = new ArrayList<>();
    }

    for (int v = 0; v < G.getV(); v ++) {
      res[visited[v]].add(v);
    }
    return res;
  }

  @Override
  public String toString() {
    return JSONArray.toJSONString(pre);
  }
}