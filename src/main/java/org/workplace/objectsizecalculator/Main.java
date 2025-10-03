package org.workplace.objectsizecalculator;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("Johnny","Deep");
        ObjectSizeCalculator calculator = new ObjectSizeCalculator();
        System.out.println(String.format("Size of the object is %s bytes.",calculator.sizeOfObject(person)));
    }

    static class Person{
        private final String firstName;
        private final String lastName;

        Person(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
