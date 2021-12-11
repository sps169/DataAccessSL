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

public class RepositoryService extends BaseService<Repository,Long, RepositoryRepo> {
    private final RepositoryMapper mapper = new RepositoryMapper();

    public RepositoryService(RepositoryRepo repository) {super(repository);}

    public List<RepositoryDTO> getAllRepositories() throws SQLException {
        List<Repository> repositories = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Repositories"));
        List<RepositoryDTO> result = new ArrayList<>();
        for(Repository repository: repositories){
            RepositoryDTO repositoryDTO = mapper.toDTO(repository);
            repositoryDTO.setCommits(this.getSetCommits(repository.getId()));
            repositoryDTO.setIssues(this.getSetIssues(repository.getId()));
            result.add(repositoryDTO);
        }
        return result;
    }

    public RepositoryDTO getRepositoryById(Long id) throws SQLException {
        Repository repository = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Repository por Id "+id));
        RepositoryDTO repositoryDTO = mapper.toDTO(repository);
        repositoryDTO.setCommits(this.getSetCommits(repository.getId()));
        repositoryDTO.setIssues(this.getSetIssues(repository.getId()));
        return repositoryDTO;
    }

    public RepositoryDTO insertRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.insert(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al insertar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }

    public RepositoryDTO updateRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.update(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al actualizar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }

    public RepositoryDTO deleteRepository (RepositoryDTO repositoryDTO) throws SQLException {
        for (Commit commit : repositoryDTO.getCommits())
            deleteCommit(commit);
        for (Issue issue : repositoryDTO.getIssues())
            deleteIssue(issue);
        Repository repository = this.delete(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al borrar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }

    private Set<Commit> getSetCommits(long id) throws SQLException {
        CommitService commitService = new CommitService(new CommitRepo());
        return commitService.findAll().orElseThrow(() -> new SQLException("Error al obtener Commits para Repository"))
                .stream().filter(s->s.getRepository().getId()==id).collect(Collectors.toSet());
    }
    private Set<Issue> getSetIssues(long id) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        return issueService.findAll().orElseThrow(() -> new SQLException("Error al obtener Issues para Repository"))
                .stream().filter(s->s.getRepository().getId()==id).collect(Collectors.toSet());
    }

    private void deleteCommit(Commit commit) throws SQLException {
        CommitService commitService = new CommitService(new CommitRepo());
        commitService.delete(commit);
    }

    private void deleteIssue(Issue issue) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        IssueAssignmentService issueAssignmentService = new IssueAssignmentService(new IssueAssigmentRepo());
        List<IssueAssignment> issueAssignmentList = issueAssignmentService.findAll().orElseThrow(
                    () -> new SQLException("Error al obtener issueAssignments de issue con id " + issue.getId())
                ).stream().filter(s -> s.getIssue().getId() == issue.getId()).collect(Collectors.toList());
        for (IssueAssignment issueAssign : issueAssignmentList)
            issueAssignmentService.delete(issueAssign);
        issueService.delete(issue);
    }
}
