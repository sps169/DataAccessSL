package jpa.daos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class that models the POJO DAO of the ProjectAssignment table
 * with JPA and Lombok marks
 * @author sps169, FedericoTB
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_assignment")
@NamedQuery(name = "ProjectAssignment.findAll", query = "SELECT b FROM ProjectAssignment b")
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_programmer",referencedColumnName = "id", nullable = false)
    private Programmer programmer;

    @ManyToOne
    @JoinColumn(name = "id_project",referencedColumnName = "id", nullable = false)
    private Project project;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectAssignment that = (ProjectAssignment) o;
        return id == that.id && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }

    @Override
    public String toString() {
        return "ProjectAssignment{" +
                "id=" + id +
                ", programmer=" + programmer +
                ", project=" + project +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
