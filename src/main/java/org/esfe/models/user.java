package org.esfe.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name",  nullable = false,length = 45)
    private String name;

    @Column(name = "lastname" , nullable = false, length = 45)
    private String lastname;

    @Column(name = "login", length = 45)
    private String login;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration", nullable = false)
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "idrole", nullable = false)
    private role role;

    @OneToMany(mappedBy = "user")
    private Set<students> students;

    public user() {
    }

    public user(int id, String name, String lastname, String login, String password,
                String status,String email, Date registrationDate, role role) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.status = status;
        this.email = email;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setUserName(String email) {
        this.email = email;
    }

    public String getUserName() {
        return email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }
}