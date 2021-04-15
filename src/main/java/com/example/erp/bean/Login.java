package com.example.erp.bean;

import javax.persistence.*;

@Entity
@Table(name="Login")
public class Login
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="login_id")
    private Integer login_id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer usertype;

    @OneToOne(mappedBy = "login")
    private Resource resource;
//    @OneToOne(mappedBy = "login")
//    private Application application;
    public Integer getLogin_id() {
        return login_id;
    }
    public void setLogin_id(Integer login_id) {
        this.login_id = login_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}


