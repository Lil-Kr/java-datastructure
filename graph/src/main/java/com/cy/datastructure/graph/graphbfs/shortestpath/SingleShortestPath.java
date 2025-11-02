package com.cy.datastructure.graph.graphbfs.shortestpath;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.*;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description: single source shortest path distance
 * 单源最短路径
 */
public class SingleShortestPath {

  private Graph G;

  private boolean[] visited;

  private int[] pre;

  /**
   * single source shortest path distance
   * 无权图单源最短路径
   */
  private int[] distance;

  public SingleShortestPath(Graph graph, int s) {
    this.G = graph;

    this.visited = new boolean[G.getV()];

    this.pre = new int[G.getV()];
    Arrays.fill(pre, -1);

    this.distance = new int[G.getV()];
    Arrays.fill(distance, -1);

    /**
     * each vertex will loop, start with 0
     */
    bfs(s);
  }

  private void bfs(int v) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(v);
    visited[v] = true;
    pre[v] = v;
    distance[v] = 0;

    while (!queue.isEmpty()) {
      Integer s = queue.poll();
      for (int w : G.adj(s)) {
        if (!visited[w]) {
          queue.offer(w);
          visited[w] = true;
          pre[w] = s;
          distance[w] = distance[s] + 1;
        }
      }
    }
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

  public int distance (int s, int t) {
    this.G.validateVertex(s);
    this.G.validateVertex(t);
    return visited[s] == visited[t] ? distance[t] - distance[s] : -1;
  }
}