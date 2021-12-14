package Manual.mappers;

import Manual.daos.Issue;
import Manual.dtos.IssueDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Issue class
 * @author sps169, FedericoTB
 */
public class IssueMapper extends BaseMapper<Issue, IssueDTO>{
    /**
     * Method that map a IssueDTO object into a Issue object.
     * @param item IssueDTO to map
     * @return Issue mapped
     */
    @Override
    public Issue fromDTO(IssueDTO item) {
        return new Issue(item.getId(), item.getTitle(), item.getText(), item.getDate(), item.getState(), item.getRepository().getId(), item.getBoss().getId());
    }
    /**
     * Method that map a Issue object into a IssueDTO object.
     * @param item Issue to map
     * @return IssueDTO mapped
     */
    @Override
    public IssueDTO toDTO(Issue item) {
        return new IssueDTO(item.getId(), item.getTitle(), item.getText(), item.getDate(), item.getState());
    }
}
