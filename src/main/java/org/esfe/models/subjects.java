package org.esfe.models;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false,length = 45)
    private String name;

    @Column(name = "description", nullable = false,length = 100)
    private String description;

    @Column(name = "mastername", nullable = false,length = 45)
    private String mastername;

    @Column(name = "surnameteacher", nullable = false,length = 45)
    private String surnameteacher;


    @ManyToOne
    @JoinColumn(name = "idstudents", nullable = false)
    private students students;

    @OneToMany(mappedBy = "subjects")
    private Set<notes> notes;

    public subjects() {
    }

    public subjects(int id, String name, String description, String mastername, String surnameteacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mastername = mastername;
        this.surnameteacher = surnameteacher;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }

    public String getSurnameteacher() {
        return surnameteacher;
    }

    public void setSurnameteacher(String surnameteacher) {
        this.surnameteacher = surnameteacher;
    }
}




