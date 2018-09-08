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
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "status")
    private List<HospitalUser> user;

    public UserStatus(String name) {
        this.name = name;
    }
}
