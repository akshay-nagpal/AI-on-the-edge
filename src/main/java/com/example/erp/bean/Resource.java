package com.example.erp.bean;

import javax.persistence.*;

@Entity
@Table(name="Resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String IP;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String sudo_password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "login_id")
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    @OneToOne(mappedBy = "Resource")
//    private Login login;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getSudo_password() {
        return sudo_password;
    }

    public void setSudo_password(String sudo_password) {
        this.sudo_password = sudo_password;
    }
}
