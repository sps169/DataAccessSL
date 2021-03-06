package Manual.daos;

import java.util.Objects;
/**
 * Class that models the POJO DAO of the Department table
 * @author sps169, FedericoTB
 */
public class Department {
     private long id;
     private String name;
     private long departmentBossId;
     private float budget;

    public Department() {
    }

    public Department(long id, long departmentBossId) {
        this.id = id;
        this.departmentBossId = departmentBossId;
    }

    public Department(long id, String name, long departmentBossId, float budget) {
        this.id = id;
        this.name = name;
        this.departmentBossId = departmentBossId;
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && departmentBossId == that.departmentBossId && Float.compare(that.budget, budget) == 0 && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentBossId, budget);
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

    public long getDepartmentBossId() {
        return departmentBossId;
    }

    public void setDepartmentBossId(long departmentBossId) {
        this.departmentBossId = departmentBossId;
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
                ", departmentBossId=" + departmentBossId +
                ", budget=" + budget +
                '}';
    }
}
