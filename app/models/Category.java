package models;

import play.db.ebean.Model;

import javax.persistence.Id;

/**
 * Created by jeremyongts92 on 3/1/15.
 */
public class Category extends Model {

    @Id
    public String label;

    
}
