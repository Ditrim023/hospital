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
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hospital_users")
public class HospitalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "hospital_users_patients",
            joinColumns = {@JoinColumn(name = "hospital_users_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")}
    )
    private List<Patient> patients;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserRole role;

    public HospitalUser(String name, String surname, String login) {
        this.name = name;
        this.surname = surname;
        this.login = login;
    }

    public HospitalUser(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
