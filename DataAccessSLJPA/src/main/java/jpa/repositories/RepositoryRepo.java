package jpa.repositories;

import jpa.daos.Repository;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Repository Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class RepositoryRepo implements CRUDRepo<Repository,Long> {
    /**
     * Method that query to database using Hibernate Controller to obtain all Repositories in the table repository.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Repository>>
     */
    @Override
    public Optional<List<Repository>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Repository> query = hb.getManager().createNamedQuery("Repository.findAll", Repository.class);
        List<Repository> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    /**
     * Method that query to database using Hibernate Controller to obtain a Repository in the table repository by an ID.
     * @param id Long of the repository to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Repository>
     */
    @Override
    public Optional<Repository> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Repository repository = hb.getManager().find(Repository.class, id);
        hb.close();
        return Optional.of(repository);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a Repository in the table repository.
     * @param repository Repository object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Repository> of Repository object inserted
     */
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
    /**
     * Method that query to database using Hibernate Controller to update a Repository in the table repository.
     * @param repository Repository object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Repository> of Repository object updated
     */
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
    /**
     * Method that query to database using Hibernate Controller to delete a Repository in the table repository.
     * @param repository Repository object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Repository> of Repository object deleted
     */
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
