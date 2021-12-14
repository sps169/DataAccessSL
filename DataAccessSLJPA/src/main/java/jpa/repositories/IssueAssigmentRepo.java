package jpa.repositories;

import jpa.daos.IssueAssignment;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for IssueAssigment Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class IssueAssigmentRepo implements CRUDRepo<IssueAssignment,Long> {
    /**
     * Method that query to database using Hibernate Controller to obtain all IssueAssigments in the table issue_assignment.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<IssueAssignment>>
     */
    @Override
    public Optional<List<IssueAssignment>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<IssueAssignment> query = hb.getManager().createNamedQuery("IssueAssignment.findAll",
                IssueAssignment.class);
        List<IssueAssignment> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a IssueAssignment in the
     * table issue_assignment by an ID.
     * @param id Long of the issueAssignment to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<IssueAssignment>
     */
    @Override
    public Optional<IssueAssignment> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        IssueAssignment issueAssignment = hb.getManager().find(IssueAssignment.class, id);
        hb.close();
        return Optional.of(issueAssignment);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a IssueAssignment in the table issue_assignment.
     * @param issueAssignment IssueAssignment object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<IssueAssignment> of IssueAssignment object inserted
     */
    @Override
    public Optional<IssueAssignment> insert(IssueAssignment issueAssignment) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(issueAssignment);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar issueAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(issueAssignment);
    }
    /**
     * Method that query to database using Hibernate Controller to update a IssueAssignment in the table issue_assignment.
     * @param issueAssignment IssueAssignment object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<IssueAssignment> of IssueAssignment object updated
     */
    @Override
    public Optional<IssueAssignment> update(IssueAssignment issueAssignment) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(issueAssignment);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update issueAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(issueAssignment);
    }
    /**
     * Method that query to database using Hibernate Controller to delete a IssueAssignment in the table issue_assignment.
     * @param issueAssignment IssueAssignment object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<IssueAssignment> of IssueAssignment object deleted
     */
    @Override
    public Optional<IssueAssignment> delete(IssueAssignment issueAssignment) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            issueAssignment = hb.getManager().find(IssueAssignment.class, issueAssignment.getId());
            hb.getManager().remove(issueAssignment);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove issueAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(issueAssignment);
    }
}
