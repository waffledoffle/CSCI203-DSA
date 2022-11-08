package Dijkstra;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dijkstra {
  static Vertex[] vertices;
  static int size = 1;
  static Double pSize = 0.0;
  
  public static void main(String[] args) {
    String fileName = "graph.txt";
    try {
        File input = new File(fileName);
        Scanner reader = new Scanner(input);
        int count = 0;
        int nVertices = 0;
        int nEdges = 0;
        String[] line = reader.nextLine().split("   ");
        nVertices = Integer.parseInt(line[0]);
        nEdges = Integer.parseInt(line[1]);
        vertices = new Vertex[nVertices];

        for (int i = 0; i < nVertices; i++) {
            line = reader.nextLine().split("    ");
            vertices[i] = new Vertex(Integer.parseInt(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]));
        }

        for (int i = 0; i < nEdges; i++) {
            line = reader.nextLine().split("    ");
            Edge eObject = new Edge(Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1, Double.parseDouble(line[2]));
            int temp = eObject.getV1();
            vertices[temp].addEdge(eObject);
        }

        line = reader.nextLine().split("    ");
        int start = Integer.parseInt(line[0]) - 1;
        int goal = Integer.parseInt(line[1]) - 1;

        shortPath(start, goal);

    }
    catch (FileNotFoundException e) {
        System.out.println(fileName + " not found");
        System.exit(0);
    }
  }

  public static void shortPath(int start, int goal) {
    
  }
}

class Vertex {
    int label;
    double x = 0.0;
    double y = 0.0;
    double h;
    double g;
    Edge[] edges;
    Vertex parent = null;
    boolean visited = false;

    public Vertex(int label, double x, double y) {
        this.label = label - 1;
        this.x = x;
        this.y = y;
        edges = new Edge[0];
    }

    public int getLabel() {
        return label;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getH() {
        return h;
    }

    public double getG() {
        return g;
    }

    public double getF() {
        return (g + h);
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Vertex getParent() {
        return parent;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void addEdge(Edge edge) {
        Edge[] temp = new Edge[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            temp[i] = edges[i];
        }
        temp[edges.length] = edge;
        edges = temp;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public void SetVisited(boolean visit) {
        visited = visit;
    }
}

class Edge {
    int vector1;
    int vector2;
    double weight;

    public Edge(int vector1, int vector2, double weight) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.weight = weight;
    }

    public int getV1() {
        return vector1;
    }

    public int getV2() {
        return vector2;
    }

    public double getWeight() {
        return weight;
    }
}
