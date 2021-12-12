package jpa.repositories;

import jpa.daos.Commit;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommitRepo implements CRUDRepo<Commit,Long>{
    @Override
    public Optional<List<Commit>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Commit> query = hb.getManager().createNamedQuery("Commit.findAll", Commit.class);
        List<Commit> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Commit> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Commit commit = hb.getManager().find(Commit.class, id);
        hb.close();
        return Optional.of(commit);
    }

    @Override
    public Optional<Commit> insert(Commit commit) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(commit);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar Commit" + ex.getMessage());
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(commit);
    }

    @Override
    public Optional<Commit> update(Commit commit) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(commit);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update Commit" + ex.getMessage());
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(commit);
    }

    @Override
    public Optional<Commit> delete(Commit commit) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            commit = hb.getManager().find(Commit.class, commit.getId());
            hb.getManager().remove(commit);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove Commit" + ex.getMessage());
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(commit);
    }
}
