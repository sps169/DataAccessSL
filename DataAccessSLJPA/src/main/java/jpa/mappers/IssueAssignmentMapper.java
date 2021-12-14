package jpa.mappers;

import jpa.daos.IssueAssignment;
import jpa.dtos.IssueAssignmentDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper IssueAssignment class
 * @author sps169, FedericoTB
 */
public class IssueAssignmentMapper extends BaseMapper<IssueAssignment, IssueAssignmentDTO>{
    /**
     * Method that map a IssueAssignmentDTO object into a IssueAssignment object.
     * @param item IssueAssignmentDTO to map
     * @return IssueAssignment mapped
     */
    @Override
    public IssueAssignment fromDTO(IssueAssignmentDTO item) {
        return new IssueAssignment(item.getId(), item.getProgrammer(), item.getIssue(), item.getStartDate());
    }
    /**
     * Method that map a IssueAssignment object into a IssueAssignmentDTO object.
     * @param item IssueAssignment to map
     * @return IssueAssignmentDTO mapped
     */
    @Override
    public IssueAssignmentDTO toDTO(IssueAssignment item) {
        return new IssueAssignmentDTO(item.getId(), item.getProgrammer(), item.getIssue(), item.getStartDate());
    }
}
