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

    @Column(name = "id_programmer", nullable = false)
    private long programmerId;

    @Column(name = "id_project", nullable = false)
    private long projectId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    public ProjectAssignment() {
    }

    public ProjectAssignment(long id, long programmerId, long projectId, LocalDateTime startDate) {
        this.id = id;
        this.programmerId = programmerId;
        this.projectId = projectId;
        this.startDate = startDate;
    }

    public ProjectAssignment(long id, long programmerId, long projectId, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.programmerId = programmerId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectAssignment that = (ProjectAssignment) o;
        boolean result = id == that.id && programmerId == that.programmerId
                && projectId == that.projectId && startDate.equals(that.startDate);
        if (endDate == null && that.endDate == null)
            return result;
        else if (endDate != null && that.endDate != null)
            return result;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmerId, projectId, startDate, endDate);
    }

    public long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(long programmerId) {
        this.programmerId = programmerId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
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
                ", programmerId=" + programmerId +
                ", projectId=" + projectId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
