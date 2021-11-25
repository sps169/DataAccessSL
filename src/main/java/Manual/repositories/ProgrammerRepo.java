package Manual.repositories;

import Manual.daos.Programmer;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepo implements CRUDRepo<Programmer,Long>{
    @Override
    public Optional<List<Programmer>> findAll() throws SQLException {
        String query = "SELECT * FROM programmer";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error ProgrammerRepository al " +
                "consultar registros de programmer"));
        ArrayList<Programmer> list = new ArrayList<Programmer>();
        db.close();
        while (result.next()) {
            list.add(
                    new Programmer(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getDate("entry_date").toLocalDate().atTime(
                                    result.getTime("entry_date").toLocalTime()),
                            result.getString("password"),
                            result.getString("technologies"),
                            result.getFloat("salary"),
                            result.getLong("id_department")
                    )
            );
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }

    @Override
    public Optional<Programmer> getById(Long ID) throws SQLException {
        String query = "SELECT * FROM programmer WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error ProgrammerRepository al " +
                "consultar programmer con ID " + ID));
        db.close();
        if (result.next()) {
            Programmer programmer = new Programmer(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getDate("entry_date").toLocalDate().atTime(
                            result.getTime("entry_date").toLocalTime()),
                    result.getString("password"),
                    result.getString("technologies"),
                    result.getFloat("salary"),
                    result.getLong("id_department")
            );
            return Optional.of(programmer);
        } else{
            throw new SQLException("Error ProgrammerRepository no existe programmer con ID: " + ID);
        }
    }

    @Override
    public Optional<Programmer> save(Programmer programmer) throws SQLException {
        String query = "INSERT INTO programmer VALUES (?,?,?,?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, programmer.getId(),programmer.getName(),programmer.getEntry_date(),
                programmer.getPassword(), programmer.getTechnologies(),programmer.getSalary(),
                programmer.getDepartmentId()).orElseThrow(() ->
                new SQLException("Error ProgrammerRepository al consultar para insertar programmer"));
        db.close();
        if (result.next()) {
            return Optional.of(programmer);
        } else{
            throw new SQLException("Error ProgrammerRepository al insertar programmer en BD");
        }
    }

    @Override
    public Optional<Programmer> update(Programmer programmer) throws SQLException {
        String query = "UPDATE programmer SET name = ?, entry_date = ?, password = ?,technologies = ?, salary =?,"
                + " id_department = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,programmer.getName(),programmer.getEntry_date(),
                programmer.getPassword(), programmer.getTechnologies(),programmer.getSalary(),
                programmer.getDepartmentId(), programmer.getId());
        db.close();
        if (result > 0)
            return Optional.of(programmer);
        else
            throw new SQLException("Error ProgrammerRepository al actualizar programmer con id: " + programmer.getId());
    }

    @Override
    public Optional<Programmer> delete(Programmer programmer) throws SQLException {
        String query = "DELETE FROM programmer WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, programmer.getId());
        db.close();
        if (res > 0)
            return Optional.of(programmer);
        else
            throw new SQLException("Error ProgrammerRepository al eliminar programmer con id: " + programmer.getId());

    }
}
