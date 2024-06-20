package THWS.Portfolio3.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;

@Entity
public class Module extends RepresentationModel<Module> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String moduleName;
    private int semester;
    private int semesterOffered;
    private int creditPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    @JsonBackReference
    private University university;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public int getSemesterOffered() { return semesterOffered; }
    public void setSemesterOffered(int semesterOffered) { this.semesterOffered = semesterOffered; }

    public int getCreditPoints() { return creditPoints; }
    public void setCreditPoints(int creditPoints) { this.creditPoints = creditPoints; }

    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }
}
