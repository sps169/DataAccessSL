package Manual.daos;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "commit")
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "id", nullable = false)
    private Repository repository;

    @ManyToOne
    @JoinColumn(name = "id_programmer", referencedColumnName = "id", nullable = false)
    private Programmer programmer;

    @ManyToOne
    @JoinColumn(name = "id_issue", referencedColumnName = "id", nullable = false)
    private Issue issue;

    public Commit() {
    }

    public Commit(long id, Repository repository, Programmer programmer, Issue issue) {
        this.id = id;
        this.repository = repository;
        this.programmer = programmer;
        this.issue = issue;
    }

    public Commit(long id, String title, String text, LocalDateTime date, Repository repository, Programmer programmer, Issue issue) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.repository = repository;
        this.programmer = programmer;
        this.issue = issue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return id == commit.id && repository.equals(commit.repository) && programmer.equals(commit.programmer) && issue.equals(commit.issue) && title.equals(commit.title) && text.equals(commit.text) && date.equals(commit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date, repository, programmer, issue);
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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repositoryId) {
        this.repository = repositoryId;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmerId) {
        this.programmer = programmerId;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", repository=" + repository +
                ", programmer=" + programmer +
                ", issue=" + issue +
                '}';
    }
}
