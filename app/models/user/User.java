package models.user;

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
    public String username;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String name;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    @Constraints.Required
    public String password;

    @Column(columnDefinition = "VARCHAR(32) NOT NULL")
    @Constraints.Required
    public String salt;

    @Formats.DateTime(pattern = "MM/DD/YY")
    @Column(columnDefinition = "DATETIME NOT NULL")
    public Date createdDate = new Date();

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date lastUpdate;

    public char userType; // 's' (student), 'i' (instructor), 'a' (admin)

    public User (char userType, String name, String username, String password){
        this.userType = userType;
        this.name = name;
        this.username = username;
        this.password = password;

    }



}
