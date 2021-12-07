//package Manual.services;
//
//import Manual.daos.Programmer;
//import Manual.daos.Project;
//import Manual.daos.ProjectAssignment;
//import Manual.dtos.ProjectAssignmentDTO;
//import Manual.mappers.ProjectAssigmentMapper;
//import Manual.repositories.ProgrammerRepo;
//import Manual.repositories.ProjectAssignmentRepo;
//import Manual.repositories.ProjectRepo;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProjectAssignmentService extends BaseService<ProjectAssignment, Long, ProjectAssignmentRepo>{
//    private final ProjectAssigmentMapper mapper = new ProjectAssigmentMapper();
//
//
//    public ProjectAssignmentService(ProjectAssignmentRepo repository) {
//        super(repository);
//    }
//
//    public List<ProjectAssignmentDTO> getAllProjectAssignments() throws SQLException {
//        List<ProjectAssignment> projectAssignments = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los project assignments"));
//        List<ProjectAssignmentDTO> projectAssignmentDTOS = new ArrayList<>();
//        for (ProjectAssignment projectAssignment : projectAssignments) {
//            ProjectAssignmentDTO dto = mapper.toDTO(projectAssignment);
//            dto.setProgrammer(this.getProgrammerById(projectAssignment.getProgrammerId()));
//            dto.setProject(this.getProjectById(projectAssignment.getProjectId()));
//            projectAssignmentDTOS.add(dto);
//        }
//        return projectAssignmentDTOS;
//    }
//
//    public ProjectAssignmentDTO getProjectAssignmentById (Long projectAssignmentId) throws SQLException {
//        ProjectAssignment projectAssignment = this.getById(projectAssignmentId).orElseThrow(() -> new SQLException("Error al obtener project assignments"));
//        ProjectAssignmentDTO dto = mapper.toDTO(projectAssignment);
//        dto.setProgrammer(this.getProgrammerById(projectAssignment.getProgrammerId()));
//        dto.setProject(this.getProjectById(projectAssignment.getProjectId()));
//        return dto;
//    }
//
//    public ProjectAssignmentDTO insertProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
//        ProjectAssignment result = this.insert(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al insertar project assignment"));
//        ProjectAssignmentDTO dto = mapper.toDTO(result);
//        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
//        dto.setProject(this.getProjectById(result.getProjectId()));
//        return dto;
//    }
//
//    public ProjectAssignmentDTO updateProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
//        ProjectAssignment result = this.update(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al actualizar project assignment"));
//        ProjectAssignmentDTO dto = mapper.toDTO(result);
//        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
//        dto.setProject(this.getProjectById(result.getProjectId()));
//        return dto;
//    }
//
//    public ProjectAssignmentDTO deleteProjectAssignment (ProjectAssignmentDTO projectAssignment) throws SQLException {
//        ProjectAssignment result = this.delete(mapper.fromDTO(projectAssignment)).orElseThrow(() -> new SQLException("Error al borrar project assignment"));
//        ProjectAssignmentDTO dto = mapper.toDTO(result);
//        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
//        dto.setProject(this.getProjectById(result.getProjectId()));
//        return dto;
//    }
//
//    private Project getProjectById(long projectId) throws SQLException {
//        ProjectService projectService = new ProjectService(new ProjectRepo());
//        return projectService.getById(projectId).orElseThrow(() -> new SQLException("Error al obtener el project de projectAssignment"));
//    }
//
//    private Programmer getProgrammerById(long programmerId) throws SQLException {
//        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
//        return programmerService.getById(programmerId).orElseThrow(() -> new SQLException("Error al obtener el programmer de projectAssignment"));
//    }
//}
