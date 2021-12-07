//package Manual.services;
//
//import Manual.daos.Issue;
//import Manual.daos.IssueAssignment;
//import Manual.daos.Programmer;
//import Manual.dtos.IssueAssignmentDTO;
//import Manual.mappers.IssueAssigmentMapper;
//import Manual.repositories.IssueAssigmentRepo;
//import Manual.repositories.IssueRepo;
//import Manual.repositories.ProgrammerRepo;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class IssueAssignmentService extends BaseService<IssueAssignment, Long, IssueAssigmentRepo>{
//    private final IssueAssigmentMapper mapper = new IssueAssigmentMapper();
//
//    public IssueAssignmentService(IssueAssigmentRepo repository) {
//        super(repository);
//    }
//
//    public List<IssueAssignmentDTO> getAllIssuesAssignment () throws SQLException {
//        List<IssueAssignment> issueAssignmentList = this.findAll().orElseThrow(() -> new SQLException("Error al obtener los issueAssignment"));
//        List<IssueAssignmentDTO> issueAssignmentDTOS = new ArrayList<>();
//        for (IssueAssignment issueAssignment : issueAssignmentList) {
//            IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
//            dto.setProgrammer(this.getProgrammerById(issueAssignment.getProgrammerId()));
//            dto.setIssue(this.getIssueById(issueAssignment.getIssueId()));
//            issueAssignmentDTOS.add(dto);
//        }
//        return issueAssignmentDTOS;
//    }
//
//    public IssueAssignmentDTO getIssueAssignmentById (Long issueId) throws SQLException {
//        IssueAssignment issueAssignment = this.getById(issueId).orElseThrow(() -> new SQLException("Error al obtener issueAssignment con id: " + issueId));
//        IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
//        dto.setProgrammer(this.getProgrammerById(issueAssignment.getProgrammerId()));
//        dto.setIssue(this.getIssueById(issueAssignment.getIssueId()));
//        return dto;
//    }
//
//    public IssueAssignmentDTO insertIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
//        IssueAssignment result = this.insert(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al insertar issueAssignment con id: " + issueAssignment.getId()));
//        IssueAssignmentDTO dto = mapper.toDTO(result);
//        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
//        dto.setIssue(this.getIssueById(result.getIssueId()));
//        return dto;
//    }
//
//    public IssueAssignmentDTO updateIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
//        IssueAssignment result = this.update(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al actualizar issueAssignment con id: " + issueAssignment.getId()));
//        IssueAssignmentDTO dto = mapper.toDTO(result);
//        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
//        dto.setIssue(this.getIssueById(result.getIssueId()));
//        return dto;
//    }
//
//    public IssueAssignmentDTO deleteIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
//        IssueAssignment result = this.delete(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al borrar issueAssignment con id: " + issueAssignment.getId()));
//        IssueAssignmentDTO dto = mapper.toDTO(result);
//        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
//        dto.setIssue(this.getIssueById(result.getIssueId()));
//        return dto;
//    }
//
//    private Issue getIssueById(long issueId) throws SQLException {
//        IssueService issueService = new IssueService(new IssueRepo());
//        return issueService.getById(issueId).orElseThrow(() -> new SQLException("Error al obtener issue de issueAssignment"));
//    }
//
//    private Programmer getProgrammerById(long programmerId) throws SQLException {
//        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
//        return programmerService.getById(programmerId).orElseThrow(() -> new SQLException("Error al obtener Programmer de issueAssignment"));
//    }
//}
