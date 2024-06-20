package THWS.Portfolio3.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class University extends RepresentationModel<University> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String departmentName;
    private String websiteUrl;
    private String contactPerson;
    private int outgoingStudents;
    private int incomingStudents;
    private LocalDate nextSpringSemester;
    private LocalDate nextAutumnSemester;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Module> modules = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public int getOutgoingStudents() { return outgoingStudents; }
    public void setOutgoingStudents(int outgoingStudents) { this.outgoingStudents = outgoingStudents; }
    public int getIncomingStudents() { return incomingStudents; }
    public void setIncomingStudents(int incomingStudents) { this.incomingStudents = incomingStudents; }
    public LocalDate getNextSpringSemester() { return nextSpringSemester; }
    public void setNextSpringSemester(LocalDate nextSpringSemester) { this.nextSpringSemester = nextSpringSemester; }
    public LocalDate getNextAutumnSemester() { return nextAutumnSemester; }
    public void setNextAutumnSemester(LocalDate nextAutumnSemester) { this.nextAutumnSemester = nextAutumnSemester; }
    public List<Module> getModules() { return modules; }
    public void setModules(List<Module> modules) { this.modules = modules; }

    public void addModule(Module module) {
        modules.add(module);
        module.setUniversity(this);
    }

    public void removeModule(Module module) {
        modules.remove(module);
        module.setUniversity(null);
    }
}

