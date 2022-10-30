package BinarySearchTree.AVLTrees;
import java.lang.Math;

public class AVLTree {
    static Node start;
    public static void main(String[] args) {
        start = new Node();
        insertFirst(3);
        insert(2, start, 1);
        insert(1, start, 1);
        insert(4, start, 1);
        insert(5, start, 1);
        insert(6, start, 1);
        insert(7, start, 1);
        insert(16, start, 1);
        insert(15, start, 1);
        insert(14, start, 1);
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

    public static Node doubleRight(Node node) {
        node.setLeft(rotateLeft(node.getLeft()));
        node = rotateRight(node);
        return node;
    }

    public static Node doubleLeft(Node node) {
        node.setRight(rotateRight(node.getRight()));
        node = rotateLeft(node);
        return node;
    }

    public static void insertFirst(int value) {
        start = new Node();
        start.setValue(value);
        start.setHeight(1);
    }

    public static Node insert(int value, Node node, int height) {
        if (node == null) {
            node = new Node();
            node.setValue(value);
            node.setHeight(height);
            return node;
        }
        else if (value < node.getValue()) {
            node.setLeft(insert(value, node.getLeft(), height + 1));
            if (node.getLeft() != null && node.getRight() != null && node.getLeft().getHeight() - node.getRight().getHeight() == 2) {
                if (value < node.getLeft().getValue()) {
                    node = rotateRight(node);
                    return node;
                }
                else {
                    node = doubleRight(node);
                    return node;
                }
            }
        }
        else if (value > node.getValue()) {
            node.setRight(insert(value, node.getRight(), height + 1));
            if (node.getRight().getHeight() - node.getLeft().getHeight() == 2) {
                if (value < node.getRight().getValue()) {
                    node = doubleLeft(node);
                }
                else {
                    node = rotateLeft(node);
                }
            }
        }
        node.setHeight(height);
        return node;
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
        height = 1;
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
