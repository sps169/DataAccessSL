package Manual.services;

import Manual.daos.Department;
import Manual.daos.Programmer;
import Manual.daos.Project;
import Manual.dtos.ProjectDTO;
import Manual.mappers.ProjectMapper;
import Manual.repositories.DepartmentRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.repositories.ProjectRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService extends BaseService<Project,Long, ProjectRepo> {
    private final ProjectMapper mapper = new ProjectMapper();
    public ProjectService(ProjectRepo repository) {super(repository);}

    public List<ProjectDTO> findAllProjects() throws SQLException {
        List<Project> projects = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los projects"));
        List<ProjectDTO> dtos = new ArrayList<>();
        for (Project project : projects) {
            dtos.add(fillProject(project));
        }
        return dtos;
    }

    public ProjectDTO getProjectById(Long id) throws SQLException {
        Project project = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener el projecto con id: " + id));
        return fillProject(project);
    }

    public ProjectDTO insertProject(ProjectDTO project) throws SQLException {
        if (checkRestrictions(project))
            throw new SQLException("Error al insertar projecto puesto que jefe de proyecto no cumple las condiciones");
        Project result = this.insert(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al insertar el projecto con id: "+ project.getId()));
        return fillProject(result);
    }

    public ProjectDTO updateProject(ProjectDTO project) throws SQLException {
        if (checkRestrictions(project))
            throw new SQLException("Error al actualizar projecto puesto que jefe de proyecto no cumple las condiciones");
        Project result = this.update(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al actualizar el projecto con id: "+ project.getId()));
        return fillProject(result);
    }

    public ProjectDTO deleteProject(ProjectDTO project) throws SQLException {
        Project result = this.delete(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al eliminar el projecto con id: "+ project.getId()));
        return fillProject(result);
    }

    private boolean checkRestrictions(ProjectDTO project) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return departmentService.getDepartmentById(project.getDepartment().getId()).getDepartmentBoss().equals(project.getProjectBoss())
                || programmerService.getProgrammerById(project.getProjectBoss().getId()).getProjects()
                .stream().anyMatch(s -> s.getDepartment().getId() == project.getDepartment().getId());
    }

    private ProjectDTO fillProject(Project project) throws SQLException {
        ProjectDTO dto = mapper.toDTO(project);
        return dto;
    }

    private Department getDepartmentById(long departmentId) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        return departmentService.getById(departmentId).orElseThrow(() -> new SQLException("Error al obtener el departamento del proyecto"));
    }

    private Programmer getBossById(long projectBossId) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(projectBossId).orElseThrow(() -> new SQLException("Error al obtener el jefe del proyecto"));
    }
}
