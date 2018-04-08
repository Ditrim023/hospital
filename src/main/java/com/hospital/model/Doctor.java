package com.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Nikita Krutoguz
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private Integer age;
    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients;

    public Doctor(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public Doctor(String name,  Integer age) {
        this.name = name;
        this.age = age;
    }
    public Doctor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Doctor(String name) {
        this.name = name;
    }
}
