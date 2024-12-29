package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Models {

}

/**
 * 描述"二叉树"的节点
 */
class TreeNode {

    public int identifier;
    public String identifierStr;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode(String identifierStr) {
        this.identifierStr = identifierStr;
    }

    public TreeNode(int identifier) {
        this.identifier = identifier;
    }
}

/**
 * 描述节点信息的类
 */
class Node {
    // 点的标识, 比如0号点, A点(需要改成String类型)
    public int identifier;
    // 跟上面的属性一样, 是点的标识, 只不过类型是String
    public String strIdentifier;
    // 入度 - 几条边(线)指向该点
    public int in;
    // 出度 - 几条边(线)从这个点指出去
    public int out;
    // 从这个点"连接出去"所到的点的集合
    public ArrayList<Node> nexts;
    // 从这个点"指出去"的边(线)的集合
    public ArrayList<Edge> edges;

    public Node(int identifier) {
        this.identifier = identifier;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Node(String strIdentifier) {
        this.strIdentifier = strIdentifier;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.strIdentifier);
        return sb.toString();
    }
}

/**
 * 描述边(线)的类
 */
class Edge {
    // 该条边(线)的权重
    public int weight;
    // 有向边的起始节点
    public Node from;
    // 有向边的目标节点
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(from);
        sb.append("->");
        sb.append(to);
        return sb.toString();
    }
}

/**
 * 描述图的类
 */
class Graph {
    // 图上所有点的集合. Key是点的编号, 比如0号点, A点(类型需要改成String)
    public HashMap<Integer, Node> nodes;
    // 与上面的属性类似, 只不过key是String
    public HashMap<String, Node> strNodes;
    // 图上所有边(线)的集合
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.strNodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("nodes : ");
        sb.append(nodes.toString());
        sb.append(" | strNodes : ");
        sb.append(strNodes.toString());
        sb.append(" | edges : ");
        sb.append(edges.toString());
        return sb.toString();
    }
}