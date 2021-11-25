package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Project {
    private long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String technologies;
    private float annualBudget;
    private String state;
    private long projectBossId;
    private long departmentId;

    public Project() {
    }

    public Project(long id, long bossId, long departmentId) {
        this.id = id;
        this.projectBossId = bossId;
        this.departmentId = departmentId;
    }

    public Project(long id, String name, LocalDateTime startDate, LocalDateTime endDate, String technologies,
                   float annualBudget, String state, long bossId, long departmentId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.annualBudget = annualBudget;
        this.state = state;
        this.projectBossId = bossId;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Float.compare(project.annualBudget, annualBudget) == 0 && projectBossId == project.projectBossId && departmentId == project.departmentId && name.equals(project.name) && startDate.equals(project.startDate) && endDate.equals(project.endDate) && technologies.equals(project.technologies) && state.equals(project.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, technologies, annualBudget, state, projectBossId, departmentId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public float getAnnualBudget() {
        return annualBudget;
    }

    public void setAnnualBudget(float annualBudget) {
        this.annualBudget = annualBudget;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getProjectBossId() {
        return projectBossId;
    }

    public void setProjectBossId(long projectBossId) {
        this.projectBossId = projectBossId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", technologies='" + technologies + '\'' +
                ", annualBudget=" + annualBudget +
                ", state='" + state + '\'' +
                ", bossId=" + projectBossId +
                ", departmentId=" + departmentId +
                '}';
    }
}
