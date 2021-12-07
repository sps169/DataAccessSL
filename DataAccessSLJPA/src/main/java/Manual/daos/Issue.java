package Manual.daos;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "issue", orphanRemoval = true)
    private Set<IssueAssignment> assignments;

}
