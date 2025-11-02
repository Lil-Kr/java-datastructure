package com.cy.datastructure.graph.graphbfs;

import com.cy.datastructure.graph.adjlist.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Lil-K
 * @Date: 2025/11/2
 * @Description: Graph bfs
 * O(V + E)
 */
public class GraphBSF {

  private Graph G;

  private boolean[] visited;

  private List<Integer> order;

  public GraphBSF(Graph graph) {
    this.G = graph;
    this.visited = new boolean[G.getV()];

    this.order = new ArrayList<>();

    // each vertex will loop
    for (int v = 0; v < G.getV(); v ++) {
      if (!visited[v]) {
        bfs(v);
      }
    }
  }

  private void bfs(int v) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(v);
    visited[v] = true;
    while (!queue.isEmpty()) {
      Integer s = queue.poll();
      order.add(s);
      for (int w : G.adj(s)) {
        if (visited[w]) continue;
        queue.offer(w);
        visited[w] = true;
      }
    }
  }

  public List<Integer> getOrder() {
    return order;
  }
}