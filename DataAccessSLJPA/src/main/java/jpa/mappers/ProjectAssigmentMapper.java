package jpa.mappers;

import jpa.daos.ProjectAssignment;
import jpa.dtos.ProjectAssignmentDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper ProjectAssignment class
 * @author sps169, FedericoTB
 */
public class ProjectAssigmentMapper extends BaseMapper<ProjectAssignment, ProjectAssignmentDTO>{ /**
 * Method that map a ProjectAssignmentDTO object into a ProjectAssignment object.
 * @param item ProjectAssignmentDTO to map
 * @return ProjectAssignment mapped
 */
    @Override
    public ProjectAssignment fromDTO(ProjectAssignmentDTO item) {
        return new ProjectAssignment(item.getId(), item.getProgrammer(), item.getProject(), item.getStartDate(), item.getEndDate());
    }
    /**
     * Method that map a ProjectAssignment object into a ProjectAssignmentDTO object.
     * @param item ProjectAssignment to map
     * @return ProjectAssignmentDTO mapped
     */
    @Override
    public ProjectAssignmentDTO toDTO(ProjectAssignment item) {
        return new ProjectAssignmentDTO(item.getId(), item.getProgrammer(),item.getProject(), item.getStartDate(), item.getEndDate());

    }
}
