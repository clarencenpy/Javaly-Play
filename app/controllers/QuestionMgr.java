package controllers;

import com.avaje.ebean.Ebean;
import models.Question;
import models.TestCase;
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

        Ebean.save(q);

        return ok("Question saved!");
    }
}
