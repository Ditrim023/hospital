package com.hospital.service;

import com.hospital.HospitalApplication;
import com.hospital.model.HospitalUser;
import com.hospital.model.UserRole;
import com.hospital.repository.UserRoleRepository;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HospitalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class HospitalUserServiceTest {
    @Autowired
    private HospitalUserService hospitalUserService;
    @Autowired
    private UserRoleRepository userRoleRepository;


    @Test
    @DirtiesContext
    public void findOne() {
        HospitalUser hospitalUserFromBase = hospitalUserService.findOne(3L);
        Assert.assertEquals(hospitalUserFromBase.getName(), "DocTest");
    }

    @Test
    @DirtiesContext
    public void getDoctors() {
        List<HospitalUser> doctors = hospitalUserService.getDoctors();
        Assert.assertTrue(doctors.size() > 0);
    }


    @Test
    @DirtiesContext
    public void createDoctor() {
        HospitalUser doctor = new HospitalUser("John", "Smith", "user7", new BCryptPasswordEncoder(10).encode("123456"), "24-05-1995", "LOR");
        doctor.setRole(userRoleRepository.findOne(2L));
        hospitalUserService.add(doctor);
        HospitalUser hospitalUserFromBase = hospitalUserService.findOne(4L);
        Assert.assertEquals(hospitalUserFromBase.getName(), "John");
        Assert.assertEquals(hospitalUserFromBase.getLogin(), "user7");
    }

    @Test
    @DirtiesContext
    public void hospitalUserUpdate() {
        HospitalUser fromBase = hospitalUserService.findOne(3L);
        fromBase.setSurname("Check");
        hospitalUserService.add(fromBase);
        HospitalUser secondTry = hospitalUserService.findOne(3L);
        Assert.assertEquals(secondTry.getName(), "DocTest");
        Assert.assertEquals(secondTry.getSurname(), "Check");
    }


    @Test
    @DirtiesContext
    public void findAll() {
        List<HospitalUser> hospitalUsers = hospitalUserService.findAll();
        Assert.assertTrue(hospitalUsers.size() > 0);
    }

}