package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by clarencenpy on 15/12/14.
 */

@Entity
@Table(name="questions")
public class Question extends Model{

    @Id
    public long id;

    @ManyToOne
    public User author;

    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    public List<TestCase> testCases = new ArrayList<>();

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String title;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String className;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Constraints.Required
    public String methodName;

    @Column(columnDefinition = "TEXT NOT NULL")
    @Constraints.Required
    public String description;

    @Column(columnDefinition = "TEXT")
    public String mainMethodCode;

    @Column(columnDefinition = "TEXT")
    public String helperMethodCode;

    @Formats.DateTime(pattern = "MM/DD/YY")
    @Column(columnDefinition = "DATETIME NOT NULL")
    public Date createdDate = new Date();

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date lastUpdate;

}
