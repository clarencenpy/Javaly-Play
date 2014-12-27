package models.user;

/**
 * Created by jeremyongts92 on 27/12/14.
 */
public class Instructor extends User {

    public Instructor (Long id, String name, String username, String password){
        super('i', id, name, username, password);


    }
}
