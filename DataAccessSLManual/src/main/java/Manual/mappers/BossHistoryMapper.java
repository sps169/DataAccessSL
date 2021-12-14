package Manual.mappers;

import Manual.daos.BossHistory;
import Manual.dtos.BossHistoryDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper BossHistory class
 * @author sps169, FedericoTB
 */
public class BossHistoryMapper extends BaseMapper<BossHistory, BossHistoryDTO>{
    /**
     * Method that map a BossHistoryDTO object into a BossHistory object.
     * @param item BossHistoryDTO to map
     * @return BossHistory mapped
     */
    @Override
    public BossHistory fromDTO(BossHistoryDTO item) {
        return new BossHistory(item.getId(), item.getProgrammer().getId(), item.getDepartment().getId(), item.getEntryDate(), item.getLeaveDate());
    }
    /**
     * Method that map a BossHistory object into a BossHistoryDTO object.
     * @param item BossHistory to map
     * @return BossHistoryDTO mapped
     */
    @Override
    public BossHistoryDTO toDTO(BossHistory item) {
        return new BossHistoryDTO(item.getId(), item.getEntryDate(), item.getLeave_date());
    }
}
