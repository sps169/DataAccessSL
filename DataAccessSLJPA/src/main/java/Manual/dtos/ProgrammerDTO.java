package Manual.dtos;

import Manual.daos.*;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Set;

public class ProgrammerDTO {
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password");
        }
    };
    private long id;
    private String name;
    private LocalDateTime entry_date;
    private String password;
    private Set<Technologies> technologies;
    private float salary;
    private Department department;
    private Set<Project> projects;
    private Set<Commit> commits;
    private Set<Issue> issuesAssigned;

    public ProgrammerDTO(long id, String name, LocalDateTime entry_date, String password,
                         Set<Technologies> technologies, float salary, Department department,
                         Set<Project> projects, Set<Commit> commits,
                         Set<Issue> issuesAssigned) {
        this.id = id;
        this.name = name;
        this.entry_date = entry_date;
        this.password = password;
        this.technologies = technologies;
        this.salary = salary;
        this.department = department;
        this.projects = projects;
        this.commits = commits;
        this.issuesAssigned = issuesAssigned;
    }

    public ProgrammerDTO() {
    }

    public ProgrammerDTO(long id, String name, LocalDateTime entry_date, String password, Set<Technologies> technologies, float salary) {
        this.id = id;
        this.name = name;
        this.entry_date = entry_date;
        this.password = password;
        this.technologies = technologies;
        this.salary = salary;
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

    public LocalDateTime getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDateTime entry_date) {
        this.entry_date = entry_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Technologies> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technologies> technologies) {
        this.technologies = technologies;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }

    public Set<Issue> getIssuesAssigned() {
        return issuesAssigned;
    }

    public void setIssuesAssigned(Set<Issue> issuesAssigned) {
        this.issuesAssigned = issuesAssigned;
    }

    @Override
    public String toString() {
        return "ProgrammerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entry_date=" + entry_date +
                ", technologies=" + technologies +
                ", salary=" + salary +
                ", department=" + department +
                ", projects=" + projects +
                ", commits=" + commits +
                ", issuesAssigned=" + issuesAssigned +
                '}';
    }
    public static ProgrammerDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, ProgrammerDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(this);
    }
}
