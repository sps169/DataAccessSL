package Manual.repositories;

import Manual.daos.Repository;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RepositoryRepo implements CRUDRepo<Repository,Long> {
    @Override
    public Optional<List<Repository>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        TypedQuery<Repository> query = hb.getManager().createNamedQuery("Repository.findAll", Repository.class);
        List<Repository> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Repository> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Repository repository = hb.getManager().find(Repository.class, id);
        hb.close();
        return Optional.of(repository);
    }

    @Override
    public Optional<Repository> insert(Repository repository) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(repository);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar Repository");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(repository);
    }

    @Override
    public Optional<Repository> update(Repository repository) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(repository);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update Repository");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(repository);
    }

    @Override
    public Optional<Repository> delete(Repository repository) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            repository = hb.getManager().find(Repository.class, repository.getId());
            hb.getManager().remove(repository);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove Repository");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(repository);
    }
}
