package Manual.services;

import Manual.daos.Issue;
import Manual.daos.IssueAssignment;
import Manual.daos.Programmer;
import Manual.dtos.IssueAssignmentDTO;
import Manual.mappers.IssueAssigmentMapper;
import Manual.repositories.IssueAssigmentRepo;
import Manual.repositories.IssueRepo;
import Manual.repositories.ProgrammerRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that models from IssueAssignmentDTO using IssueAssignmentMapper to be intermediary between the
 * IssueAssignmentController and the database
 * @author sps169, FedericoTB
 */
public class IssueAssignmentService extends BaseService<IssueAssignment, Long, IssueAssigmentRepo>{
    private final IssueAssigmentMapper mapper = new IssueAssigmentMapper();

    public IssueAssignmentService(IssueAssigmentRepo repository) {
        super(repository);
    }
    /**
     * Method that query to database using IssueAssignment DAO to obtain all BossHistories in the table issue_assignment.
     * @throws SQLException when fails in the query transaction
     * @return List<IssueAssignmentDto>
     */
    public List<IssueAssignmentDTO> getAllIssuesAssignment () throws SQLException {
        List<IssueAssignment> issueAssignmentList = this.findAll().orElseThrow(() -> new SQLException("Error al obtener los issueAssignment"));
        List<IssueAssignmentDTO> issueAssignmentDTOS = new ArrayList<>();
        for (IssueAssignment issueAssignment : issueAssignmentList) {
            IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
            dto.setProgrammer(this.getProgrammerById(issueAssignment.getProgrammerId()));
            dto.setIssue(this.getIssueById(issueAssignment.getIssueId()));
            issueAssignmentDTOS.add(dto);
        }
        return issueAssignmentDTOS;
    }
    /**
     * Method that query to database using IssueAssignment DAO to obtain a IssueAssignment in the table issue_assignment by an ID.
     * @param issueId Long of the issueAssignment to find
     * @throws SQLException when fails in the query transaction
     * @return IssueAssignmentDTO
     */
    public IssueAssignmentDTO getIssueAssignmentById (Long issueId) throws SQLException {
        IssueAssignment issueAssignment = this.getById(issueId).orElseThrow(() -> new SQLException("Error al obtener issueAssignment con id: " + issueId));
        IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
        dto.setProgrammer(this.getProgrammerById(issueAssignment.getProgrammerId()));
        dto.setIssue(this.getIssueById(issueAssignment.getIssueId()));
        return dto;
    }
    /**
     * Method that query to database using IssueAssignment DAO to insert a IssueAssignment in the table issue_assignment.
     * @param issueAssignment IssueAssignment object to insert
     * @throws SQLException when fails in the query transaction
     * @return IssueAssignmentDTO of IssueAssignment object inserted
     */
    public IssueAssignmentDTO insertIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
        IssueAssignment result = this.insert(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al insertar issueAssignment con id: " + issueAssignment.getId()));
        IssueAssignmentDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setIssue(this.getIssueById(result.getIssueId()));
        return dto;
    }
    /**
     * Method that query to database using IssueAssignment DAO to update a IssueAssignment in the table issue_assignment.
     * @param issueAssignment IssueAssignment object to update
     * @throws SQLException when fails in the query transaction
     * @return IssueAssignmentDTO of IssueAssignment object updated
     */
    public IssueAssignmentDTO updateIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
        IssueAssignment result = this.update(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al actualizar issueAssignment con id: " + issueAssignment.getId()));
        IssueAssignmentDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setIssue(this.getIssueById(result.getIssueId()));
        return dto;
    }
    /**
     * Method that query to database using IssueAssignment DAO to delete a IssueAssignment in the table issue_assignment.
     * @param issueAssignment IssueAssignment object to delete
     * @throws SQLException when fails in the query transaction
     * @return IssueAssignmentDTO of IssueAssignment object deleted
     */
    public IssueAssignmentDTO deleteIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
        IssueAssignment result = this.delete(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al borrar issueAssignment con id: " + issueAssignment.getId()));
        IssueAssignmentDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setIssue(this.getIssueById(result.getIssueId()));
        return dto;
    }
    /**
     * Method that query to database using Issue DAO to find a Issue in the table issue by id.
     * @param issueId Long id to find Issue
     * @throws SQLException when fails in the query transaction
     * @return Issue
     */
    private Issue getIssueById(long issueId) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        return issueService.getById(issueId).orElseThrow(() -> new SQLException("Error al obtener issue de issueAssignment"));
    }
    /**
     * Method that query to database using Programmer DAO to find a Programmer in the table programmer by id.
     * @param programmerId Long id to find Programmer
     * @throws SQLException when fails in the query transaction
     * @return Programmer
     */
    private Programmer getProgrammerById(long programmerId) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(programmerId).orElseThrow(() -> new SQLException("Error al obtener Programmer de issueAssignment"));
    }
}
