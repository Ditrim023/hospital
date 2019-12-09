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
    @Column(nullable = false)
    private Long dateLastChange = System.currentTimeMillis();
    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;
    @Column(nullable = false, updatable = false)
    private String author;
    @Column(nullable = false, updatable = false)
    private Long authorId;
    private String lastEditor;
    private Long lastEditorId;

    public Comment() {
    }

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

    public Comment(String text, Patient patient, String author) {
        this.text = text;
        this.patient = patient;
        this.author = author;
    }

    public Comment(String text, Patient patient,String lastEditor ,String author, Long authorId, Long lastEditorId) {
        this.text = text;
        this.patient = patient;
        this.author = author;
        this.lastEditor = lastEditor;
        this.authorId = authorId;
        this.lastEditorId = lastEditorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Long getDateLastChange() {
        return dateLastChange;
    }

    public void setDateLastChange(Long dateLastChange) {
        this.dateLastChange = dateLastChange;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(String lastEditor) {
        this.lastEditor = lastEditor;
    }

    public Long getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(Long lastEditorId) {
        this.lastEditorId = lastEditorId;
    }
}
