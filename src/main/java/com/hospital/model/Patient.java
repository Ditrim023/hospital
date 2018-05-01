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
    @ManyToMany(mappedBy = "patients")
    private List<HospitalUser> hospitalUsers;

    private int age;

    public Patient(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

}
