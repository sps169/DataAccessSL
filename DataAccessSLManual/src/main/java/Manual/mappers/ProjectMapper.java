package Manual.mappers;

import Manual.daos.Project;
import Manual.dtos.ProjectDTO;
import Manual.utils.TechnologiesParser;
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
                item.getState(), item.getProjectBoss().getId(),item.getDepartment().getId());
    }

    @Override
    public ProjectDTO toDTO(Project item) {
        return new ProjectDTO(item.getId(), item.getName(), item.getStartDate(), item.getEndDate(),
                TechnologiesParser.technologiesToSet(item.getTechnologies()), item.getAnnualBudget(),
                item.getState());
    }
}
