package models;

import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by clarencenpy on 15/12/14.
 */

@Entity
@Table(name="test_cases")
public class TestCase {

    @Id
    public long id;

    @ManyToOne
    public Question question;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @Constraints.Required
    public String input;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @Constraints.Required
    public String output;

}
