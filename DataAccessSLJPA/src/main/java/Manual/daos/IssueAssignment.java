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
@Table(name = "issue_assignment")
@NamedQuery(name = "IssueAssignment.findAll", query = "SELECT b FROM IssueAssignment b")
public class IssueAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_programmer",referencedColumnName = "id", nullable = false)
    private Programmer programmer;
    @ManyToOne
    @JoinColumn(name = "id_issue",referencedColumnName = "id",nullable = false)
    private Issue issue;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;
}
