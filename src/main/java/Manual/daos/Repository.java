package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Repository {
     private long id;
     private String name;
     private LocalDateTime creation_date;
     private long projectId;

    public Repository() {
    }

    public Repository(long id, long projectId) {
        this.id = id;
        this.projectId = projectId;
    }

    public Repository(long id, String name, LocalDateTime creation_date, long projectId) {
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return id == that.id && projectId == that.projectId && name.equals(that.name) && creation_date.equals(that.creation_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creation_date, projectId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creation_date=" + creation_date +
                ", projectId=" + projectId +
                '}';
    }
}
