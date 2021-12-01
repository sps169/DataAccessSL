package Manual.dtos;

import Manual.daos.Commit;
import Manual.daos.Issue;
import Manual.daos.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Set;

public class RepositoryDTO {
    private long id;
    private String name;
    private LocalDateTime creationDate;
    private Project project;
    private Set<Issue> issues;
    private Set<Commit> commits;

    public RepositoryDTO(long id, String name, LocalDateTime creationDate, Project project, Set<Issue> issues, Set<Commit> commits) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.project = project;
        this.issues = issues;
        this.commits = commits;
    }

    public RepositoryDTO() {
    }

    public RepositoryDTO(long id, String name, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Set<Commit> getCommits() {return commits;}

    public void setCommits(Set<Commit> commits) {this.commits = commits;}

    @Override
    public String toString() {
        return "RepositoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", project=" + project +
                ", issues=" + issues +
                ", commits=" + commits +
                '}';
    }
    public static RepositoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, RepositoryDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
