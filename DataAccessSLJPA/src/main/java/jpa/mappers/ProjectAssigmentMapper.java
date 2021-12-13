package jpa.mappers;

import jpa.daos.Programmer;
import jpa.daos.Project;
import jpa.daos.ProjectAssignment;
import jpa.dtos.ProjectAssignmentDTO;

public class ProjectAssigmentMapper extends BaseMapper<ProjectAssignment, ProjectAssignmentDTO>{
    @Override
    public ProjectAssignment fromDTO(ProjectAssignmentDTO item) {
        return new ProjectAssignment(item.getId(), item.getProgrammer(), item.getProject(), item.getStartDate(), item.getEndDate());
    }

    @Override
    public ProjectAssignmentDTO toDTO(ProjectAssignment item) {
        return new ProjectAssignmentDTO(item.getId(),
               new Programmer(item.getProgrammer().getId()),new Project(item.getProject().getId()),
                item.getStartDate(), item.getEndDate());

    }
}
