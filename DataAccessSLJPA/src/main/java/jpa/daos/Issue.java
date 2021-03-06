package jpa.daos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
/**
 * Class that models the POJO DAO of the Issue table
 * with JPA and Lombok marks
 * @author sps169, FedericoTB
 */
@Getter
@Setter
@Entity
@Table(name = "issue")
@NamedQuery(name = "Issue.findAll", query = "SELECT b FROM Issue b")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column(nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_repository",referencedColumnName = "id", nullable = false)
    private transient Repository repository;

    @Transient
    private long repositoryId;

    @ManyToOne
    @JoinColumn(name = "id_boss",referencedColumnName="id", nullable = false)
    private transient Programmer boss;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "issue", orphanRemoval = true)
    private transient Set<IssueAssignment> assignments;

    public Issue(long id, String title, String text, LocalDateTime date, String state, Repository repository, Programmer boss) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.state = state;
        this.repository = repository;
        this.repositoryId = repository.getId();
        this.boss = boss;
    }

    public Issue(long id) {
        this.id = id;
    }

    public Issue() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && title.equals(issue.title) && text.equals(issue.text) && date.equals(issue.date) && state.equals(issue.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date, state);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", boss=" + boss +
                ", assignments=" + assignments +
                '}';
    }
}
