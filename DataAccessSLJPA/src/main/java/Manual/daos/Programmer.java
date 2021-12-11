package Manual.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programmer")
@NamedQuery(name = "Programmer.findAll", query = "SELECT b FROM Programmer b")
public class Programmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime entry_date;

    @Column(nullable = false)
    @ColumnTransformer(write = "SHA256(?)")
    private String password;

    @Column(nullable = false)
    private String technologies;

    @Column(nullable = false)
    private float salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department", referencedColumnName = "id", nullable = false)
    private Department department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", orphanRemoval = true)
    private Set<IssueAssignment> issueAssignmentList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", orphanRemoval = true)
    private Set<ProjectAssignment> projectAssignmentList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", orphanRemoval = true)
    private Set<Commit> commits;

    public Programmer(long id, String name, LocalDateTime entry_date, String password, String technologies, float salary, Department department) {
        this.id = id;
        this.name = name;
        this.entry_date = entry_date;
        this.password = password;
        this.technologies = technologies;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return id == that.id && Float.compare(that.salary, salary) == 0 && name.equals(that.name) && entry_date.equals(that.entry_date) && password.equals(that.password) && technologies.equals(that.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, entry_date, password, technologies, salary);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entry_date=" + entry_date +
                ", password='" + password + '\'' +
                ", technologies='" + technologies + '\'' +
                ", salary=" + salary +
                ", issueAssignmentList=" + issueAssignmentList +
                ", projectAssignmentList=" + projectAssignmentList +
                ", commits=" + commits +
                '}';
    }
}
