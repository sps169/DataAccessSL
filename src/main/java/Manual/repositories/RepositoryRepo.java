package Manual.repositories;

import Manual.daos.Repository;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryRepo implements CRUDRepo<Repository,Long> {
    @Override
    public Optional<List<Repository>> findAll() throws SQLException {
        String query = "SELECT * FROM repository";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error RepositoryRepo al " +
                "consultar registros de repository"));
        ArrayList<Repository> list = new ArrayList<Repository>();
        db.close();
        while (result.next()) {
            list.add(
                    new Repository(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getDate("creation_date").toLocalDate().atTime(
                                    result.getTime("creation_date").toLocalTime()),
                            result.getLong("id_project")
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<Repository> getById(Long ID) throws SQLException {
        String query = "SELECT * FROM repository WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error RepositoryRepo al " +
                "consultar repository con ID " + ID));
        db.close();
        if (result.next()) {
            Repository repository = new Repository(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getDate("creation_date").toLocalDate().atTime(
                            result.getTime("creation_date").toLocalTime()),
                    result.getLong("id_project")
            );
            return Optional.of(repository);
        } else{
            throw new SQLException("Error RepositoryRepo no existe repository con ID: " + ID);
        }
    }

    @Override
    public Optional<Repository> save(Repository repository) throws SQLException {
        String query = "INSERT INTO repository VALUES (?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, repository.getId(),repository.getName(),repository.getCreationDate(),
                repository.getProjectId()).orElseThrow(() ->
                new SQLException("Error RepositoryRepo al consultar para insertar repository"));
        db.close();
        if (result.next()) {
            return Optional.of(repository);
        } else{
            throw new SQLException("Error RepositoryRepo al insertar repository en BD");
        }
    }

    @Override
    public Optional<Repository> update(Repository repository) throws SQLException {
        String query = "UPDATE repository SET name = ?, creation_date = ?, id_project = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,repository.getName(),repository.getCreationDate(),
                repository.getProjectId(), repository.getId());
        db.close();
        if (result > 0)
            return Optional.of(repository);
        else
            throw new SQLException("Error RepositoryRepo al actualizar repository con id: " + repository.getId());
    }

    @Override
    public Optional<Repository> delete(Repository repository) throws SQLException {
        String query = "DELETE FROM repository WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, repository.getId());
        db.close();
        if (res > 0)
            return Optional.of(repository);
        else
            throw new SQLException("Error RepositoryRepo al eliminar repository con id: " + repository.getId());

    }
}
