package THWS.Portfolio3.server.repository;

import THWS.Portfolio3.server.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByUniversityId(Long universityId);
    Optional<Module> findByIdAndUniversityId(Long moduleId, Long universityId);
}

