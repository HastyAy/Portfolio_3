package THWS.Portfolio3.server.states.universities;

import THWS.Portfolio3.server.model.University;
import THWS.Portfolio3.server.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PutSingleUniversity {

    private final UniversityService universityService;
    private final RepresentationModelAssembler<University, EntityModel<University>> assembler;

    @Autowired
    public PutSingleUniversity(UniversityService universityService, RepresentationModelAssembler<University, EntityModel<University>> assembler) {
        this.universityService = universityService;
        this.assembler = assembler;
    }

    public EntityModel<University> execute(Long id, University universityDetails) {
        University updatedUniversity = universityService.updateUniversity(id, universityDetails);
        return assembler.toModel(updatedUniversity);
    }
}
