package controllers;

import com.avaje.ebean.Ebean;
import engine.TestEngine;
import models.Question;
import models.TestCase;
import models.TestCaseResult;
import models.user.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import actions.*;
import scala.util.parsing.json.JSONArray$;
import views.html.questionsubmit;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;
/**
 * Created by clarencenpy on 25/12/14.
 */


//@Security.Authenticated(Secured.class)
public class QuestionMgr extends Controller{

    public static Result showAllQuestion(){
        List<Question> qList = Ebean.find(Question.class).findList();

        for(Question q : qList) {
            q.testCases = null;
            q.author = null;
        }
        //Gson gsonObj = new Gson();

        return ok(toJson(qList));
    }

    public static Result showQuestion(int id) {
        Question q = Ebean.find(Question.class, id);
        q.testCases = null;
        return ok(toJson(q));

    }

    public static Result showAddQuestion(){
        return ok(views.html.questionsubmit.render("Stuff,"));
    }

    public static Result addQuestion() {
        Question q = bindQuestionForm();
        User author = Ebean.find(User.class)
                .where()
                .eq("username", session().get("username"))
                .findUnique();

        //FOR DEBUGGING
        if(author==null){
            author = User.getDebugUser();
            Ebean.save(author);
        }

        q.author = author;
        Ebean.save(q);

        q.testCases = null;
        q.author = null;//workaround for a bug in toJson
        return ok(toJson(q));
    }

    public static Result modQuestion() {
        Question q = bindQuestionForm();
        Ebean.update(q);
        q.testCases = null;
        q.author = null;    //workaround for a bug in toJson
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

        for(int i = 1;
                requestData.get("tc"+i+"input") != null &&
                requestData.get("tc"+i+"output") != null;
                i++) {
            TestCase t = new TestCase();
            t.input = requestData.get("tc"+i+"input");
            t.output =  requestData.get("tc"+i+"output");
            testCases.add(t);
        }
        q.testCases = testCases;

        return q;
    }


}