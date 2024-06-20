package THWS.Portfolio3.server.assembler;

import THWS.Portfolio3.server.controller.UniversityController;
import THWS.Portfolio3.server.model.University;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UniversityModelAssembler implements RepresentationModelAssembler<University, EntityModel<University>> {

    @Override
    public EntityModel<University> toModel(University university) {
        return EntityModel.of(university,
                linkTo(methodOn(UniversityController.class).getUniversityById(university.getId())).withSelfRel(),
                linkTo(methodOn(UniversityController.class).getAllUniversities(0, 10, new String[]{"name", "asc"})).withRel("universities"));
    }

    @Override
    public CollectionModel<EntityModel<University>> toCollectionModel(Iterable<? extends University> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(UniversityController.class).getAllUniversities(0, 10, new String[]{"name", "asc"})).withSelfRel());
    }

    public PagedModel<EntityModel<University>> toPagedModel(Page<University> page, Link selfLink) {
        return PagedModel.of(
                page.map(this::toModel).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()),
                selfLink
        );
    }
}
