package jpa.repositories;


import jpa.daos.ProjectAssignment;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for ProjectAssignment Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class ProjectAssignmentRepo implements CRUDRepo<ProjectAssignment,Long> {
    /**
     * Method that query to database using Hibernate Controller to obtain all ProjectAssignments in the table project_assignment.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<ProjectAssignment>>
     */
    @Override
    public Optional<List<ProjectAssignment>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<ProjectAssignment> query = hb.getManager().createNamedQuery("ProjectAssignment.findAll", ProjectAssignment.class);
        List<ProjectAssignment> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a ProjectAssignment in the table
     * project_assignment by an ID.
     * @param id Long of the ProjectAssignment to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<ProjectAssignment>
     */
    @Override
    public Optional<ProjectAssignment> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        ProjectAssignment projectAssignment = hb.getManager().find(ProjectAssignment.class, id);
        hb.close();
        return Optional.of(projectAssignment);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a ProjectAssignment in the table project_assignment.
     * @param projectAssignment ProjectAssignment object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<ProjectAssignment> of ProjectAssignment object inserted
     */
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
    /**
     * Method that query to database using Hibernate Controller to update a ProjectAssignment in the table project_Assignment.
     * @param projectAssignment ProjectAssignment object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Repository> of ProjectAssignment object updated
     */
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
    /**
     * Method that query to database using Hibernate Controller to delete a ProjectAssignment in the table project_Assignment.
     * @param projectAssignment ProjectAssignment object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<ProjectAssignment> of ProjectAssignment object deleted
     */
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
