package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Unique;
import play.db.jpa.GenericModel;
import play.libs.Codec;

@Entity
@Table(name = "user")
public class User extends GenericModel {
    @Id
    public String id;

    @Column(name = "type")
    public String type;
    
    @Column(name = "name")
    public String name;

    @Column(name = "username")
    public String username;
    
    @Column(name = "password")
    public String password;
    
    public User(){
      this.id = Codec.UUID();
    }

}
