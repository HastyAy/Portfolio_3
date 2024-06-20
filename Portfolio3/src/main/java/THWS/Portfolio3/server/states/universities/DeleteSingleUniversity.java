package THWS.Portfolio3.server.states.universities;

import THWS.Portfolio3.server.service.UniversityService;
import org.springframework.stereotype.Service;

@Service
public class DeleteSingleUniversity {

    private final UniversityService universityService;

    public DeleteSingleUniversity(UniversityService universityService) {
        this.universityService = universityService;
    }

    public void execute(Long id) {
        universityService.deleteUniversity(id);
    }
}
