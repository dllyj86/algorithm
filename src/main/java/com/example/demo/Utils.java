package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    /**
     * 生成二叉树
     * 
     *         1
     *        / \
     *       2   3
     *      / \
     *     4   5
     * 
     * @return
     */
    public static TreeNode buildBinaryTree() {

        TreeNode root = new TreeNode("1");
        TreeNode nodeA = new TreeNode("2");
        TreeNode nodeB = new TreeNode("3");
        TreeNode nodeC = new TreeNode("4");
        TreeNode nodeD = new TreeNode("5");
        root.left = nodeA;
        root.right = nodeB;
        nodeA.left = nodeC;
        nodeA.right = nodeD;
        return root;
    }

    /**
     *  使用邻接表表示图的结构, 图结构如下:
     *
     *      0 -- 5 -- 7 -- 9
     *           |
     *           |
     *           4 -- 1 -- 2
     *                |
     *                |
     *      6 -- 3 -- 8
     *
     * @return
     */
    public static List<List<Integer>> buildIntegerGraph() {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            graph.add(new ArrayList<>());
        }


        graph.get(0).add(5);

        graph.get(5).add(0);
        graph.get(5).add(7);
        graph.get(5).add(4);

        graph.get(7).add(5);
        graph.get(7).add(9);

        graph.get(9).add(7);

        graph.get(4).add(5);
        graph.get(4).add(1);

        graph.get(1).add(4);
        graph.get(1).add(2);
        graph.get(1).add(8);

        graph.get(2).add(1);

        graph.get(8).add(1);
        graph.get(8).add(3);

        graph.get(3).add(6);
        graph.get(3).add(8);

        graph.get(6).add(3);

        return graph;
    }

    /**
     * 生成的图结构如下
     *
     *   A --- B --- E
     *   |     |     |
     *   | --- C --- |
     *   |     |     |
     *   | --- D --- |
     *
     *   A -> B : 7
     *   A -> C : 3
     *   A -> D : 5
     *   B -> C : 2
     *   B -> E : 11
     *   C -> D : 7
     *   C -> E : 16
     *   D -> E : 13
     * @return
     */
    public static Graph buildNodeGraph() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        Edge edgeAB = new Edge(7, nodeA, nodeB);
        Edge edgeAC = new Edge(3, nodeA, nodeC);
        Edge edgeAD = new Edge(5, nodeA, nodeD);
        nodeA.edges.add(edgeAB);
        nodeA.edges.add(edgeAC);
        nodeA.edges.add(edgeAD);

        Edge edgeBC = new Edge(2, nodeB, nodeC);
        Edge edgeBE = new Edge(11, nodeB, nodeE);
        Edge edgeBA = new Edge(7, nodeB, nodeA);
        nodeB.edges.add(edgeBC);
        nodeB.edges.add(edgeBE);
        nodeB.edges.add(edgeBA);

        Edge edgeCA = new Edge(3, nodeC, nodeA);
        Edge edgeCB = new Edge(2, nodeC, nodeB);
        Edge edgeCD = new Edge(7, nodeC, nodeD);
        Edge edgeCE = new Edge(16, nodeC, nodeE);
        nodeC.edges.add(edgeCA);
        nodeC.edges.add(edgeCB);
        nodeC.edges.add(edgeCD);
        nodeC.edges.add(edgeCE);

        Edge edgeDA = new Edge(5, nodeD, nodeA);
        Edge edgeDC = new Edge(7, nodeD, nodeC);
        Edge edgeDE = new Edge(13, nodeD, nodeE);
        nodeD.edges.add(edgeDA);
        nodeD.edges.add(edgeDC);
        nodeD.edges.add(edgeDE);

        Edge edgeEB = new Edge(11, nodeE, nodeB);
        Edge edgeEC = new Edge(16, nodeE, nodeC);
        Edge edgeED = new Edge(13, nodeE, nodeD);
        nodeE.edges.add(edgeEB);
        nodeE.edges.add(edgeEC);
        nodeE.edges.add(edgeED);

        Graph graph = new Graph();
        graph.strNodes.put("A", nodeA);
        graph.strNodes.put("B", nodeB);
        graph.strNodes.put("C", nodeC);
        graph.strNodes.put("D", nodeD);
        graph.strNodes.put("E", nodeE);

        graph.edges.add(edgeAB);
        graph.edges.add(edgeAC);
        graph.edges.add(edgeAD);

        graph.edges.add(edgeBC);
        graph.edges.add(edgeBE);
        graph.edges.add(edgeBA);

        graph.edges.add(edgeCA);
        graph.edges.add(edgeCB);
        graph.edges.add(edgeCD);
        graph.edges.add(edgeCE);

        graph.edges.add(edgeDA);
        graph.edges.add(edgeDC);
        graph.edges.add(edgeDE);

        graph.edges.add(edgeEB);
        graph.edges.add(edgeEC);
        graph.edges.add(edgeED);

        return graph;
    }

    public static int[] generateArray() {
        return new int[]{77, 98, 45, 83, 2, 77, 38};
    }

    public static void swap(int[] array, int m, int n) {

        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    public static int[] getRandomArray(int size) {

        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }
}
