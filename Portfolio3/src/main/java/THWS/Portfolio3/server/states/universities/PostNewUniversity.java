package THWS.Portfolio3.server.states.universities;

import THWS.Portfolio3.server.model.University;
import THWS.Portfolio3.server.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PostNewUniversity {

    private final UniversityService universityService;
    private final RepresentationModelAssembler<University, EntityModel<University>> assembler;

    @Autowired
    public PostNewUniversity(UniversityService universityService, RepresentationModelAssembler<University, EntityModel<University>> assembler) {
        this.universityService = universityService;
        this.assembler = assembler;
    }

    public EntityModel<University> execute(University university) {
        University createdUniversity = universityService.createUniversity(university);
        return assembler.toModel(createdUniversity);
    }
}
