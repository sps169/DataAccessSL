package Manual.repositories;

import Manual.daos.Department;
import Manual.daos.Programmer;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Department Repository class
 * with the Database using Database Controller
 * @author sps169, FedericoTB
 */
public class DepartmentRepo implements CRUDRepo<Department,Long> {
    /**
     * Method that query to database using Database Controller to obtain all Departments in the table department.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Department>>
     */
    @Override
    public Optional<List<Department>> findAll() throws SQLException {
        String query = "SELECT * FROM department";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error DepartmentRepository al " +
                "consultar registros de department"));
        ArrayList<Department> list = new ArrayList<Department>();
        db.close();
        while (result.next()) {
            list.add(
                    new Department(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getLong("boss"),
                            result.getFloat("budget")
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }
    /**
     * Method that query to database using Database Controller to obtain a Department in the table department by an ID.
     * @param id Long of the department to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department>
     */
    @Override
    public Optional<Department> getById(Long ID) throws SQLException {
        String query = "SELECT * FROM department WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error DepartmentRepository al " +
                "consultar department con ID " + ID));
        db.close();
        if (result.next()) {
            Department department = new Department(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getLong("boss"),
                    result.getFloat("budget")
            );
            return Optional.of(department);
        } else{
            throw new SQLException("Error DepartmentRepository no existe department con ID: " + ID);
        }
    }
    /**
     * Method that query to database using Database Controller to insert a Department in the table department.
     * @param department Department object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department> of Department object inserted
     */
    @Override
    public Optional<Department> insert(Department department) throws SQLException {
        String query = "INSERT INTO department VALUES (?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, department.getId(),department.getName(),department.getDepartmentBossId(),
                department.getBudget()).orElseThrow(() ->
                new SQLException("Error DepartmentRepository al consultar para insertar department"));
        db.close();
        if (result.next()) {
            return Optional.of(department);
        } else{
            throw new SQLException("Error DepartmentRepository al insertar department en BD");
        }
    }
    /**
     * Method that query to database using Database Controller to update a Department in the table department.
     * @param department Department object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department> of Department object updated
     */
    @Override
    public Optional<Department> update(Department department) throws SQLException {
        String query = "UPDATE department SET name = ?, boss = ?, budget = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,department.getName(),department.getDepartmentBossId(),
                department.getBudget(), department.getId());
        db.close();
        if (result > 0)
            return Optional.of(department);
        else
            throw new SQLException("Error DepartmentRepository al actualizar department con id: " + department.getId());
    }
    /**
     * Method that query to database using Database Controller to delete a Department in the table department.
     * @param department Department object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department> of Department object deleted
     */
    @Override
    public Optional<Department> delete(Department department) throws SQLException {
        String query = "DELETE FROM department WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, department.getId());
        db.close();
        if (res > 0)
            return Optional.of(department);
        else
            throw new SQLException("Error DepartmentRepository al eliminar department con id: " + department.getId());

    }
}
