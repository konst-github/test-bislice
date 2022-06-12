
package com.mycompany.algotest1;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Solution {
    
    static private final boolean DEBUG = true;

    public int solveArray(int[] A) {
        
        if(DEBUG) {
            System.out.println("========== K's SOLUTION START ==========");
        }
        
        long timestampStart = systemTime();

        if(!isArrayLengthValid(A.length)) {
            return Defines.INVALID_VALUE;
        }

        int biSliceLength = 0;
        int biSliceLongest = 0;
        int biValueRepeats = 0;

        int bi0 = A[0];
        int bi1 = A[1];
        
        for(int value : A) { // foreach sometimes takes a bit longer (for 800M items ~ +450ms on my PC)
//        for(int index = 0; index < A.length; index++) {

            // storing current value in variable to avoid usage of [] operator every time we need to compare value etc.
            // saves ~ 200ms for 800M items
//            int value = A[index]; 

            if(value < Defines.MIN_VALUE || value > Defines.MAX_VALUE) {
                if(DEBUG) {
                    System.out.println("K's SOLUTION => ERROR: Input array contains an invalud value (" + value + ")");
                }
                return Defines.INVALID_VALUE;
            }

            // 1. fastest
            if(bi0 != value && bi1 != value) {
//                System.out.println("K's SOLUTION => SWAP, value " + value + ", bi0: " + bi0 + ", bi1: " + bi1);
                biSliceLength = biValueRepeats + 1;
            }
            else {
//                System.out.println("K's SOLUTION => INCREASE, value " + value);
                biSliceLength += 1;
            }

            if(value == bi1) {
                biValueRepeats += 1;
            }
            else {
                biValueRepeats = 1;
                bi0 = bi1;
                bi1 = value;
            }
            biSliceLongest = Math.max(biSliceLongest, biSliceLength);
        }

        long timestampEnd = systemTime();
        System.out.println("K's SOLUTION => Execution Time: " + (timestampEnd - timestampStart) + " ms");
        if(DEBUG) {
            System.out.println("K's SOLUTION => The longest bi-valued slice is " + biSliceLongest);        
            System.out.println("========== K's SOLUTION END ==========");
        }

        return biSliceLongest;
    }

    static private boolean isArrayLengthValid(int length) {
        boolean isValid = (length >= Defines.MIN_LENGTH && length <= Defines.MAX_LENGTH);        
        if(DEBUG) {
            System.out.println("K's SOLUTION => Input length is " + (isValid ? "" : "not ") + "valid (" + length + ")");
        }
        return isValid;
    }

    static private long systemTime() {
        long timestamp = System.currentTimeMillis();
        if(DEBUG) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timestamp);
            System.out.println("K's SOLUTION => " + formatter.format(date) + " (" + timestamp + " ms)");
        }
        return timestamp;
    }    
    
    /*
        Some googled solutions to compare performance
    */

    public int solveArrayGoogled(int[] A) {
      
        // Source - https://javabypatel.blogspot.com/2020/09/find-longest-length-bi-valued-slice-in-array-in-java.html
        // Same approach, only added outputs and validation of the current value
        
        if(DEBUG) System.out.println("========== SOLUTION GOOGLED START ==========");
        long timestampStart = systemTime();

        // Validate input length
        if(!isArrayLengthValid(A.length)) {
            return Defines.INVALID_VALUE;
        }

        int lastSeen = -1;
        int secondLastSeen = -1;
        int lbs = 0;
        int tempCount = 0;
        int lastSeenNumberRepeatedCount = 0;
 
        for (int current : A) {
            
            // Added this so both algorithms will be in same conditions, as my performs validity check
            if(current < Defines.MIN_VALUE || current > Defines.MAX_VALUE) {
                if(DEBUG) {
                    System.out.println("GOOGLED SOLUTION => ERROR: Input array contains an invalud value (" + current + ")");
                }
                return Defines.INVALID_VALUE;
            }

            if (current == lastSeen || current == secondLastSeen) {
                tempCount ++;
            } 
            else {
                // if the current number is not in our read list it means new series has started, tempCounter value in this case will be
                // how many times lastSeen number repeated before this new number encountered + 1 for current number.
                tempCount = lastSeenNumberRepeatedCount + 1;
            }
 
            if (current == lastSeen) {
                lastSeenNumberRepeatedCount++;
            } 
            else {
                lastSeenNumberRepeatedCount = 1;
 
                secondLastSeen = lastSeen;
                lastSeen = current;
            }
 
            lbs = Math.max(tempCount, lbs);
        }

        long timestampEnd = systemTime();   
        System.out.println("GOOGLED SOLUTION => Execution Time: " + (timestampEnd - timestampStart) + " ms");

        if(DEBUG) {
            System.out.println("GOOGLED SOLUTION => The longest bi-valued slice is " + lbs);       
            System.out.println("========== GOOGLED SOLUTION GOOGLED END ==========");
        }

        return lbs;
    }
    
    // Dirty copypaste just to compare algorithm speed
    public int solveArrayLeetcode1(int[] A) {
        
        if(DEBUG) {
            System.out.println("========== SOLUTION LEETCODE 1 START ==========");
        }

        long timestampStart = systemTime();

	if (A.length <= 2) {
            return A.length;
        }
        
	HashSet<Integer> set = new HashSet<>();
	int max = 0 ;
	int i =0, j=1;
        
	while (j< A.length && i<=j) {
		set.add(A[i]);
		set.add(A[j]);
		if (set.size()<=2) {
                    j++;
		} 
                else {
                    int length = j -i;
                    if (length > max) {
			max = length;
                    }
                    set = new HashSet<>();
                    i++;
                    j=i+1;
		}
	}
	
	max = j-i>max?j-i:max;
        
        long timestampEnd = systemTime();
        System.out.println("SOLUTION => Execution Time: " + (timestampEnd - timestampStart) + " ms");
        if(DEBUG) {
            System.out.println("SOLUTION => The longest bi-valued slice is " + max);        
            System.out.println("========== SOLUTION LEETCODE 1 END ==========");
        }

	return max;
    }   
    
    // Dirty copypaste just to compare algorithm speed
    public int solveArrayLeetcode2(int[] arr) {
        if(DEBUG) System.out.println("========== SOLUTION LEETCODE 2 START ==========");
        long timestampStart = systemTime();

        int longestFound = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++) {
          Set<Integer> bivalues = new HashSet<>();
          int currentLength = 1;
          bivalues.add(arr[i]);
          for(int j=i+1; j<arr.length; j++) {
            if(bivalues.contains(arr[j])) {
              currentLength++;
            } else if(!bivalues.contains(arr[j]) && bivalues.size() == 1) {
              currentLength++;
              bivalues.add(arr[j]);
            } else {
              break;
            }
          }
          longestFound = Math.max(longestFound, currentLength);
        }

        
        long timestampEnd = systemTime();
        System.out.println("SOLUTION => Execution Time: " + (timestampEnd - timestampStart) + " ms");
        if(DEBUG) {
            System.out.println("SOLUTION => The longest bi-valued slice is " + longestFound);        
            System.out.println("========== SOLUTION LEETCODE 2 END ==========");
        }

        return longestFound;
    }
}
