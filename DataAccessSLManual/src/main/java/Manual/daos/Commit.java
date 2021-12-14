package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class that models the POJO DAO of the Commit table
 * @author sps169, FedericoTB
 */
public class Commit {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private long repositoryId;
    private long programmerId;
    private long issueId;

    public Commit() {
    }

    public Commit(long id, long repositoryId, long programmerId, long id_issue) {
        this.id = id;
        this.repositoryId = repositoryId;
        this.programmerId = programmerId;
        this.issueId = id_issue;
    }

    public Commit(long id, String title, String text, LocalDateTime date, long repositoryId, long programmerId, long id_issue) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.repositoryId = repositoryId;
        this.programmerId = programmerId;
        this.issueId = id_issue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return id == commit.id && repositoryId == commit.repositoryId && programmerId == commit.programmerId && issueId == commit.issueId && title.equals(commit.title) && text.equals(commit.text) && date.equals(commit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date, repositoryId, programmerId, issueId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(long repositoryId) {
        this.repositoryId = repositoryId;
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

    @Override
    public String toString() {
        return "Commit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", repositoryId=" + repositoryId +
                ", programmerId=" + programmerId +
                ", issueId=" + issueId +
                '}';
    }
}
