package jpa.repositories;

import jpa.daos.Department;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Department Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class DepartmentRepo implements CRUDRepo<Department,Long> {
    /**
     * Method that query to database using Hibernate Controller to obtain all Departments in the table department.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Department>>
     */
    @Override
    public Optional<List<Department>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Department> query = hb.getManager().createNamedQuery("Department.findAll", Department.class);
        List<Department> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a Department in the table department by an ID.
     * @param id Long of the department to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department>
     */
    @Override
    public Optional<Department> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Department department = hb.getManager().find(Department.class, id);
        hb.close();
        return Optional.of(department);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a Department in the table department.
     * @param department Department object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department> of Department object inserted
     */
    @Override
    public Optional<Department> insert(Department department) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(department);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar department");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(department);
    }
    /**
     * Method that query to database using Hibernate Controller to update a Department in the table department.
     * @param department Department object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department> of Department object updated
     */
    @Override
    public Optional<Department> update(Department department) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(department);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update department");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(department);
    }
    /**
     * Method that query to database using Hibernate Controller to delete a Department in the table department.
     * @param department Department object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Department> of Department object deleted
     */
    @Override
    public Optional<Department> delete(Department department) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            department = hb.getManager().find(Department.class, department.getId());
            hb.getManager().remove(department);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove department");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(department);
    }
}
