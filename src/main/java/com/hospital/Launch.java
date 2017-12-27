package com.hospital;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nikita Krutoguz
 */

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Launch {

    public static DoctorRepository doctorRepository;

    public static Doctor max() {
        Doctor doctor = doctorRepository.findOne(1L);
        return doctor;
    }

    public static void main(String[] args) {

        System.out.println(max().getName());
    }
}
