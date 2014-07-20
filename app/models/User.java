package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Unique;
import play.db.jpa.GenericModel;

@Entity
@Table(name = "user")
public class User extends GenericModel {
    @Id
    public String id;

    @Column(name = "account")
    @Unique
    public String account;

    @Column(name = "password")
    public String password;

    String hello;}

}

