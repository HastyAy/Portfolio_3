package THWS.Portfolio3.server.controller;

import THWS.Portfolio3.server.exception.ResourceNotFoundException;
import THWS.Portfolio3.server.model.Module;
import THWS.Portfolio3.server.states.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/universities/{universityId}/modules")
public class ModuleController {

    private final GetAllModules getAllModules;
    private final GetSingleModule getSingleModule;
    private final PostNewModule postNewModule;
    private final PutSingleModule putSingleModule;
    private final DeleteSingleModule deleteSingleModule;

    @Autowired
    public ModuleController(GetAllModules getAllModules,
                            GetSingleModule getSingleModule,
                            PostNewModule postNewModule,
                            PutSingleModule putSingleModule,
                            DeleteSingleModule deleteSingleModule) {
        this.getAllModules = getAllModules;
        this.getSingleModule = getSingleModule;
        this.postNewModule = postNewModule;
        this.putSingleModule = putSingleModule;
        this.deleteSingleModule = deleteSingleModule;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Module>>> getAllModules(@PathVariable Long universityId) {
        List<Module> modules = getAllModules.execute(universityId);
        List<EntityModel<Module>> moduleModels = modules.stream()
                .map(module -> EntityModel.of(module,
                        linkTo(methodOn(ModuleController.class).getModuleById(universityId, module.getId())).withSelfRel(),
                        linkTo(methodOn(ModuleController.class).getAllModules(universityId)).withRel("modules"),
                        linkTo(methodOn(UniversityController.class).getUniversityById(universityId)).withRel("university")
                ))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Module>> collectionModel = CollectionModel.of(moduleModels,
                linkTo(methodOn(ModuleController.class).getAllModules(universityId)).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Module>> getModuleById(@PathVariable Long universityId, @PathVariable Long id) {
        EntityModel<Module> module = getSingleModule.execute(universityId, id)
                .map(mod -> EntityModel.of(mod,
                        linkTo(methodOn(ModuleController.class).getModuleById(universityId, id)).withSelfRel(),
                        linkTo(methodOn(ModuleController.class).getAllModules(universityId)).withRel("modules"),
                        linkTo(methodOn(UniversityController.class).getUniversityById(universityId)).withRel("university")
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        return ResponseEntity.ok(module);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Module>> createModule(@PathVariable Long universityId, @RequestBody Module module) {
        EntityModel<Module> createdModule = postNewModule.execute(universityId, module);
        return ResponseEntity.created(linkTo(methodOn(ModuleController.class).getModuleById(universityId, createdModule.getContent().getId())).toUri())
                .body(createdModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Module>> updateModule(@PathVariable Long universityId, @PathVariable Long id, @RequestBody Module moduleDetails) {
        EntityModel<Module> updatedModule = putSingleModule.execute(universityId, id, moduleDetails);
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long universityId, @PathVariable Long id) {
        deleteSingleModule.execute(id);
        return ResponseEntity.noContent()
                .header("Link", linkTo(methodOn(ModuleController.class).getAllModules(universityId)).withRel("modules").toString())
                .build();
    }

}
