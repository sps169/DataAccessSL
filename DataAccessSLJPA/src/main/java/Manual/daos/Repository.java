package Manual.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @OneToOne
    @JoinColumn(name = "id_project", nullable = false)
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", orphanRemoval = true)
    private Set<Issue> issues;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", orphanRemoval = true)
    private Set<Commit> commits;
}
