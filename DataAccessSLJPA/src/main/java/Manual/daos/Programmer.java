package Manual.daos;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "programmer")
public class Programmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime entry_date;

    @Column(nullable = false)
    @ColumnTransformer(write = "SHA256(?)")
    private String password;

    @Column(nullable = false)
    private String technologies;

    @Column(nullable = false)
    private float salary;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id", nullable = false)
    private Department department;

    public Programmer() {
    }

    public Programmer(long id, Department department) {
        this.id = id;
        this.department = department;
    }

    public Programmer(long id, String name, LocalDateTime entry_date, String password, String technologies, float salary, Department department) {
        this.id = id;
        this.name = name;
        this.entry_date = entry_date;
        this.password = password;
        this.technologies = technologies;
        this.salary = salary;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return id == that.id &&
                Float.compare(that.salary, salary) == 0 &&
                department.equals(that.department)
                && name.equals(that.name)
                && entry_date.equals(that.entry_date)
                && password.equals(that.password)
                && technologies.equals(that.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, entry_date, password, technologies, salary, department);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entry_date=" + entry_date +
                ", technologies='" + technologies + '\'' +
                ", salary=" + salary +
                ", idDepartment=" + department +
                '}';
    }
}
