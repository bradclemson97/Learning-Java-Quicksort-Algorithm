package com.bradclemson97;

/* The quicksort algorithm is 3 steps:
    1. choose one of the numbers in your array as the 'pivot' - it's better to choose it at random
    2. partitioning - move all numbers in the array lower than the pivot to the left of it and all number higher to the right of it
    3. recursively quicksort all-of the values to the left and right of the pivot
 */

import java.util.Random;

public class Main {


    public static void main(String[] args) {

        // this will generate a random array of 10 integers between 1-99
        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(100);
        }

        System.out.println("Before:");
        printArray(numbers);

        quicksort(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
    }

    //overloading of the quicksort method - when we have more than one method with the same name but different parameters
    private static void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    //low index sub array = number lowest-than the pivot
    //high index sub array = number highest-than the pivot
    private static void quicksort(int[] array, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex) {
            return;
        }

        //STEP 1. choosing a random element in the array as pivot
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        //STEP 3. recursively quick sorting values to the left and right of the pivot

        //STEP 3.1. sorting the left of the pivot
        //our low index will be our low index (the very first number in the array)
        //our high index will be our leftpointer -1
        quicksort(array, lowIndex, leftPointer - 1);

        //STEP 3.2. sorting the right of the pivot
        //our low index will be our leftpointer +1
        //our high index will be our high index (the very last number in the array)
        quicksort(array, leftPointer + 1, highIndex);

    }
        //STEP 2. partitioning
        //declare left pointer and right pointer, starting at the left and right side of the array that we are sorting
    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer) {

            // Walk from the left until we find a number greater than the pivot, or hit the right pointer.
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            // Walk from the right until we find a number less than the pivot, or hit the left pointer.
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);
        }

        // fixes an issue where the last value could potentially be out of order.
        if(array[leftPointer] > array[highIndex]) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }

        return leftPointer;
    }

    //method to swap the two leftpointer (index1) and rightpointer (index2) values once they meet
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }

}






