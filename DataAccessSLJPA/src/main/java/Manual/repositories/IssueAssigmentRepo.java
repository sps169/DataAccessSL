package Manual.repositories;

import Manual.daos.IssueAssignment;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class IssueAssigmentRepo implements CRUDRepo<IssueAssignment,Long> {
    @Override
    public Optional<List<IssueAssignment>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<IssueAssignment> query = hb.getManager().createNamedQuery("IssueAssignment.findAll", IssueAssignment.class);
        List<IssueAssignment> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<IssueAssignment> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        IssueAssignment issueAssignment = hb.getManager().find(IssueAssignment.class, id);
        hb.close();
        return Optional.of(issueAssignment);
    }

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
