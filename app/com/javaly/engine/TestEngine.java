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

    //@waituck, model attributes/methods may be changed muhhaaaaha
    public ArrayList<TestCaseResult> run(){
        ArrayList<TestCaseResult> testCaseResults = new ArrayList<>();
        String codeWithMethod = CodeInjector.injectMethod(q.getOriginalCode(), answer);
        int id = 0;

        for(TestCase t: q.getTestCases()){
            String codeWithTestCase =
                    CodeInjector.injectTestCase(codeWithMethod, q.getMethodName(), t.getInput());
            String output = Compiler.compileAndRun(codeWithTestCase);
            TestCaseResult tcr =
                    new TestCaseResult(++id, t.getInput(), output, t.getOutput(), output.equals(t.getOutput()));
            testCaseResults.add(tcr);
        }

        return testCaseResults;
    }
}
