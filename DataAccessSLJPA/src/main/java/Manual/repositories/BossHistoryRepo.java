package Manual.repositories;

import Manual.daos.BossHistory;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BossHistoryRepo implements CRUDRepo<BossHistory,Long> {
    @Override
    public Optional<List<BossHistory>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        TypedQuery<BossHistory> query = hb.getManager().createNamedQuery("BossHistory.findAll", BossHistory.class);
        List<BossHistory> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<BossHistory> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        BossHistory bossHistory = hb.getManager().find(BossHistory.class, id);
        hb.close();
        return Optional.of(bossHistory);
    }

    @Override
    public Optional<BossHistory> insert(BossHistory bossHistory) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(bossHistory);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar boss history");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(bossHistory);
    }

    @Override
    public Optional<BossHistory> update(BossHistory bossHistory) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(bossHistory);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update boss history");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(bossHistory);
    }

    @Override
    public Optional<BossHistory> delete(BossHistory bossHistory) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            bossHistory = hb.getManager().find(BossHistory.class, bossHistory.getId());
            hb.getManager().remove(bossHistory);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove boss history");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(bossHistory);
    }
}
