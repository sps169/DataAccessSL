package Manual.services;

import Manual.daos.Programmer;
import Manual.daos.Project;
import Manual.daos.ProjectAssignment;
import Manual.dtos.ProjectAssignmentDTO;
import Manual.mappers.ProjectAssigmentMapper;
import Manual.repositories.ProgrammerRepo;
import Manual.repositories.ProjectAssignmentRepo;
import Manual.repositories.ProjectRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that models from ProjectAssignmentDTO using ProjectAssignmentMapper to be intermediary between
 * the ProjectAssignmentController and the database
 * @author sps169, FedericoTB
 */
public class ProjectAssignmentService extends BaseService<ProjectAssignment, Long, ProjectAssignmentRepo>{
    private final ProjectAssigmentMapper mapper = new ProjectAssigmentMapper();


    public ProjectAssignmentService(ProjectAssignmentRepo repository) {
        super(repository);
    }
    /**
     * Method that query to database using ProjectAssignment DAO to obtain all BossHistories in the table project_assignment.
     * @throws SQLException when fails in the query transaction
     * @return List<ProjectAssignmentDto>
     */
    public List<ProjectAssignmentDTO> getAllProjectAssignments() throws SQLException {
        List<ProjectAssignment> projectAssignments = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los project assignments"));
        List<ProjectAssignmentDTO> projectAssignmentDTOS = new ArrayList<>();
        for (ProjectAssignment projectAssignment : projectAssignments) {
            ProjectAssignmentDTO dto = mapper.toDTO(projectAssignment);
            dto.setProgrammer(this.getProgrammerById(projectAssignment.getProgrammerId()));
            dto.setProject(this.getProjectById(projectAssignment.getProjectId()));
            projectAssignmentDTOS.add(dto);
        }
        return projectAssignmentDTOS;
    }
    /**
     * Method that query to database using ProjectAssignment DAO to obtain a ProjectAssignment in the table project_assignment by an ID.
     * @param projectAssignmentId Long of the projectAssignment to find
     * @throws SQLException when fails in the query transaction
     * @return ProjectAssignmentDTO
     */
    public ProjectAssignmentDTO getProjectAssignmentById (Long projectAssignmentId) throws SQLException {
        ProjectAssignment projectAssignment = this.getById(projectAssignmentId).orElseThrow(() -> new SQLException("Error al obtener project assignments"));
        ProjectAssignmentDTO dto = mapper.toDTO(projectAssignment);
        dto.setProgrammer(this.getProgrammerById(projectAssignment.getProgrammerId()));
        dto.setProject(this.getProjectById(projectAssignment.getProjectId()));
        return dto;
    }
    /**
     * Method that query to database using ProjectAssignment DAO to insert a ProjectAssignment in the table project_assignment.
     * @param projectAssignment ProjectAssignment object to insert
     * @throws SQLException when fails in the query transaction
     * @return ProjectAssignmentDTO of ProjectAssignment object inserted
     */
    public ProjectAssignmentDTO insertProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
        ProjectAssignment result = this.insert(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al insertar project assignment"));
        ProjectAssignmentDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setProject(this.getProjectById(result.getProjectId()));
        return dto;
    }
    /**
     * Method that query to database using ProjectAssignment DAO to update a ProjectAssignment in the table project_assignment.
     * @param projectAssignment ProjectAssignment object to update
     * @throws SQLException when fails in the query transaction
     * @return ProjectAssignmentDTO of ProjectAssignment object updated
     */
    public ProjectAssignmentDTO updateProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
        ProjectAssignment result = this.update(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al actualizar project assignment"));
        ProjectAssignmentDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setProject(this.getProjectById(result.getProjectId()));
        return dto;
    }
    /**
     * Method that query to database using ProjectAssignment DAO to delete a ProjectAssignment in the table project_assignment.
     * @param projectAssignment ProjectAssignment object to delete
     * @throws SQLException when fails in the query transaction
     * @return ProjectAssignmentDTO of ProjectAssignment object deleted
     */
    public ProjectAssignmentDTO deleteProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
        ProjectAssignment result = this.delete(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al borrar project assignment"));
        ProjectAssignmentDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setProject(this.getProjectById(result.getProjectId()));
        return dto;
    }
    /**
     * Method that query to database using Project DAO to find a Project in the table project by id.
     * @param projectId Long id to find Project
     * @throws SQLException when fails in the query transaction
     * @return Project
     */
    private Project getProjectById(long projectId) throws SQLException {
        ProjectService projectService = new ProjectService(new ProjectRepo());
        return projectService.getById(projectId).orElseThrow(() -> new SQLException("Error al obtener el project de projectAssignment"));
    }
    /**
     * Method that query to database using Programmer DAO to find a Programmer in the table programmer by id.
     * @param programmerId Long id to find Programmer
     * @throws SQLException when fails in the query transaction
     * @return Programmer
     */
    private Programmer getProgrammerById(long programmerId) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(programmerId).orElseThrow(() -> new SQLException("Error al obtener el programmer de projectAssignment"));
    }
}
