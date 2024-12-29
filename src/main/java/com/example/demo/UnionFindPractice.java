package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFindPractice {

    int[] nodeTree;

    public UnionFindPractice(int size) {
        nodeTree = new int[size];
        for (int i = 0; i < size; i++) {
            nodeTree[i] = i;
        }
    }

    public int findRoot(int target) {

        if(nodeTree[target] != target) {
            nodeTree[target] = findRoot(nodeTree[target]);
        }

        return nodeTree[target];
    }

    public void union(int x, int y) {

        int rootX = findRoot(x);
        int rootY = findRoot(y);

        if(rootX != rootY) {
            nodeTree[rootX] = rootY;
        }
    }

    public boolean isInSameSet(int x, int y) {
        return findRoot(x) == findRoot(y);
    }

    public static void main(String[] args) {

        UnionFindPractice ufp = new UnionFindPractice(10);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));

        ufp.union(1, 0);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(9, 8);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(6, 7);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(2, 3);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(4, 5);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(1, 3);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(4, 6);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
        ufp.union(5, 9);
        System.out.println("Array: " + Arrays.toString(ufp.nodeTree));
    }
}

class UnionFindNodePractice {

    Map<Node, Node> parentMap = new HashMap<>();

    public UnionFindNodePractice(List<Node> nodeList) {

        for(Node node : nodeList) {
            parentMap.put(node, node);
        }
    }

    public Node findRoot(Node node) {
        if(!parentMap.containsKey(node)) {
            throw new IllegalArgumentException("Invalid node");
        }

        Node rootNode = parentMap.get(node);
        if(rootNode != node) {
            rootNode = findRoot(rootNode);
        }

        return rootNode;
    }

    public void union(Node nodeX, Node nodeY) {
        Node rootNodeX = findRoot(nodeX);
        Node rootNodeY = findRoot(nodeY);

        if(rootNodeX != rootNodeY) {
            parentMap.put(rootNodeX, rootNodeY);
        }
    }

    public boolean isInSameSet(Node nodeX, Node nodeY) {

        return findRoot(nodeX) == findRoot(nodeY);
    }

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        UnionFindNode ufn = new UnionFindNode(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        System.out.println("Is A and B in same set : " + ufn.isInSameSet(nodeA, nodeB));
        ufn.union(nodeA, nodeB);
        System.out.println("Is A and B in same set : " + ufn.isInSameSet(nodeA, nodeB));

        System.out.println("Is D and E in same set : " + ufn.isInSameSet(nodeD, nodeE));
        ufn.union(nodeD, nodeE);
        System.out.println("Is D and E in same set : " + ufn.isInSameSet(nodeD, nodeE));

        System.out.println("Is C and A in same set : " + ufn.isInSameSet(nodeC, nodeA));
        ufn.union(nodeC, nodeA);
        System.out.println("Is C and A in same set : " + ufn.isInSameSet(nodeC, nodeA));

        System.out.println("============");
        System.out.println("Is A and E in same set : " + ufn.isInSameSet(nodeA, nodeE));
        System.out.println("Is A and D in same set : " + ufn.isInSameSet(nodeA, nodeD));
        System.out.println("合并A和E");
        ufn.union(nodeA, nodeE);
        System.out.println("Is A and E in same set : " + ufn.isInSameSet(nodeA, nodeE));
        System.out.println("Is A and D in same set : " + ufn.isInSameSet(nodeA, nodeD));
    }
}
