package Manual.services;

import Manual.daos.Commit;
import Manual.daos.Issue;
import Manual.daos.Project;
import Manual.daos.Repository;
import Manual.dtos.RepositoryDTO;
import Manual.mappers.RepositoryMapper;
import Manual.repositories.CommitRepo;
import Manual.repositories.IssueRepo;
import Manual.repositories.ProjectRepo;
import Manual.repositories.RepositoryRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            repositoryDTO.setProject(this.getProjectById(repository.getProjectId()));
            repositoryDTO.setCommits(this.getSetCommits(repository.getId()));
            repositoryDTO.setIssues(this.getSetIssues(repository.getId()));
            result.add(repositoryDTO);
        }
        return result;
    }

    public RepositoryDTO getRepositoryById(Long id) throws SQLException {
        Repository repository = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Repository por Id "+id));
        RepositoryDTO repositoryDTO = mapper.toDTO(repository);
        repositoryDTO.setProject(this.getProjectById(repository.getProjectId()));
        repositoryDTO.setCommits(this.getSetCommits(repository.getId()));
        repositoryDTO.setIssues(this.getSetIssues(repository.getId()));
        return repositoryDTO;
    }

    public RepositoryDTO insertRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.insert(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al insertar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setProject(this.getProjectById(repository.getProjectId()));
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }

    public RepositoryDTO updateRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.update(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al actualizar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setProject(this.getProjectById(repository.getProjectId()));
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }

    public RepositoryDTO deleteRepository (RepositoryDTO repositoryDTO) throws SQLException {
        Repository repository = this.delete(mapper.fromDTO(repositoryDTO)).orElseThrow(()->new SQLException("Error al borrar Repository"));
        RepositoryDTO result = mapper.toDTO(repository);
        result.setProject(this.getProjectById(repository.getProjectId()));
        result.setCommits(this.getSetCommits(repository.getId()));
        result.setIssues(this.getSetIssues(repository.getId()));
        return result;
    }
    private Project getProjectById(long projectId) throws SQLException {
        ProjectService projectService = new ProjectService(new ProjectRepo());
        return projectService.getById(projectId).orElseThrow(()->new SQLException("Error al intentar obtener Project por id"+ projectId+" Repository"));
    }
    private Set<Commit> getSetCommits(long id) throws SQLException {
        CommitService commitService = new CommitService(new CommitRepo());
        return commitService.findAll().get().stream().filter(s->s.getRepositoryId()==id).collect(Collectors.toSet());
    }
    private Set<Issue> getSetIssues(long id) throws SQLException {
        IssueService issueService = new IssueService(new IssueRepo());
        return issueService.findAll().get().stream().filter(s->s.getRepositoryId()==id).collect(Collectors.toSet());
    }
}
