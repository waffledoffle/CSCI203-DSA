package BinarySearchTree.AVLTrees;
import java.lang.Math;

public class AVLTree {
    static Node start;
    public static void main(String[] args) {
        start = new Node();
        insert(3, start);
        insert(2, start);
        insert(1, start);
        insert(4, start);
        insert(5, start);
        insert(6, start);
        insert(7, start);
        insert(16, start);
        insert(15, start);
        insert(14, start);
        visit(start);

    }

    public static Node rotateRight(Node node) {
        Node left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
        left.setHeight(Math.max(left.getLeft().getHeight(), node.getHeight()) + 1);
        return left;
    }

    public static Node rotateLeft(Node node) {
        Node left = node.getRight();
        node.setRight(left.getLeft());
        left.setLeft(node);
        node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
        left.setHeight(Math.max(left.getRight().getHeight(), node.getHeight()) + 1);
        return left;
    }

    public static void doubleRight(Node node) {
        node = rotateLeft(node.getLeft());
        rotateRight(node);
    }

    public static void doubleLeft(Node node) {
        node = rotateRight(node.getRight());
        rotateLeft(node);
    }

    public static void insert(int value, Node node) {
        if (node == null) {
            node = new Node();
            node.setValue(value);
        }
        else if (value < node.getValue()) {
            insert(value, node.getLeft());
            if (node.getLeft().getHeight() - node.getRight().getHeight() == 2) {
                if (value < node.getLeft().getValue()) {
                    rotateRight(node);
                }
                else {
                    doubleRight(node);
                }
            }
        }
        else if (value > node.getValue()) {
            insert(value, node.getRight());
            if (node.getRight().getHeight() - node.getLeft().getHeight() == 2) {
                if (value < node.getRight().getValue()) {
                    doubleLeft(node);
                }
                else {
                    rotateLeft(node);
                }
            }
        }
        node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
    }

    public static void visit(Node node) {
        if (node.getLeft() != null) {
            visit(node.getLeft());
        }
        System.out.println(node.getValue());
        if (node.getRight() != null) {
            visit(node.getRight());
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;
    int height;

    public Node() {
        value = 0;
        height = 0;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setHeight(int h) {
        height = h;
    }
}
