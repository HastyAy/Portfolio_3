package THWS.Portfolio3.server.states.universities;

import THWS.Portfolio3.server.model.University;
import THWS.Portfolio3.server.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetAllUniversities {

    private final UniversityRepository universityRepository;

    @Autowired
    public GetAllUniversities(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public Page<University> execute(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }
}
