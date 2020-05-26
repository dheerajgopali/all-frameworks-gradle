package com.dheeraj.learning.af.ds.tree;

import java.util.*;

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

    public static void inOrderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while(!stack.isEmpty() || curr!=null) {
            if(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                System.out.print(curr.val+" ");
                curr = curr.right;
            }
        }
    }

    public static void inOrderRecursiveWithRandomNode(Node root){
        if(root == null)
            return;
        inOrderRecursiveWithRandomNode(root.left);
        System.out.print(root.val + " ");
        if(root.random!=null)
            System.out.print("->"+root.random.val+ " ");
        inOrderRecursiveWithRandomNode(root.right);
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
     * Alogorithm : Using single stack.
     * 1.1 Create an empty stack
     * 2.1 Do following while root is not NULL
     *     a) Push root's right child and then root to stack.
     *     b) Set root as root's left child.
     * 2.2 Pop an item from stack and set it as root.
     *     a) If the popped item has a right child and the right child
     *        is at top of stack, then remove the right child from stack,
     *        push the root back and set root as root's right child.
     *     b) Else print root's data and set root as NULL.
     * 2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
     *
     * @param root
     */
    public static void postOrderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr=root;
        while(!stack.isEmpty() || curr != null) {
            while(curr!=null) {
                if(curr.right!=null)
                    stack.push(curr.right);
                stack.push(curr);
                curr=curr.left;
            }

            curr=stack.pop();

            if (curr.right != null && !stack.isEmpty() && curr.right == stack.peek()) {
                Node rightNode = stack.pop();
                stack.push(curr);
                curr = rightNode;
            } else {
                System.out.print(curr.val+" ");
                curr = null;
            }
        }
    }

    /**
     * Algorithm : Simpler version
     * Push directly root node two times while traversing to the left.
     * While popping if you find stack top() is same as root then go for root->right else print root.
     * @param root
     */
    public static void postOrderIterativeSimpler(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr=root;
        while(true) {
            while(curr!=null) {
                stack.push(curr);
                stack.push(curr);
                curr = curr.left;
            }

            if(stack.isEmpty())
                return;
            curr = stack.pop();

            if(!stack.isEmpty() && curr==stack.peek()) {
                curr = curr.right;
            } else {
                System.out.print(curr.val+" ");
                curr = null;
            }
        }
    }

    /**
     * Algorithm: PostOrder traversal with two stacks.
     *
     */
    public static void postOrderIterativeTwoStacks(Node root) {
        if(root==null)
            return;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while(!stack1.isEmpty()) {
            Node temp = stack1.pop();
            stack2.push(temp);
            if(temp.left!=null)
            stack1.push(temp.left);
            if(temp.right!=null)
            stack1.push(temp.right);
        }
        while(!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }

    /**
     * Algorithm: PostOrder traversal with one stack and easy to understand.
     * 1.If curr not null, push it to stack and move left.
     * 2.else if stack peek's right is null, pop it and print it
     * 3.While the curr is same as stack peek's right, pop it and print it.
     * 4.if stack peek's right is not null in step 2, traverse the right subtree. i.e assign curr to stacks peek;
     * https://www.youtube.com/watch?v=xLQKdq0Ffjg
     *
     */
    public static void postOrderIterativeWithSingleStackOwn(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while(!stack.isEmpty() || curr!=null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                Node temp = stack.peek().right;
                if(temp == null) {
                    temp = stack.pop();
                    System.out.print(temp.val+" ");
                    while(!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        System.out.print(temp.val+" ");
                    }
                } else {
                    curr = temp;
                }
            }
        }
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

    /**
     * Level order with queues
     * @param root
     */
    public static void levelOrderIterative(Node root) {
        if(root==null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node curr = queue.remove();
            System.out.print(curr.val+" ");
            if(curr.left!=null)
                queue.add(curr.left);
            if(curr.right!=null)
                queue.add(curr.right);
        }
    }

    public static void levelOrderRecursive(Node root) {
        //Find height of the tree. h
        int h = height(root);
        for(int i=0;i<=h;i++)
            levelOrderRecursiveSub(root, i, 0);
    }

    /**
     * This can also be done only with level param and not sending h.
     * Have to reduce it's value int the method.
     * Can refer this for improvement : http://www.crazyforcode.com/recursive-level-order-traversal-tree/
     *  void printLevelOrder(struct node* root)
     * {
     *     int h = height(root);
     *     int i;
     *     for(i=1; i<=h; i++)
     *         printLevel(root, i);
     * }
     * void printLevel(struct node* root, int level)
     * {
     *     if(root == NULL)
     *         return;
     *     if(level == 0)
     *         printf("%d ", root->data);
     *     else if (level > 1)
     *     {
     *         printLevel(root->left, level-1);
     *         printLevel(root->right, level-1);
     *     }
     * }
     * @param root
     * @param h
     * @param level
     */
    private static void levelOrderRecursiveSub(Node root, int h, int level) {
        if(root!=null) {
            if(h==level)
                System.out.print(root.val+" ");
            else if (h > level){
                levelOrderRecursiveSub(root.left,h,level+1);
                levelOrderRecursiveSub(root.right,h,level+1);
            }
        }
    }

    /**
     * Level order with queues to print level by level
     * @param root
     */
    public static void levelOrderPrintLevelByLevel(Node root) {
        if(root==null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(null);
        int count=1;
        Node curr;
        System.out.print("Level "+count+": ");
        while(!queue.isEmpty()){
            curr = queue.remove();
            if(curr!=null) {
                System.out.print(curr.val+" ");
                if(curr.left!=null)
                    queue.add(curr.left);
                if(curr.right!=null)
                    queue.add(curr.right);
            }else{
                if(queue.isEmpty())
                    break;
                queue.add(null);
                System.out.print("\nLevel "+(++count)+": ");
            }
        }
    }

    public static void levelOrderWithMap(Node root) {
        Map<Integer, List<Node>> map = new HashMap<>();
        Node curr = root;
        int level=1;
        List<Node> list = new ArrayList<Node>();
        list.add(curr);
        map.put(level,list);
        while(true) {
            List<Node> nodesInGivenLevel = map.get(level);
            System.out.println("Level "+level+" : "+Arrays.toString(nodesInGivenLevel.toArray()));
            List<Node> nodesInLevelPlusOne = new ArrayList<>();
            for (Node temp : nodesInGivenLevel) {
                if(temp.left!=null)
                    nodesInLevelPlusOne.add(temp.left);
                if(temp.right!=null)
                    nodesInLevelPlusOne.add(temp.right);
            }
            if(nodesInLevelPlusOne.isEmpty())
                break;
            map.put(++level, nodesInLevelPlusOne);
        }
    }

    /**
     * Not most accurate method. Have some flaws.
     * This takes the order of traversal and bottom nodes in the vertical may get printed before top node.
     * Instead better to try with level order traversal and then construct map.
     *
     */
    public static void verticalOrder(Node root) {
        if(root==null)
            return;
        TreeMap<Integer,ArrayList<Node>> map = new TreeMap<>();
        constructVerticalOrderMap(root, 0, map);
        for (Integer verticalLevel : map.keySet()) {
            ArrayList<Node> currVerticalList = map.get(verticalLevel);
            System.out.printf("\nVertical %s : ",verticalLevel);
            for (Node curr :
                    currVerticalList) {
                System.out.print(curr.val+" ");
            }
        }
    }

    /**
     * Algorithm: This is the accurate algo as it traverses level by level and vertically sorts them in correct order.
     */
    public static void verticalOrderUsingLevelOrderTraversal(Node root) {
        if(root==null)
            return;
        TreeMap<Integer,ArrayList<Node>> verticalMap= new TreeMap<>();
        levelOrderForVerticalOrder(root, verticalMap);
        for (Integer verticalLevel : verticalMap.keySet()) {
            ArrayList<Node> currVerticalList = verticalMap.get(verticalLevel);
            System.out.printf("\nVertical %s : ",verticalLevel);
            for (Node curr : currVerticalList) {
                System.out.print(curr.val+" ");
            }
        }
    }

    /**
     * Level order with queues to print level by level
     * @param root
     */
    private static void levelOrderForVerticalOrder(Node root,
                                                   TreeMap<Integer,ArrayList<Node>> verticalMap) {
        Map<Node,Integer> nodeVerticalLevel = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node curr;
        nodeVerticalLevel.put(root, 0);

        while(!queue.isEmpty()){
            curr = queue.remove();
            int verticalLevel = nodeVerticalLevel.get(curr);
            verticalMap.computeIfAbsent(verticalLevel, key->new ArrayList<>());
            verticalMap.get(verticalLevel).add(curr);
            if(curr.left!=null) {
                queue.add(curr.left);
                nodeVerticalLevel.put(curr.left, verticalLevel-1);
            }
            if(curr.right!=null) {
                queue.add(curr.right);
                nodeVerticalLevel.put(curr.right, verticalLevel+1);
            }
        }
    }


    private static void constructVerticalOrderMap(Node root, int level, TreeMap<Integer,ArrayList<Node>> map) {
        map.computeIfAbsent(level,key -> new ArrayList<>());
        map.get(level).add(root);
        if(root.left!=null)
            constructVerticalOrderMap(root.left,level-1,map);
        if(root.right!=null)
            constructVerticalOrderMap(root.right,level+1,map);
    }

    public static void topViewOfATree(Node root) {
        if(root==null)
            return;
        TreeMap<Integer,ArrayList<Node>> map = new TreeMap<>();
        levelOrderForVerticalOrder(root,  map);
        for (Integer verticalLevel : map.keySet()) {
            ArrayList<Node> currVerticalList = map.get(verticalLevel);
            System.out.print(currVerticalList.get(0)+" ");
        }
    }

    public static void topViewOwn(Node root) {
        if(root==null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Map<Node, Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(root,0);
        Map<Integer, Node> verticalLevelMap = new TreeMap<>();
        Node curr;

        while(!queue.isEmpty()) {
            curr = queue.remove();
            int level = nodeLevelMap.get(curr);//Contains check needed.
            verticalLevelMap.putIfAbsent(level,curr);
            if(curr.left!=null) {
                nodeLevelMap.put(curr.left,level-1);
                queue.add(curr.left);
            }
            if(curr.right!=null) {
                nodeLevelMap.put(curr.right,level+1);
                queue.add(curr.right);
            }
        }

        System.out.println(verticalLevelMap.values());
    }

    public static void bottomViewOfATree(Node root) {
        if(root==null)
            return;
        TreeMap<Integer,ArrayList<Node>> map = new TreeMap<>();
        levelOrderForVerticalOrder(root, map);
        for (Integer verticalLevel : map.keySet()) {
            ArrayList<Node> currVerticalList = map.get(verticalLevel);
            System.out.print(currVerticalList.get(currVerticalList.size()-1)+" ");
        }
    }

    public static int height(Node root) {
        if (root == null)
            return -1;
        else {
            int leftTreeHeight = height(root.left);
            int rightTreeHeight = height(root.right);

            if(leftTreeHeight>rightTreeHeight)
                return leftTreeHeight+1;
            else
                return rightTreeHeight+1;
        }

    }

    /**
     * Cloning regular tree with left and right sub trees.
     * @param root
     */
    public static Node clone(Node root) {
        if(root==null)
            return null;
        Node clone = new Node(root.val);
        if(root.left!=null)
            clone.left = clone(root.left);
        if(root.right!=null)
            clone.right = clone(root.right);
        return clone;
    }

    /**
     * To clone a tree with each node having a random pointer to another node in the tree.
     *
     * @param root
     * @return
     */
    public static Node cloneATreeWithRandomPointer(Node root) {
        Map<Node,Node> originalToCloneMap = new HashMap<>();
        return clone(root,originalToCloneMap);
    }

    private static Node clone(Node root, Map<Node,Node> originalToCloneMap){
        if(root == null)
            return null;
        Node clone = getOriginalToCloneNode(root,originalToCloneMap);

        if(root.left!=null)
            clone.left = clone(root.left, originalToCloneMap);
        if(root.right!=null)
            clone.right = clone(root.right, originalToCloneMap);
        if(root.random!=null) {
            clone.random = getOriginalToCloneNode(root.random, originalToCloneMap);
        }
        return clone;
    }

    private static Node getOriginalToCloneNode(Node root, Map<Node,Node> originalToCloneMap){

        if(!originalToCloneMap.containsKey(root)){
            Node clone = new Node(root.val);
            originalToCloneMap.put(root,clone);
            return clone;
        } else {
            return originalToCloneMap.get(root);
        }
    }

    /**
     * Traverse a tree in spiral order. It means the first level in left to right, the second level in right to left
     * and so on.
     *
     * Improvement :
     * 1. Using a Degueue (Double ended queue) decreases the space required.
     * 2. Using a map of Dequeue will simplify the program.
     *
     * @param root
     */
    public static void spiralOrder(Node root) {
        if(root==null)
            return;
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        queue.add(root);
        queue.add(null);
        Node curr = null;
        int level=0;
        while(!queue.isEmpty()) {
            curr = queue.remove();
            if(curr!=null) {
                System.out.print(curr.val+" ");
                if(level%2==1){
                    if(curr.right!=null)
                        stack.add(curr.right);
                    if(curr.left!=null)
                        stack.add(curr.left);
                }else {
                    if(curr.left!=null)
                        stack.add(curr.left);
                    if(curr.right!=null)
                        stack.add(curr.right);
                }
            }else {
                if(stack.isEmpty())
                    break;
                while(!stack.isEmpty()) {
                    queue.add(stack.pop());
                }
                queue.add(null);
                level++;
            }
        }
    }
}
