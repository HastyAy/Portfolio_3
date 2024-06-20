package THWS.Portfolio3.server.assembler;

import THWS.Portfolio3.server.controller.ModuleController;
import THWS.Portfolio3.server.model.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ModuleModelAssembler extends RepresentationModelAssemblerSupport<Module, EntityModel<Module>> {

    public ModuleModelAssembler() {
        super(ModuleController.class, (Class<EntityModel<Module>>) (Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<Module> toModel(Module module) {
        return EntityModel.of(module,
                linkTo(methodOn(ModuleController.class).getModuleById(module.getUniversity().getId(), module.getId())).withSelfRel(),
                linkTo(methodOn(ModuleController.class).getAllModules(module.getUniversity().getId())).withRel("modules"));
    }

    public PagedModel<EntityModel<Module>> toPagedModel(Page<Module> modules, Long universityId) {
        return PagedModel.of(modules.stream().map(this::toModel).collect(Collectors.toList()),
                new PagedModel.PageMetadata(modules.getSize(), modules.getNumber(), modules.getTotalElements()),
                linkTo(methodOn(ModuleController.class).getAllModules(universityId)).withSelfRel());
    }
}
