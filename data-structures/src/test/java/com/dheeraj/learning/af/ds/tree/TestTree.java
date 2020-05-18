package com.dheeraj.learning.af.ds.tree;

import org.junit.Before;
import org.junit.Test;

public class TestTree {
    /**
     * Balanced tree.
     */
    Node balancedTreeNode;

    /**
     * Unbalanced tree
     */
    Node unbalancedTreeNode;

    @Before
    public void before() {
        balancedTreeNode = TreeUtil.createBST("1,2,3,4,5,6,7");
        unbalancedTreeNode = new Node(1);
        unbalancedTreeNode.left = new Node(2);
        unbalancedTreeNode.right = new Node(3);
        unbalancedTreeNode.right.left = new Node(5);
        unbalancedTreeNode.right.right = new Node(6);
        unbalancedTreeNode.right.left.left = new Node(7);
        unbalancedTreeNode.right.left.right = new Node(8);
        unbalancedTreeNode.right.left.right.left = new Node(9);
        unbalancedTreeNode.right.left.right.right = new Node(10);
    }

    @Test
    public void testVerticalOrder() {
        Node root = unbalancedTreeNode;
        System.out.println("\nVertical order of a tree (Not accurate)");
        Tree.verticalOrder(root);
        System.out.println("\nAccurate Vertical order of a tree using level order traversal");
        Tree.verticalOrderUsingLevelOrderTraversal(root);
        System.out.println("\nTop view of a tree.");
        Tree.topViewOfATree(root);
        System.out.println("\nBottom view of a tree.");
        Tree.bottomViewOfATree(root);

        //TODO : Recursive level order traversal.
        //http://www.crazyforcode.com/recursive-level-order-traversal-tree/
        System.out.println();
    }



    @Test
    public void testPreInPostOrders() {
        Node root = unbalancedTreeNode;
        System.out.println("\npreOrderRecursive");
        Tree.preOrderRecursive(root);
        System.out.println("\npreOrderIterative");
        Tree.preOrderIterative(root);
        System.out.println("\ninOrderRecursive");
        Tree.inOrderRecursive(root);
        System.out.println("\ninOrderIterative");
        Tree.inOrderIterative(root);
        System.out.println("\npostOrderRecursive");
        Tree.postOrderRecursive(root);
        System.out.println("\npostOrderIterative");
        Tree.postOrderIterative(root);
        System.out.println("\npostOrderIterativeSimpler");
        Tree.postOrderIterativeSimpler(root);
        System.out.println("\npostOrderIterativeTwoStacks");
        Tree.postOrderIterativeTwoStacks(root);
        System.out.println("\npostOrderIterativeWithSingleStackOwn");
        Tree.postOrderIterativeWithSingleStackOwn(root);
    }

    @Test
    public void testLevelOrderTraversal() {
        Node root = unbalancedTreeNode;
        System.out.println("\nLevel order traversal Iterative");
        Tree.levelOrderIterative(root);
        System.out.println("\nLevel order traversal (Print level by level)");
        Tree.levelOrderPrintLevelByLevel(root);
        System.out.println("\nLevel order traversal without queue");
        Tree.levelOrderWithMap(root);
        System.out.println("\nLevel order traversal Recursive");
        Tree.levelOrderRecursive(root);
    }
}
