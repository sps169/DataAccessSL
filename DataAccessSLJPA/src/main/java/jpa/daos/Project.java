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
    private transient Programmer projectBoss;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id",nullable = false)
    private transient Department department;

    @Transient
    private long departmentId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project", orphanRemoval = true)
    private transient Repository repository;

    public Project(long id) {
        this.id = id;
    }

    public Project(long id, String name, LocalDateTime startDate, LocalDateTime endDate, String technologies, float annualBudget, String state, Programmer projectBoss, Department department, Repository repository) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.annualBudget = annualBudget;
        this.state = state;
        this.projectBoss = projectBoss;
        this.department = department;
        this.departmentId = department.getId();
        this.repository = repository;
    }

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
