package models.user;

import models.Question;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jeremyongts92 on 27/12/14.
 */

@Entity
public class Instructor extends Model {

    @Id
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String username;

    @OneToOne
    public User user;

    @OneToMany(mappedBy="author")
    public List<Question> questions;

    
}
