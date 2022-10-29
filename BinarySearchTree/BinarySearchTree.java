package BinarySearchTree;

public class BinarySearchTree {
    static Node start;

    public static void main(String[] args) {
        insertFirst(15);
        insert(33, start);
        insert(9, start);
        insert(13, start);
        insert(5, start);
        insert(21, start);
        insert(11, start);
        visit(start);
    }

    public static void find(int search, Node node) {
        if (search == 0) {
            System.out.println("Not found");
            return;
        }
        if (search == node.getValue()) {
            System.out.println("Item was found");
        }
        else if (search < node.getValue()) {
            find(search, node.getLeft());
        }
        else {
            find(search, node.getRight());
        }
    }

    public static void insertFirst(int value) {
        start = new Node();
        start.setValue(value);
    }

    public static void insert(int value, Node node) {
        Node next;
        boolean left;
        if (value == node.getValue()) {
            return;
        }
        else if (value < node.getValue()) {
            next = node.getLeft();
            left = true;
        }
        else {
            next = node.getRight();
            left = false;
        }

        if (next != null) {
            insert(value, next);
        }
        else {
            next = new Node();
            next.setValue(value);
            if (left) {
                node.setLeft(next);
            }
            else {
                node.setRight(next);
            }
        }
    }

    public static Node remove(int value, Node node) {
        if (node == null) {
            return null;
        }
        if (value < node.getValue()) {
            node.setLeft(remove(value, node.getLeft()));
        }
        else if (value > node.getValue()) {
            node.setRight(remove(value, node.getRight()));
        }
        else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            else if (node.getLeft() == null) {
                return node.getRight();
            }
            else if (node.getRight() == null) {
                return node.getLeft();
            }
            else {
                Node successor = node.getRight();
                while (true) {
                    if (successor.getRight() != null) {
                        successor = successor.getRight(); 
                    }
                    else if (successor.getLeft() != null) {
                        successor = successor.getLeft();
                    }
                    else {
                        break;
                    }
                }
                node.setValue(successor.getValue());
                remove(successor.getValue(), node.getRight());
            }
        }
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

    public Node() {
        value = 0;
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

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
