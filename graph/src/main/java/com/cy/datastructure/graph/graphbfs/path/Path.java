package com.cy.datastructure.graph.graphbfs.path;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.*;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description:
 * BFS: all source path and if find target will return right now
 */
public class Path {

  private Graph G;

  private boolean[] visited;
  private int[] pre;
  private int s;
  private int t;

  public Path(Graph graph, int source, int target) {
    this.G = graph;
    this.visited = new boolean[G.getV()];

    G.validateVertex(source);
    G.validateVertex(target);
    this.s = source;
    this.t = target;

    this.pre = new int[G.getV()];
    Arrays.fill(this.pre, -1);

    bfs(this.s, this.t);
  }

  private boolean bfs(int s, int t) {
    visited[s] = true;
    pre[s] = s;

    if (s == t) return true;

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(s);

    while (!queue.isEmpty()) {
      int v = queue.poll();
      if (v == t) return true;
      for (int w : G.adj(v)) {
        if (visited[w]) continue;
        queue.offer(w);
        visited[w] = true;
        pre[w] = v;

        if (w == t) return true;
      }
    }
    return false;
  }

  public boolean isConnected() {
    return visited[this.t];
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