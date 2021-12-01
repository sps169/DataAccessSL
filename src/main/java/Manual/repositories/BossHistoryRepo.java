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

public class BossHistoryRepo implements CRUDMultRepo<BossHistory,Long> {
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

    @Override
    public Optional<BossHistory> getById(Long IDprog, Long IDdep, LocalDateTime entry) throws SQLException {
        String query = "SELECT * FROM boss_history WHERE id_programmer = ? AND id_department = ? AND entry_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, IDprog,IDdep,entry).orElseThrow(() -> new SQLException("Error BossHistoryRepository al " +
                "consultar boss_history con ID " + IDprog));
        db.close();
        if (result.next()) {
           BossHistory bossHistory = new BossHistory(
                    result.getLong("id_programmer"),
                    result.getLong("id_department"),
                   result.getTimestamp("entry_date").toLocalDateTime(),
                    result.getTimestamp("leave_date").toLocalDateTime()
            );
            return Optional.of(bossHistory);
        } else{
            throw new SQLException("Error BossHistoryRepository no existe boss_history con ID: " + IDprog);
        }
    }

    @Override
    public Optional<BossHistory> insert(BossHistory bossHistory) throws SQLException {
        String query = "INSERT INTO boss_history VALUES (?,?,?,?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, bossHistory.getProgrammerId(),bossHistory.getDepartmentId(),
                bossHistory.getEntryDate(), bossHistory.getLeave_date()).orElseThrow(() ->
                new SQLException("Error BossHistoryRepository al consultar para insertar boss_history"));
        System.out.println(bossHistory);
        System.out.println(result.first());
        db.close();
        if (result != null) {
            return Optional.of(bossHistory);
        } else{
            throw new SQLException("Error BossHistoryRepository al insertar boss_history en BD");
        }
    }

    @Override
    public Optional<BossHistory> update(BossHistory bossHistory) throws SQLException {
        String query = "UPDATE boss_history SET id_programmer = ?, id_department = ?, entry_date = ?,leave_date = ? " +
                "WHERE id_programmer = ? AND id_department = ? AND entry_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int result = db.update(query,bossHistory.getProgrammerId(),bossHistory.getDepartmentId(),
                bossHistory.getEntryDate(), bossHistory.getLeave_date(),bossHistory.getProgrammerId(),
                bossHistory.getDepartmentId(), bossHistory.getEntryDate());
        db.close();
        if (result > 0)
            return Optional.of(bossHistory);
        else
            throw new SQLException("Error BossHistoryRepository al actualizar boss_history con id: " +
                    bossHistory.getProgrammerId());
    }

    @Override
    public Optional<BossHistory> delete(BossHistory bossHistory) throws SQLException {
        String query = "DELETE FROM boss_history WHERE id_programmer = ? AND id_department = ? AND entry_date = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, bossHistory.getProgrammerId(),
                bossHistory.getDepartmentId(), bossHistory.getEntryDate());
        db.close();
        if (res > 0)
            return Optional.of(bossHistory);
        else
            throw new SQLException("Error BossHistoryRepository al eliminar boss_history con id: " +
                    bossHistory.getProgrammerId());
    }
}
