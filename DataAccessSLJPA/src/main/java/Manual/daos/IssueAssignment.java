package Manual.daos;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "issue_assignment")
public class IssueAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_programmer", nullable = false)
    private long programmerId;

    @Column(name = "id_issue", nullable = false)
    private long issueId;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    public IssueAssignment() {
    }

    public IssueAssignment(long id, long programmerId, long issueId) {
        this.id = id;
        this.programmerId = programmerId;
        this.issueId = issueId;
    }

    public IssueAssignment(long id, long programmerId, long issueId, LocalDateTime startDate) {
        this.id = id;
        this.programmerId = programmerId;
        this.issueId = issueId;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueAssignment that = (IssueAssignment) o;
        return id == that.id && programmerId == that.programmerId
                && issueId == that.issueId && startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmerId, issueId, startDate);
    }

    public long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(long programmerId) {
        this.programmerId = programmerId;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IssueAssignment{" +
                "id=" + id +
                ", programmerId=" + programmerId +
                ", issueId=" + issueId +
                ", startDate=" + startDate +
                '}';
    }
}
