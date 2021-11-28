package Manual.dtos;

import java.time.LocalDateTime;
import java.util.Set;

public class ProgrammerDTO {
    private long id;
    private String name;
    private LocalDateTime entry_date;
    private String password;
    private Set<Technologies> technologies;
    private float salary;
    private DepartmentDTO department;
    private Set<ProjectAssignmentDTO> projects;
    private Set<CommitDTO> commits;
    private Set<IssueAssignmentDTO> issuesAssigned;

    public ProgrammerDTO(long id, String name, LocalDateTime entry_date, String password, Set<Technologies> technologies, float salary, DepartmentDTO department, Set<ProjectAssignmentDTO> projects, Set<CommitDTO> commits, Set<IssueAssignmentDTO> issuesAssigned) {
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

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public Set<Technologies> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technologies> technologies) {
        this.technologies = technologies;
    }

    public Set<ProjectAssignmentDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectAssignmentDTO> projects) {
        this.projects = projects;
    }

    public Set<CommitDTO> getCommits() {
        return commits;
    }

    public void setCommits(Set<CommitDTO> commits) {
        this.commits = commits;
    }

    public Set<IssueAssignmentDTO> getIssuesAssigned() {
        return issuesAssigned;
    }

    public void setIssuesAssigned(Set<IssueAssignmentDTO> issuesAssigned) {
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
}
