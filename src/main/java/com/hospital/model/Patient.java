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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_doctor",
            joinColumns = {@JoinColumn(name = "patient_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id")}
    )
    private List<Doctor> doctors;

    private int age;

    public Patient(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Patient(String name, String surname, List<Doctor> doctors, int age) {
        this.name = name;
        this.surname = surname;
        this.doctors = doctors;
        this.age = age;
    }
}
