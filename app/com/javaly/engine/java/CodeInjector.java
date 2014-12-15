package com.javaly.engine.java;

/**
 *  Utility class to inject methods and test cases
 * @author ASUS
 */
public class CodeInjector {
    private static final String METHOD_INSERTION_HASH =
            "//011f94cb6b87975609c962660ce9261cbf117cbca49057e6a0e9f7a274f28780";
    private static final String TEST_CASE_INSERTION_HASH =
            "//ad168bea2e8cab5643936a5b223d859adc763f9dd468d225152f824041080559";

    private CodeInjector(){
    }

    /**
     *  Injects a test case into the main method body of the source code.
     *  @TODO: throw exception if injection point not found
     *  @param  code the original source code
     *  @param methodName the name of the method to be injected
     *  @param input the inputs to the method to be injected
     *  @return String which is the output code
     */
    public static String injectTestCase(String code, String methodName, String input){
        StringBuilder testCaseCode = new StringBuilder();
        testCaseCode.append("System.out.println(");
        testCaseCode.append(methodName);
        testCaseCode.append("(");
        testCaseCode.append(input);
        testCaseCode.append("));");

        String outputCode = code.replaceAll(TEST_CASE_INSERTION_HASH, testCaseCode.toString());

        return outputCode;
    }

    /**
     *  Injects a method into the class body of the source code.
     *  @TODO: throw exception if injection point not found
     *  @param  code the original source code
     *  @param input the method to be injected
     *  @return String which is the output code
     */
    public static String injectMethod(String code,  String input){
        String outputCode = code.replaceAll(METHOD_INSERTION_HASH, input);
        return outputCode;
    }

}
