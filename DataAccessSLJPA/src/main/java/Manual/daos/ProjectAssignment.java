package Manual.daos;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "project_assignment")
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    public ProjectAssignment() {
    }

    public ProjectAssignment(long id, Programmer programmer, Project project, LocalDateTime startDate) {
        this.id = id;
        this.programmer = programmer;
        this.project = project;
        this.startDate = startDate;
    }

    public ProjectAssignment(long id, Programmer programmer, Project project, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.programmer = programmer;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectAssignment that = (ProjectAssignment) o;
        boolean result = id == that.id && programmer.equals(that.programmer)
                && project.equals(that.project)  && startDate.equals(that.startDate);
        if (endDate == null && that.endDate == null)
            return result;
        else if (endDate != null && that.endDate != null)
            return result;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmer, project, startDate, endDate);
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(long programmerId) {
        this.programmer = programmer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
