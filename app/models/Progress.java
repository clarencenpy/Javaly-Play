package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 1/26/2015.
 */
@Entity
@Table(name="progress")
public class Progress {
    @Id
    public long progressId;

    @OneToMany(mappedBy="progress", cascade= CascadeType.ALL)
    public List<Attempt> attempts = new ArrayList<>();

}
