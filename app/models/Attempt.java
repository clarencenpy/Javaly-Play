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
    /*
    TODO:
    public int lastTiming;
    public int cumulativeTiming;
    public int attemptCount;
    */

    public boolean isCorrect;

    public Date lastAttemptedDate;

    @ManyToOne
    public User user;

    @ManyToOne
    public Question question;

    @ManyToOne
    public Progress progress;

    public Attempt(String submittedCode, boolean isCorrect,
                    Date lastAttemptedDate, User user, Question question){
        this.submittedCode = submittedCode;
        this.isCorrect = isCorrect;
        this.lastAttemptedDate = lastAttemptedDate;
        this.user = user;
        this.question = question;
    }
}
