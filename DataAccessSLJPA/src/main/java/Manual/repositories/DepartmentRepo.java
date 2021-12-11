package Manual.repositories;

import Manual.daos.BossHistory;
import Manual.daos.Department;
import Manual.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartmentRepo implements CRUDRepo<Department,Long> {
    @Override
    public Optional<List<Department>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Department> query = hb.getManager().createNamedQuery("Department.findAll", Department.class);
        List<Department> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Department> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Department department = hb.getManager().find(Department.class, id);
        hb.close();
        return Optional.of(department);
    }

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
