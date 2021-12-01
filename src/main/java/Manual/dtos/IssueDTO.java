package Manual.dtos;

import Manual.daos.Programmer;
import Manual.daos.Project;
import Manual.daos.Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Set;

public class IssueDTO {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private String state;
    private Project project;
    private Repository repository;
    private Programmer boss;

    public IssueDTO(long id, String title, String text, LocalDateTime date, String state, Project project, Repository repository, Programmer boss) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.state = state;
        this.project = project;
        this.repository = repository;
        this.boss = boss;
    }

    public IssueDTO() {
    }

    public IssueDTO(long id, String title, String text, LocalDateTime date, String state) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.state = state;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", project=" + project +
                ", repository=" + repository +
                ", boss=" + boss +
                '}';
    }
    public static IssueDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, IssueDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
