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
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String technologies;

    @Column(nullable = false)
    private float annualBudget;

    @Column(nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_boss",referencedColumnName = "id", nullable = false)
    private Programmer projectBoss;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id",nullable = false)
    private Department department;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "project", orphanRemoval = true)
    private Repository repository;

}
