package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Project {
    private long id;
    private String name;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
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

    public Project(long id, String name, LocalDateTime start_date, LocalDateTime end_date, String technologies,
                   float annualBudget, String state, long bossId, long departmentId) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
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
        return id == project.id && Float.compare(project.annualBudget, annualBudget) == 0 && projectBossId == project.projectBossId && departmentId == project.departmentId && name.equals(project.name) && start_date.equals(project.start_date) && end_date.equals(project.end_date) && technologies.equals(project.technologies) && state.equals(project.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start_date, end_date, technologies, annualBudget, state, projectBossId, departmentId);
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

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
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
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", technologies='" + technologies + '\'' +
                ", annualBudget=" + annualBudget +
                ", state='" + state + '\'' +
                ", bossId=" + projectBossId +
                ", departmentId=" + departmentId +
                '}';
    }
}
