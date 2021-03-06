package jpa.daos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class that models the POJO DAO of the Commit table
 * with JPA and Lombok marks
 * @author sps169, FedericoTB
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Commit.findAll", query = "SELECT b FROM Commit b")
@Table(name = "commit")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(nullable = false)
    private String title;

    @Basic
    @Column(nullable = false)
    private String text;

    @Basic
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "id", nullable = false)
    private transient Repository repository;

    @Transient
    private long repositoryId;

    @ManyToOne
    @JoinColumn(name = "id_programmer", referencedColumnName = "id", nullable = false)
    private transient Programmer programmer;

    @Transient
    private long programmerId;

    @ManyToOne
    @JoinColumn(name = "id_issue", referencedColumnName = "id", nullable = false)
    private transient Issue issue;

    @Transient
    private long issueId;

    public Commit(long id, String title, String text, LocalDateTime date, Repository repository, Programmer programmer, Issue issue) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.repository = repository;
        this.repositoryId = repository.getId();
        this.programmer = programmer;
        this.programmerId = programmer.getId();
        this.issue = issue;
        this.issueId = issue.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return id == commit.id && title.equals(commit.title) && text.equals(commit.text) && date.equals(commit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date);
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
