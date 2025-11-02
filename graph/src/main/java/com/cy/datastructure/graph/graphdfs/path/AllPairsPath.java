package com.cy.datastructure.graph.graphdfs.path;

import com.cy.datastructure.graph.adjlist.Graph;

/**
 * @Author: Lil-K
 * @Date: 2025/10/31
 * @Description:
 * 多源路径
 */
public class AllPairsPath {

  private Graph G;

  // 我们定义一个 SingleSourcePath 的数组，存储每一个顶点对应的单源路径问题的解
  private SingleSourcePath[] paths;

  // 在构造函数中，只需要传入图 G 就好了，不需要指定任何“源”
  public AllPairsPath(Graph G){
    this.G = G;

    // 初始化 paths 数组
    // 图中有 V 个顶点，paths 数组中就有 V 个元素
    paths = new SingleSourcePath[G.getV()];

    // 初始化，对于图中的每一个顶点 v
    // paths[v] 解决了图 G 的从源 v 开始的路径问题
    for(int v = 0; v < G.getV(); v ++)
      paths[v] = new SingleSourcePath(G, v);
  }

  // 看从顶点 s 到顶点 t 是否有路径？
  public boolean isConnected(int s, int t){
    // 直接复用 SingleSourcePath 中的 isConnectedTo 方法
    // 我们只需要验证 s 的合法性即可；
    // 因为 t 的合法性在 isConnectedTo 中会验证
    G.validateVertex(s);
    return paths[s].isConnectedTo(t);
  }

  // 求从顶点 s 到顶点 t 的路径
  public Iterable<Integer> path(int s, int t){
    // 直接复用 SingleSourcePath 中的 path 方法
    G.validateVertex(s);
    return paths[s].path(t);
  }
}