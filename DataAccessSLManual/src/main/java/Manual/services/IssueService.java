package Manual.services;

import Manual.daos.Issue;
import Manual.daos.Programmer;
import Manual.daos.Repository;
import Manual.dtos.IssueDTO;
import Manual.mappers.IssueMapper;
import Manual.repositories.IssueRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.repositories.RepositoryRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            issueDTO.setRepository(this.getRepositoryById(issue.getRepositoryId()));
            issueDTO.setBoss(this.getBossProgrammerById(issue.getBossId()));
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
        issueDTO.setRepository(this.getRepositoryById(issue.getRepositoryId()));
        issueDTO.setBoss(this.getBossProgrammerById(issue.getBossId()));
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
        result.setRepository(this.getRepositoryById(issue.getRepositoryId()));
        result.setBoss(this.getBossProgrammerById(issue.getBossId()));
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
        result.setRepository(this.getRepositoryById(issue.getRepositoryId()));
        result.setBoss(this.getBossProgrammerById(issue.getBossId()));
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
        result.setRepository(this.getRepositoryById(issue.getRepositoryId()));
        result.setBoss(this.getBossProgrammerById(issue.getBossId()));
        return result;
    }
    /**
     * Method that query to database using Repository DAO to find a Repository in the table repository by id.
     * @param repositoryId Long id to find Repository
     * @throws SQLException when fails in the query transaction
     * @return Repository
     */
    private Repository getRepositoryById(long repositoryId) throws SQLException {
        RepositoryService repositoryService = new RepositoryService(new RepositoryRepo());
        return repositoryService.getById(repositoryId).orElseThrow(()->new SQLException("Error al intentar obtener Repository por id"+ repositoryId+" para Issue"));
    }/**
     * Method that query to database using Programmer DAO to find a Boss in the table programmer by id.
     * @param bossId Long id to find Programmer
     * @throws SQLException when fails in the query transaction
     * @return Programmer
     */
    private Programmer getBossProgrammerById(long bossId) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(bossId).orElseThrow(()->new SQLException("Error al intentar obtener Boss Programmer por id"+ bossId+" para Issue"));
    }
}
