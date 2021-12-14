package Manual.dtos;

import Manual.daos.Programmer;
import Manual.daos.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
/**
 * Class that models the POJO DTO of the ProjectAssignment table
 * with ToJason FromJason methods
 * @author sps169, FedericoTB
 */
public class ProjectAssignmentDTO {
    private long id;
    private Programmer programmer;
    private Project project;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ProjectAssignmentDTO(long id, Programmer programmer, Project project, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.programmer = programmer;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectAssignmentDTO() {
    }

    public ProjectAssignmentDTO(long id, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ProjectAssignmentDTO{" +
                "programmer=" + programmer +
                ", project=" + project +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
    public static ProjectAssignmentDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, ProjectAssignmentDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
