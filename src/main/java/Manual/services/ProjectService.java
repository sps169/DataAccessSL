package Manual.services;


import Manual.daos.Project;
import Manual.mappers.ProjectMapper;
import Manual.repositories.ProjectRepo;



public class ProjectService extends BaseService<Project,Long, ProjectRepo> {
    private final ProjectMapper mapper = new ProjectMapper();
    public ProjectService(ProjectRepo repository) {super(repository);}

}
