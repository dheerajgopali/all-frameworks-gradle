package com.dheeraj.learning.af.ds.tree;

import org.junit.Test;

public class TestGraphVizUtil {
    @Test
    public void testBalancedTree() {
        Node root = TreeUtil.createBST("1,2,3,4,5,6,7");
        System.out.println(GraphVizUtil.generateGraphVizGraph(root));
    }

    @Test
    public void testUnbalancedTree() {
        Node unbalancedTreeNode = new Node(1);
        unbalancedTreeNode.left = new Node(2);
        unbalancedTreeNode.right = new Node(3);
        unbalancedTreeNode.right.left = new Node(5);
        unbalancedTreeNode.right.right = new Node(6);
        unbalancedTreeNode.right.left.left = new Node(7);
        unbalancedTreeNode.right.left.right = new Node(8);
        unbalancedTreeNode.right.left.right.left = new Node(9);
        unbalancedTreeNode.right.left.right.right = new Node(10);
        System.out.println(GraphVizUtil.generateGraphVizGraph(unbalancedTreeNode));
    }
}
