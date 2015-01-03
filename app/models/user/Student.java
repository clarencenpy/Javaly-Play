package models.user;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by jeremyongts92 on 27/12/14.
 */

@Entity
public class Student extends Model {

    @Id
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String username;

    @OneToOne
    public User user;

    public Long attempts;




}
