package THWS.Portfolio3.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversityClientModel extends RepresentationModel<UniversityClientModel> {
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
    private List<ModuleClientModel> modules = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getOutgoingStudents() {
        return outgoingStudents;
    }

    public void setOutgoingStudents(int outgoingStudents) {
        this.outgoingStudents = outgoingStudents;
    }

    public int getIncomingStudents() {
        return incomingStudents;
    }

    public void setIncomingStudents(int incomingStudents) {
        this.incomingStudents = incomingStudents;
    }

    public LocalDate getNextSpringSemester() {
        return nextSpringSemester;
    }

    public void setNextSpringSemester(LocalDate nextSpringSemester) {
        this.nextSpringSemester = nextSpringSemester;
    }

    public LocalDate getNextAutumnSemester() {
        return nextAutumnSemester;
    }

    public void setNextAutumnSemester(LocalDate nextAutumnSemester) {
        this.nextAutumnSemester = nextAutumnSemester;
    }

    public List<ModuleClientModel> getModules() {
        return modules;
    }

    public void setModules(List<ModuleClientModel> modules) {
        this.modules = modules;
    }

    @JsonIgnore
    public Optional<Link> getSelfLink() {
        return getLink("self");
    }
}
