import java.util.Random;

public class Main {
    public static void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partitionQuickSort(array, begin, end);
            quickSort(array, begin, partitionIndex-1);
            quickSort(array, partitionIndex+1, end);
        }
    }

    private static int partitionQuickSort(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                int swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        int swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i+1;
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[middle + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void heapSort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    private static int[] random(int length) {
        Random random = new Random();
        int[] numbers = new int[length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
        return numbers;
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = random(100000);
        long startTimeQuick = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long endTimeQuick = System.currentTimeMillis();
        System.out.println("QuickSort took: " + (endTimeQuick - startTimeQuick) + " milliseconds");

        long startTimeMerge = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1);
        long endTimeMerge = System.currentTimeMillis();
        System.out.println("MergeSort took: " + (endTimeMerge - startTimeMerge) + " milliseconds");

        long startTimeHeap = System.currentTimeMillis();
        heapSort(array);
        long endTimeHeap = System.currentTimeMillis();
        System.out.println("HeapSort took: " + (endTimeHeap - startTimeHeap) + " milliseconds");

        long startTimeInsertion = System.currentTimeMillis();
        insertionSort(array);
        long endTimeInsertion = System.currentTimeMillis();
        System.out.println("InsertionSort took: " + (endTimeInsertion - startTimeInsertion) + " milliseconds");

        long startTimeSelection = System.currentTimeMillis();
        selectionSort(array);
        long endTimeSelection = System.currentTimeMillis();
        System.out.println("SelectionSort took: " + (endTimeSelection - startTimeSelection) + " milliseconds");
    }
}