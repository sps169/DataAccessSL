package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class that models the POJO DAO of the Issue table
 * @author sps169, FedericoTB
 */
public class Issue {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private String state;
    private long repositoryId;
    private long bossId;

    public Issue() {
    }

    public Issue(long id, long repositoryId, long bossId) {
        this.id = id;
        this.repositoryId = repositoryId;
        this.bossId = bossId;
    }

    public Issue(long id, String title, String text, LocalDateTime date, String state, long repositoryId, long bossId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.state = state;
        this.repositoryId = repositoryId;
        this.bossId = bossId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && repositoryId == issue.repositoryId && bossId == issue.bossId && title.equals(issue.title) && text.equals(issue.text) && date.equals(issue.date) && state.equals(issue.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date, state, repositoryId, bossId);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public long getBossId() {
        return bossId;
    }

    public void setBossId(long bossId) {
        this.bossId = bossId;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", repositoryId=" + repositoryId +
                ", bossId=" + bossId +
                '}';
    }
}
