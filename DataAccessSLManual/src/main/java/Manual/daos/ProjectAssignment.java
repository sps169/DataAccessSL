package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProjectAssignment {
    private long id;
    private long programmerId;
    private long projectId;
    private LocalDateTime startDate;
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
