package models;

import models.user.User;
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

    @ManyToOne
    public User author;
    

    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    public List<TestCase> testCases = new ArrayList<>();

    public static final String TEST_CASE_INJECTION_HASH = "//ad168bea2e8cab5643936a5b223d859adc763f9dd468d225152f824041080559";
    public static final String METHOD_INJECTION_HASH = "//011f94cb6b87975609c962660ce9261cbf117cbca49057e6a0e9f7a274f28780";
    public static final String HELPER_METHOD_INJECTION_HASH = "//123123";
    public static final String INIT_CODE_INJECTION_HASH = "//121233";

    public static final String INJECTION_TEMPLATE =
            "public class Test{\n" +
            "    public static void main(String[] args){\n" +
            "        //init code insertion point\n" +
            "        " + INIT_CODE_INJECTION_HASH + "\n" +
            "        //test case insertion point\n" +
            "        " +  TEST_CASE_INJECTION_HASH + "\n" +
            "    }\n" +
            "\n" +
            "    //submitted method insertion point\n" +
            "    " + METHOD_INJECTION_HASH + "\n" +
            "\n" +
            "    //helper methods insertion point\n" +
            "    " + HELPER_METHOD_INJECTION_HASH + "\n" +
            "}";

}
