package THWS.Portfolio3.server.controller;

import THWS.Portfolio3.server.URLS;
import THWS.Portfolio3.server.assembler.UniversityModelAssembler;
import THWS.Portfolio3.server.model.University;
import THWS.Portfolio3.server.states.universities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final GetAllUniversities getAllUniversities;
    private final GetSingleUniversity getSingleUniversity;
    private final PostNewUniversity postNewUniversity;
    private final PutSingleUniversity putSingleUniversity;
    private final DeleteSingleUniversity deleteSingleUniversity;
    private final UniversityModelAssembler assembler;

    @Autowired
    public UniversityController(GetAllUniversities getAllUniversities,
                                GetSingleUniversity getSingleUniversity,
                                PostNewUniversity postNewUniversity,
                                PutSingleUniversity putSingleUniversity,
                                DeleteSingleUniversity deleteSingleUniversity,
                                UniversityModelAssembler assembler) {
        this.getAllUniversities = getAllUniversities;
        this.getSingleUniversity = getSingleUniversity;
        this.postNewUniversity = postNewUniversity;
        this.putSingleUniversity = putSingleUniversity;
        this.deleteSingleUniversity = deleteSingleUniversity;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<University>>> getAllUniversities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortOrder = Sort.by(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);

        Page<University> universityPage = getAllUniversities.execute(pageable);

        PagedModel<EntityModel<University>> pagedModel = assembler.toPagedModel(
                universityPage,
                linkTo(methodOn(UniversityController.class).getAllUniversities(page, size, sort)).withSelfRel()
        );

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<University>> getUniversityById(@PathVariable Long id) {
        EntityModel<University> universityModel = getSingleUniversity.execute(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).getAllUniversities(0, 10, new String[]{"name", "asc"})).withRel("allUniversities").toString());
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).updateUniversity(id, null)).withRel("updateUniversity").toString());
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).deleteUniversity(id)).withRel("deleteUniversity").toString());
        return new ResponseEntity<>(universityModel, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<University>> createUniversity(@RequestBody University university) {
        EntityModel<University> universityModel = postNewUniversity.execute(university);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).getAllUniversities(0, 10, new String[]{"name", "asc"})).withRel("allUniversities").toString());
        return new ResponseEntity<>(universityModel, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<University>> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        EntityModel<University> universityModel = putSingleUniversity.execute(id, university);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).getAllUniversities(0, 10, new String[]{"name", "asc"})).withRel("allUniversities").toString());
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).getUniversityById(id)).withRel("getUniversityById").toString());
        return new ResponseEntity<>(universityModel, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        deleteSingleUniversity.execute(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LINK, linkTo(methodOn(UniversityController.class).getAllUniversities(0, 10, new String[]{"name", "asc"})).withRel("allUniversities").toString());
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
