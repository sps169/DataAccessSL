package jpa.mappers;


import jpa.daos.Department;
import jpa.daos.Programmer;
import jpa.daos.Project;
import jpa.daos.Repository;
import jpa.dtos.ProjectDTO;
import jpa.utils.TechnologiesParser;

public class ProjectMapper extends BaseMapper<Project, ProjectDTO> {
    @Override
    public Project fromDTO(ProjectDTO item) {
        return new Project(item.getId(), item.getName(), item.getStartDate(), item.getEndDate(),
                TechnologiesParser.technologiesToString(item.getTechnologies()), item.getAnnualBudget(),
                item.getState(), item.getProjectBoss(),item.getDepartment(), item.getRepository());
    }

    @Override
    public ProjectDTO toDTO(Project item) {
        return new ProjectDTO(item.getId(), item.getName(), item.getStartDate(), item.getEndDate(),
                TechnologiesParser.technologiesToSet(item.getTechnologies()), item.getAnnualBudget(),
                item.getState(),
                new Programmer(item.getProjectBoss().getId()), new Department(item.getDepartment().getId()),
                new Repository(item.getRepository().getId()));
    }
}
