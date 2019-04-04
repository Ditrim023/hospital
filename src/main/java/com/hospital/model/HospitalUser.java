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
    @Column(nullable = false,unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String dateBirth;
    @Column(nullable = false)
    private String position;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "doctor")
    private List<Patient> patients;
    @Column(nullable = false, insertable = false, updatable = false)
    private Long roleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private UserRole role;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserStatus status;

    public HospitalUser(String name, String surname, String login, String password, String dateBirth, String position, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.position = position;
        this.dateBirth = dateBirth;
        this.role = role;
    }
    public HospitalUser(String name, String surname, String login, String password, String dateBirth, String position, UserRole role, UserStatus status) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.position = position;
        this.dateBirth = dateBirth;
        this.role = role;
        this.status = status;
    }
    public HospitalUser(String name, String surname, String login, String password, String dateBirth, String position) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.dateBirth = dateBirth;
        this.position = position;
    }

    public HospitalUser(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }





}