package Manual.mappers;

import Manual.daos.IssueAssignment;
import Manual.dtos.IssueAssignmentDTO;

public class IssueAssigmentMapper extends BaseMapper<IssueAssignment, IssueAssignmentDTO>{

    @Override
    public IssueAssignment fromDTO(IssueAssignmentDTO item) {
        return new IssueAssignment(item.getId(), item.getProgrammer().getId(), item.getIssue().getId(),
                item.getStartDate());
    }

    @Override
    public IssueAssignmentDTO toDTO(IssueAssignment item) {
        return new IssueAssignmentDTO(item.getId(),  item.getStartDate());
    }
}
