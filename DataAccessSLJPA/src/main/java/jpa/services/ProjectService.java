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
/**
 * Class that models from ProjectDTO using ProjectMapper to be intermediary between the ProjectController and
 * the database
 * @author sps169, FedericoTB
 */
public class ProjectService extends BaseService<Project,Long, ProjectRepo> {
    private final ProjectMapper mapper = new ProjectMapper();
    public ProjectService(ProjectRepo project) {super(project);}
    /**
     * Method that query to database using Project DAO to obtain all BossHistories in the table project.
     * @throws SQLException when fails in the query transaction
     * @return List<ProjectDto>
     */
    public List<ProjectDTO> findAllProjects() throws SQLException {
        List<Project> projects = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los projects"));
        List<ProjectDTO> dtos = new ArrayList<>();
        for (Project project : projects) {
            dtos.add(mapper.toDTO(project));
        }
        return dtos;
    }
    /**
     * Method that query to database using Project DAO to obtain a Project in the table project by an ID.
     * @param id Long of the Project to find
     * @throws SQLException when fails in the query transaction
     * @return ProjectDTO
     */
    public ProjectDTO getProjectById(Long id) throws SQLException {
        Project project = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener el projecto con id: " + id));
        return mapper.toDTO(project);
    }
    /**
     * Method that query to database using Project DAO to insert a Project in the table project.
     * @param project ProjectDTO object to insert
     * @throws SQLException when fails in the query transaction
     * @return ProjectDTO of Project object inserted
     */
    public ProjectDTO insertProject(ProjectDTO project) throws SQLException {
        if (checkRestrictions(project))
            throw new SQLException("Error al insertar projecto puesto que jefe de proyecto no cumple las condiciones");
        Project result = this.insert(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al insertar el projecto con id: "+ project.getId()));
        return mapper.toDTO(result);
    }
    /**
     * Method that query to database using Project DAO to update a Project in the table project.
     * @param project ProjectDTO object to update
     * @throws SQLException when fails in the query transaction
     * @return ProjectDTO of Project object updated
     */
    public ProjectDTO updateProject(ProjectDTO project) throws SQLException {
        if (checkRestrictions(project))
            throw new SQLException("Error al actualizar projecto puesto que jefe de proyecto no cumple las condiciones");
        Project result = this.update(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al actualizar el projecto con id: "+ project.getId()));
        return mapper.toDTO(result);
    }
    /**
     * Method that query to database using Project DAO to delete a Project in the table project.
     * @param project ProjectDTO object to delete
     * @throws SQLException when fails in the query transaction
     * @return ProjectDTO of Project object deleted
     */
    public ProjectDTO deleteProject(ProjectDTO project) throws SQLException {
        Project result = this.delete(mapper.fromDTO(project)).orElseThrow(() -> new SQLException("Error al eliminar el projecto con id: "+ project.getId()));
        return mapper.toDTO(result);
    }
    /**
     * Method that check if a Programmer belongs to any Deparment.
     * @param project ProjectDTO of the Programmer of a Department with the project ID to find
     * @throws SQLException when fails in the query transaction
     * @return boolean
     */
    private boolean checkRestrictions(ProjectDTO project) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return departmentService.getDepartmentById(project.getDepartment().getId()).getDepartmentBoss().equals(project.getProjectBoss())
                || programmerService.getProgrammerById(project.getProjectBoss().getId()).getProjects()
                .stream().anyMatch(s -> s.getDepartment().getId() == project.getDepartment().getId());
    }
    /**
     * Method that query to database using DepartmentService to obtain an Department in the with the project ID.
     * @param departmentId Long of the Department with the project ID to find
     * @throws SQLException when fails in the query transaction
     * @return Department
     */
    private Department getDepartmentById(long departmentId) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        return departmentService.getById(departmentId).orElseThrow(() -> new SQLException("Error al obtener el departamento del proyecto"));
    }
    /**
     * Method that query to database using ProgrammerService to obtain an Programmer in the with the programmer ID.
     * @param projectBossId Long of the Programmer with the project ID to find
     * @throws SQLException when fails in the query transaction
     * @return Programmer
     */
    private Programmer getBossById(long projectBossId) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(projectBossId).orElseThrow(() -> new SQLException("Error al obtener el jefe del proyecto"));
    }
}
