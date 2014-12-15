package controllers;

import com.avaje.ebean.Ebean;
import models.User;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("hello world!"));
    }

    public static Result addUser() {
        User u = Form.form(User.class).bindFromRequest().get();
        u.save();
        return redirect(routes.Application.index());
    }

    public static Result getUsers() {
        List<User> users = new Model.Finder(String.class, User.class).all();
        return ok(toJson(users));
    }

    public static Result test() {
        User u = Ebean.find(User.class, 2);
        return ok(toJson(u));
    }
}
