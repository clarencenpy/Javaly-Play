package models;

import models.user.Student;

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

    //@Id
    public long attemptId;

    public static List<Attempt> attempts;

    //@ManyToOne
    public Student student;

    @ManyToOne
    public Question question;

    public String submittedCode;
    public int lastTiming;
    public int cumulativeTiming;
    public int attemptCount;
    public Date lastAttemptedDate;

}
