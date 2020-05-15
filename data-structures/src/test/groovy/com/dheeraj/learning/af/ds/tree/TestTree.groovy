package com.dheeraj.learning.af.ds.tree;

import spock.lang.Specification;

class TestTree extends Specification {
    def "Test Tree"() {
        Node root = TreeUtil.createBST("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        Tree.preOrderRecursive(root)
        Tree.preOrderIterative(root)
        Tree.inOrderRecursive(root)
        //TODO : Yet to implements other traversals.
    }
}
