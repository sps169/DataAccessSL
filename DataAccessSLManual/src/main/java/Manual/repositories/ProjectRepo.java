package Manual.repositories;

import Manual.daos.Project;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepo implements CRUDRepo<Project,Long>{

    @Override
    public Optional<List<Project>> findAll() throws SQLException {
        String query = "SELECT * FROM project";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error ProjectRepository al consultar " +
                "registros de project"));
        ArrayList<Project> list = new ArrayList<Project>();
        db.close();
        while (result.next()) {
             Project project = new Project(
                     result.getLong("id"),
                     result.getString("name"),
                     result.getDate("start_date").toLocalDate().atTime(
                             result.getTime("start_date").toLocalTime()),
                     result.getString("technologies"),
                     result.getFloat("annual_Budget"),
                     result.getString("state"),
                     result.getLong("id_boss"),
                     result.getLong("id_department")
            );
            if (result.getDate("end_date") != null){
                project.setEndDate(result.getDate("end_date").toLocalDate().atTime(
                        result.getTime("end_date").toLocalTime()));
            }
            list.add(project);
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<Project> getById(Long ID) throws SQLException {
        String query = "SELECT * FROM project WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error ProjectRepository al " +
                "consultar project con ID " + ID));
        db.close();
        if (result.next()) {
            Project project = new Project(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getDate("start_date").toLocalDate().atTime(
                            result.getTime("start_date").toLocalTime()),
                    result.getString("technologies"),
                    result.getFloat("annual_Budget"),
                    result.getString("state"),
                    result.getLong("id_boss"),
                    result.getLong("id_department")
            );
            if (result.getDate("end_date") != null){
                project.setEndDate(result.getDate("end_date").toLocalDate().atTime(
                        result.getTime("end_date").toLocalTime()));
            }
            return Optional.of(project);
        } else{
            throw new SQLException("Error ProjectRepository no existe project con ID: " + ID);
        }
    }

    @Override
    public Optional<Project> insert(Project project) throws SQLException {
        String query = "INSERT INTO project VALUES (?,?,?,?,?,?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, project.getId(),project.getName(),project.getStartDate(),
                project.getEndDate(), project.getTechnologies(),project.getAnnualBudget(),project.getState(),
                project.getProjectBossId(),project.getDepartmentId()).orElseThrow(() ->
                new SQLException("Error ProjectRepository al consultar para insertar project"));
        db.close();
        if (result.next()) {
            return Optional.of(project);
        } else{
            throw new SQLException("Error ProjectRepository al insertar project en BD");
        }
    }

    @Override
    public Optional<Project> update(Project project) throws SQLException {
        String query = "UPDATE project SET name = ?, start_date = ?, end_date = ?,technologies = ?, annual_budget =?,"
                + "state = ?, id_boss = ?, id_department = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query, project.getName(),project.getStartDate(),
                project.getEndDate(), project.getTechnologies(),project.getAnnualBudget(),project.getState(),
                project.getProjectBossId(),project.getDepartmentId(), project.getId());
        db.close();
        if (result > 0)
            return Optional.of(project);
        else
            throw new SQLException("Error ProjectRepository al actualizar project con id: " + project.getId());
    }

    @Override
    public Optional<Project> delete(Project project) throws SQLException {
        String query = "DELETE FROM project WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, project.getId());
        db.close();
        if (res > 0)
            return Optional.of(project);
        else
            throw new SQLException("Error ProjectRepository al eliminar project con id: " + project.getId());

    }
}
