package controllers;

import com.avaje.ebean.Ebean;
import engine.util.SecurityUtility;
import models.user.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import javax.crypto.*;
import org.apache.commons.codec.binary.Base64;
import java.net.URLEncoder;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


import java.security.SecureRandom;
import java.util.Date;

import static play.libs.Json.toJson;
/**
 * Created by clarencenpy on 25/12/14.
 */
public class UserMgr extends Controller{
    private static final String TEMP_PASSWORD = "Sis_Student2015";


    //TODO: refactor to a security class
    public static Result loginUser() {
        DynamicForm requestData = Form.form().bindFromRequest();

        final String[] keys = {
                "oauth_callback",
                "oauth_consumer_key",
                "oauth_nonce",
                "oauth_signature_method",
                "oauth_timestamp",
                "oauth_version",
                "smu_domain",
                "smu_fullname",
                "smu_groups",
                "smu_username"
        };

        final String SECRET_KEY = "1234567890";

        String callbackUrl = "http://localhost:9000/user/login";

        String uri = "POST&" + encode(callbackUrl) + "&" ;

        String pairs = "";
        for (int i = 0 ; i < keys.length-1 ; i++) {
            pairs += keys[i] + "=" + encode(requestData.get(keys[i])) + "&";
        }

        pairs += keys[keys.length - 1] + "=" + encode(requestData.get(keys[keys.length - 1]));

        uri += encode(pairs);


        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKey secretKey = new SecretKeySpec( (SECRET_KEY + "&").getBytes(), mac.getAlgorithm());

            try {
                mac.init(secretKey);

                String generatedSignature = Base64.encodeBase64String(mac.doFinal(uri.getBytes())).trim();
                String serverSignature = (requestData.get("oauth_signature"));

                long serverSignatureTime = Long.parseLong(requestData.get("oauth_timestamp"));
                //requestData.get("oauth_timestamp")
                long currentTime = System.currentTimeMillis() / 1000;


                if (serverSignature.equals(generatedSignature) && (Math.abs(serverSignatureTime - currentTime) <= 30)) {
                    if(!userExists(requestData.get("smu_username"))){
                        //TODO: register instructors accordingly
                        String[] passwords = SecurityUtility.getHashPair(TEMP_PASSWORD);
                        User user = new User(requestData.get("smu_username"),
                                requestData.get("smu_fullname"),passwords[0],passwords[1], 's');
                        Ebean.save(user);
                    }
                    session().put("username", requestData.get("smu_username"));

                    return ok("<form action=\"logout\" method=\"POST\">"+requestData.get("smu_username")+ "<input type=\"submit\" value=\"Logout!\"></form>").as("text/html");
                }

            }catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return ok("Login failed");
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
                session().put("username", u.username);
                return ok("Welcome "+ u.username);
            } catch (Exception e){
                e.printStackTrace();
            }
        }



        return status(503, "Something went wrong!");
    }

    private static boolean userExists(String username){
            return (Ebean.find(User.class).where().eq("username", username).findUnique() != null);
    }

    public static String encode(String plain) {
        try {
            String encoded = URLEncoder.encode(plain, "UTF-8");

            return encoded.replace("*", "%2A")
                    .replace("+", "%20")
                    .replace("%7E", "~");
        } catch (Exception e) {
            e.printStackTrace(); // hopefully this wont happen
        }
        return "";
    }

}
