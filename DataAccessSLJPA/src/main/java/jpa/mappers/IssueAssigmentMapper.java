package jpa.mappers;

import jpa.daos.Issue;
import jpa.daos.IssueAssignment;
import jpa.daos.Programmer;
import jpa.dtos.IssueAssignmentDTO;

public class IssueAssigmentMapper extends BaseMapper<IssueAssignment, IssueAssignmentDTO>{

    @Override
    public IssueAssignment fromDTO(IssueAssignmentDTO item) {
        return new IssueAssignment(item.getId(), item.getProgrammer(), item.getIssue(), item.getStartDate());
    }

    @Override
    public IssueAssignmentDTO toDTO(IssueAssignment item) {
        return new IssueAssignmentDTO(item.getId(), new Programmer(item.getProgrammer().getId()), new Issue(item.getIssue().getId()), item.getStartDate());
    }
}
