package com.dheeraj.learning.af.ds.tree;

import org.junit.Test;

public class TestGraphVizUtil {
    @Test
    public void testTree() {
        Node root = TreeUtil.createBST("1,2,3,4,5,6,7");
        System.out.println(GraphVizUtil.generateGraphVizGraph(root));
    }
}
