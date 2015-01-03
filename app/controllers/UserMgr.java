package controllers;

import com.avaje.ebean.Ebean;
import engine.util.SecurityUtility;
import models.user.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Date;

import static play.libs.Json.toJson;
/**
 * Created by clarencenpy on 25/12/14.
 */
public class UserMgr extends Controller{

    public static Result loginUser() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String username = requestData.get("username");
        String password = requestData.get("password");

        User u = Ebean.find(User.class)
                .where()
                .eq("username", username)
                .findUnique();

        boolean isAuth = false;

        if (u == null) {
            return status(419, "User not found!");
        } else{
            try {
                String userProvidedPass = SecurityUtility.getHash(password, u.salt);
                isAuth = userProvidedPass.equals(u.password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isAuth) {
                session().put("username", username);
                return ok("<form action=\"logout\" method=\"POST\"> <input type=\"submit\" value=\"Logout!\"></form>").as("text/html");
            } else {
                return status(420, "Wrong password!");
            }
        }

    }

    public static Result logoutUser() {
        session().clear();
        return redirect("/");
    }

    public static Result showRegisterUser() {
        return ok(views.html.register.render());
    }

    public static Result registerUser() {
        Form<User> userForm = Form.form(User.class);
        User u = userForm.bindFromRequest().get();

        //check if username exists
        //TODO use regex
        if(u.username.length() > 0 && u.password.length() > 0 &&
                u.name.length() > 0 &&
                Ebean.find(User.class).where().eq("username", u.username).findUnique() == null){
            try {
                String[] passwords = SecurityUtility.getHashPair(u.password);
                u.password = passwords[0];
                u.salt = passwords[1];
                u.createdDate = new Date();
                Ebean.save(u);
            } catch (Exception e){
                e.printStackTrace();
            }
        }



        return ok("Welcome " + u.username);
    }

}
