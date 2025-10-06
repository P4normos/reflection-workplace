package org.workplace.fields_and_arrays.arrayflattening;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer [] result = concat(Integer.class, 1,2,3,4,5);
        //int [] result = concat(int.class, 1, 2, 3, new int[] {4, 5, 6}, 7);
        System.out.println(Arrays.toString(result));
    }

    public static <T> T concat(Class<?> type, Object... arguments) {

        if (arguments.length == 0) {
            return null;
        }

        List<Object> elements = new ArrayList<>();


        for(Object obj : arguments){
            if(obj.getClass().isArray()){
                int length = Array.getLength(obj);
                for(int i = 0 ; i< length; i++){
                    elements.add(Array.get(obj,i));
                }
            }else{
                elements.add(obj);
            }
        }

        Object arrayObject = Array.newInstance(type, elements.size());
        for(int i = 0 ; i<elements.size(); i++){
            Array.set(arrayObject,i,elements.get(i));
        }

        return (T) arrayObject;


    }
}
