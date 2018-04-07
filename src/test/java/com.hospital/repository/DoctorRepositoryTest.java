package com.hospital.repository;

import com.hospital.HospitalApplication;
import com.hospital.model.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.print.Doc;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Nikita Krutoguz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HospitalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorRepositoryTest {


    @Autowired
    private DoctorRepository doctorRepository;


    @Test
    public void notNull() {
        List<Doctor> finDocs = doctorRepository.findAll();
        assertTrue(finDocs.size() > 0);
    }

    @Test
    public void findByNameTest() {
        Doctor found = doctorRepository.findByName("Petr");
        assertTrue(found.getSurname().equalsIgnoreCase("Terapevt"));
        assertTrue(found.getAge() == 42);
    }

    @Test
    public void saveDoc() {
        Doctor createDoc = new Doctor("Kolya", "Anesteziolog", 35);
        doctorRepository.save(createDoc);
        Doctor found = doctorRepository.findByName("Kolya");
        assertTrue(found.getAge() == 35);
    }
}
