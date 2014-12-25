package engine;

import models.Question;
import models.TestCase;
import models.TestCaseResult;
import engine.util.CodeInjectorUtility;

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
        String template = Question.INJECTION_TEMPLATE;
        String codeWithMethod = CodeInjectorUtility.injectMethod(template, answer);
        int id = 0;

        for(TestCase t: q.testCases){
            String codeWithTestCase =
                    CodeInjectorUtility.injectTestCase(codeWithMethod, q.methodName, t.input);
            Compiler c = new Compiler(codeWithTestCase);
            String output = c.compileAndRun();
            TestCaseResult tcr =
                    new TestCaseResult(++id, t.input, output, t.output, output.equals(t.output));
            testCaseResults.add(tcr);
        }

        return testCaseResults;
    }
}
