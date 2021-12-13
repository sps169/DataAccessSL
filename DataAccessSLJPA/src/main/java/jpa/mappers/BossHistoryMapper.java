package jpa.mappers;

import jpa.daos.BossHistory;
import jpa.daos.Department;
import jpa.daos.Programmer;
import jpa.dtos.BossHistoryDTO;

public class BossHistoryMapper extends BaseMapper<BossHistory, BossHistoryDTO>{
    @Override
    public BossHistory fromDTO(BossHistoryDTO item) {
        return new BossHistory(item.getId(), item.getProgrammer(), item.getDepartment(), item.getEntryDate(), item.getLeaveDate());
    }

    @Override
    public BossHistoryDTO toDTO(BossHistory item) {
        return new BossHistoryDTO(item.getId(), item.getProgrammer(), item.getDepartment(), item.getEntryDate(), item.getLeave_date());
    }
}
