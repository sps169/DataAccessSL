package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class that models the POJO DAO of the Repository table
 * @author sps169, FedericoTB
 */
public class Repository {
     private long id;
     private String name;
     private LocalDateTime creationDate;
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
        this.creationDate = creation_date;
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return id == that.id && projectId == that.projectId && name.equals(that.name) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate, projectId);
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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
                ", creationDate=" + creationDate +
                ", projectId=" + projectId +
                '}';
    }
}
