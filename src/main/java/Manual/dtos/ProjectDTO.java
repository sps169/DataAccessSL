package Manual.dtos;

import Manual.daos.Department;
import Manual.daos.Programmer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Set;

public class ProjectDTO {
    private long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<Technologies> technologies;
    private float annualBudget;
    private String state;
    private Programmer projectBoss;
    private Department department;

    public ProjectDTO(long id, String name, LocalDateTime startDate, LocalDateTime endDate,
                      Set<Technologies> technologiesToSet, float annualBudget, String state) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.annualBudget = annualBudget;
        this.state = state;
    }

    public ProjectDTO(long id, String name, LocalDateTime startDate, LocalDateTime endDate,
                      Set<Technologies> technologies, float annualBudget, String state,
                      Programmer projectBoss, Department department) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.annualBudget = annualBudget;
        this.state = state;
        this.projectBoss = projectBoss;
        this.department = department;
    }

    public ProjectDTO() {
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

    public Set<Technologies> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technologies> technologies) {
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

    public Programmer getProjectBoss() {
        return projectBoss;
    }

    public void setProjectBoss(Programmer projectBoss) {
        this.projectBoss = projectBoss;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", technologies=" + technologies +
                ", annualBudget=" + annualBudget +
                ", state='" + state + '\'' +
                ", projectBoss=" + projectBoss +
                ", department=" + department +
                '}';
    }
    public static ProjectDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, ProjectDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
