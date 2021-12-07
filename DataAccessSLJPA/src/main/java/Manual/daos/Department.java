package Manual.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToOne
    private Programmer boss;

    @Column(nullable = false)
    private float budget;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true)
    private Set<Project> projects;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", orphanRemoval = true)
    private Set<BossHistory> bosses;


}
