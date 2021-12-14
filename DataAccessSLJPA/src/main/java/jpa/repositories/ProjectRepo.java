package jpa.repositories;

import jpa.daos.Project;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Project Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class ProjectRepo implements CRUDRepo<Project,Long>{
    /**
     * Method that query to database using Hibernate Controller to obtain all Projects in the table project.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Project>>
     */
    @Override
    public Optional<List<Project>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Project> query = hb.getManager().createNamedQuery("Project.findAll", Project.class);
        List<Project> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a Project in the table project by an ID.
     * @param id Long of the Project to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Project>
     */
    @Override
    public Optional<Project> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Project project = hb.getManager().find(Project.class, id);
        hb.close();
        return Optional.of(project);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a Project in the table project.
     * @param project Project object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Project> of Project object inserted
     */
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
    /**
     * Method that query to database using Hibernate Controller to update a Project in the table project.
     * @param project Project object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Project> of Project object updated
     */
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
    /**
     * Method that query to database using Hibernate Controller to delete a Project in the table project.
     * @param project Project object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Project> of Project object deleted
     */
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
