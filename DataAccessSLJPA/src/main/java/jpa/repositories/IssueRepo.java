package jpa.repositories;

import jpa.daos.Issue;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class IssueRepo implements CRUDRepo<Issue,Long> {
    @Override
    public Optional<List<Issue>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Issue> query = hb.getManager().createNamedQuery("Issue.findAll", Issue.class);
        List<Issue> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Issue> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Issue issue = hb.getManager().find(Issue.class, id);
        hb.close();
        return Optional.of(issue);
    }

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
