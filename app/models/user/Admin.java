package models.user;

/**
 * Created by jeremyongts92 on 27/12/14.
 */
public class Admin extends User {

    public Admin (Long id, String name, String username, String password){
        super('a', id, name, username, password);


    }
}
