package models;

import models.user.Student;
import models.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by jeremyongts92 on 27/12/14.
 */

@Entity
public class Attempt {

    @Id
    public long attemptId;

    public String submittedCode;
    public int lastTiming;
    public int cumulativeTiming;
    public int attemptCount;
    public Date lastAttemptedDate;


    public static List<Attempt> attempts;

    @ManyToOne
    public User user;

    //@ManyToOne find out how to have multiple OneToMany annotations for 'Question' class
    Question question;

}
