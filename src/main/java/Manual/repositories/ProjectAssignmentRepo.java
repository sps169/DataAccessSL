package Manual.repositories;

import Manual.daos.BossHistory;
import Manual.daos.ProjectAssignment;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectAssignmentRepo implements CRUDMultRepo<ProjectAssignment,Long> {
    @Override
    public Optional<List<ProjectAssignment>> findAll() throws SQLException {
        String query = "SELECT * FROM project_assignment";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error ProjectAssignmentRepository al " +
                "consultar registros de project_assignment"));
        ArrayList<ProjectAssignment> list = new ArrayList<ProjectAssignment>();
        db.close();
        while (result.next()) {
            list.add(
                    new ProjectAssignment(
                            result.getLong("id_programmer"),
                            result.getLong("id_project"),
                            result.getDate("start_date").toLocalDate().atTime(
                                    result.getTime("start_date").toLocalTime()),
                            result.getDate("end_date").toLocalDate().atTime(
                                    result.getTime("end_date").toLocalTime())
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<ProjectAssignment> getById(Long IDprog, Long IDdep, LocalDateTime entry) throws SQLException {
        String query = "SELECT * FROM project_assignment WHERE id_programmer = ? AND id_project = ? AND start_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, IDprog,IDdep,entry).orElseThrow(() -> new SQLException("Error " +
                "ProjectAssignmentRepository al consultar project_assignment con ID " + IDprog));
        db.close();
        if (result.next()) {
            ProjectAssignment projectAssignment = new ProjectAssignment(
                    result.getLong("id_programmer"),
                    result.getLong("id_project"),
                    result.getDate("start_date").toLocalDate().atTime(
                            result.getTime("start_date").toLocalTime()),
                    result.getDate("end_date").toLocalDate().atTime(
                            result.getTime("end_date").toLocalTime())
            );
            return Optional.of(projectAssignment);
        } else{
            throw new SQLException("Error ProjectAssignmentRepository no existe project_assignment con ID: " + IDprog);
        }
    }

    @Override
    public Optional<ProjectAssignment> insert(ProjectAssignment projectAssignment) throws SQLException {
        String query = "INSERT INTO project_assignment VALUES (?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, projectAssignment.getProgrammerId(),projectAssignment.getProjectId(),
                projectAssignment.getStartDate(), projectAssignment.getEndDate()).orElseThrow(() ->
                new SQLException("Error ProjectAssignmentRepository al consultar para insertar project_assignment"));
        db.close();
        if (result.next()) {
            return Optional.of(projectAssignment);
        } else{
            throw new SQLException("Error ProjectAssignmentRepository al insertar project_assignment en BD");
        }
    }

    @Override
    public Optional<ProjectAssignment> update(ProjectAssignment projectAssignment) throws SQLException {
        String query = "UPDATE project_assignment SET id_programmer = ?, id_project = ?, start_date = ?,end_date = ? " +
                "WHERE id_programmer = ? AND id_project = ? AND start_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query, projectAssignment.getProgrammerId(),projectAssignment.getProjectId(),
                projectAssignment.getStartDate(), projectAssignment.getEndDate(), projectAssignment.getProgrammerId(),
                projectAssignment.getProjectId(), projectAssignment.getStartDate());
        db.close();
        if (result > 0)
            return Optional.of(projectAssignment);
        else
            throw new SQLException("Error ProjectAssignmentRepository al actualizar project_assignment con id: " +
                    projectAssignment.getProgrammerId());
    }

    @Override
    public Optional<ProjectAssignment> delete(ProjectAssignment projectAssignment) throws SQLException {
        String query = "DELETE FROM project_assignment WHERE id_programmer = ? AND id_project = ? AND start_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, projectAssignment.getProgrammerId(),projectAssignment.getProjectId(),
                projectAssignment.getStartDate());
        db.close();
        if (res > 0)
            return Optional.of(projectAssignment);
        else
            throw new SQLException("Error ProjectAssignmentRepository al eliminar project_assignment con id: " +
                    projectAssignment.getProgrammerId());
    }
}
