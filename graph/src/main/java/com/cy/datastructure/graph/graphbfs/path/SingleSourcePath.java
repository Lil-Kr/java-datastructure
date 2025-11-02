package com.cy.datastructure.graph.graphbfs.path;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.*;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description:
 * BFS -> fix single source path
 */
public class SingleSourcePath {

  private Graph G;

  private int s;

  private boolean[] visited;

  private int[] pre;

  public SingleSourcePath(Graph graph, int s) {
    this.G = graph;
    this.s = s;
    this.visited = new boolean[G.getV()];

    this.pre = new int[G.getV()];
    Arrays.fill(pre, -1);

    bfs(s);
  }

  private void bfs(int s) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(s);
    visited[s] = true;
    pre[s] = s;
    while (!queue.isEmpty()) {
      int v = queue.poll();
      for (int w : G.adj(v)) {
        if (visited[w]) continue;
        queue.offer(w);
        visited[w] = true;
        pre[w] = v;
      }
    }
  }

  public boolean isConnectedTo(int t) {
    G.validateVertex(t);
    return visited[t];
  }

  public List<Integer> path(int t) {
    List<Integer> res = new ArrayList<>();
    if (!isConnectedTo(t)) return res;

    int cur = t;
    while (cur != this.s) {
      res.add(cur);
      cur = pre[cur];
    }
    res.add(this.s);
    Collections.reverse(res);
    return res;
  }
}