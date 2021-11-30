package Manual.daos;

import java.time.LocalDateTime;
import java.util.Objects;

public class BossHistory {
    private long programmerId;
    private long departmentId;
    private LocalDateTime entryDate;
    private LocalDateTime leave_date;

    public BossHistory() {
    }

    public BossHistory(long programmerId, long departmentId, LocalDateTime entryDate) {
        this.programmerId = programmerId;
        this.departmentId = departmentId;
        this.entryDate = entryDate;
    }

    public BossHistory(long programmerId, long departmentId, LocalDateTime entryDate, LocalDateTime leave_date) {
        this.programmerId = programmerId;
        this.departmentId = departmentId;
        this.entryDate = entryDate;
        this.leave_date = leave_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BossHistory that = (BossHistory) o;
        boolean equals = programmerId == that.programmerId
                && departmentId == that.departmentId
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
        return Objects.hash(programmerId, departmentId, entryDate, leave_date);
    }

    public long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(long programmerId) {
        this.programmerId = programmerId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
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

    @Override
    public String toString() {
        return "BossHistory{" +
                "programmerId=" + programmerId +
                ", departmentId=" + departmentId +
                ", entryDate=" + entryDate +
                ", leave_date=" + leave_date +
                '}';
    }


}
