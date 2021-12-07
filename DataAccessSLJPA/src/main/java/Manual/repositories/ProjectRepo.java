package Manual.repositories;

import Manual.daos.Project;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjectRepo implements CRUDRepo<Project,Long>{
    @Override
    public Optional<List<Project>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        TypedQuery<Project> query = hb.getManager().createNamedQuery("Project.findAll", Project.class);
        List<Project> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Project> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Project project = hb.getManager().find(Project.class, id);
        hb.close();
        return Optional.of(project);
    }

    @Override
    public Optional<Project> insert(Project project) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(project);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar Project");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(project);
    }

    @Override
    public Optional<Project> update(Project project) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(project);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update Project");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(project);
    }

    @Override
    public Optional<Project> delete(Project project) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            project = hb.getManager().find(Project.class, project.getId());
            hb.getManager().remove(project);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove Project");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(project);
    }
}
