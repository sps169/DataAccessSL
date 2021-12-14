package Manual.repositories;

import Manual.daos.Issue;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Issue Repository class
 * with the Database using Database Controller
 * @author sps169, FedericoTB
 */
public class IssueRepo implements CRUDRepo<Issue,Long> {
    /**
     * Method that query to database using Database Controller to obtain all Issues in the table issue.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Issue>>
     */
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
    /**
     * Method that query to database using Database Controller to obtain a Issue in the table issue by an ID.
     * @param ID Long of the issue to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue>
     */
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
    /**
     * Method that query to database using Database Controller to insert a Issue in the table issue.
     * @param issue Issue object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue> of Issue object inserted
     */
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
    /**
     * Method that query to database using Database Controller to update a Issue in the table issue.
     * @param issue Issue object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue> of Issue object updated
     */
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
    /**
     * Method that query to database using Database Controller to delete a Issue in the table issue.
     * @param issue Issue object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue> of Issue object deleted
     */
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
