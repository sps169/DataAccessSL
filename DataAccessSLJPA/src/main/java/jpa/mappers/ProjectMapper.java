package jpa.mappers;

import jpa.daos.Project;
import jpa.dtos.ProjectDTO;
import jpa.utils.TechnologiesParser;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Project class
 * @author sps169, FedericoTB
 */
public class ProjectMapper extends BaseMapper<Project, ProjectDTO> {
    /**
    * Method that map a ProjectDTO object into a Project object.
    * @param item ProjectDTO to map
    * @return Project mapped
    */
    @Override
    public Project fromDTO(ProjectDTO item) {
        return new Project(item.getId(), item.getName(), item.getStartDate(), item.getEndDate(),
                TechnologiesParser.technologiesToString(item.getTechnologies()), item.getAnnualBudget(),
                item.getState(), item.getProjectBoss(),item.getDepartment(), item.getRepository());
    }
    /**
     * Method that map a Project object into a ProjectDTO object.
     * @param item Project to map
     * @return ProjectDTO mapped
     */
    @Override
    public ProjectDTO toDTO(Project item) {
        return new ProjectDTO(item.getId(), item.getName(), item.getStartDate(), item.getEndDate(),
                TechnologiesParser.technologiesToSet(item.getTechnologies()), item.getAnnualBudget(),
                item.getState(), item.getProjectBoss(), item.getDepartment(), item.getRepository());
    }
}
