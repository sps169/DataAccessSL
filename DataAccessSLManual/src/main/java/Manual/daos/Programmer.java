package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class that models the POJO DAO of the Programmer table
 * @author sps169, FedericoTB
 */
public class Programmer {
    private long id;
    private String name;
    private LocalDateTime entry_date;
    private String password;
    private String technologies;
    private float salary;
    private long departmentId;

    public Programmer() {
    }

    public Programmer(long id, long idDepartment) {
        this.id = id;
        this.departmentId = idDepartment;
    }

    public Programmer(long id, String name, LocalDateTime entry_date, String password, String technologies, float salary, long idDepartment) {
        this.id = id;
        this.name = name;
        this.entry_date = entry_date;
        this.password = password;
        this.technologies = technologies;
        this.salary = salary;
        this.departmentId = idDepartment;
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

    public LocalDateTime getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDateTime entry_date) {
        this.entry_date = entry_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return id == that.id && Float.compare(that.salary, salary) == 0 && departmentId == that.departmentId && name.equals(that.name) && entry_date.equals(that.entry_date) && password.equals(that.password) && technologies.equals(that.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, entry_date, password, technologies, salary, departmentId);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entry_date=" + entry_date +
                ", technologies='" + technologies + '\'' +
                ", salary=" + salary +
                ", idDepartment=" + departmentId +
                '}';
    }
}
