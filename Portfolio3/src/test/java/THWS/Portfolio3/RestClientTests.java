package THWS.Portfolio3;

import THWS.Portfolio3.client.models.UniversityClientModel;
import THWS.Portfolio3.client.models.ModuleClientModel;
import THWS.Portfolio3.client.RestClient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestClientTests {

    @Autowired
    private RestClient restClient;

    private Long createdUniversityId;
    private Long createdModuleId;

    @BeforeEach
    public void setUp() {
        createdUniversityId = null;
        createdModuleId = null;

        UniversityClientModel university = new UniversityClientModel();
        university.setName("Test University");
        university.setCountry("Test Country");
        university.setDepartmentName("Test Department");
        university.setWebsiteUrl("http://testuniversity.com");
        university.setContactPerson("Test Person");
        university.setOutgoingStudents(10);
        university.setIncomingStudents(15);
        university.setNextSpringSemester(LocalDate.of(2024, 3, 1));
        university.setNextAutumnSemester(LocalDate.of(2024, 9, 1));

        EntityModel<UniversityClientModel> createdUniversity = restClient.createUniversity(university);
        assertNotNull(createdUniversity);
        assertNotNull(createdUniversity.getContent());
        createdUniversityId = createdUniversity.getContent().getId();
    }

    @AfterEach
    public void tearDown() {
        if (createdModuleId != null) {
            restClient.deleteModule(createdUniversityId, createdModuleId);
        }
        if (createdUniversityId != null) {
            restClient.deleteUniversity(createdUniversityId);
        }
    }

    @Test
    public void testCreateUniversity() {
        UniversityClientModel university = new UniversityClientModel();
        university.setName("Another Test University");
        university.setCountry("Another Test Country");
        university.setDepartmentName("Another Test Department");
        university.setWebsiteUrl("http://anothertestuniversity.com");
        university.setContactPerson("Another Test Person");
        university.setOutgoingStudents(10);
        university.setIncomingStudents(15);
        university.setNextSpringSemester(LocalDate.of(2024, 3, 1));
        university.setNextAutumnSemester(LocalDate.of(2024, 9, 1));

        EntityModel<UniversityClientModel> createdUniversity = restClient.createUniversity(university);
        assertNotNull(createdUniversity);
        assertNotNull(createdUniversity.getContent());
        assertNotNull(createdUniversity.getContent().getId());

        restClient.deleteUniversity(createdUniversity.getContent().getId());
    }

    @Test
    public void testGetAllUniversities() {
        Pageable pageable = PageRequest.of(0, 10);
        PagedModel<EntityModel<UniversityClientModel>> universities = restClient.getAllUniversities(pageable);
        assertNotNull(universities);
        assertFalse(universities.getContent().isEmpty());
    }

    @Test
    public void testGetUniversityById() {
        EntityModel<UniversityClientModel> fetchedUniversity = restClient.getUniversityById(createdUniversityId);
        assertNotNull(fetchedUniversity);
        assertNotNull(fetchedUniversity.getContent());
    }

    @Test
    public void testUpdateUniversity() {
        UniversityClientModel updatedUniversityDetails = new UniversityClientModel();
        updatedUniversityDetails.setName("Updated Test University");
        updatedUniversityDetails.setCountry("Updated Country");
        updatedUniversityDetails.setDepartmentName("Updated Department");
        updatedUniversityDetails.setWebsiteUrl("http://updateduniversity.com");
        updatedUniversityDetails.setContactPerson("Updated Person");
        updatedUniversityDetails.setOutgoingStudents(20);
        updatedUniversityDetails.setIncomingStudents(25);
        updatedUniversityDetails.setNextSpringSemester(LocalDate.of(2024, 4, 1));
        updatedUniversityDetails.setNextAutumnSemester(LocalDate.of(2024, 10, 1));

        EntityModel<UniversityClientModel> updatedUniversity = restClient.updateUniversity(createdUniversityId, updatedUniversityDetails);
        assertNotNull(updatedUniversity);
        assertNotNull(updatedUniversity.getContent());
    }

    @Test
    public void testDeleteUniversity() {
        restClient.deleteUniversity(createdUniversityId);
        createdUniversityId = null; // Indicate that the university has been deleted
    }

    @Test
    public void testCreateModule() {
        ModuleClientModel module = new ModuleClientModel();
        module.setName("Test Module");
        module.setModuleName("TM101");
        module.setSemester(1);
        module.setSemesterOffered(2);
        module.setCreditPoints(5);

        EntityModel<ModuleClientModel> createdModule = restClient.createModule(createdUniversityId, module);
        assertNotNull(createdModule);
        assertNotNull(createdModule.getContent());
        assertNotNull(createdModule.getContent().getId());
        createdModuleId = createdModule.getContent().getId();
    }

    @Test
    public void testGetAllModules() {
        ModuleClientModel module = new ModuleClientModel();
        module.setName("Test Module");
        module.setModuleName("TM101");
        module.setSemester(1);
        module.setSemesterOffered(2);
        module.setCreditPoints(5);

        EntityModel<ModuleClientModel> createdModule = restClient.createModule(createdUniversityId, module);
        assertNotNull(createdModule);
        assertNotNull(createdModule.getContent());
        createdModuleId = createdModule.getContent().getId();

        PagedModel<EntityModel<ModuleClientModel>> modules = restClient.getAllModules(createdUniversityId);
        assertNotNull(modules);
        assertFalse(modules.getContent().isEmpty());
    }

    @Test
    public void testGetModuleById() {
        ModuleClientModel module = new ModuleClientModel();
        module.setName("Test Module");
        module.setModuleName("TM101");
        module.setSemester(1);
        module.setSemesterOffered(2);
        module.setCreditPoints(5);

        EntityModel<ModuleClientModel> createdModule = restClient.createModule(createdUniversityId, module);
        assertNotNull(createdModule);
        assertNotNull(createdModule.getContent());
        createdModuleId = createdModule.getContent().getId();

        EntityModel<ModuleClientModel> retrievedModule = restClient.getModuleById(createdUniversityId, createdModuleId);
        assertNotNull(retrievedModule);
        assertNotNull(retrievedModule.getContent());
        assertEquals(createdModuleId, retrievedModule.getContent().getId());
    }

    @Test
    public void testUpdateModule() {
        ModuleClientModel module = new ModuleClientModel();
        module.setName("Test Module");
        module.setModuleName("TM101");
        module.setSemester(1);
        module.setSemesterOffered(2);
        module.setCreditPoints(5);

        EntityModel<ModuleClientModel> createdModule = restClient.createModule(createdUniversityId, module);
        assertNotNull(createdModule);
        assertNotNull(createdModule.getContent());
        createdModuleId = createdModule.getContent().getId();

        module.setName("Updated Module");
        EntityModel<ModuleClientModel> updatedModule = restClient.updateModule(createdUniversityId, createdModuleId, module);
        assertNotNull(updatedModule);
        assertNotNull(updatedModule.getContent());
        assertEquals("Updated Module", updatedModule.getContent().getName());
    }

    @Test
    public void testDeleteModule() {
        ModuleClientModel module = new ModuleClientModel();
        module.setName("Test Module");
        module.setModuleName("TM101");
        module.setSemester(1);
        module.setSemesterOffered(2);
        module.setCreditPoints(5);

        EntityModel<ModuleClientModel> createdModule = restClient.createModule(createdUniversityId, module);
        assertNotNull(createdModule);
        assertNotNull(createdModule.getContent());
        createdModuleId = createdModule.getContent().getId();

        restClient.deleteModule(createdUniversityId, createdModuleId);
        createdModuleId = null; // Indicate that the module has been deleted

        assertThrows(Exception.class, () -> {
            restClient.getModuleById(createdUniversityId, createdModuleId);
        });
    }
}
