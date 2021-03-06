package Manual.repositories;

import Manual.daos.BossHistory;
import Manual.daos.Commit;
import Manual.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for BossHistory Repository class
 * with the Database using Database Controller
 * @author sps169, FedericoTB
 */
public class BossHistoryRepo implements CRUDRepo<BossHistory,Long> {
    /**
     * Method that query to database using Database Controller to obtain all BossHistories in the table boss_history.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<BossHistory>>
     */
    @Override
    public Optional<List<BossHistory>> findAll() throws SQLException {
        String query = "SELECT * FROM boss_history";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error BossHistoryRepository al " +
                "consultar registros de boss_history"));
        ArrayList<BossHistory> list = new ArrayList<>();
        db.close();
        while (result.next()) {

                    BossHistory history = new BossHistory(
                            result.getLong("id"),
                            result.getLong("id_programmer"),
                            result.getLong("id_department"),
                            result.getDate("entry_date").toLocalDate().atTime(
                                    result.getTime("entry_date").toLocalTime()));
                    if (result.getDate("leave_date") != null){
                        history.setLeave_date(result.getDate("leave_date").toLocalDate().atTime(
                                result.getTime("leave_date").toLocalTime()));
                    }
                    list.add(history);
        }
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list);
    }
    /**
     * Method that query to database using Database Controller to obtain a BossHistory in the table boss_history by an ID.
     * @param id Long of the bossHistory to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory>
     */
    @Override
    public Optional<BossHistory> getById(Long id) throws SQLException {
        String query = "SELECT * FROM boss_history WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error BossHistoryRepository al " +
                "consultar boss_history con ID " + id));
        db.close();
        if (result.next()) {
           BossHistory bossHistory = new BossHistory(
                   result.getLong("id"),
                    result.getLong("id_programmer"),
                    result.getLong("id_department"),
                    result.getDate("entry_date").toLocalDate().atTime(
                            result.getTime("entry_date").toLocalTime()),
                    result.getDate("leave_date").toLocalDate().atTime(
                            result.getTime("leave_date").toLocalTime())
            );
            return Optional.of(bossHistory);
        } else{
            throw new SQLException("Error BossHistoryRepository no existe boss_history con ID: " + id);
        }
    }
    /**
     * Method that query to database using Database Controller to insert a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory> of BossHistory object inserted
     */
    @Override
    public Optional<BossHistory> insert(BossHistory bossHistory) throws SQLException {
        String query = "INSERT INTO boss_history VALUES (?,?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, bossHistory.getId(), bossHistory.getProgrammerId(),bossHistory.getDepartmentId(),
                bossHistory.getEntryDate(), bossHistory.getLeave_date()).orElseThrow(() ->
                new SQLException("Error BossHistoryRepository al consultar para insertar boss_history"));
        db.close();
        if (result.next()) {
            return Optional.of(bossHistory);
        } else{
            throw new SQLException("Error BossHistoryRepository al insertar boss_history en BD");
        }
    }
    /**
     * Method that query to database using Database Controller to update a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory> of BossHistory object updated
     */
    @Override
    public Optional<BossHistory> update(BossHistory bossHistory) throws SQLException {
        String query = "UPDATE boss_history SET id_programmer = ?, id_department = ?, entry_date = ?,leave_date = ? " +
                "WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,bossHistory.getProgrammerId(),bossHistory.getDepartmentId(),
                bossHistory.getEntryDate(), bossHistory.getLeave_date(), bossHistory.getId());
        db.close();
        if (result > 0)
            return Optional.of(bossHistory);
        else
            throw new SQLException("Error BossHistoryRepository al actualizar boss_history con id: " +
                    bossHistory.getId());
    }
    /**
     * Method that query to database using Database Controller to delete a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory> of BossHistory object deleted
     */
    @Override
    public Optional<BossHistory> delete(BossHistory bossHistory) throws SQLException {
        String query = "DELETE FROM boss_history WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, bossHistory.getId());
        db.close();
        if (res > 0)
            return Optional.of(bossHistory);
        else
            throw new SQLException("Error BossHistoryRepository al eliminar boss_history con id: " +
                    bossHistory.getId());
    }
}
