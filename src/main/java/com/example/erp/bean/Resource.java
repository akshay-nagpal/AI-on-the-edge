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
    private String sudo_password;
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
