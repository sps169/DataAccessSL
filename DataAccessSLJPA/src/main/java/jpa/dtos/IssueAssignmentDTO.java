package jpa.dtos;

import jpa.daos.Issue;
import jpa.daos.Programmer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
/**
 * Class that models the POJO DTO of the IssueAssignment table
 * with ToJason FromJason methods
 * @author sps169, FedericoTB
 */
public class IssueAssignmentDTO {
    private long id;
    private Programmer programmer;
    private Issue issue;
    private LocalDateTime startDate;

    public IssueAssignmentDTO() {
    }

    public IssueAssignmentDTO(long id,Programmer programmer, Issue issue, LocalDateTime startDate) {
        this.id = id;
        this.programmer = programmer;
        this.issue = issue;
        this.startDate = startDate;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
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
