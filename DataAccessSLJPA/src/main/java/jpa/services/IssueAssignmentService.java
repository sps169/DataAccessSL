package jpa.services;

import jpa.daos.IssueAssignment;

import jpa.dtos.IssueAssignmentDTO;
import jpa.mappers.IssueAssignmentMapper;
import jpa.repositories.IssueAssigmentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that models from IssueAssignmentDTO using IssueAssignmentMapper to be intermediary between the
 * IssueAssignmentController and the database
 * @author sps169, FedericoTB
 */
public class IssueAssignmentService extends BaseService<IssueAssignment, Long, IssueAssigmentRepo>{
    private final IssueAssignmentMapper mapper = new IssueAssignmentMapper();

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
            issueAssignmentDTOS.add(dto);
        }
        return issueAssignmentDTOS;
    }
    /**
     * Method that query to database using IssueAssignment DAO to obtain a IssueAssignment in the table issue_assignment by an ID.
     * @param issueAssignmentId Long of the issueAssignment to find
     * @throws SQLException when fails in the query transaction
     * @return IssueAssignmentDTO
     */
    public IssueAssignmentDTO getIssueAssignmentById (Long issueAssignmentId) throws SQLException {
        IssueAssignment issueAssignment = this.getById(issueAssignmentId).orElseThrow(() -> new SQLException("Error al obtener issueAssignment con id: " + issueAssignmentId));
        IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
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
        return dto;
    }
}
