package com.javaly.engine;

import models.Question;
import models.TestCase;
import models.TestCaseResult;
import com.javaly.engine.java.CodeInjector;
import com.javaly.engine.java.Compiler;

import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ASUS
 */
public class TestEngine {
    private final Question q;
    private final String answer;

    public TestEngine(Question q, String answer){
        this.q = q;
        this.answer = answer;
    }

    public ArrayList<TestCaseResult> run(){
        ArrayList<TestCaseResult> testCaseResults = new ArrayList<>();
        String template = "public class Test{\n" +
                "    public static void main(String[] args){\n" +
                "        //test case insertion point\n" +
                "        //ad168bea2e8cab5643936a5b223d859adc763f9dd468d225152f824041080559\n" +
                "    }\n" +
                "\n" +
                "    //function insertion point\n" +
                "    //011f94cb6b87975609c962660ce9261cbf117cbca49057e6a0e9f7a274f28780\n" +
                "}";
        String codeWithMethod = CodeInjector.injectMethod(template, answer);
        int id = 0;

        for(TestCase t: q.testCases){
            String codeWithTestCase =
                    CodeInjector.injectTestCase(codeWithMethod, q.methodName, t.input);
            String output = Compiler.compileAndRun(codeWithTestCase);
            TestCaseResult tcr =
                    new TestCaseResult(++id, t.input, output, t.output, output.equals(t.output));
            testCaseResults.add(tcr);
        }

        return testCaseResults;
    }
}
