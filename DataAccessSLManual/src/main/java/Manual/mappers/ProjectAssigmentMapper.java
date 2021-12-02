package Manual.mappers;

import Manual.daos.ProjectAssignment;
import Manual.dtos.ProjectAssignmentDTO;

public class ProjectAssigmentMapper extends BaseMapper<ProjectAssignment, ProjectAssignmentDTO>{
    @Override
    public ProjectAssignment fromDTO(ProjectAssignmentDTO item) {
        return new ProjectAssignment(item.getId(), item.getProgrammer().getId(), item.getProject().getId(), item.getStartDate(), item.getEndDate());
    }

    @Override
    public ProjectAssignmentDTO toDTO(ProjectAssignment item) {
        return new ProjectAssignmentDTO(item.getId(), item.getStartDate(), item.getEndDate());
    }
}
