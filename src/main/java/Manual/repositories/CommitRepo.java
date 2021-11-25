package Manual.repositories;

import Manual.daos.Commit;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommitRepo implements CRUDRepo<Commit,Long>{
    @Override
    public Optional<List<Commit>> findAll() throws SQLException {
        String query = "SELECT * FROM commit";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error CommitRepository al " +
                "consultar registros de Commit"));
        ArrayList<Commit> list = new ArrayList<Commit>();
        db.close();
        while (result.next()) {
            list.add(
                    new Commit(
                            result.getLong("id"),
                            result.getString("text"),
                            result.getString("title"),
                            result.getDate("date").toLocalDate().atTime(
                                    result.getTime("date").toLocalTime()),
                            result.getLong("id_repository"),
                            result.getLong("id_programmer"),
                            result.getLong("id_issue")
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<Commit> getById(Long ID) throws SQLException {
        String query = "SELECT * FROM commit WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error CommitRepository al " +
                "consultar commit con ID " + ID));
        db.close();
        if (result.next()) {
            Commit commit = new Commit(
                    result.getLong("id"),
                    result.getString("text"),
                    result.getString("title"),
                    result.getDate("date").toLocalDate().atTime(
                            result.getTime("date").toLocalTime()),
                    result.getLong("id_repository"),
                    result.getLong("id_programmer"),
                    result.getLong("id_issue")
            );
            return Optional.of(commit);
        } else{
            throw new SQLException("Error CommitRepository no existe commit con ID: " + ID);
        }
    }

    @Override
    public Optional<Commit> save(Commit commit) throws SQLException {
        String query = "INSERT INTO commit VALUES (?,?,?,?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, commit.getId(),commit.getText(),commit.getTitle(),
                commit.getDate(), commit.getRepositoryId(),commit.getProgrammerId(),
                commit.getIssueId()).orElseThrow(() ->
                new SQLException("Error CommitRepository al consultar para insertar commit"));
        db.close();
        if (result.next()) {
            return Optional.of(commit);
        } else{
            throw new SQLException("Error CommitRepository al insertar commit en BD");
        }
    }

    @Override
    public Optional<Commit> update(Commit commit) throws SQLException {
        String query = "UPDATE commit SET text = ?, title = ?, date = ?,id_repository = ?, id_programmer =?,"
                + " id_issue = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,commit.getText(),commit.getTitle(),
                commit.getDate(), commit.getRepositoryId(),commit.getProgrammerId(),
                commit.getIssueId(), commit.getId());
        db.close();
        if (result > 0)
            return Optional.of(commit);
        else
            throw new SQLException("Error CommitRepository al actualizar commit con id: " + commit.getId());
    }

    @Override
    public Optional<Commit> delete(Commit commit) throws SQLException {
        String query = "DELETE FROM commit WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, commit.getId());
        db.close();
        if (res > 0)
            return Optional.of(commit);
        else
            throw new SQLException("Error CommitRepository al eliminar commit con id: " + commit.getId());
    }
}
