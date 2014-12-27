package controllers;

import com.avaje.ebean.Ebean;
import engine.TestEngine;
import models.Question;
import models.TestCase;
import models.TestCaseResult;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.questionsubmit;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;
/**
 * Created by clarencenpy on 25/12/14.
 */
public class QuestionMgr extends Controller{

    public static Result showAddQuestion(){
        return ok(views.html.questionsubmit.render("Stuff,"));
    }

    public static Result addQuestion() {
        Question q = bindQuestionForm();
        Ebean.save(q);
        q.testCases = null; //workaround for a bug in toJson
        return ok(toJson(q));
    }

    public static Result modQuestion() {
        Question q = bindQuestionForm();
        Ebean.update(q);
        q.testCases = null; //workaround for a bug in toJson
        return ok(toJson(q));
    }

    public static Result submitAnswer() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String code = requestData.get("code");
        long id = Long.parseLong(requestData.get("id"));

        TestEngine te = new TestEngine(Ebean.find(Question.class, id), code);
        ArrayList<TestCaseResult> results = te.run();

        return ok(toJson(results));
    }

    private static Question bindQuestionForm(){
        Form<Question> questionForm = Form.form(Question.class);

        Question q = questionForm.bindFromRequest().get();

        //binds additional Test Case data
        DynamicForm requestData = Form.form().bindFromRequest();

        List<TestCase> testCases = new ArrayList<>();

        //TODO: Change test case adding according to number provided by client
        for(int i = 1; i <= 3; i++) {
            TestCase t = new TestCase();
            t.input = requestData.get("tc"+i+"input");
            t.output =  requestData.get("tc"+i+"output");
            testCases.add(t);
        }
        q.testCases = testCases;

        return q;
    }

}
