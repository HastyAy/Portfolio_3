package THWS.Portfolio3.server.service;

import THWS.Portfolio3.server.model.University;
import THWS.Portfolio3.server.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    public Optional<University> getUniversityById(Long id) {
        return universityRepository.findById(id);
    }

    /*public Page<University> getAllUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }*/

    public University updateUniversity(Long id, University universityDetails) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));

        university.setName(universityDetails.getName());
        university.setCountry(universityDetails.getCountry());
        university.setDepartmentName(universityDetails.getDepartmentName());
        university.setWebsiteUrl(universityDetails.getWebsiteUrl());
        university.setContactPerson(universityDetails.getContactPerson());
        university.setOutgoingStudents(universityDetails.getOutgoingStudents());
        university.setIncomingStudents(universityDetails.getIncomingStudents());

        return universityRepository.save(university);
    }

    public void deleteUniversity(Long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
        universityRepository.delete(university);
    }

}
