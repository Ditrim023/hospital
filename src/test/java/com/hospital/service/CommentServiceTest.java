package com.hospital.service;

import com.hospital.HospitalApplication;
import com.hospital.model.Comment;
import com.hospital.model.HospitalUser;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nikita Krutoguz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HospitalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private HospitalUserService hospitalUserService;

    @Test
    @DirtiesContext
    public void commentUpdate() {
        Comment fromBase = commentService.findOne(1L);
        fromBase.setText("Update");
        commentService.add(fromBase);
        Assert.assertEquals(commentService.findOne(1L).getText(),"Update");
    }

    @Test
    @DirtiesContext
    public void saveComment() {
        Comment comment = new Comment("Save Comment");
        comment.setPatient(patientService.findOne(3L));
        comment.setAuthor(hospitalUserService.findOne(3L).getName());
        comment.setAuthorId(3L);
        comment.setLastEditor(hospitalUserService.findOne(3L).getName());
        commentService.add(comment);
        Assert.assertEquals(commentService.findOne(2L).getText(),"Save Comment");
    }


    @Test
    @DirtiesContext
    public void findAll() {
        Assert.assertTrue(commentService.findAll().size() > 0);
    }


    @Test
    @DirtiesContext
    public void getTextComment() {
        Assert.assertEquals(commentService.getTextComment(1L),"Text Comment");
    }

    @Test
    @DirtiesContext
    public void findOne() {
        Comment fromBase = commentService.findOne(1L);
        Assert.assertEquals(fromBase.getText(), "Text Comment");
        Assert.assertEquals(fromBase.getPatient().getName(), "Comm");
    }
}