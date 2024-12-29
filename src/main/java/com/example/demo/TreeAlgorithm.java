package com.example.demo;

import java.util.LinkedList;

public class TreeAlgorithm {

    /**
     * 搜索"二叉树"的方法有
     * 深度优先搜索(DFS)
     * 广度优先搜索(BFS)
     * 
     * 深度优先搜索, 又分成
     * 前序遍历 根 -> 左 -> 右
     * 中序遍历 左 -> 根 -> 右
     * 后序遍历 左 -> 右 -> 根
     * 
     * 广度优先搜索, 是层序遍历, 一层一层遍历
     * 
     * 因为树不存在环, 所以用这两种搜索方法搜索树的时候, 不用记录节点是否被访问过了, 比图要简单
     */

    public static void main(String[] args) {

        TreeNode rootNode = Utils.buildBinaryTree();
        // 答案是 12453
        preorderTraversalDFS(rootNode);
        // 答案是 42513
        inorderTraversalDFS(rootNode);
        // 答案是 45231
        postorderTraversalDFS(rootNode);
        // 答案是 12345
        levelOrderTraversalBFS(rootNode);
    }

    /**
     * 前序遍历 - 递归的形式
     * 
     * @param root
     */
    public static void preorderTraversalDFS(TreeNode root) {

        if (root == null) {
            return;
        }

        // 处理root节点
        System.out.println("访问节点: " + root.identifierStr);
        preorderTraversalDFS(root.left);
        preorderTraversalDFS(root.right);
    }

    /**
     * 中序遍历 - 递归的形式
     * 
     * @param root
     */
    public static void inorderTraversalDFS(TreeNode root) {

        if (root == null)
            return;

        inorderTraversalDFS(root.left);
        // 处理root节点
        System.out.println("访问节点: " + root.identifierStr);
        inorderTraversalDFS(root.right);
    }

    /**
     * 后序遍历 - 递归的形式
     */
    public static void postorderTraversalDFS(TreeNode root) {

        if (root == null)
            return;

        postorderTraversalDFS(root.left);
        postorderTraversalDFS(root.right);

        // 处理root节点
        System.out.println("访问节点: " + root.identifierStr);
    }

    /**
     * 层序遍历二叉树
     * @param root
     */
    public static void levelOrderTraversalBFS(TreeNode root) {

        if (root == null)
            return;

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println("访问节点: " + node.identifierStr);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

        }
    }
}
