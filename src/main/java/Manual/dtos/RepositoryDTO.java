package Manual.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Set;

public class RepositoryDTO {
    private long id;
    private String name;
    private LocalDateTime creationDate;
    private ProjectDTO project;
    private Set<CommitDTO> commits;
    private Set<IssueDTO> issues;

    public RepositoryDTO(long id, String name, LocalDateTime creationDate, ProjectDTO project, Set<CommitDTO> commits, Set<IssueDTO> issues) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.project = project;
        this.commits = commits;
        this.issues = issues;
    }

    public RepositoryDTO() {
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

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public Set<CommitDTO> getCommits() {
        return commits;
    }

    public void setCommits(Set<CommitDTO> commits) {
        this.commits = commits;
    }

    public Set<IssueDTO> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueDTO> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "RepositoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", project=" + project +
                ", commits=" + commits +
                ", issues=" + issues +
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
