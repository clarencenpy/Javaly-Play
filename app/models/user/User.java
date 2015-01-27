package models.user;

import com.avaje.ebean.Ebean;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by clarencenpy on 14/12/14.
 */

@Entity
@Table(name="users")
public class User extends Model {

    @Id
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    @OneToOne(cascade=CascadeType.ALL)
    public String username;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @Constraints.Required
    public String name;

    public String password;
    public String salt;

    @Formats.DateTime(pattern = "MM/DD/YY")
    @Column(columnDefinition = "DATETIME NOT NULL")
    public Date createdDate = new Date();

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date lastUpdate;

    @Constraints.Required
    @Column(columnDefinition = "VARCHAR(1) NOT NULL")
    public char userType; // 's' (student), 'i' (instructor), 'a' (admin)

    public User (String username, String name, String password, String salt, char userType){
        this.name = name;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createdDate = new Date();
        this.userType = userType;
    }

    public static User getDebugUser(){
        User user = Ebean.find(User.class)
                .where()
                .eq("username","Debug")
                .findUnique();
        if(user == null)
            return new User("Debug","Debug","Debug","Debug", 'i');
        else
            return user;
    }

}
