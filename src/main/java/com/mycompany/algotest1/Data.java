package com.mycompany.algotest1;

import java.util.concurrent.ThreadLocalRandom;

public class Data {

    static int[] randomArray() {
        int[] array = new int[Defines.MAX_LENGTH];
        
        int MAX = Defines.MAX_VALUE * 2;
        for(int index = 0; index < Defines.MAX_LENGTH; index++) {
            int value = ThreadLocalRandom.current().nextInt(0, MAX);
            if(value > Defines.MAX_VALUE) {
                value = Defines.MAX_VALUE - value;
            }
            array[index] = value;
        }
        return array;
    }

    static int[] randomArrayControlled() {
        int[] array = new int[Defines.MAX_LENGTH];
        
        int sequenceLength = Defines.MAX_LENGTH / 2 + 1;
        System.out.println("sequenceLength " + sequenceLength);

        int[] values = 
        {
            ThreadLocalRandom.current().nextInt(0, Defines.MAX_VALUE),            
            ThreadLocalRandom.current().nextInt(0, Defines.MAX_VALUE)
        };

        int sequenceStartIndex = ThreadLocalRandom.current().nextInt(0, Defines.MAX_LENGTH / 3);
        System.out.println("sequenceStartIndex " + sequenceStartIndex);

        int MAX = Defines.MAX_VALUE * 2;
        for(int index = 0; index < Defines.MAX_LENGTH; index++) {
            
            int value;
            if(index >= sequenceStartIndex && index < sequenceStartIndex + sequenceLength) {
                int random = ThreadLocalRandom.current().nextInt(0, 2);
                value = values[random];
            }
            else {
                value = ThreadLocalRandom.current().nextInt(0, MAX);
                if(value > Defines.MAX_VALUE) {
                    value = Defines.MAX_VALUE - value;
                }
            }
            array[index] = value;
        }
        return array;
    }
    
    static int[][] testArrays() {
        int[][] array = {
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {},
            {1},
            {0, 5, 4, 4, 5, 12},
            {4, 4, 5, 5, 4, 4, 5, 5, 5, 5, 5, 5, 4, 5, 4, 4, 4, 4},
            {1000000000, -1000000000},
            {1000000000, -1000000002},
            {0, 1, 10, 20, 30, 20, 100000000},
            {100, 1, 10, 20, 30, 2000000000, 444, 555},
            {100, 1, 10, -1234567890, 20, 30, 444, 555},
            {2, 1, 2, 1, 2, 3, 444, 555},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };   
        return array;
    }
}
