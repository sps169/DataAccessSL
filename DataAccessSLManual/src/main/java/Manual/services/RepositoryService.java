package Manual.services;

import Manual.daos.*;
import Manual.dtos.RepositoryDTO;
import Manual.mappers.RepositoryMapper;
import Manual.repositories.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Class that models from RepositoryDTO using RepositoryMapper to be intermediary between the RepositoryController and
 * the database
 * @author sps169, FedericoTB
 */
public class RepositoryService extends BaseService<Repository,Long, RepositoryRepo> {
    private final RepositoryMapper mapper = new RepositoryMapper();

    public RepositoryService(RepositoryRepo repository) {super(repository);}
    /**
     * Method that query to database using Repository DAO to obtain all BossHistories in the table repository.
     * @throws SQLException when fails in the query transaction
     * @return List<RepositoryDto>
     */
    public List<RepositoryDTO> getAllRepositories() throws SQLException {
        List<Repository> repositories = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Repositories"));
        List<RepositoryDTO> result = new ArrayList<>();
        for(Repository repository: repositories){
            RepositoryDTO repositoryDTO = mapper.toDTO(repository);
            repositoryDTO.setProject(this.getProjectById(repository.getProjectId()));
            repositoryDTO.setCommits(this.getSetCommits(repository.getId()));
            repositoryDTO.setIssues(this.getSetIssues(repository.getId()));
            result.add(repositoryDTO);
        }
        return result;
    }
    /**
     * Method that query to database using Repository DAO to obtain a Repository in the table repository by an ID.
     * @param id Long of the repository to find
     * @throws SQLException when fails in the query transaction
     * @return RepositoryDTO
     */
    public RepositoryDTO getRepositoryById(Long id) throws SQLException {
        Repository repository = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Repository por Id "+id));
        RepositoryDTO repositoryDTO = mapper.toDTO(repository);
        repositoryDTO.setProject(this.getProjectById(repository.getProjectId()));
        repositoryDTO.setCommits(this.getSetCommits(repository.getId()));
        repositoryDTO.setIssues(this.getSetIssues(repository.getId()));
        return repositoryDTO;
    }
    /**
     * Method that query to database using Repository DAO to insert a Repository in the table repository.
     * @param repositoryDTO Repository object to insert
     * @throws SQLException when fails in the query transaction
     * @return RepositoryDTO of Repository object inserted
     */
    public RepositoryDTO insertRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.insert(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al insertar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setProject(this.getProjectById(repository.getProjectId()));
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }
    /**
     * Method that query to database using Repository DAO to update a Repository in the table repository.
     * @param repositoryDTO Repository object to update
     * @throws SQLException when fails in the query transaction
     * @return RepositoryDTO of Repository object updated
     */
    public RepositoryDTO updateRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.update(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al actualizar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setProject(this.getProjectById(repository.getProjectId()));
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }
    /**
     * Method that query to database using Repository DAO to delete a Repository in the table repository.
     * @param repositoryDTO Repository object to delete
     * @throws SQLException when fails in the query transaction
     * @return RepositoryDTO of Repository object deleted
     */
    public RepositoryDTO deleteRepository (RepositoryDTO repositoryDTO) throws SQLException {
        for (Commit commit : repositoryDTO.getCommits())
            deleteCommit(commit);
        for (Issue issue : repositoryDTO.getIssues())
            deleteIssue(issue);
        Repository repository = this.delete(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al borrar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setProject(this.getProjectById(repository.getProjectId()));
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }
    /**
     * Method that query to database using Project DAO to find a Project in the table project by id.
     * @param projectId Long id to find Project
     * @throws SQLException when fails in the query transaction
     * @return Project
     */
    private Project getProjectById(long projectId) throws SQLException {
        ProjectService projectService = new ProjectService(new ProjectRepo());
        return projectService.getById(projectId).orElseThrow(()->new SQLException("Error al intentar obtener Project por id"+ projectId+" Repository"));
    }
    /**
     * Method that query to database using CommitService to obtain a Commit in the with the repository ID.
     * @param id Long of the Commit with the repository ID to find
     * @throws SQLException when fails in the query transaction
     * @return Set<Commit>
     */
    private Set<Commit> getSetCommits(long id) throws SQLException {
        CommitService commitService = new CommitService(new CommitRepo());
        return commitService.findAll().orElseThrow(() -> new SQLException("Error al obtener Commits para Repository"))
                .stream().filter(s->s.getRepositoryId()==id).collect(Collectors.toSet());
    }
    /**
     * Method that query to database using IssueService to obtain an Issue in the with the repository ID.
     * @param id Long of the Issue with the repository ID to find
     * @throws SQLException when fails in the query transaction
     * @return Set<Commit>
     */
    private Set<Issue> getSetIssues(long id) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        return issueService.findAll().orElseThrow(() -> new SQLException("Error al obtener Issues para Repository"))
                .stream().filter(s->s.getRepositoryId()==id).collect(Collectors.toSet());
    }
    /**
     * Method that query to database using CommitService to delete a Commit in the table commit.
     * @param commit Commit object to delete
     * @throws SQLException when fails in the query transaction
     */
    private void deleteCommit(Commit commit) throws SQLException {
        CommitService commitService = new CommitService(new CommitRepo());
        commitService.delete(commit);
    }
    /**
     * Method that query to database using IssueService to delete a Issue in the table issue.
     * @param issue Issue object to delete
     * @throws SQLException when fails in the query transaction
     */
    private void deleteIssue(Issue issue) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        IssueAssignmentService issueAssignmentService = new IssueAssignmentService(new IssueAssigmentRepo());
        List<IssueAssignment> issueAssignmentList = issueAssignmentService.findAll().orElseThrow(
                    () -> new SQLException("Error al obtener issueAssignments de issue con id " + issue.getId())
                ).stream().filter(s -> s.getIssueId() == issue.getId()).collect(Collectors.toList());
        for (IssueAssignment issueAssign : issueAssignmentList)
            issueAssignmentService.delete(issueAssign);
        issueService.delete(issue);
    }
}
