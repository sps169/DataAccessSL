package jpa.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@NamedQuery(name = "Department.findAll", query = "SELECT b FROM Department b")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_boss", referencedColumnName = "id", nullable = false)
    private Programmer boss;

    @Column(nullable = false)
    private float budget;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true)
    private Set<Project> projects;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true)
    private Set<BossHistory> bosses;

    public Department(long id, String name, Programmer boss, float budget) {
        this.id = id;
        this.name = name;
        this.boss = boss;
        this.budget = budget;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Float.compare(that.budget, budget) == 0 && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, budget);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", boss=" + boss +
                ", budget=" + budget +
                ", projects=" + projects +
                ", bosses=" + bosses +
                '}';
    }
}
