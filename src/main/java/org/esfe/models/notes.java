package org.esfe.models;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "notes")
public class notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "notes", nullable = false, precision = 10, scale = 0)
    private BigDecimal notes;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idsubjects", nullable = false)
    private subjects subjects;

    public notes() {
    }

    public notes(int id, BigDecimal notes, Date date) {
        this.id = id;
        this.notes = notes;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getNotes() {
        return notes;
    }

    public void setNotes(BigDecimal notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

