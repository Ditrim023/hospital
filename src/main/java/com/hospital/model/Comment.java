package com.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

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
    private Long dateCreate = System.currentTimeMillis();

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    public Comment(String text) {
        this.text = text;
    }

    public Comment(String text, Long dateCreate) {
        this.text = text;
        this.dateCreate = dateCreate;
    }

    public Comment(String text, Patient patient) {
        this.text = text;
        this.patient = patient;
    }
}
