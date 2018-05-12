package com.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_comment",
            joinColumns = {@JoinColumn(name = "patient_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "comment_id", referencedColumnName = "id")}
    )
    private List<Comment> comments;
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
}
