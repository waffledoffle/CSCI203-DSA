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
        Vertex current;
        int count = 0;
        Vertex[] open = new Vertex[vertices.length];
        Vertex[] closed = new Vertex[vertices.length];
        add(vertices[start], open);
        open[0] = vertices[start];
            
        //While count is less than the amount of vertices in the graph
        while (count < vertices.length) {
            current = open[0];
            remove(open);
            closed[count] = current;
            count = count + 1;

            if (current == vertices[goal]) {
				path(current, start);
				break;
			}

            OUTER_LOOP: for (Edge edge : current.getEdges()) {
                Vertex neighbour = vertices[edge.getV2()];
				for (Vertex vertex : open) {
                    if (neighbour == vertex) {
                        continue OUTER_LOOP;
                    }
                }
                for (Vertex vertex : closed) {
                    if (neighbour == vertex) {
                        continue OUTER_LOOP;
                    }
                }
				neighbour.setG(current.getG() + 1.0);
				neighbour.setParent(current);
				add(neighbour, open);
			}
        }
    }

    public static Vertex[] add(Vertex vertex, Vertex[] heap) {
            
        //adds element to last position in the heap
        heap[size] = vertex;
        size = size + 1;
        
        heap = siftUp(size - 1, heap);
        
        return heap;
    }

    //Deals with an element being removed from the heap
    public static Vertex[] remove(Vertex[] heap) {  
        
        //makes the top element equal to the last element of the heap
        int end = size - 1;
        if (end == 0) {
            heap[0] = null;
            size = size - 1;
        }
        else {
            heap[0] = heap[end];
            heap[end] = null;
            size = size - 1;
        
            heap = siftDown(0, heap);
        }
        return heap;
    }

    public static Vertex[] siftDown(int current, Vertex[] heap) {
		int child = 2 * current + 1;
		if (child >= size) {
			return heap;
		}
		if ((child + 1 < size) && ((heap[child].getG() > heap[child + 1].getG()))) {
			child = child + 1;
		}
		
		//If the current objects time is greater than its childs or if its priority is less than its childs swap the elements and siftdown
		if (heap[current].getG() > heap[child].getG()) {
			heap = swap(current, child, heap);
			heap = siftDown(child, heap);
		}
		return heap;
	}
	
	//Recursively performs heap operations on the queue array to ensure when an object is added the next element that should be at the top is
	public static Vertex[] siftUp(int current, Vertex[] heap) {
		if (current == 0) {
			return heap;
		} 
		int parent = (current - 1) / 2;
		
		//If the current time is less than the parents time or if the current priority is greater than the parents priority swap the elements and siftup
		if (heap[current].getG() < heap[parent].getG()) {
			heap = swap(current, parent, heap);
			heap = siftUp(parent, heap);
		}
		return heap;
	}

    public static Vertex[] swap(int current, int other, Vertex[] heap) {
		Vertex temp = heap[current];
		heap[current] = heap[other];
		heap[other] = temp;
		return heap;
	}

    public static void path(Vertex c, int start) {
		Vertex[] path = new Vertex[0];
		Vertex current = c;
		Vertex end = vertices[start];
		
		//While the current node is not the end node
		while (current != end) {
			Vertex[] temp = new Vertex[path.length + 1];
			
			//For every item in path
			for (int i = 0; i < path.length; i++) {
				temp[i] = path[i];
			}
			temp[path.length] = current;
			path = temp;
			current = current.getParent();
		}

		System.out.println("Shortest Path");
		System.out.print((vertices[start].getLabel() + 1));
		for (int i = path.length - 1; i >= 0; i--) {
			System.out.print(" -> " + (path[i].getLabel() + 1));
		}
	}
}

class Vertex {
    int label;
    double g;
    Edge[] edges;
    Vertex parent = null;
    boolean visited = false;

    public Vertex(int label, double x, double y) {
        this.label = label - 1;
        edges = new Edge[0];
    }

    public int getLabel() {
        return label;
    }

    public double getG() {
        return g;
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
