package controllers;

import play.mvc.*;
import actions.*;

@CorsComposition.Cors
public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result preflight(String path) {
        return ok("");
    }

}
