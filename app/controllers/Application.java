package controllers;

import play.mvc.*;
import actions.*;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result app() {
        return redirect("/app/index.html");
    }

    public static Result preflight(String all) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Allow", "*");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
        return ok();
    }


}
