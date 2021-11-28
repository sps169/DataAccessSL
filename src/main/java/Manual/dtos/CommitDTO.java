package Manual.dtos;

import java.time.LocalDateTime;

public class CommitDTO {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private RepositoryDTO repository;
    private ProjectDTO project;
    private ProgrammerDTO programmer;
    private IssueDTO issue;

    public CommitDTO() {
    }

    public CommitDTO(long id, String title, String text, LocalDateTime date, RepositoryDTO repository, ProjectDTO project, ProgrammerDTO programmer, IssueDTO issue) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.repository = repository;
        this.project = project;
        this.programmer = programmer;
        this.issue = issue;
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

    public RepositoryDTO getRepository() {
        return repository;
    }

    public void setRepository(RepositoryDTO repository) {
        this.repository = repository;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public ProgrammerDTO getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerDTO programmer) {
        this.programmer = programmer;
    }

    public IssueDTO getIssue() {
        return issue;
    }

    public void setIssue(IssueDTO issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "CommitDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", repository=" + repository +
                ", project=" + project +
                ", programmer=" + programmer +
                ", issue=" + issue +
                '}';
    }
}
