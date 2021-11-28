package Manual.dtos;

import java.time.LocalDateTime;

public class IssueAssignmentDTO {
    private ProgrammerDTO programmer;
    private IssueDTO issue;
    private LocalDateTime startDate;

    public IssueAssignmentDTO(ProgrammerDTO programmer, IssueDTO issue, LocalDateTime startDate) {
        this.programmer = programmer;
        this.issue = issue;
        this.startDate = startDate;
    }

    public IssueAssignmentDTO() {
    }

    public ProgrammerDTO getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerDTO programmer) {
        this.programmer = programmer;
    }

    public IssueDTO getIssue() {
        return issue;
    }

    public void setIssue(IssueDTO issue) {
        this.issue = issue;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "IssueAssignmentDTO{" +
                "programmerId=" + programmer +
                ", issueId=" + issue +
                ", startDate=" + startDate +
                '}';
    }
}
