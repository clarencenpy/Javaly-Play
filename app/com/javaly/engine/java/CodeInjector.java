package com.javaly.engine.java;

import models.Question;

/**
 *  Utility class to inject methods and test cases
 * @author ASUS
 */
public class CodeInjector {

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

        String outputCode = code.replaceAll(Question.TEST_CASE_INJECTION_HASH, testCaseCode.toString());

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
        String outputCode = code.replaceAll(Question.METHOD_INJECTION_HASH, input);
        return outputCode;
    }

}
