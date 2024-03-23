package metodos_ordenamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Metodos_Ordenamiento {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Ingrese los numeros separados por espacios:");
            String input = scanner.nextLine();
            String[] elements = input.split("\\s+");
            int[] array = Arrays.stream(elements).mapToInt(Integer::parseInt).toArray();

            System.out.println("|---------Seleccione el metodo de ordenamiento que quiere visualizar:---------|");
            System.out.println("1). Selection sort (seleccion)");
            System.out.println("2). Bubble sort (burbuja)");
            System.out.println("3). Insertion sort (insercion)");
            System.out.println("4). Merge sort (combinacion)");
            System.out.println("5). Quick sort (rapida)");
            System.out.println("6). Heap sort (monton)");
            System.out.println("7). Counting sort (conteo)");
            System.out.println("8). Radix sort (raiz)");
            System.out.println("9). Bucket sort (cubo)");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    selectionSort(array);
                    break;
                case 2:
                    bubbleSort(array);
                    break;
                case 3:
                    insertionSort(array);
                    break;
                case 4:
                    mergeSort(array);
                    break;
                case 5:
                    quickSort(array, 0, array.length - 1);
                    break;
                case 6:
                    heapSort(array);
                    break;
                case 7:
                    countingSort(array);
                    break;
                case 8:
                    radixSort(array);
                    break;
                case 9:
                    bucketSort(array);
                    break;
                default:
                    System.out.println("El ordenamiento solicitado no se encuentra en la lista.....");
                    continuar = false;
                    break;
            }

            if (continuar) {
                System.out.println("Respuesta: " + Arrays.toString(array));
                System.out.println("Desea Verificar un nuevo ordenamiento? (s/n)");
                String respuesta = scanner.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    continuar = false;
                }
            }
        }
        System.out.println("Gracias por utilizar el programa! :)");
    }

    public static void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void insertionSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = array[mid + 1 + j];

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

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i; // Inicializar el nodo raíz como el más grande
        int leftChild = 2 * i + 1; // Índice del hijo izquierdo
        int rightChild = 2 * i + 2;
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
 
            heapify(array, n, largest);
        }
    }

    public static void countingSort(int[] array) {
        int n = array.length;

    int max = Arrays.stream(array).max().getAsInt();

    int[] count = new int[max + 1];
    for (int num : array) {
        count[num]++;
    }

    for (int i = 1; i <= max; i++) {
        count[i] += count[i - 1];
    }

    int[] sortedArray = new int[n];
    for (int i = n - 1; i >= 0; i--) {
        sortedArray[count[array[i]] - 1] = array[i];
        count[array[i]]--;
    }

    System.arraycopy(sortedArray, 0, array, 0, n);
}
    public static void radixSort(int[] array) {
    int max = Arrays.stream(array).max().getAsInt();

    int digits = countDigits(max);

    for (int exp = 1; max / exp > 0; exp *= 10) {
        countingSortByDigit(array, exp);
    }
}

private static void countingSortByDigit(int[] array, int exp) {
    int n = array.length;
    int[] output = new int[n];
    int[] count = new int[10];

    for (int num : array) {
        count[(num / exp) % 10]++;
    }

    for (int i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
        output[count[(array[i] / exp) % 10] - 1] = array[i];
        count[(array[i] / exp) % 10]--;
    }

    System.arraycopy(output, 0, array, 0, n);
}

private static int countDigits(int num) {
    int count = 0;
    while (num != 0) {
        count++;
        num /= 10;
    }
    return count;
}

    public static void bucketSort(int[] array) {
        int n = array.length;
        
        int numBuckets = 5;
        
        ArrayList<Integer>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Encuentra el valor máximo en el array
        int maxValue = Integer.MIN_VALUE;
        for (int num : array) {
            maxValue = Math.max(maxValue, num);
        }
        
        // Distribuye los elementos en los cubos
        for (int num : array) {
            int bucketIndex = num * numBuckets / (maxValue + 1);
            buckets[bucketIndex].add(num);
        }
        
        // Ordena individualmente cada cubo
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }
        
        // Concatena los elementos de los cubos ordenados
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int num : bucket) {
                array[index++] = num;
            }
        }
    }
}    