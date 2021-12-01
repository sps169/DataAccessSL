package Manual.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class BossHistoryDTO {
    private long id;
    private ProgrammerDTO programmer;
    private DepartmentDTO department;
    private LocalDateTime entryDate;
    private LocalDateTime leaveDate;

    public BossHistoryDTO() {
    }

    public BossHistoryDTO(long id, ProgrammerDTO programmer, DepartmentDTO department, LocalDateTime entryDate, LocalDateTime leaveDate) {
        this.id = id;
        this.programmer = programmer;
        this.department = department;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
    }

    public BossHistoryDTO(long id, LocalDateTime entryDate, LocalDateTime leaveDate) {
        this.id = id;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProgrammerDTO getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerDTO programmer) {
        this.programmer = programmer;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Override
    public String toString() {
        return "BossHistoryDTO{" +
                "id=" + id +
                ", programmer=" + programmer +
                ", department=" + department +
                ", entryDate=" + entryDate +
                ", leaveDate=" + leaveDate +
                '}';
    }

    public static BossHistoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, BossHistoryDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
