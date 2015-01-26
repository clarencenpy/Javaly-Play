package controllers;

import com.avaje.ebean.Ebean;
import engine.TestEngine;
import models.Attempt;
import models.Question;
import models.TestCaseResult;
import models.user.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by ASUS on 1/26/2015.
 */
public class AttemptMgr extends Controller{
    public static Result processAttempt() {
        //bind data from form
        DynamicForm requestData = Form.form().bindFromRequest();
        String code = requestData.get("code");
        long questionId = Long.parseLong(requestData.get("id"));

        //retrieve question
        Question question = Ebean.find(Question.class, questionId);

        if(question != null) {
            TestEngine te = new TestEngine(question, code);
            List<TestCaseResult> results = te.run();
            saveAttempt(code, question, results);
            return ok(toJson(results));
        }

        return status(400, "Invalid Question ID!");
    }

    public static void saveAttempt(String code, Question question, List<TestCaseResult> results){
        User user = Ebean.find(User.class).where().eq("username", session().get("username")).findUnique();
        user = (user == null) ? User.getDebugUser():user;
        Attempt attempt = new Attempt(code, isAllCorrect(results), new Date(), user, question);
        Ebean.save(attempt);
    }

    public static boolean isAllCorrect(List<TestCaseResult> results){
        for(TestCaseResult t: results){
            if(!t.getHasPassed()){
                return false;
            }
        }
        return true;
    }

}
