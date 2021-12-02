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
    @ManyToOne
    @JoinColumn(name = "id_programmer",referencedColumnName = "id", nullable = false)
    private Programmer programmer;
    @ManyToOne
    @JoinColumn(name = "id_issue",referencedColumnName = "id",nullable = false)
    private Issue issue;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    public IssueAssignment() {
    }

    public IssueAssignment(long id, Programmer programmer, Issue issue) {
        this.id = id;
        this.programmer = programmer;
        this.issueId = issue;
    }

    public IssueAssignment(long id, Programmer programmer, Issue issue, LocalDateTime startDate) {
        this.id = id;
        this.programmer = programmer;
        this.issue = issue;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueAssignment that = (IssueAssignment) o;
        return id == that.id &&
                programmer.equals(that.programmer) &&
                issue.equals(that.issue)  &&
                startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmer, issue, startDate);
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issueId) {
        this.issue = issue;
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
                ", programmer=" + programmer +
                ", issue=" + issue +
                ", startDate=" + startDate +
                '}';
    }
}
