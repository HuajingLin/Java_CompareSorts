/*
 * For CSCI 112 - week 6 Assignment
 * Unit 6 Programming Assignment - Benchmarking Sorting Algorithms.
 * Student: Huajing Lin
 * Date: 2/25/2017
 */
package comparesortsproject;

public class BubbleSort {
    BubbleSort(){
        //
    }
    public void doBubbleSort(int array[]) {
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }            
        }// end for loop
    }// end doBubbleSort()
  
    private void swapNumbers(int i, int j, int[] array) {  
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }// end swapNumbers()
}// end class
