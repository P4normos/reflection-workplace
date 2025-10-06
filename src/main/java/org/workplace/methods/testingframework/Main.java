package org.workplace.methods.testingframework;
import java.util.*;
import java.lang.reflect.*;

public class Main {


    public class TestingFramework {
        public void runTestSuite(Class<?> testClass) throws Throwable {
            Method[] allMethods = testClass.getDeclaredMethods();
            List<Method> testMethods = findMethodsByPrefix(allMethods, "test");
            Method beforeClass = findMethodByName(allMethods,"beforeClass");
            Method setupTest = findMethodByName(allMethods, "setupTest");
            Method afterClass = findMethodByName(allMethods, "afterClass");
            if(beforeClass != null){
                beforeClass.invoke(testClass);
            }
            for(Method test : testMethods){
                Object instance  = testClass.getDeclaredConstructor().newInstance();
                if(setupTest != null){
                    setupTest.invoke(instance);
                }
                test.invoke(instance);
            }

            if(afterClass != null){
                afterClass.invoke(testClass);
            }
            /**
             * Complete your code here
             */
        }

        /**
         * Helper method to find a method by name
         * Returns null if a method with the given name does not exist
         */
        private Method findMethodByName(Method[] methods, String name) {
            for (Method method : methods) {
                if (method.getName().equals(name)
                        && method.getParameterCount() == 0
                        &&  method.getReturnType() == void.class) {

                    return method;
                }
            }
            return null;
            /**
             * Complete your code here
             */
        }

        /**
         * Helper method to find all the methods that start with the given prefix
         */
        private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
            List<Method> testMethods = new ArrayList<>();
            for (Method method : methods){
                if (method.getName().startsWith(prefix)
                        && method.getParameterCount() == 0
                        &&  method.getReturnType() == void.class) {

                    testMethods.add(method);
                }
            }
            return testMethods;
        }
    }
}
