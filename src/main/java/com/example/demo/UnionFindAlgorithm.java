package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UnionFind是使用Integer类型的基础的并查集逻辑
 */
class UnionFind {
    int[] nodeTree;

    // 初始化所有节点
    public UnionFind(int size) {
        this.nodeTree = new int[size];

        for(int i =0; i < size; i++) {
            /**
             * 初始化节点时, 节点的下标和节点的名称一致, 比如 int[0] = 0
             * 标识每个节点各自是一个集合
             */
            this.nodeTree[i] = i;
        }
    }

    /**
     * 寻找根节点, 并在寻找的过程中执行路径压缩
     * 将所有节点的父节点都直接连接到树的根节点上.
     * @param x
     * @return
     */
    public int findRoot(int x) {
        System.out.println("调用 findRoot(" + x + ")");
        if(nodeTree[x] != x) {
            System.out.println("findRoot : nodeTree[" + x + "] 指向的节点是 " + nodeTree[x]);
            int temp = findRoot(nodeTree[x]);
            nodeTree[x] = temp;
            System.out.println("findRoot : 将 nodeTree[" + x + "] 的值设置成了 " + temp);
        }
        // 当nodeTree[x] 等于 x 时, 表示找到了根节点, 就返回根节点的名称
        System.out.println("findRoot : 最终找到了节点 " + x + " 的根节点是 " + nodeTree[x]);
        return nodeTree[x];
    }

    public void union(int x, int y) {
        System.out.println("Union : 要合并 " + x + " 和 " + y);
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        System.out.println("Union : 节点 " + x + " 的根节点是 " + rootX + " | 节点 " + y + "的根节点是 " + rootY);
        // 如果两个节点不在同一棵树上, 则合并两棵树, 将一棵树的根节点的父节点设置成另一棵树的根节点
        if(rootX != rootY) {
            System.out.println("Union : 将根节点 " + rootX + " 的父节点设置成了 " + rootY);
            nodeTree[rootX] = rootY;
        } else {
            System.out.println("Union : 不需要合并");
        }
    }

    /**
     * 判断两个节点是否在同一棵树上.
     * 如果在同一棵树上, 则属于同一个集合
     * @param x
     * @param y
     * @return
     */
    public boolean isSameSet(int x, int y) {
        System.out.println("isSameSet : ");
        int rootX = findRoot(x);
        System.out.println("isSameSet : 找到了第一个节点 " + x + " 的根节点 " + rootX);
        int rootY = findRoot(y);
        System.out.println("isSameSet : 找到了第二个节点 " + y + " 的根节点 " + rootY);
        return rootX == rootY;
    }
}

class UnionFindNode {

    Map<Node, Node> nodeParentMap = new HashMap<Node, Node>();

    public UnionFindNode(List<Node> nodeList) {
        for(Node node : nodeList) {
            this.nodeParentMap.put(node, node);
        }
    }

    public Node findRoot(Node node) {
//        System.out.println("调用 findRoot(" + node + ")");

        if(!this.nodeParentMap.containsKey(node)) {
            throw new IllegalArgumentException("Node不存在");
        }

        Node rootNode = this.nodeParentMap.get(node);
//        System.out.println("findRoot: 节点" + node + "的父节点是" + rootNode);

        if(rootNode != node) {
//            System.out.println("findRoot : 节点" + node + "与父节点" + rootNode + "不同");
            rootNode = findRoot(rootNode);
        }
//        System.out.println("findRoot: 最终节点" + node + "的根节点是" + rootNode);
        return rootNode;
    }

    public void union(Node first, Node second) {
//        System.out.println("Union: 合并" + first + " 和 " + second);
        Node firstRootNode = findRoot(first);
        Node secondRootNode = findRoot(second);
//        System.out.println("Union: 节点" + first + "的根节点是" + firstRootNode + " | 节点" + second + "的根节点是" + secondRootNode);
        if(firstRootNode != secondRootNode) {
//            System.out.println("Union: 将根节点" + firstRootNode + "的父节点设置成" + secondRootNode);
            this.nodeParentMap.put(firstRootNode, secondRootNode);
        }
    }

    public boolean isInSameSet(Node first, Node second) {
        Node firstRoot = findRoot(first);
        Node secondRoot = findRoot(second);
//        System.out.println("isInSameSet: 节点" + first + "的根节点是" + firstRoot + " | 节点" + second + "的根节点是" + secondRoot + " | 它俩相等: " + (firstRoot == secondRoot));
        return firstRoot == secondRoot;
    }
}

public class UnionFindAlgorithm {
    public static void main(String[] args) {
//        testIntegerUnionFind();
        testNodeUnionFind();
    }

    public static void testIntegerUnionFind() {
        UnionFind uf = new UnionFind(10);
        System.out.println("初始化的节点数组: " + Arrays.toString(uf.nodeTree));
        System.out.println("节点 1 和 2 是否在同一个集合(树)中 : " + uf.isSameSet(1, 2)); // true
        System.out.println("开始合并节点 1 和 2");
        uf.union(1, 0);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(9, 8);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(6, 7);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(2, 3);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(4, 5);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(1, 3);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(4, 6);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
        uf.union(5, 9);
        System.out.println("Array: " + Arrays.toString(uf.nodeTree));

//        uf.union(1, 4);
//        System.out.println("Array: " + Arrays.toString(uf.nodeTree));
//        uf.union(1, 6);
//        System.out.println("Array: " + Arrays.toString(uf.nodeTree));

        /**
         * 此处, 分成了两棵树
         * 一颗是:
         *
         *      3 -- 0
         *      | -- 1
         *      | -- 2
         *
         *      8 -- 9
         *      | -- 7
         *           | -- 6
         *           | -- 5 -- 4
         */
        System.out.println("is same set " + uf.isSameSet(0, 9));
        System.out.println("is same set " + uf.isSameSet(2, 4));
        System.out.println("is same set " + uf.isSameSet(3, 8));
        System.out.println("is same set " + uf.isSameSet(1, 6));
    }

    public static void testNodeUnionFind() {
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
