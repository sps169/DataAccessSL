package jpa.repositories;

import jpa.daos.Issue;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Issue Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class IssueRepo implements CRUDRepo<Issue,Long> {
    /**
     * Method that query to database using Hibernate Controller to obtain all Issues in the table issue.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Issue>>
     */
    @Override
    public Optional<List<Issue>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Issue> query = hb.getManager().createNamedQuery("Issue.findAll", Issue.class);
        List<Issue> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a Issue in the table issue by an ID.
     * @param id Long of the issue to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue>
     */
    @Override
    public Optional<Issue> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Issue issue = hb.getManager().find(Issue.class, id);
        hb.close();
        return Optional.of(issue);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a Issue in the table issue.
     * @param issue Issue object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue> of Issue object inserted
     */
    @Override
    public Optional<Issue> insert(Issue issue) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(issue);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar issue");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(issue);
    }
    /**
     * Method that query to database using Hibernate Controller to update a Issue in the table issue.
     * @param issue Issue object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue> of Issue object updated
     */
    @Override
    public Optional<Issue> update(Issue issue) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(issue);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update issue");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(issue);
    }
    /**
     * Method that query to database using Hibernate Controller to delete a Issue in the table issue.
     * @param issue Issue object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Issue> of Issue object deleted
     */
    @Override
    public Optional<Issue> delete(Issue issue) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            issue = hb.getManager().find(Issue.class, issue.getId());
            hb.getManager().remove(issue);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove issue");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(issue);
    }
}
