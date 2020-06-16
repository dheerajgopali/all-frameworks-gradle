package com.dheeraj.learning.afg.ds.tree;

/**
 * Graphviz is a tool to display graphs efficiently with coding.
 * We've used it to display trees.
 */
public class GraphVizUtil {
    static int nullcount = 0;

    /**
     * The output of this method can be used in GraphViztool to display the graph.
     * Open graphviz/release/bin/gvedit.exe
     *
     * @param root
     * @return
     */
    public static String generateGraphVizGraph(Node root) {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph BST {\n");
        sb.append("node [fontname=\"Arial\"];\n");

        if (root==null)
            sb.append("\n");
        else if (root.right==null && root.left==null)
            sb.append("    ").append(root.val).append(";\n");
        else
            generateNode(root, sb);

        sb.append("}\n");

        return sb.toString();
    }

    private static void generateNode(Node node, StringBuffer sb)
    {
        if (node.left!=null)
        {
            sb.append("    ").append(node.val).append(" -> ").append(node.left.val).append(";\n");
            generateNode(node.left, sb);
        }
        else
            generateNullNode(node.val, nullcount++, sb);

        if (node.right!=null)
        {
            sb.append("    ").append(node.val).append(" -> ").append(node.right.val).append(";\n");
            generateNode(node.right, sb);
        }
        else
            generateNullNode(node.val, nullcount++, sb);
    }

    private static void generateNullNode(int val, int nullcount, StringBuffer sb)
    {
        sb.append("    null").append(nullcount).append(" [shape=point];\n");
        sb.append("    ").append(val).append(" -> null").append(nullcount).append(";\n");
    }
}
