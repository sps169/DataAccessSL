package Manual.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boss_history")
@NamedQuery(name = "BossHistory.findAll", query = "SELECT b FROM BossHistory b")
public class BossHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_programmer", referencedColumnName = "id", nullable = false)
    private Programmer programmer;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id", nullable = false)
    private Department department;

    @Column(name = "entry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime entryDate;

    @Column(name = "leave_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime leave_date;
}
