package jpa.services;

import jpa.daos.ProjectAssignment;
import jpa.dtos.ProjectAssignmentDTO;
import jpa.mappers.ProjectAssigmentMapper;
import jpa.repositories.ProjectAssignmentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectAssignmentService extends BaseService<ProjectAssignment, Long, ProjectAssignmentRepo>{
    private final ProjectAssigmentMapper mapper = new ProjectAssigmentMapper();


    public ProjectAssignmentService(ProjectAssignmentRepo repository) {
        super(repository);
    }

    public List<ProjectAssignmentDTO> getAllProjectAssignments() throws SQLException {
        List<ProjectAssignment> projectAssignments = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los project assignments"));
        List<ProjectAssignmentDTO> projectAssignmentDTOS = new ArrayList<>();
        for (ProjectAssignment projectAssignment : projectAssignments) {
            ProjectAssignmentDTO dto = mapper.toDTO(projectAssignment);
            projectAssignmentDTOS.add(dto);
        }
        return projectAssignmentDTOS;
    }

    public ProjectAssignmentDTO getProjectAssignmentById (Long projectAssignmentId) throws SQLException {
        ProjectAssignment projectAssignment = this.getById(projectAssignmentId).orElseThrow(() -> new SQLException("Error al obtener project assignments"));
        ProjectAssignmentDTO dto = mapper.toDTO(projectAssignment);
        return dto;
    }

    public ProjectAssignmentDTO insertProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
        ProjectAssignment result = this.insert(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al insertar project assignment"));
        ProjectAssignmentDTO dto = mapper.toDTO(result);
        return dto;
    }

    public ProjectAssignmentDTO updateProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
        ProjectAssignment result = this.update(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al actualizar project assignment"));
        ProjectAssignmentDTO dto = mapper.toDTO(result);
        return dto;
    }

    public ProjectAssignmentDTO deleteProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
        ProjectAssignment result = this.delete(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al borrar project assignment"));
        ProjectAssignmentDTO dto = mapper.toDTO(result);
        return dto;
    }
}
