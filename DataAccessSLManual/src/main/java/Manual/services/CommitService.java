package Manual.services;

import Manual.daos.Commit;
import Manual.daos.Issue;
import Manual.daos.Programmer;
import Manual.daos.Repository;
import Manual.dtos.CommitDTO;
import Manual.mappers.CommitMapper;
import Manual.repositories.CommitRepo;
import Manual.repositories.IssueRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.repositories.RepositoryRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class that models from CommitDTO using CommitMapper to be intermediary between the CommitController and
 * the database
 * @author sps169, FedericoTB
 */
public class CommitService extends BaseService<Commit,Long, CommitRepo> {
    private final CommitMapper mapper = new CommitMapper();
    public CommitService(CommitRepo repository) {
        super(repository);
    }
    public List<CommitDTO> getAllCommits() throws SQLException {
        List<Commit> commits = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Projects"));
        List<CommitDTO> result = new ArrayList<>();
        for(Commit commit: commits){
            CommitDTO commitDTO = mapper.toDTO(commit);
            commitDTO.setRepository(this.getRepositoryById(commit.getRepositoryId()));
            commitDTO.setProgrammer(this.getProgrammerById(commit.getProgrammerId()));
            commitDTO.setIssue(this.getIssueById(commit.getIssueId()));
            result.add(commitDTO);
        }
        return result;
    }
    /**
     * Method that query to database using Commit DAO to obtain all BossHistories in the table commit.
     * @throws SQLException when fails in the query transaction
     * @return List<CommitDto>
     */
    public CommitDTO getCommitById(Long id) throws SQLException {
        Commit commit = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Project por Id "+id));
        CommitDTO commitDTO = mapper.toDTO(commit);
        commitDTO.setRepository(this.getRepositoryById(commit.getRepositoryId()));
        commitDTO.setProgrammer(this.getProgrammerById(commit.getProgrammerId()));
        commitDTO.setIssue(this.getIssueById(commit.getIssueId()));
        return commitDTO;
    }
    /**
     * Method that query to database using Commit DAO to obtain a Commit in the table commit by an ID.
     * @param commitDTO Long of the commit to find
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO
     */
    public CommitDTO insertCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.insert(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al insertar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        result.setRepository(this.getRepositoryById(commit.getRepositoryId()));
        result.setProgrammer(this.getProgrammerById(commit.getProgrammerId()));
        result.setIssue(this.getIssueById(commit.getIssueId()));
        return result;
    }
    /**
     * Method that query to database using Commit DAO to insert a Commit in the table commit.
     * @param commitDTO Commit object to insert
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO of Commit object inserted
     */
    public CommitDTO updateCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.update(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al actualizar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        result.setRepository(this.getRepositoryById(commit.getRepositoryId()));
        result.setProgrammer(this.getProgrammerById(commit.getProgrammerId()));
        result.setIssue(this.getIssueById(commit.getIssueId()));
        return result;
    }
    /**
     * Method that query to database using Commit DAO to update a Commit in the table commit.
     * @param commitDTO Commit object to update
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO of Commit object updated
     */
    public CommitDTO deleteCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.delete(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al borrar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        result.setRepository(this.getRepositoryById(commit.getRepositoryId()));
        result.setProgrammer(this.getProgrammerById(commit.getProgrammerId()));
        result.setIssue(this.getIssueById(commit.getIssueId()));
        return result;
    }
    /**
     * Method that query to database using Issue DAO to find a Issue in the table issue by id.
     * @param issueId Long id to find Issue
     * @throws SQLException when fails in the query transaction
     * @return Issue
     */
    private Issue getIssueById(long issueId) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        return issueService.getById(issueId).orElseThrow(
                ()->new SQLException("Error al intentar obtener Issue por id"+ issueId+" para Commit"));
    }
    /**
     * Method that query to database using Programmer DAO to find a Programmer in the table programmer by id.
     * @param programmerId Long id to find Programmer
     * @throws SQLException when fails in the query transaction
     * @return Programmer
     */
    private Programmer getProgrammerById(long programmerId) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(programmerId).orElseThrow(
                ()->new SQLException("Error al intentar obtener Programmer por id"+ programmerId+" para Commit"));
    }
    /**
     * Method that query to database using Repository DAO to find a Repository in the table repository by id.
     * @param repositoryId Long id to find Repository
     * @throws SQLException when fails in the query transaction
     * @return Repository
     */
    private Repository getRepositoryById(long repositoryId) throws SQLException {
        RepositoryService repositoryService = new RepositoryService(new RepositoryRepo());
        return repositoryService.getById(repositoryId).orElseThrow(
                ()->new SQLException("Error al intentar obtener Repository por id"+ repositoryId+" para Commit"));
    }
}
