package Manual.mappers;

import Manual.daos.Commit;
import Manual.dtos.CommitDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Commit class
 * @author sps169, FedericoTB
 */
public class CommitMapper extends BaseMapper<Commit, CommitDTO>{
    /**
     * Method that map a CommitDTO object into a Commit object.
     * @param item CommitDTO to map
     * @return Commit mapped
     */
    @Override
    public Commit fromDTO(CommitDTO item) {
        return new Commit(item.getId(), item.getTitle(), item.getText(), item.getDate(), item.getRepository().getId(), item.getProgrammer().getId(), item.getIssue().getId());
    }
    /**
     * Method that map a Commit object into a CommitDTO object.
     * @param item Commit to map
     * @return CommitDTO mapped
     */
    @Override
    public CommitDTO toDTO(Commit item) {
        return new CommitDTO(item.getId(), item.getTitle(), item.getText(), item.getDate());
    }
}
