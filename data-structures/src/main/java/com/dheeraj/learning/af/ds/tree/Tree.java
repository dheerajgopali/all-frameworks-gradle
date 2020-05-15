package com.dheeraj.learning.af.ds.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Tree {

    //PREORDER
    public static void preOrderRecursive(Node root){
        if(root == null)
            return;
        System.out.print(root.val + " ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }

    public static void preOrderIterative(Node root) {
        if (root == null) return;

        final Stack<Node> stack = new Stack<Node>();
        stack.add(root);

        while (!stack.empty()) {
            Node node = stack.pop();
            System.out.print(node.val + " ");
            // LIFO
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    //INORDER
    public static void inOrderRecursive(Node root){
        if(root == null)
            return;
        inOrderRecursive(root.left);
        System.out.print(root.val + " ");
        inOrderRecursive(root.right);
    }



    //POSTORDER
    public static void postOrderRecursive(Node root){
        if(root == null)
            return;
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * Implemented it without help. Used a visited set to store the visited nodes.
     * Not efficient but works.
     * @param root
     */
    public static void postOrderIterativeOwnImpl(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Set<Node> visited = new HashSet<>();
        visited.add(null);
        while(!stack.isEmpty() || curr != null) {
            if(curr != null) {
                if(!visited.contains(curr)) {
                    stack.push(curr);
                    visited.add(curr);
                    if(curr.right != null) {
                        stack.push(curr.right);
                    }
                    curr = curr.left;
                } else {
                    curr = stack.isEmpty()?null:stack.pop();
                    if(curr == null)
                        continue;
                    if(visited.contains(curr.left) && visited.contains(curr.right)) {
                        visited.add(curr);
                        System.out.print(curr.val + " ");
                    }
                }
            } else {
                curr = stack.pop();

                if(visited.contains(curr.left) && visited.contains(curr.right)) {
                    visited.add(curr);
                    System.out.print(curr.val + " ");
                }
            }
        }
    }

    /**
     * Slight improved version of method postOrder()
     * @param root
     */
    public static void postOrderIterativeOwnImplV2(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Set<Node> visited = new HashSet<>();
        visited.add(null);
        while(!stack.isEmpty() || curr != null) {
            if(curr == null) {
                curr = stack.pop();

                if(visited.contains(curr.left) && visited.contains(curr.right)) {
                    visited.add(curr);
                    System.out.print(curr.val + " ");
                }
            } else {
                if(!visited.contains(curr)) {
                    stack.push(curr);
                    visited.add(curr);
                    if(curr.right != null) {
                        stack.push(curr.right);
                    }
                    curr = curr.left;
                } else {
                    curr = null;
                }
            }
        }
    }


}
