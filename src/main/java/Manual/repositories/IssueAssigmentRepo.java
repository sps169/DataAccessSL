package Manual.repositories;

import Manual.daos.BossHistory;
import Manual.daos.IssueAssignment;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IssueAssigmentRepo implements CRUDMultRepo<IssueAssignment,Long> {
    @Override
    public Optional<List<IssueAssignment>> findAll() throws SQLException {
        String query = "SELECT * FROM issue_assignment";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error IssueAssignmentRepository al " +
                "consultar registros de issue_assignment"));
        ArrayList<IssueAssignment> list = new ArrayList<IssueAssignment>();
        db.close();
        while (result.next()) {
            list.add(
                    new IssueAssignment(
                            result.getLong("id_programmer"),
                            result.getLong("id_issue"),
                            result.getTimestamp("start_date").toLocalDateTime()
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<IssueAssignment> getById(Long IDprog, Long IDissue, LocalDateTime entry) throws SQLException {
            String query = "SELECT * FROM issue_assignment WHERE id_programmer = ? AND id_issue = ? AND start_date = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query, IDprog,IDissue,entry).orElseThrow(() -> new SQLException("Error " +
                    "IssueAssignmentRepository al consultar issue_assignment con ID " + IDprog));
            db.close();
            if (result.next()) {
                IssueAssignment issueAssignment = new IssueAssignment(
                        result.getLong("id_programmer"),
                        result.getLong("id_issue"),
                        result.getTimestamp("start_date").toLocalDateTime()
                );
                return Optional.of(issueAssignment);
            } else{
                throw new SQLException("Error IssueAssignmentRepository no existe issue_assignment con ID: " + IDprog);
            }
    }

    @Override
    public Optional<IssueAssignment> insert(IssueAssignment issueAssignment) throws SQLException {
        String query = "INSERT INTO issue_assignment VALUES (?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, issueAssignment.getProgrammerId(),issueAssignment.getIssueId(),
                issueAssignment.getStartDate()).orElseThrow(() ->
                new SQLException("Error IssueAssignmentRepository al consultar para insertar issue_assignment"));
        db.close();
        if (result != null) {
            return Optional.of(issueAssignment);
        } else{
            throw new SQLException("Error IssueAssignmentRepository al insertar issue_assignment en BD");
        }
    }

    @Override
    public Optional<IssueAssignment> update(IssueAssignment issueAssignment) throws SQLException {
        String query = "UPDATE issue_assignment SET id_programmer = ?, id_issue = ?, start_date = ? " +
                "WHERE id_programmer = ? AND id_issue = ? AND start_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,issueAssignment.getProgrammerId(),issueAssignment.getIssueId(),
                issueAssignment.getStartDate(), issueAssignment.getProgrammerId(),issueAssignment.getIssueId(),
                issueAssignment.getStartDate());
        db.close();
        if (result > 0)
            return Optional.of(issueAssignment);
        else
            throw new SQLException("Error IssueAssignmentRepository al actualizar issue_assignment con id: " +
                    issueAssignment.getProgrammerId());
    }

    @Override
    public Optional<IssueAssignment> delete(IssueAssignment issueAssignment) throws SQLException {
        String query = "DELETE FROM issue_assignment WHERE id_programmer = ? AND id_issue = ? AND start_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, issueAssignment.getProgrammerId(),issueAssignment.getIssueId(),
                issueAssignment.getStartDate());
        db.close();
        if (res > 0)
            return Optional.of(issueAssignment);
        else
            throw new SQLException("Error IssueAssignmentRepository al eliminar issue_assignment con id: " +
                    issueAssignment.getProgrammerId());
    }
}
