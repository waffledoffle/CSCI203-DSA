package BinarySearchTree.AVLTrees;
import java.lang.Math;

public class AVLTree {
    static Node start;
    public static void main(String[] args) {
        start = insert(3, start);
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
        Node right = node.getRight();
        int leftH = 0;
        int rightH = 0;
        node.setRight(right.getLeft());
        right.setLeft(node);
        if (node.getLeft() != null) {
            leftH = node.getLeft().getHeight();
        }
        if (node.getRight() != null) {
            rightH = node.getRight().getHeight();
        }
        node.setHeight(Math.max(leftH, rightH) + 1);
        if (right.getLeft() != null) {
            leftH = right.getLeft().getHeight();
        }
        if (right.getRight() != null) {
            rightH = right.getRight().getHeight();
        }
        right.setHeight(Math.max(leftH, rightH) + 1);
        return right;
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

    public static Node insert(int value, Node node) {
        if (node == null) {
            node = new Node();
            node.setValue(value);
        }
        int left = 0;
        int right = 0;
        if (node.getLeft() != null) {
           left = node.getLeft().getHeight();
        }
        if (node.getRight() != null) {
            right = node.getRight().getHeight();
        }
        if (value < node.getValue()) {
            node.setLeft(insert(value, node.getLeft()));
            if (left - right > 1) {
                if (value < node.getLeft().getValue()) {
                    node = rotateRight(node);
                }
                else {
                    node = doubleRight(node);
                }
            }
        }
        else if (value > node.getValue()) {
            node.setRight(insert(value, node.getRight()));
            if (right - left > 1) {
                if (value < node.getRight().getValue()) {
                    node = doubleLeft(node);
                }
                else {
                    node = rotateLeft(node);
                }
            }
        }
        node.setHeight(Math.max(left, right) + 1);
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
