package models;

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
    public Long id;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String name;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String username;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String password;

    @Formats.DateTime(pattern = "MM/DD/YY")
    @Column(columnDefinition = "DATETIME NOT NULL")
    public Date createdDate = new Date();

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date lastUpdate;
}
