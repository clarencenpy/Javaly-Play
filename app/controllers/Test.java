package controllers;

import com.avaje.ebean.Ebean;
import engine.TestEngine;
import models.Question;
import models.TestCase;
import models.TestCaseResult;
import models.user.User;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class Test extends Controller {

    public static Result addUser() {
        User u = Form.form(User.class).bindFromRequest().get();
        u.save();
        return redirect(routes.Application.index());
    }

    public static Result getUsers() {
        List<User> users = new Model.Finder(String.class, User.class).all();
        return ok(toJson(users));
    }

    public static Result getUser(long id) {
        User u = Ebean.find(User.class, id);
        return ok(toJson(u));
    }

    public static Result addFakeQuestion() {
        //add question to db
        Question q = new Question();
        q.title = "Test question";
        q.className = "Test";
        q.methodName = "doSomething";
        q.description = "test test test";

        List<TestCase> testCases = new ArrayList<>();
        TestCase t = new TestCase();
        t.input = "1";
        t.output = "2";
        testCases.add(t);
        q.testCases = testCases;

        Ebean.save(q);
        return redirect(routes.Application.index());
    }

    public static Result run() {
        String code = "public static int doSomething(int i){int [] j = new int[1]; return i++;}";
        TestEngine te = new TestEngine(Ebean.find(Question.class, 1), code);
        ArrayList<TestCaseResult> results = te.run();
        return ok(toJson(results));
    }
}
