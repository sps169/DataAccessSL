package Manual.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class IssueAssignmentDTO {
    private long id;
    private ProgrammerDTO programmer;
    private IssueDTO issue;
    private LocalDateTime startDate;



    public IssueAssignmentDTO() {
    }

    public IssueAssignmentDTO(long id,ProgrammerDTO programmer, IssueDTO issue, LocalDateTime startDate) {
        this.id = id;
        this.programmer = programmer;
        this.issue = issue;
        this.startDate = startDate;
    }

    public IssueAssignmentDTO(long id, LocalDateTime startDate) {
        this.id = id;
        this.startDate = startDate;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

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
                "id=" + id +
                ", programmerId=" + programmer +
                ", issueId=" + issue +
                ", startDate=" + startDate +
                '}';
    }
    public static IssueAssignmentDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, IssueAssignmentDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
