package com.dheeraj.learning.afg.ds.tree;

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

    /**
     * Along with left and right subtrees, each node has a random pointer for this.
     */
    Node treeWithRandomPointers;

    /**
     * Balanced tree:
     *                  4
     *          2               6
     *      1       3       5       7
     * Unbalanced tree:
     *                  1
     *              2       3
     *                  5       6
     *
     *              7       8
     *                  9       10
     */
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

        treeWithRandomPointers = new Node(1);
        treeWithRandomPointers.left = new Node(2);
        treeWithRandomPointers.right = new Node(3);
        treeWithRandomPointers.right.left = new Node(5);
        treeWithRandomPointers.right.right = new Node(6);
        treeWithRandomPointers.right.left.left = new Node(7);
        treeWithRandomPointers.right.left.right = new Node(8);
        treeWithRandomPointers.right.left.right.left = new Node(9);
        treeWithRandomPointers.right.left.right.right = new Node(10);
        treeWithRandomPointers.random=treeWithRandomPointers.right.right;
        treeWithRandomPointers.left.random = treeWithRandomPointers.right.left.right.left;
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
        System.out.println("\nTop view implemented without clues/help.");
        Tree.topViewOwn(root);
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

    @Test
    public void testCloneTree(){
        Node root = unbalancedTreeNode;
        System.out.println("\nInorder Traversal of actual tree:");
        Tree.inOrderRecursive(root);
        Node clone=Tree.clone(root);
        System.out.println("\nInorder Traversal of cloned tree:");
        Tree.inOrderRecursive(clone);
    }

    @Test
    public void testCloneTreeWithRandomNode(){
        Node root = treeWithRandomPointers;
        System.out.println("\nInorder Traversal of actual tree with random nodes:");
        Tree.inOrderRecursiveWithRandomNode(root);
        Node clone=Tree.cloneATreeWithRandomPointer(root);
        System.out.println("\nInorder Traversal of cloned tree with random nodes:");
        Tree.inOrderRecursiveWithRandomNode(clone);
    }

    @Test
    public void testSpiralOrderTraversal(){
        Node root = balancedTreeNode;
        System.out.println("\nSpiral Order Traversal");
        Tree.spiralOrder(root);
        //TODO - Spiral order with doublylinked list and map.
        /*
        void spiralOrderWithMapAndDeque(Node root) {
	Map<Integer, ArrayDequeue> levelMap = new HashMap<>();
	spiralOrder(root,levelMap,1);
	//sout each level in map.
}

void spiralOrder(root,levelMap,level) {
	if(root == null)
		return;
	levelMap.putIfAbsent(level,new ArrayDequeue());

	if(level%2==1) {
		levelMap.get(level).addFront(root.left);
		levelMap.get(level).addFront(root.right);
	}

}
        * */

    }
}
