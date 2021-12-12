package jpa.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repository")
@NamedQuery(name = "Repository.findAll", query = "SELECT b FROM Repository b")
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @OneToOne
    @JoinColumn(name = "id_project", nullable = false)
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", orphanRemoval = true)
    private Set<Issue> issues;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", orphanRemoval = true)
    private Set<Commit> commits;

    public Repository(long id, String name, LocalDateTime creationDate, Project project) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return id == that.id && name.equals(that.name) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", issues=" + issues +
                ", commits=" + commits +
                '}';
    }
}
