package com.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;


    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @Column(nullable = false)
    private long dateCreate = System.currentTimeMillis();

    @ManyToMany(mappedBy = "comments")
    private List<Patient> patients;

    public Comment(String text) {
        this.text = text;
    }
}
