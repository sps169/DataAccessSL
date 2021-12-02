package Manual.daos;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "issue")
public class Issue {

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

    @Column(nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_repository",referencedColumnName = "id", nullable = false)
    private Repository repository;

    @ManyToOne
    @JoinColumn(name = "id_boss",referencedColumnName="id", nullable = false)
    private Programmer boss;

    public Issue() {
    }

    public Issue(long id, Repository repository, Programmer boss) {
        this.id = id;
        this.repository = repository;
        this.boss = boss;
    }

    public Issue(long id, String title, String text, LocalDateTime date, String state, Repository repository, Programmer boss) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.state = state;
        this.repository = repository;
        this.boss = boss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                repository.equals(issue.repository) &&
                boss.equals(issue.boss) &&
                title.equals(issue.title) &&
                text.equals(issue.text) &&
                date.equals(issue.date) &&
                state.equals(issue.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date, state, repository, boss);
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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", repository=" + repository +
                ", boss=" + boss +
                '}';
    }
}
