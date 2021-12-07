package Manual.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programmer")
public class Programmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime entry_date;

    @Column(nullable = false)
    @ColumnTransformer(write = "SHA256(?)")
    private String password;

    @Column(nullable = false)
    private String technologies;

    @Column(nullable = false)
    private float salary;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id", nullable = false)
    private Department department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", orphanRemoval = true)
    private Set<IssueAssignment> issueAssignmentList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", orphanRemoval = true)
    private Set<ProjectAssignment> projectAssignmentList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", orphanRemoval = true)
    private Set<Commit> commits;
}
