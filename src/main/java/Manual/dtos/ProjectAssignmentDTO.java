package Manual.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class ProjectAssignmentDTO {
    private ProgrammerDTO programmer;
    private ProjectDTO project;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ProjectAssignmentDTO(ProgrammerDTO programmer, ProjectDTO project, LocalDateTime startDate, LocalDateTime endDate) {
        this.programmer = programmer;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectAssignmentDTO() {
    }

    public ProgrammerDTO getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerDTO programmer) {
        this.programmer = programmer;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
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
