package com.dheeraj.learning.af.ds.tree;

import org.junit.Test;

public class TestTree {
    @Test
    public void testTree() {
        //Node root = TreeUtil.createBST("1,2,3,4,5,6,7");
        /*System.out.println("\npreOrderRecursive");
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
        System.out.println("\nLevel order traversal");
        Tree.levelOrderIterative(root);
        System.out.println("\nLevel order traversal (Print level by level)");
        Tree.levelOrderPrintLevelByLevel(root);
        System.out.println("\nLevel order traversal without queue");
        Tree.levelOrderWithMap(root);*/
        System.out.println("\nVertical order of a tree");
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(8);
        root.right.left.right.left = new Node(9);
        root.right.left.right.right = new Node(10);

        Tree.verticalOrder(root);
        System.out.println("\nAccurate Vertical order of a tree");
        Tree.verticalOrderUsingLevelOrderTraversal(root);
        System.out.println("\nTop view of a tree.");
        Tree.topViewOfATree(root);
        System.out.println("\nBottom view of a tree.");
        Tree.bottomViewOfATree(root);

        //TODO : Yet to implements other traversals.*/
    }
}
