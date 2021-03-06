package jpa.dtos;

import jpa.daos.Department;
import jpa.daos.Programmer;
import jpa.daos.Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Set;
/**
 * Class that models the POJO DTO of the Project table
 * with ToJason FromJason methods
 * @author sps169, FedericoTB
 */
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
    private Repository repository;

    public ProjectDTO(long id, String name, LocalDateTime startDate, LocalDateTime endDate,
                      Set<Technologies> technologies, float annualBudget, String state,
                      Programmer projectBoss, Department department, Repository repository) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.annualBudget = annualBudget;
        this.state = state;
        this.projectBoss = projectBoss;
        this.department = department;
        this.repository = repository;
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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
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
                ", repository=" + repository +
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
