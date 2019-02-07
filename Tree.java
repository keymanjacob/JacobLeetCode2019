Tree traversal

DFS Depth First Traversal
Inorder Preorder Postorder

void printInorder (Node node) {
    if (node == null) return;
    printInorder(node.left);
    System.out.println(node.key + " ");
    printInorder(node.right);
}

void printPreorder (Node node ){
    if (node == null ) return;
    System.out.println(node.key+ " ");
    printPreorder (node.left);
    printPreorder (node.right);
}

void printPostorder (Node node) {
    if (node == null) return;
    printPostorder (node.left);
    printPostorder (node.right);
    System.out.println(node.key+ " ");
}

BFS Breadth First Traversal
public void printBFSBinaryTree (Node root) {
    Queue<Node> q = LinkedList<Node> ();
    q.add (root);
    while (!q.isEmpty()){
        Node temp_node  = q.poll();
        System.out.println(temp_node.data + " ");
        if (temp_node.left != null) q.add(temp_node.left);
        if (temp_node.right != null) q.add(temp_node.right);
    }

}


