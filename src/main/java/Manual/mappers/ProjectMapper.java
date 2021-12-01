package Manual.mappers;

import Manual.daos.Programmer;
import Manual.daos.Project;
import Manual.dtos.ProgrammerDTO;
import Manual.dtos.ProjectDTO;
import Manual.utils.TechnologiesParser;

public class ProjectMapper extends BaseMapper<Project, ProjectDTO> {
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
