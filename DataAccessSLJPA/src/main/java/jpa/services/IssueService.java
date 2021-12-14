package jpa.services;

import jpa.daos.Issue;
import jpa.dtos.IssueDTO;
import jpa.mappers.IssueMapper;
import jpa.repositories.IssueRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that models from IssueDTO using IssueMapper to be intermediary between the IssueController and
 * the database
 * @author sps169, FedericoTB
 */
public class IssueService extends BaseService<Issue,Long, IssueRepo> {
    private final IssueMapper mapper = new IssueMapper();
    public IssueService(IssueRepo repository) {super(repository);}
    /**
     * Method that query to database using Issue DAO to obtain all BossHistories in the table issue.
     * @throws SQLException when fails in the query transaction
     * @return List<IssueDto>
     */
    public List<IssueDTO> getAllIssues() throws SQLException {
        List<Issue> issues = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Issues"));
        List<IssueDTO> result = new ArrayList<>();
        for(Issue issue: issues){
            IssueDTO issueDTO = mapper.toDTO(issue);
            result.add(issueDTO);
        }
        return result;
    }
    /**
     * Method that query to database using Issue DAO to obtain a Issue in the table issue by an ID.
     * @param id Long of the issue to find
     * @throws SQLException when fails in the query transaction
     * @return IssueDTO
     */
    public IssueDTO getIssueById(Long id) throws SQLException {
        Issue issue = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Issue por Id "+id));
        IssueDTO issueDTO = mapper.toDTO(issue);
        return issueDTO;
    }
    /**
     * Method that query to database using Issue DAO to insert a Issue in the table issue.
     * @param issueDTO Issue object to insert
     * @throws SQLException when fails in the query transaction
     * @return IssueDTO of Issue object inserted
     */
    public IssueDTO insertIssue (IssueDTO issueDTO) throws SQLException {
        Issue issue = this.insert(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al insertar Issue"));
        IssueDTO result = mapper.toDTO(issue);
        return result;
    }
    /**
     * Method that query to database using Issue DAO to update a Issue in the table issue.
     * @param issueDTO Issue object to update
     * @throws SQLException when fails in the query transaction
     * @return IssueDTO of Issue object updated
     */
    public IssueDTO updateIssue (IssueDTO issueDTO) throws SQLException {
        Issue issue = this.update(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al actualizar Issue"));
        IssueDTO result = mapper.toDTO(issue);
        return result;
    }
    /**
     * Method that query to database using Issue DAO to delete a Issue in the table issue.
     * @param issueDTO Issue object to delete
     * @throws SQLException when fails in the query transaction
     * @return IssueDTO of Issue object deleted
     */
    public IssueDTO deleteIssue (IssueDTO issueDTO) throws SQLException {
        Issue issue = this.delete(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al borrar Issue"));
        IssueDTO result = mapper.toDTO(issue);
        return result;
    }
}
