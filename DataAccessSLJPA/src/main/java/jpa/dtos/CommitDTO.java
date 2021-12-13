package jpa.dtos;

import jpa.daos.Issue;
import jpa.daos.Programmer;
import jpa.daos.Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jpa.utils.HibernateProxyTypeAdapter;

import java.time.LocalDateTime;

public class CommitDTO {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private Repository repository;
    private Programmer programmer;
    private Issue issue;

    public CommitDTO() {
    }

    public CommitDTO(long id, String title, String text, LocalDateTime date, Repository repository,
                     Programmer programmer, Issue issue) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.repository = repository;
        this.programmer = programmer;
        this.issue = issue;
    }

    public CommitDTO(long id, String title, String text, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
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

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "CommitDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
    public static CommitDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CommitDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
