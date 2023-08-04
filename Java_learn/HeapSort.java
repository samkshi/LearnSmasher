public class HeapSort {

   
    private static void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyMax(arr, n, i);
    }

    
    private static void heapifyMax(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapifyMax(arr, n, largest);
        }
    }

   
    private static void buildMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyMin(arr, n, i);
    }

    
    private static void heapifyMin(int[] arr, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] < arr[smallest])
            smallest = left;

        if (right < n && arr[right] < arr[smallest])
            smallest = right;

        if (smallest != i) {
            swap(arr, i, smallest);
            heapifyMin(arr, n, smallest);
        }
    }

    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
    public static void heapSortAscending(int[] arr) {
        int n = arr.length;

        // Build max heap
        buildMaxHeap(arr);

        // Heap Sort
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapifyMax(arr, i, 0);
        }
    }

    
    public static void heapSortDescending(int[] arr) {
        int n = arr.length;

        // Build min heap
        buildMinHeap(arr);

        // Heap Sort
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapifyMin(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Original Array:");
        printArray(arr);

        heapSortAscending(arr);
        System.out.println("Array sorted in ascending order (Heap Sort):");
        printArray(arr);

        heapSortDescending(arr);
        System.out.println("Array sorted in descending order (Heap Sort):");
        printArray(arr);
    }

    // Helper method to print the array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
