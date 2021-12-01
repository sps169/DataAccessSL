package Manual.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Set;

public class DepartmentDTO {
    private long id;
    private String name;
    private ProgrammerDTO departmentBoss;
    private float budget;
    private Set<ProjectDTO> finishedProjects;
    private Set<ProjectDTO> ongoingProjects;
    private List<ProgrammerDTO> historyBosses;

    public DepartmentDTO(long id, String name, ProgrammerDTO departmentBoss, float budget, Set<ProjectDTO> finishedProjects, Set<ProjectDTO> ongoingProjects, List<ProgrammerDTO> historyBosses) {
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

    public Set<ProjectDTO> getFinishedProjects() {
        return finishedProjects;
    }

    public void setFinishedProjects(Set<ProjectDTO> finishedProjects) {
        this.finishedProjects = finishedProjects;
    }

    public Set<ProjectDTO> getOngoingProjects() {
        return ongoingProjects;
    }

    public void setOngoingProjects(Set<ProjectDTO> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    public List<ProgrammerDTO> getHistoryBosses() {
        return historyBosses;
    }

    public void setHistoryBosses(List<ProgrammerDTO> historyBosses) {
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

    public ProgrammerDTO getDepartmentBoss() {
        return departmentBoss;
    }

    public void setDepartmentBoss(ProgrammerDTO departmentBoss) {
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
