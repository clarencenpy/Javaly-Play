package models.user;

/**
 * Created by jeremyongts92 on 27/12/14.
 */
public class Student extends User {

    public Long attempts;

    public Student (Long id, String name, String username, String password){
        super('s', id, name, username, password);

    }


}
