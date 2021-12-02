package Manual.daos;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "boss_history")
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

    public BossHistory() {
    }

    public BossHistory(long id, Programmer programmer, Department department, LocalDateTime entryDate) {
        this.id = id;
        this.programmer = programmer;
        this.department = department;
        this.entryDate = entryDate;
    }

    public BossHistory(long id,Programmer programmer, Department department, LocalDateTime entryDate, LocalDateTime leave_date) {
        this.id = id;
        this.programmer = programmer;
        this.department = department;
        this.entryDate = entryDate;
        this.leave_date = leave_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BossHistory that = (BossHistory) o;
        boolean equals = id == that.id
                && programmer.equals(that.programmer)
                && department.equals(that.department)
                && entryDate.equals(that.entryDate);
        if (that.leave_date != null && this.leave_date != null)
            return equals && this.leave_date.equals(that.leave_date);
        else if (that.leave_date == null && this.leave_date == null)
            return equals;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmer, department, entryDate, leave_date);
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(LocalDateTime leave_date) {
        this.leave_date = leave_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
