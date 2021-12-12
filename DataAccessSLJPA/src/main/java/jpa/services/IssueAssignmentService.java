package jpa.services;

import jpa.daos.IssueAssignment;

import jpa.dtos.IssueAssignmentDTO;
import jpa.mappers.IssueAssigmentMapper;
import jpa.repositories.IssueAssigmentRepo;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueAssignmentService extends BaseService<IssueAssignment, Long, IssueAssigmentRepo>{
    private final IssueAssigmentMapper mapper = new IssueAssigmentMapper();

    public IssueAssignmentService(IssueAssigmentRepo repository) {
        super(repository);
    }

    public List<IssueAssignmentDTO> getAllIssuesAssignment () throws SQLException {
        List<IssueAssignment> issueAssignmentList = this.findAll().orElseThrow(() -> new SQLException("Error al obtener los issueAssignment"));
        List<IssueAssignmentDTO> issueAssignmentDTOS = new ArrayList<>();
        for (IssueAssignment issueAssignment : issueAssignmentList) {
            IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
            issueAssignmentDTOS.add(dto);
        }
        return issueAssignmentDTOS;
    }

    public IssueAssignmentDTO getIssueAssignmentById (Long issueId) throws SQLException {
        IssueAssignment issueAssignment = this.getById(issueId).orElseThrow(() -> new SQLException("Error al obtener issueAssignment con id: " + issueId));
        IssueAssignmentDTO dto = mapper.toDTO(issueAssignment);
        return dto;
    }

    public IssueAssignmentDTO insertIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
        IssueAssignment result = this.insert(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al insertar issueAssignment con id: " + issueAssignment.getId()));
        IssueAssignmentDTO dto = mapper.toDTO(result);
        return dto;
    }

    public IssueAssignmentDTO updateIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
        IssueAssignment result = this.update(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al actualizar issueAssignment con id: " + issueAssignment.getId()));
        IssueAssignmentDTO dto = mapper.toDTO(result);
        return dto;
    }

    public IssueAssignmentDTO deleteIssueAssignment (IssueAssignmentDTO issueAssignment) throws SQLException {
        IssueAssignment result = this.delete(mapper.fromDTO(issueAssignment)).orElseThrow(() -> new SQLException("Error al borrar issueAssignment con id: " + issueAssignment.getId()));
        IssueAssignmentDTO dto = mapper.toDTO(result);
        return dto;
    }
}
