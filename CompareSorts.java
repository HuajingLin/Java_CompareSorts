/*
 * For CSCI 112 - week 6 Assignment
 * Unit 6 Programming Assignment - Benchmarking Sorting Algorithms.
 * Student: Huajing Lin
 * Date: 2/25/2017
 */

package comparesortsproject;

public class CompareSorts {
    BubbleSort bubbleSort;
    SelectionSort selectionSort;
    InsertionSort insertionSort;
    MergeSort mergeSort;
    //MergeSortII mergeSortII;
    QuickSort quickSort;
    CompareSorts(){
        bubbleSort = new BubbleSort();
        selectionSort = new SelectionSort();
        insertionSort = new InsertionSort();
        mergeSort = new MergeSort();
        quickSort = new QuickSort();
    }
    public double RunSortTimes(int sortType, int size, int times){
        
        switch (sortType) {
            
            case 1:
                System.out.printf("1. Bubble sort, size: %d, %d times.\n", size, times);
                break;
            case 2:
                System.out.printf("2. Selection sort, size: %d, %d times.\n", size, times);
                break;
            case 3:
                System.out.printf("3. Insertion sort, size: %d, %d times.\n", size, times);
                break;
            case 4:
                System.out.printf("4. Merge sort, size: %d, %d times.\n", size, times);
                break;
            case 5:
                System.out.printf("5. Quick sort, size: %d, %d times.\n", size, times);
                break;
            default:;
                break;
        }
                
        long startTime = 0; // save the start time
        long endTime = 0;   // save the end time
        long duration = 0;  // save elapsed time 
        double timeTotal = 0;
        double timeAverage = 0;//save average time(second)
        double timeSort = 0;
        double timeMax = 0;
        double timeMin = 100.0;
        boolean bOvertime = false;
        //create a random array with parameter size
        int[] array = new int[size];
        
        for (int k = 1; k <= times; k++) {
            
            // fill the array with random integers
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * size + 1);                
            }
        
            // get the start time in nanoseconds
            startTime = System.nanoTime();

            switch (sortType) {
                case 1:
                    bubbleSort.doBubbleSort(array);
                    break;
                case 2:
                    selectionSort.doSelectionSort(array);
                    break;
                case 3:
                    insertionSort.doInsertionSort(array);
                    break;
                case 4: //call mergesort to sort the entire array
                    mergeSort.sort(array);                    
                    break;
                case 5:
                    quickSort.sort(array);
                    break;
                default:
                    System.out.printf("Error parameter %d\n", sortType);
                    break;
            }
            
            // get the end time in nanoseconds
            endTime = System.nanoTime();

            // calculate elapsed time in nanoseconds
            duration = endTime - startTime;
            
            // print the elapsed time in seconds   (nanaoseconds/ 1 billion)
            //System.out.printf("%12.8f %n", (double) duration / 100000000);
            timeSort = (double) duration / 1000000000.0;
            //System.out.printf("\t\t%d sort time: %12.8f\n", k, timeSort);
            if(timeSort > 1.0){
                bOvertime = true;
                System.out.printf("\tSort timeout: %12.8f\n", timeSort);
                break;
            }
            else{
                timeTotal += timeSort;
                if(timeSort > timeMax)
                    timeMax = timeSort;
                if(timeSort < timeMin)
                    timeMin = timeSort;
            }
            
        }//end For loop times
        
        if(bOvertime)
            return 0.0;
        else{
            //System.out.printf("sort total time: %d\n", timeAverage);
            timeAverage = timeTotal / (double)times;
            System.out.printf("\t Average time: %12.8f \n", timeAverage);
            System.out.printf("\t Time of best case: %12.8f \n", timeMin);
            System.out.printf("\t Time of worst case: %12.8f \n", timeMax);
            return timeAverage;
        }
        
    }// end RunSortTimes()
    
}// end class
