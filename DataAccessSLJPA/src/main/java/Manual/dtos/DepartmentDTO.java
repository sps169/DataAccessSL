package Manual.dtos;

import Manual.daos.Programmer;
import Manual.daos.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Set;

public class DepartmentDTO {
    private long id;
    private String name;
    private Programmer departmentBoss;
    private float budget;
    private Set<Project> finishedProjects;
    private Set<Project> ongoingProjects;
    private List<Programmer> historyBosses;

    public DepartmentDTO(long id, String name, Programmer departmentBoss, float budget, Set<Project> finishedProjects, Set<Project> ongoingProjects, List<Programmer> historyBosses) {
        this.id = id;
        this.name = name;
        this.departmentBoss = departmentBoss;
        this.budget = budget;
        this.finishedProjects = finishedProjects;
        this.ongoingProjects = ongoingProjects;
        this.historyBosses = historyBosses;
    }

    public DepartmentDTO(long id, String name, float budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public Set<Project> getFinishedProjects() {
        return finishedProjects;
    }

    public void setFinishedProjects(Set<Project> finishedProjects) {
        this.finishedProjects = finishedProjects;
    }

    public Set<Project> getOngoingProjects() {
        return ongoingProjects;
    }

    public void setOngoingProjects(Set<Project> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    public List<Programmer> getHistoryBosses() {
        return historyBosses;
    }

    public void setHistoryBosses(List<Programmer> historyBosses) {
        this.historyBosses = historyBosses;
    }

    public DepartmentDTO() {
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

    public Programmer getDepartmentBoss() {
        return departmentBoss;
    }

    public void setDepartmentBoss(Programmer departmentBoss) {
        this.departmentBoss = departmentBoss;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentBoss=" + departmentBoss +
                ", budget=" + budget +
                ", finishedProjects=" + finishedProjects +
                ", ongoingProjects=" + ongoingProjects +
                ", historyBosses=" + historyBosses +
                '}';
    }
    public static DepartmentDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, DepartmentDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
