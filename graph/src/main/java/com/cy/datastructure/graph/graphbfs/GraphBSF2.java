package com.cy.datastructure.graph.graphbfs;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description: Graph bfs, component count
 * 记录联通分量, 做环检测, 做二分图检测
 * O(V + E)
 */
public class GraphBSF2 {

  private Graph G;

  private int[] visited;

  private int count;

  private int[] pre;

  private boolean hasCycle = false;

  private int[] colors;

  private boolean isBipartite = true;

  public GraphBSF2(Graph graph) {
    this.G = graph;
    this.count = 0;

    this.visited = new int[G.getV()];
    Arrays.fill(visited, -1);

    this.pre = new int[G.getV()];
    Arrays.fill(pre, -1);

    this.colors = new int[G.getV()];
    Arrays.fill(colors, -1);

    /**
     * each vertex will loop, start with 0
     */
    for (int v = 0; v < G.getV(); v ++) {
      if (visited[v] != -1) continue;
      bfs(v, count);
      count ++;
    }
  }

  private void bfs(int v, int ccid) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(v);
    visited[v] = ccid;
    pre[v] = v;
    colors[v] = 0;

    while (!queue.isEmpty()) {
      Integer s = queue.poll();
      for (int w : G.adj(s)) {
        if (visited[w] == -1) {
          queue.offer(w);
          visited[w] = ccid;
          pre[w] = s;
          colors[w] = 1 - colors[s];
        } else if (pre[s] != w) {
          hasCycle = true;
        } else if (colors[s] == colors[w]) {
          isBipartite = false;
        }
      }
    }
  }

  /**
   * 返回联通分量的分布情况
   * @return component
   */
  public List<Integer> getVisited() {
    return Arrays.stream(this.visited).boxed().collect(Collectors.toList());
  }

  /**
   * source connected target
   * @param s
   * @param t
   * @return
   */
  public boolean isConnected(int s, int t) {
    G.validateVertex(s);
    G.validateVertex(t);
    return visited[s] == visited[t];
  }

  /**
   * component count
   * @return
   */
  public int getCount() {
    return count;
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

  public boolean isHasCycle() {
    return hasCycle;
  }

  /**
   * 二分图检测
   * @return the Graph is bipartite or not
   */
  public boolean isBipartite() {
    return isBipartite;
  }

  public List<Integer> path(int s, int t) {
    List<Integer> res = new ArrayList<>();

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