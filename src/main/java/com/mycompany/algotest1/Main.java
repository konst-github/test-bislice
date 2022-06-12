
package com.mycompany.algotest1;

import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) {
                
        // some primitive tests - simulation of unit tests without any checks
        int[] RND = Data.randomArrayControlled();        
        int[][] testArrays = Data.testArrays();

        Solution solution = new Solution();
        for(int[] A : testArrays) {
            System.out.println("Input array: " + Arrays.toString(A));
            solution.solveArray(A);
            System.out.println("");
        }        

        solution.solveArray(RND);
        System.out.println("");
        solution.solveArrayGoogled(RND);
        
        // Leetcode solutions - HINT: they are too long, so just no sense to waste time with them
//        System.out.println("");
//        solution.solveArrayLeetcode1(RND);
//        System.out.println("");
//        solution.solveArrayLeetcode2(RND);
    }
}
