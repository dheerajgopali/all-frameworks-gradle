package com.dheeraj.learning.af.ds.tree;

public class TreeUtil {
    /**
     * Creates a perfect balanced tree
     *
     * @param a string containing comma separated integers in sorted order
     * @return
     */
    public static Node createBST(String a){
        Node root = null;
        String[] str = a.split(",");
        root = binaryInsert(root,str,0,str.length-1);

        return root;
    }

    /**
     * Creates a perfect balanced tree
     *
     * @param a string containing comma separated integers in binary sorted order
     * @return
     */
    public static Node createBSTWithBinarySortedString(String a){
        Node root = null;
        String[] str = a.split(",");
        for(int i=0;i<str.length;i++){
            root = insert(root,Integer.parseInt(str[i]));
        }

        return root;
    }

    /**
     * Inserts a list of string values into tree in perfectly balanced manner.
     *
     * @param root
     * @param str
     * @param low
     * @param high
     * @return
     */
    private static Node binaryInsert(Node root, String[] str, int low, int high){
        if(low>high)
            return root;
        int mid=(low+high)/2;
        root = insert(root, Integer.parseInt(str[mid]));
        root.left = binaryInsert(root.left, str, low, mid - 1);
        root.right = binaryInsert(root.right, str, mid + 1, high);
        return root;
    }

    /**
     * Inserts a new node with given value in the tree in the appropirate location.
     * Wont guarantee balanced tree.
     * @param root
     * @param x
     * @return
     */
    private static Node insert(Node root, int x){
        if(root == null){
            root = new Node(x);
            //The below extra condition is for interviewbit input standard for tree
            if(x==-1)
                return null;
            return root;
        }
        if(x <= root.val){
            root.left = insert(root.left, x);
        }else{
            root.right = insert(root.right, x);
        }
        return root;
    }
}
