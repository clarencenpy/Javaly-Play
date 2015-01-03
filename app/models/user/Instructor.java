package models.user;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by jeremyongts92 on 27/12/14.
 */

@Entity
public class Instructor extends Model {

    @Id
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public User username;

    @OneToOne
    public User user;


    
}
