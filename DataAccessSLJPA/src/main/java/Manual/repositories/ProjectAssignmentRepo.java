package Manual.repositories;


import Manual.daos.ProjectAssignment;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjectAssignmentRepo implements CRUDRepo<ProjectAssignment,Long> {
    @Override
    public Optional<List<ProjectAssignment>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<ProjectAssignment> query = hb.getManager().createNamedQuery("ProjectAssignment.findAll", ProjectAssignment.class);
        List<ProjectAssignment> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<ProjectAssignment> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        ProjectAssignment projectAssignment = hb.getManager().find(ProjectAssignment.class, id);
        hb.close();
        return Optional.of(projectAssignment);
    }

    @Override
    public Optional<ProjectAssignment> insert(ProjectAssignment projectAssignment) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(projectAssignment);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar ProjectAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(projectAssignment);
    }

    @Override
    public Optional<ProjectAssignment> update(ProjectAssignment projectAssignment) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(projectAssignment);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update ProjectAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(projectAssignment);
    }

    @Override
    public Optional<ProjectAssignment> delete(ProjectAssignment projectAssignment) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            projectAssignment = hb.getManager().find(ProjectAssignment.class, projectAssignment.getId());
            hb.getManager().remove(projectAssignment);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove ProjectAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(projectAssignment);
    }
}
