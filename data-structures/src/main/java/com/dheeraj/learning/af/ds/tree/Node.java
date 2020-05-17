package com.dheeraj.learning.af.ds.tree;

public class Node {
    public int val;
    public Node left;
    public Node right;

    Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val+"";

    }
}