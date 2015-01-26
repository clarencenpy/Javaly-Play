package controllers;

import akka.actor.*;
import com.avaje.ebean.Ebean;
import engine.TestEngine;
import models.Question;
import models.TestCase;
import models.TestCaseResult;
import models.user.User;
import play.data.Form;
import play.db.ebean.Model;

import play.libs.Akka;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

import static akka.pattern.Patterns.ask;
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

    public static Result getUser(String username) {
        User u = Ebean.find(User.class, username);
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

    public static Result fakeResult() {
        ArrayList<TestCaseResult> results = new ArrayList<>();
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput><line2><expectedOutput><line2><expectedOutput><line2>", false));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        results.add(new TestCaseResult(1,"<input>","<resultOutput>", "<expectedOutput>", true));
        return ok(toJson(results));
    }

    public static WebSocket<String> socket() {

        return new WebSocket<String>() {

            // Called when the Websocket Handshake is done.
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {

                // For each event received on the socket,
                in.onMessage(new F.Callback<String>() {
                    public void invoke(String event) {

                        // Log events to the console
                        System.out.println(event);

                    }
                });

                // When the socket is closed.
                in.onClose(new F.Callback0() {
                    public void invoke() {

                        System.out.println("Disconnected");

                    }
                });

                // Send a single 'Hello!' message
                out.write("Hello!");

            }

        };
    }
}
