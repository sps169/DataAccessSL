package jpa.daos;

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
@NamedQuery(name = "Project.findAll", query = "SELECT b FROM Project b")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String technologies;

    @Column(name = "annual_budget", nullable = false)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Float.compare(project.annualBudget, annualBudget) == 0 && name.equals(project.name) && startDate.equals(project.startDate) && endDate.equals(project.endDate) && technologies.equals(project.technologies) && state.equals(project.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, technologies, annualBudget, state);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", technologies='" + technologies + '\'' +
                ", annualBudget=" + annualBudget +
                ", state='" + state + '\'' +
                ", projectBoss=" + projectBoss +
                ", repository=" + repository +
                '}';
    }
}
