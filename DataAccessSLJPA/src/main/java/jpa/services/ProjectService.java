package jpa.services;

import jpa.daos.Department;
import jpa.daos.Programmer;
import jpa.daos.Project;
import jpa.dtos.ProjectDTO;
import jpa.mappers.ProjectMapper;
import jpa.repositories.DepartmentRepo;
import jpa.repositories.ProgrammerRepo;
import jpa.repositories.ProjectRepo;

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
            dtos.add(mapper.toDTO(project));
        }
        return dtos;
    }

    public ProjectDTO getProjectById(Long id) throws SQLException {
        Project project = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener el projecto con id: " + id));
        return mapper.toDTO(project);
    }

    public ProjectDTO insertProject(ProjectDTO project) throws SQLException {
        if (checkRestrictions(project))
            throw new SQLException("Error al insertar projecto puesto que jefe de proyecto no cumple las condiciones");
        Project result = this.insert(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al insertar el projecto con id: "+ project.getId()));
        return mapper.toDTO(result);
    }

    public ProjectDTO updateProject(ProjectDTO project) throws SQLException {
        if (checkRestrictions(project))
            throw new SQLException("Error al actualizar projecto puesto que jefe de proyecto no cumple las condiciones");
        Project result = this.update(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al actualizar el projecto con id: "+ project.getId()));
        return mapper.toDTO(result);
    }

    public ProjectDTO deleteProject(ProjectDTO project) throws SQLException {
        Project result = this.delete(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al eliminar el projecto con id: "+ project.getId()));
        return mapper.toDTO(result);
    }

    private boolean checkRestrictions(ProjectDTO project) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return departmentService.getDepartmentById(project.getDepartment().getId()).getDepartmentBoss().equals(project.getProjectBoss())
                || programmerService.getProgrammerById(project.getProjectBoss().getId()).getProjects()
                .stream().anyMatch(s -> s.getDepartment().getId() == project.getDepartment().getId());
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
