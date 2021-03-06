package jpa.daos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class that models the POJO DAO of the BossHistory table
 * with JPA and Lombok marks
 * @author sps169, FedericoTB
 */
@Getter
@Setter
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
    private LocalDateTime entryDate;

    @Column(name = "leave_date")
    private LocalDateTime leave_date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BossHistory that = (BossHistory) o;
        return id == that.id && entryDate.equals(that.entryDate) && leave_date.equals(that.leave_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entryDate, leave_date);
    }

    @Override
    public String toString() {
        return "BossHistory{" +
                "id=" + id +
                ", programmer=" + programmer +
                ", department=" + department +
                ", entryDate=" + entryDate +
                ", leave_date=" + leave_date +
                '}';
    }
}
