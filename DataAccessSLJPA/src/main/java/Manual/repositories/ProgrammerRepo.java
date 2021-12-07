package Manual.repositories;

import Manual.daos.Programmer;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepo implements CRUDRepo<Programmer,Long>{
    @Override
    public Optional<List<Programmer>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        TypedQuery<Programmer> query = hb.getManager().createNamedQuery("Programmer.findAll", Programmer.class);
        List<Programmer> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Programmer> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Programmer programmer = hb.getManager().find(Programmer.class, id);
        hb.close();
        return Optional.of(programmer);
    }

    @Override
    public Optional<Programmer> insert(Programmer programmer) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(programmer);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar Programmer");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(programmer);
    }

    @Override
    public Optional<Programmer> update(Programmer programmer) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(programmer);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update Programmer");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(programmer);
    }

    @Override
    public Optional<Programmer> delete(Programmer programmer) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            programmer = hb.getManager().find(Programmer.class, programmer.getId());
            hb.getManager().remove(programmer);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove ProjectAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(programmer);
    }
}
