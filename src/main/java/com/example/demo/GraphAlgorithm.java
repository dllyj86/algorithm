package com.example.demo;

import java.util.*;

/**
 * 这个类是所有图相关算法
 */
public class GraphAlgorithm {

    public static void main(String[] args) {

        Graph graph = Utils.buildNodeGraph();
        Map<Node, Integer> distanceMap = dijkstra(graph.strNodes.get("A"));
        System.out.println("最终源节点到各个节点的最短距离分别是: " + distanceMap);
        kruskal();
        prim();
        dfs();
        bfs();
    }

    public static Map<Node, Integer> dijkstra(Node head) {

//        System.out.println("Start dijkstra. 源节点是 " + head);

        /**
         * distanceMap 存放了从 head(源点) 出发到所有点的最小距离
         * key : 从head出发到达key
         * value : 从head出发到key的最小距离
         * 如果在表中，没有记录的点，含义是从head出发到这个点的距离为正无穷
         */
        Map<Node, Integer> distanceMap = new HashMap<>();
        // 默认先把源点到自身的距离加到distanceMap中
        distanceMap.put(head, 0);

//        System.out.println("distanceMap 是 " + distanceMap);

        // 已经求过距离的点，存在selectedNodes中，以后再也不碰
        Set<Node> calculatedNodes = new HashSet<>();

        Node minDistanceNode = getMinDistanceAndUnselectedNode(distanceMap, calculatedNodes);

        while (minDistanceNode != null) {
//            System.out.println("minNode 是 " + minNode + " , 是当前处理的节点");

            // distance是源节点到minNode节点的距离, 也就是当前所有节点中距离最小的节点的距离
            int minDistance = distanceMap.get(minDistanceNode);

//            System.out.println("源节点到 miniNode " + minNode + " 的距离是 " + distance);

            // 这里根据节点发出的线来一条条处理, 而不是根据目标节点
            for (Edge edge : minDistanceNode.edges) {
                // 获得线所指向的节点
                Node toNode = edge.to;
//                System.out.println(minNode + " 发出的线所到的目标节点是 " + toNode);

//                    System.out.println("distanceMap 中没有从源点到 " + toNode + " 的距离");
//                    System.out.println("将 " + toNode + " 添加到 distanceMap中, 这时, 源节点到 "
//                            + toNode + " 的距离是: 源节点到 " + minNode + " 的距离 " + distance + " 加上 " + minNode + " 到 " + toNode + " 的距离 " + edge.weight);

                // 如果不存在则放入, 如果存在则更新距离
                distanceMap.put(toNode, Math.min(distanceMap.getOrDefault(toNode, Integer.MAX_VALUE), minDistance + edge.weight));

//                System.out.println("因为, 源节点到 " + minNode + " 的距离是 " + distance);
//                System.out.println(minNode + " 到 " + toNode + "的距离是 " + edge.weight);
//                System.out.println("所以 distanceMap 中此时源节点到 " + edge.to + " 节点的最小距离取 " + Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            // 从节点发出来的所有线都处理过后, 节点就再也不用处理了, 放入calculatedNodes集合中
            calculatedNodes.add(minDistanceNode);
            /**
             * 因为属于贪心算法, 所以就继续找出源节点到 distarnceMap 中的节点距离最小的点作为 minNode
             * 再次进入 while 循环, 处理从这个距离最小节点发出的线的距离, 更新它所连接到的节点的距离(源节点到这些点的距离).
             */
            minDistanceNode = getMinDistanceAndUnselectedNode(distanceMap, calculatedNodes);
        }
        return distanceMap;
    }

    /**
     * dijkstra算法使用的辅助函数
     *
     * @param distanceMap
     * @param calculatedNodes
     * @return
     */
    public static Node getMinDistanceAndUnselectedNode(Map<Node, Integer> distanceMap, Set<Node> calculatedNodes) {
        Node minDistanceNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!calculatedNodes.contains(node) && distance < minDistance) {
                minDistance = distance;
                minDistanceNode = node;
            }
        }
//        System.out.println("getMinDistanceAndUnselectedNode 返回所有已知距离的节点中, 到源节点距离最小的节点 " + minDistanceNode + " , 它对应的最小距离minDistance 是 " + minDistance);
        return minDistanceNode;
    }

    public static void kruskal() {

        Graph graph = Utils.buildNodeGraph();
        // 初始化并查集的数据
        UnionFindNode ufn = new UnionFindNode(graph.strNodes.values().stream().toList());
        System.out.println("节点数量是" + ufn.nodeParentMap.size());
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        for (Edge edge : graph.edges) {
            queue.offer(edge);
        }
        System.out.println("边的数量是" + queue.size());
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            Node fromNode = edge.from;
            Node toNode = edge.to;
            if (!ufn.isInSameSet(fromNode, toNode)) {
                System.out.println("将边" + edge + "加入结果集中");
                result.add(edge);
                ufn.union(fromNode, toNode);
            }
        }

        /**
         *  以 Utils.buildNodeGraph() 函数生成的图做计算, 则最小生成树是(无向图, 不关心方向):
         *  D - A, E - B, B - C, C - A
         */
        System.out.println("最小生成树的长度是: " + result.toString());
    }

    public static void prim() {

        System.out.println("Prim算法开始执行");
        Graph graph = Utils.buildNodeGraph();
        System.out.println("图是" + graph);
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        HashSet<Edge> result = new HashSet<>();
        HashSet<Node> handledNodes = new HashSet<>();

        /**
         * 外层的for循环是为了应对多颗树(森林)的情况. 如果只是普通的连通的图, 则不需要
         * 当题目是森林时, 则每棵树要分别处理.
         */
        for (Node node : graph.strNodes.values().stream().toList()) {
            // 判断点是否被处理过, 没有处理过则将它放入已处理的集合中, 处理它.
            if (!handledNodes.contains(node)) {
                handledNodes.add(node);
                List<Edge> edges = node.edges;
                queue.addAll(edges);
                while (!queue.isEmpty()) {
                    Edge shortEdge = queue.poll();
                    Node toNode = shortEdge.to;
                    if (!handledNodes.contains(toNode)) {
                        handledNodes.add(toNode);
                        result.add(shortEdge);
                        queue.addAll(toNode.edges);
                    }
                }
            }
        }

        System.out.println("最小生成树是 " + result);
    }

    public static void dfs() {

        System.out.println("DFS start");
        List<List<Integer>> graph = Utils.buildIntegerGraph();
        LinkedList<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        // 假设从节点 1 开始遍历
        stack.push(1);
        while (!stack.isEmpty()) {
            System.out.println("Stack is " + stack);
            int current = stack.pop();
            visited[current] = true;
            System.out.println("Visited node " + current);
            System.out.println("stack left: " + stack);
            ArrayList<Integer> connectedNodes = (ArrayList<Integer>) graph.get(current);
            System.out.println("NodeIndex " + current + " has connected nodes " + connectedNodes);
            // 遍历邻居节点
            for (int node : connectedNodes) {
                if (!visited[node]) {
                    stack.push(node);
                } else {
                    System.out.println("Node " + node + " visited. Not push to stack.");
                }
            }
        }
        System.out.println("DFS done");
    }

    public static void dfsWithRecursion() {
        System.out.println("DFS with recursion start");

        List<List<Integer>> graph = Utils.buildIntegerGraph();
        // 用来标记第几个节点是否被访问过. 下标是节点的index, 而不是节点的号码
        boolean[] visited = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfsRecursion(i, graph, visited);
            }
        }

        System.out.println("DFS with recursion done");
    }

    public static void dfsRecursion(int nodeIndex, List<List<Integer>> graph, boolean[] visited) {

        visited[nodeIndex] = true;
        System.out.println("Visited node " + nodeIndex);
        List<Integer> connectedNodes = graph.get(nodeIndex);
        System.out.println("NodeIndex " + nodeIndex + " has connected nodes " + connectedNodes);

        for (int nodeNumber : connectedNodes) {
            if (!visited[nodeNumber]) {
                dfsRecursion(nodeNumber, graph, visited);
            } else {
                System.out.println("Node number " + nodeNumber + " was visited, skip.");
            }
        }
    }

    public static void bfs() {

        System.out.println("BFS start");
        // 生成图
        List<List<Integer>> graph = Utils.buildIntegerGraph();
        // 记录哪些节点被访问过了
        boolean[] visited = new boolean[graph.size()];
        // 准备算法要使用的queue, 存放将要访问的节点
        LinkedList<Integer> queue = new LinkedList<>();

        // 从下标是0的节点开始
        queue.offer(4);

        // 队列不为空, 说明有还没搜索过的点
        while (!queue.isEmpty()) {
            System.out.println("Queue is " + queue);
            // 取出队列的第一个节点
            int current = queue.poll();
            visited[current] = true;
            System.out.println("Visited node " + current);
            System.out.println("Queue left: " + queue);
            // 取出当前访问的节点所连接的所有节点
            ArrayList<Integer> connectedNodes = (ArrayList<Integer>) graph.get(current);
            System.out.println("NodeIndex " + current + " has connected nodes " + connectedNodes);
            for (int node : connectedNodes) {
                // 将没有访问过的节点放入队列, 准备访问. 提过访问过的节点, 防止重复访问
                if (!visited[node]) {
                    queue.add(node);
                } else {
                    System.out.println("Node " + node + " visited. Not push to stack.");
                }
            }
        }
        System.out.println("BFS done");
    }
}





