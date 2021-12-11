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
            result.add(commitDTO);
        }
        return result;
    }

    public CommitDTO getCommitById(Long id) throws SQLException {
        Commit commit = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Project por Id "+id));
        CommitDTO commitDTO = mapper.toDTO(commit);
        return commitDTO;
    }

    public CommitDTO insertCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.insert(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al insertar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        return result;
    }

    public CommitDTO updateCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.update(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al actualizar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        return result;
    }

    public CommitDTO deleteCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.delete(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al borrar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        return result;
    }
}
