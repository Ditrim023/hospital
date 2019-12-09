package com.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    /*@Column(nullable = false)*/
    private String dateBirth;
    @ManyToOne(fetch = FetchType.EAGER)
    private HospitalUser doctor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Comment> comments;
    @Column(nullable = false)
    private Long dateTransfer = System.currentTimeMillis();

    public Patient() {
    }

    public Patient(String name, String surname, String dateBirth) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
    }

    public Patient(String name, String surname, HospitalUser doctor) {
        this.name = name;
        this.surname = surname;
        this.doctor = doctor;
    }

    public Patient(String name, String surname, String dateBirth, HospitalUser doctor) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
        this.doctor = doctor;
    }

    public Patient(String name, String surname, List<Comment> comments) {
        this.name = name;
        this.surname = surname;
        this.comments = comments;
    }

    public Patient(String name, String surname, String dateBirth, List<Comment> comments) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public HospitalUser getDoctor() {
        return doctor;
    }

    public void setDoctor(HospitalUser doctor) {
        this.doctor = doctor;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Long dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}
