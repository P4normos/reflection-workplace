package org.workplace.methods.apitest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class PoductTest {

    public static void main(String[] args) {
        //testGetterMethods(Product.class);
        //testSetterMethods(Product.class);
        testSetterMethods(ClothingProduct.class);
        testGetterMethods(ClothingProduct.class);
    }

    private static List<Field> getAllFields(Class<?> clazz){
        if(clazz == null || clazz.equals(Object.class)){
            return Collections.emptyList();
        }

        Field[] currentClassFields = clazz.getDeclaredFields();
        List<Field> inheritedFields = getAllFields(clazz.getSuperclass());

        List<Field> allFields = new ArrayList<>();
        allFields.addAll(Arrays.asList(currentClassFields));
        allFields.addAll(inheritedFields);

        return allFields;
    }

    private static void testSetterMethods(Class<?> dataClass){

        List<Field> fields = getAllFields(dataClass);

        for(Field field : fields){
            String setterName = "set" + capilitizeFistLetter(field.getName());

            Method setterMethod = null;
            try {
                setterMethod = dataClass.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(String.format("Setter : %s not found", setterName));
            }

            if(!setterMethod.getReturnType().equals(void.class)){
                throw new IllegalStateException(String.format("Setter method : %s has to return void", setterName));
            }
        }
    }

    private static void testGetterMethods(Class<?> dataClass){
        List<Field> fields = getAllFields(dataClass);

        Map<String,Method> methodNameToMethod = mapMethodNameToMethod(dataClass);

        for(Field field : fields){
            String getterName = "get" + capilitizeFistLetter(field.getName());
            if(!methodNameToMethod.containsKey(getterName)){
                throw new IllegalStateException(String.format("Field : %s doesnt have a getter method",field.getName()));
            }

            Method getter = methodNameToMethod.get(getterName);

            if(!getter.getReturnType().equals(field.getType())){
                throw new IllegalStateException(String.format("Getter method : %s() has return type %s but expected %s",
                        field.getName(),
                        getter.getReturnType().getName(),
                        field.getType().getName()
                        ));
            }

            if(getter.getParameterCount()> 0){
                throw new IllegalStateException(String.format("Getter : %s has %s arguments",
                        getter.getName(),
                        getter.getParameterCount()
                ));
            }
        }
    }

    private static String capilitizeFistLetter(String name) {
        return name.substring(0,1).toUpperCase().concat(name.substring(1));
    }

    private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass){
        Method[] methods = dataClass.getMethods();

        Map<String, Method> nameToMethod = new HashMap<>();

        for(Method method : methods){
            nameToMethod.put(method.getName(), method);
        }
        return nameToMethod;
    }
}
