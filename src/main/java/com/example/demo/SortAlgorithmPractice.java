package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SortAlgorithmPractice {

    public static void main(String[] args) {

    //    bubbleSort(Utils.generateArray());
    //    selectionSort(Utils.generateArray());
    //    insertionSort(Utils.generateArray());
    //    mergeSort(Utils.generateArray());
    //    quickSort(Utils.generateArray());
       heapSort(Utils.generateArray());
//        dfs();
//        dfs2();
//        bfs();
        // bfs2();
    }

    public static void bubbleSort(int[] array) {
        System.out.println("bubbleSort: " + Arrays.toString(array));
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j + 1]) {
                    Utils.swap(array, j, j + 1);
                }
            }
        }
        System.out.println("bubbleSort done: " + Arrays.toString(array));
    }

    public static void selectionSort(int[] array) {
        System.out.println("selectionSort: " + Arrays.toString(array));

        for(int i = 0; i < array.length; i++) {
            int minIndex = i;
            for(int j = i; j < array.length; j++) {
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            Utils.swap(array, i, minIndex);
        }
        System.out.println("selectionSort done: " + Arrays.toString(array));
    }

    public static void insertionSort(int[] array) {
        System.out.println("insertionSort: " + Arrays.toString(array));

        for(int i = 0; i < array.length; i++) {
            int selectIdx = i;
            for(int j = selectIdx; j > 0; j--) {
                if(array[j] < array[j - 1]) {
                    Utils.swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
        System.out.println("insertionSort done: " + Arrays.toString(array));
    }

    public static void mergeSort(int[] array) {

        System.out.println("mergeSort: " + Arrays.toString(array));
        array = splitAndMerge(array);
        System.out.println("mergeSort done: " + Arrays.toString(array));
    }

    public static int[] splitAndMerge(int[] array) {

        if(array.length <= 1) {
            return array;
        }

        int middleIndex = array.length >> 1;

        int[] left = Arrays.copyOfRange(array, 0, middleIndex);
        int[] right = Arrays.copyOfRange(array, middleIndex, array.length);

        return merge(splitAndMerge(left), splitAndMerge(right));
    }

    public static int[] merge(int[] left, int[] right) {

        int[] result = new int[left.length + right.length];

        int indexLeft = 0;
        int indexRight = 0;
        int indexResult = 0;

        while(indexLeft < left.length && indexRight < right.length) {
            if(left[indexLeft] <= right[indexRight]) {
                result[indexResult] = left[indexLeft];
                indexResult++;
                indexLeft++;
            } else {
                result[indexResult] = right[indexRight];
                indexResult++;
                indexRight++;
            }
        }

        for(int i = indexLeft; i < left.length; i++) {
            result[indexResult] = left[i];
            indexResult++;
        }

        for(int i = indexRight; i < right.length; i++) {
            result[indexResult] = right[i];
            indexResult++;
        }

        return result;
    }


    public static void quickSort(int[] array) {

        System.out.println("quickSort: " + Arrays.toString(array));
        quickRecursion(array, 0, array.length - 1);
        System.out.println("quickSort done: " + Arrays.toString(array));
    }

    public static void quickRecursion(int[] array, int low, int high) {

        if(low < high) {
            int pivotIndex = partition(array, low, high);
            quickRecursion(array, low, pivotIndex - 1);
            quickRecursion(array, pivotIndex + 1, high);
        }

    }

    public static int partition(int[] array, int low, int high) {

        int pivotValue = array[high];
        int lastIndexOfLess = low - 1;
        for(int loopIndex = low; loopIndex < high; loopIndex++) {
            if(array[loopIndex] <= pivotValue) {
                Utils.swap(array, loopIndex, lastIndexOfLess + 1);
                lastIndexOfLess++;
            }
        }

        Utils.swap(array, high, lastIndexOfLess + 1);
        return lastIndexOfLess;
    }

    public static void heapSort(int[] array) {

        System.out.println("heapSort: " + Arrays.toString(array));
        int lastNonLeafIndex = array.length / 2 - 1;

        for(int i = lastNonLeafIndex; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }

        // sort
        for(int i = array.length - 1; i > 0; i--) {
            Utils.swap(array, 0, i);
            downAdjust(array, 0, i);
        }
        System.out.println("heapSort done: " + Arrays.toString(array));
    }

    public static void downAdjust(int[] array, int target, int length) {

        int leftIndex = 2 * target + 1;
        int rightIndex = 2 * target + 2;
        int largerIndex = target;
        
        if(leftIndex < length && array[largerIndex] < array[leftIndex]) {
            largerIndex = leftIndex;
        }
        if(rightIndex < length && array[largerIndex] < array[rightIndex]) {
            largerIndex = rightIndex;
        }

        if(target != largerIndex) {
            Utils.swap(array, target, largerIndex);
            downAdjust(array, largerIndex, length);
        }
    }


}