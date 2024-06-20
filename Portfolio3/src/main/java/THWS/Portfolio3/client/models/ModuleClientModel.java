package THWS.Portfolio3.client.models;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class ModuleClientModel extends RepresentationModel<ModuleClientModel> {
    private Long id;
    private String name;
    private String moduleName;
    private int semester;
    private int semesterOffered;
    private int creditPoints;

    // Getters and Setters
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getSemesterOffered() {
        return semesterOffered;
    }

    public void setSemesterOffered(int semesterOffered) {
        this.semesterOffered = semesterOffered;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }


    public Link getSelfLink() {
        return getLink("self").orElse(null);
    }
}
