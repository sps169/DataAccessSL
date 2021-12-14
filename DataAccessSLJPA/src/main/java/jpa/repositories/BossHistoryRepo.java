package jpa.repositories;

import jpa.daos.BossHistory;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for BossHistory Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class BossHistoryRepo implements CRUDRepo<BossHistory,Long> {
    /**
     * Method that query to database using Hibernate Controller to obtain all BossHistories in the table boss_history.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<BossHistory>>
     */
    @Override
    public Optional<List<BossHistory>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<BossHistory> query = hb.getManager().createNamedQuery("BossHistory.findAll", BossHistory.class);
        List<BossHistory> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a BossHistory in the table boss_history by an ID.
     * @param id Long of the bossHistory to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory>
     */
    @Override
    public Optional<BossHistory> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        BossHistory bossHistory = hb.getManager().find(BossHistory.class, id);
        hb.close();
        return Optional.of(bossHistory);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory> of BossHistory object inserted
     */
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
    /**
     * Method that query to database using Hibernate Controller to update a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory> of BossHistory object updated
     */
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
    /**
     * Method that query to database using Hibernate Controller to delete a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<BossHistory> of BossHistory object deleted
     */
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
