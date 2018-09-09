package com.hospital.service;

import com.hospital.HospitalApplication;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.PatientRepository;
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
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    @DirtiesContext
    public void createPatient() {
        patientService.createPatient("Ivan", "Ivanov", "01-01-2001", 2L);
        Patient getPatient = patientService.findOne(4L);
        Assert.assertEquals(getPatient.getName(), "Ivan");
        Assert.assertEquals(getPatient.getSurname(), "Ivanov");
        Assert.assertFalse(getPatient.getDateBirth().equalsIgnoreCase("01-01-2002"));
    }

    @Test
    @DirtiesContext
    public void findOne() {
        Assert.assertTrue(patientService.findOne(1L).getName().equalsIgnoreCase("Test"));
    }

    @Test
    @DirtiesContext
    public void findAll() {
        Assert.assertTrue(patientService.findAll().size() > 0);
    }

    @Test
    @DirtiesContext
    public void patientUpdate() {
        Patient p1 = patientService.findOne(1L);
        Assert.assertEquals(p1.getName(), "Test");
        p1.setSurname("Petrov");
        patientService.add(p1);
        Patient p2 = patientService.findOne(1L);
        Assert.assertEquals(p2.getName(), "Test");
        Assert.assertEquals(p2.getSurname(), "Petrov");
    }
}