package Manual.daos;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true )
    private Set<BossHistory> bossHistory;

    @Column(nullable = false)
    private float budget;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true)
    private Set<Programmer> programmers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true)
    private Set<Project> projects;

    public Department() {
    }

    public Department(long id, String name, Programmer departmentBoss, float budget) {
        this.id = id;
        this.name = name;
        this.departmentBoss = departmentBoss;
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Float.compare(that.budget, budget) == 0 && name.equals(that.name) && bossHistory.equals(that.bossHistory) && programmers.equals(that.programmers) && projects.equals(that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentBoss, budget);
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

    public void setDepartmentBoss(Programmer departmentBossId) {
        this.departmentBoss = departmentBossId;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentBoss=" + departmentBoss +
                ", budget=" + budget +
                '}';
    }
}
