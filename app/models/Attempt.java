package models;

import models.user.Student;

import java.util.Date;
import java.util.List;

/**
 * Created by jeremyongts92 on 27/12/14.
 */
public class Attempt {

    public static List<Attempt> attempts;
    public long attemptId;
    public Student student;
    public Question question;
    public String submittedCode;
    public int lastTiming;
    public int cumulativeTiming;
    public int attemptCount;
    public Date lastAttemptedDate;

}
