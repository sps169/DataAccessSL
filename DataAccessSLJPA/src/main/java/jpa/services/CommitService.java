package jpa.services;

import jpa.daos.Commit;
import jpa.dtos.CommitDTO;
import jpa.mappers.CommitMapper;
import jpa.repositories.CommitRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    /**
     * Method that query to database using Commit DAO to obtain all BossHistories in the table commit.
     * @throws SQLException when fails in the query transaction
     * @return List<CommitDto>
     */
    public List<CommitDTO> getAllCommits() throws SQLException {
        List<Commit> commits = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Projects"));
        List<CommitDTO> result = new ArrayList<>();
        for(Commit commit: commits){
            CommitDTO commitDTO = mapper.toDTO(commit);
            result.add(commitDTO);
        }
        return result;
    }
    /**
     * Method that query to database using Commit DAO to obtain a Commit in the table commit by an ID.
     * @param id Long of the commit to find
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO
     */
    public CommitDTO getCommitById(Long id) throws SQLException {
        Commit commit = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Project por Id "+id));
        CommitDTO commitDTO = mapper.toDTO(commit);
        return commitDTO;
    }
    /**
     * Method that query to database using Commit DAO to insert a Commit in the table commit.
     * @param commitDTO Commit object to insert
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO of Commit object inserted
     */
    public CommitDTO insertCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.insert(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al insertar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        return result;
    }
    /**
     * Method that query to database using Commit DAO to update a Commit in the table commit.
     * @param commitDTO Commit object to update
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO of Commit object updated
     */
    public CommitDTO updateCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.update(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al actualizar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        return result;
    }
    /**
     * Method that query to database using Commit DAO to delete a Commit in the table commit.
     * @param commitDTO Commit object to delete
     * @throws SQLException when fails in the query transaction
     * @return CommitDTO of Commit object deleted
     */
    public CommitDTO deleteCommit (CommitDTO commitDTO) throws SQLException {
        Commit commit = this.delete(mapper.fromDTO(commitDTO)).orElseThrow(()->new SQLException("Error al borrar Commit"));
        CommitDTO result = mapper.toDTO(commit);
        return result;
    }
}
