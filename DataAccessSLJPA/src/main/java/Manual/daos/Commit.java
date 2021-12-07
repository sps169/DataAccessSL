package Manual.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commit")
@NamedQuery(name = "Commit.findAll", query = "SELECT b FROM Commit b")
public class Commit {
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

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "id", nullable = false)
    private Repository repository;

    @ManyToOne
    @JoinColumn(name = "id_programmer", referencedColumnName = "id", nullable = false)
    private Programmer programmer;

    @ManyToOne
    @JoinColumn(name = "id_issue", referencedColumnName = "id", nullable = false)
    private Issue issue;

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
