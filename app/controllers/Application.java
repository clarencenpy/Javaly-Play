package controllers;

import com.avaje.ebean.Ebean;
import com.javaly.engine.TestEngine;
import models.Question;
import models.TestCase;
import models.TestCaseResult;
import models.User;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("hello world!"));
    }

}
