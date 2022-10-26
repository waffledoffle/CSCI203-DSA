public class Heap {
    //static final int HEAP_SIZE = 20;
    static int[] heap = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, -1, -2, -3, -4};
    static int size = 20;

    public static void main(String[] args) {
        makeHeap();
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
        if ((child + 1 < size) && (heap[child] < heap[child + 1])) {
            child = child + 1;
        }
        if (heap[current] < heap[child]) {
            swap(current, child);
            siftDown(child);
        }
    }

    public static void add(int new) {
        
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