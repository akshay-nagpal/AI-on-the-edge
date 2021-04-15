package com.example.erp.bean;

import javax.persistence.*;

@Entity
@Table(name="Application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="login_id")
    private Integer id;
    @Column
    private String appname;
    @Column
    private String email;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
