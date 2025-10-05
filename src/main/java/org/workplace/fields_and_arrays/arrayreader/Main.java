package org.workplace.fields_and_arrays.arrayreader;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        int [] input = new int[] {0, 10, 20, 30, 40};
        System.out.println(ArrayReader.getArrayElement(input,1));
        System.out.println(ArrayReader.getArrayElement(input,0));
        System.out.println(ArrayReader.getArrayElement(input,-2));
    }

    public class ArrayReader{
        public static Object getArrayElement(Object array, int index) {
            if (index >= 0) {
                return Array.get(array, index);
            }
            int arrayLength = Array.getLength(array);
            return Array.get(array, arrayLength + index);
        }
    }
}
