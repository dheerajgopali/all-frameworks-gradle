package com.dheeraj.learning.afg.ds.tree;

public class Node {
    public int val;
    public Node left;
    public Node right;

    /**
     * This is not required always. Only in specific DS questions this is required.
     */
    public Node random;

    Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val+"";

    }
}