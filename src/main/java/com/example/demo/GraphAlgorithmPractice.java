package com.example.demo;

import java.util.*;

import static java.util.Map.*;

public class GraphAlgorithmPractice {

    public static void main(String[] args) {

        Graph graph = Utils.buildNodeGraph();
        dijkstra(graph.strNodes.get("A"));

        kruskal();

        prim();
    }

    public static void dijkstra(Node head) {

        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> calculatedNodes = new HashSet<>();

        distanceMap.put(head, 0);

        Node minNode = getMinNodeFromUncalculated(distanceMap, calculatedNodes);

        while(minNode != null) {
            int distance = distanceMap.get(minNode);
            for(Edge edge : minNode.edges) {
                Node toNode = edge.to;
                distanceMap.put(toNode, Math.min(distanceMap.getOrDefault(toNode, Integer.MAX_VALUE), distance + edge.weight));
            }
            calculatedNodes.add(minNode);
            minNode = getMinNodeFromUncalculated(distanceMap, calculatedNodes);
        }

        System.out.println("源点 A 到各个节点的最小距离是 " + distanceMap);
    }

    public static Node getMinNodeFromUncalculated(Map<Node, Integer> distanceMap, Set<Node> calculatedNodes) {

        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for(Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if(!calculatedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }

    public static void kruskal() {

        Graph graph = Utils.buildNodeGraph();

        UnionFindNode ufn = new UnionFindNode(graph.strNodes.values().stream().toList());

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        queue.addAll(graph.edges);

        Set<Edge> result = new HashSet<>();

        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            if(!ufn.isInSameSet(edge.from, edge.to)) {
                result.add(edge);
                ufn.union(edge.from, edge.to);
            }
        }

        System.out.println("kruskal的最小生成树是: " + result);
    }

    public static void prim() {

        Graph graph = Utils.buildNodeGraph();
        Set<Node> calculatedNodes = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        Set<Edge> result = new HashSet<>();

        for(Node node : graph.strNodes.values().stream().toList()) {

            if(!calculatedNodes.contains(node)) {
                calculatedNodes.add(node);
                queue.addAll(node.edges);
                while(!queue.isEmpty()) {
                    Edge shortEdge = queue.poll();
                    if(!calculatedNodes.contains(shortEdge.to)) {
                        calculatedNodes.add(shortEdge.to);
                        queue.addAll(shortEdge.to.edges);
                        result.add(shortEdge);
                    }
                }
            }
        }
        System.out.println("prim的最小生成树是: " + result);
    }

    public static void dfs() {

        List<List<Integer>> graph = Utils.buildIntegerGraph();

        LinkedList<Integer> stack = new LinkedList<>();

        
    }
}
