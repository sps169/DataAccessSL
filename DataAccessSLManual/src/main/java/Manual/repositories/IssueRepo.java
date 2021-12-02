package Manual.repositories;

import Manual.daos.Issue;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IssueRepo implements CRUDRepo<Issue,Long> {
    @Override
    public Optional<List<Issue>> findAll() throws SQLException {
        String query = "SELECT * FROM issue";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error IssueRepository al " +
                "consultar registros de issue"));
        ArrayList<Issue> list = new ArrayList<Issue>();
        db.close();
        while (result.next()) {
            list.add(
                    new Issue(
                            result.getLong("id"),
                            result.getString("title"),
                            result.getString("text"),
                            result.getDate("date").toLocalDate().atTime(
                                    result.getTime("date").toLocalTime()),
                            result.getString("state"),
                            result.getLong("id_repository"),
                            result.getLong("id_boss")
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<Issue> getById(Long ID) throws SQLException {
        String query = "SELECT * FROM issue WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error IssueRepository al " +
                "consultar issue con ID " + ID));
        db.close();
        if (result.next()) {
            Issue issue = new Issue(
                    result.getLong("id"),
                    result.getString("title"),
                    result.getString("text"),
                    result.getDate("date").toLocalDate().atTime(
                            result.getTime("date").toLocalTime()),
                    result.getString("state"),
                    result.getLong("id_repository"),
                    result.getLong("id_boss")
            );
            return Optional.of(issue);
        } else{
            throw new SQLException("Error IssueRepository no existe issue con ID: " + ID);
        }
    }

    @Override
    public Optional<Issue> insert(Issue issue) throws SQLException {
        String query = "INSERT INTO issue VALUES (?,?,?,?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, issue.getId(),issue.getTitle(),issue.getText(),
                issue.getDate(),issue.getState(), issue.getRepositoryId(),issue.getBossId()).orElseThrow(() ->
                new SQLException("Error CommitRepository al consultar para insertar issue"));
        db.close();
        if (result.next()) {
            return Optional.of(issue);
        } else{
            throw new SQLException("Error IssueRepository al insertar issue en BD");
        }
    }

    @Override
    public Optional<Issue> update(Issue issue) throws SQLException {
        String query = "UPDATE issue SET title = ?, text = ?, date = ?, state =?, id_repository = ?,"
                + " id_boss = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,issue.getTitle(),issue.getText(),
                issue.getDate(), issue.getState(), issue.getRepositoryId(),
                issue.getBossId(), issue.getId());
        db.close();
        if (result > 0)
            return Optional.of(issue);
        else
            throw new SQLException("Error IssueRepository al actualizar issue con id: " + issue.getId());
    }

    @Override
    public Optional<Issue> delete(Issue issue) throws SQLException {
        String query = "DELETE FROM issue WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, issue.getId());
        db.close();
        if (res > 0)
            return Optional.of(issue);
        else
            throw new SQLException("Error IssueRepository al eliminar issue con id: " + issue.getId());
    }
}
