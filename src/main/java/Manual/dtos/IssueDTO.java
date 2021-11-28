package Manual.dtos;

import java.time.LocalDateTime;
import java.util.Set;

public class IssueDTO {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private String state;
    private ProjectDTO project;
    private RepositoryDTO repository;
    private ProgrammerDTO boss;
    private Set<IssueAssignmentDTO> programmers;

    public IssueDTO(long id, String title, String text, LocalDateTime date, String state, ProjectDTO project, RepositoryDTO repository, ProgrammerDTO boss, Set<IssueAssignmentDTO> programmers) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.state = state;
        this.project = project;
        this.repository = repository;
        this.boss = boss;
        this.programmers = programmers;
    }

    public IssueDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public RepositoryDTO getRepository() {
        return repository;
    }

    public void setRepository(RepositoryDTO repository) {
        this.repository = repository;
    }

    public ProgrammerDTO getBoss() {
        return boss;
    }

    public void setBoss(ProgrammerDTO boss) {
        this.boss = boss;
    }

    public Set<IssueAssignmentDTO> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(Set<IssueAssignmentDTO> programmers) {
        this.programmers = programmers;
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", project=" + project +
                ", repository=" + repository +
                ", boss=" + boss +
                ", programmers=" + programmers +
                '}';
    }
}
