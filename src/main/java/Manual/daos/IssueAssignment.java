package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;

public class IssueAssignment {
    private long programmerId;
    private long issueId;
    private LocalDateTime startDate;

    public IssueAssignment() {
    }

    public IssueAssignment(long programmerId, long issueId) {
        this.programmerId = programmerId;
        this.issueId = issueId;
    }

    public IssueAssignment(long programmerId, long issueId, LocalDateTime startDate) {
        this.programmerId = programmerId;
        this.issueId = issueId;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueAssignment that = (IssueAssignment) o;
        return programmerId == that.programmerId && issueId == that.issueId && startDate.equals(that.startDate);
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

    @Override
    public String toString() {
        return "IssueAssignment{" +
                "programmerId=" + programmerId +
                ", issueId=" + issueId +
                ", startDate=" + startDate +
                '}';
    }
}
