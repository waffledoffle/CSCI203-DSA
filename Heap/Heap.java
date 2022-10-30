package Heap;
public class Heap {
    //static final int HEAP_SIZE = 20;
    static int[] heap = {5, 14, 23, 32, 41, 87, 90, 50, 64, 53, 43};
    static int size = heap.length;

    public static void main(String[] args) {
        add(16);
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }

    public static void makeHeap() {
        for (int i = size - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public static void siftDown(int current) {
        int child = 2 * current + 1;
        if (child >= size) {
            return;
        }
        if ((child + 1 < size) && (heap[child] > heap[child + 1])) {
            System.out.println("Comparing child: " + heap[child] + " With child + 1: " + heap[child + 1]);
            child = child + 1;
        }
        System.out.println("Comparing current: " + heap[current] + " With child: " + heap[child]);
        if (heap[current] > heap[child]) {
            swap(current, child);
            siftDown(child);
        }
    }

    public static void add(int added) {
        int[] temp = new int[heap.length + 1];
        for (int i = 0; i < heap.length; i++) {
            temp[i] = heap[i];
        } 
        temp[heap.length] = added;
        heap = temp;
        size = size + 1;
        siftUp(size - 1);

    }

    public static void remove() {
        int end = size - 1;
        if (end == 0) {
            heap[0] = 0;
            size = size - 1;
        }
        else {
            int[] temp = new int[end];
            heap[0] = heap[end];
            for (int i = 0; i < end; i++) {
                temp[i] = heap[i];
            }
            heap = temp;
            size = size - 1;
            siftDown(0);
        }
    
    }

    public static void siftUp(int current) {
        if (current == 0) {
            return;
        }
        int parent = (current - 1) / 2;

        System.out.println("Comparing current: " + heap[current] + " With parent: " + heap[parent]);
        if (heap[current] < heap[parent]) {
            swap(current, parent);
            siftUp(parent);
        }
    }

    public static void swap(int current, int other) {
        int temp = heap[current];
            heap[current] = heap[other];
            heap[other] = temp;
    }
}