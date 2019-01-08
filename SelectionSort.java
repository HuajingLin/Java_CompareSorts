/*
 * For CSCI 112 - week 6 Assignment
 * Unit 6 Programming Assignment - Benchmarking Sorting Algorithms.
 * Student: Huajing Lin
 * Date: 2/25/2017
 */
package comparesortsproject;

public class SelectionSort {
    SelectionSort(){
        //
    }
    public void doSelectionSort(int[] array){
         
        for (int i = 0; i < array.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[index])
                    index = j;
      
            int smallerNumber = array[index]; 
            array[index] = array[i];
            array[i] = smallerNumber;
        }        
    }// end doSelectionSort()

}// end class
