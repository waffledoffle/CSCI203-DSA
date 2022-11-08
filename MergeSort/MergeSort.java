package MergeSort;

public class MergeSort {

    public static void main(String[] args) {
        int[] sort = new int[] {57, 44, 27, 81, 45, 39, 64, 42, 17, 29};
        mergeSort(sort, 0, sort.length - 1);

        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
       if (left < right) {
            int centre = left + (right - left) / 2;

            System.out.println("Splitting array");
            mergeSort(array, left, centre);
            mergeSort(array, centre + 1, right);

            merge(left, centre, right, array);
       }
    }

    public static void merge(int left, int centre, int right, int[] array) {
        int[] lArray = new int[centre - left + 1];
        int[] rArray = new int[right - centre];

        for (int i = 0; i < lArray.length; i++) {
            lArray[i] = array[left + i];
        }

        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = array[centre + 1 + i];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < lArray.length && j < rArray.length) {
            if (lArray[i] > rArray[j]) {
                array[k] = lArray[i];
                i = i + 1;
            }
            else if (lArray[i] < rArray[j]) {
                array[k] = rArray[j];
                j = j + 1;
            }
            k = k + 1;
        }

        while (i < lArray.length) {
            array[k] = lArray[i];
            i = i + 1;
            k = k + 1;
        }

        while (j < rArray.length) {
            array[k] = rArray[j];
            j = j + 1;
            k = k + 1;
        }
    }
}
