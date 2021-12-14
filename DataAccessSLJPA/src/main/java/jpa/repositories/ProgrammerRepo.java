package jpa.repositories;

import jpa.daos.Programmer;
import jpa.utils.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Class that models the CRUD operations for Programmer Repository class
 * with the Database using Hibernate Controller
 * @author sps169, FedericoTB
 */
public class ProgrammerRepo implements CRUDRepo<Programmer,Long>{
    /**
     * Method that query to database using Hibernate Controller to obtain all Programmers in the table programmer.
     * @throws SQLException when fails in the query transaction
     * @return Optional<List<Programmer>>
     */
    @Override
    public Optional<List<Programmer>> findAll() throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        TypedQuery<Programmer> query = hb.getManager().createNamedQuery("Programmer.findAll", Programmer.class);
        List<Programmer> list = query.getResultList();
        hb.close();
        return Optional.of(list);
    }
    /**
     * Method that query to database using Hibernate Controller to obtain a Programmer in the table programmer by an ID.
     * @param id Long of the programmer to find
     * @throws SQLException when fails in the query transaction
     * @return Optional<Programmer>
     */
    @Override
    public Optional<Programmer> getById(Long id) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        hb.open();
        Programmer programmer = hb.getManager().find(Programmer.class, id);
        hb.close();
        return Optional.of(programmer);
    }
    /**
     * Method that query to database using Hibernate Controller to insert a Programmer in the table programmer.
     * @param programmer Programmer object to insert
     * @throws SQLException when fails in the query transaction
     * @return Optional<Programmer> of Programmer object inserted
     */
    @Override
    public Optional<Programmer> insert(Programmer programmer) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().persist(programmer);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al insertar Programmer");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(programmer);
    }
    /**
     * Method that query to database using Hibernate Controller to update a Programmer in the table programmer.
     * @param programmer Programmer object to update
     * @throws SQLException when fails in the query transaction
     * @return Optional<Programmer> of Programmer object updated
     */
    @Override
    public Optional<Programmer> update(Programmer programmer) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            hb.getManager().merge(programmer);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al update Programmer");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(programmer);
    }
    /**
     * Method that query to database using Hibernate Controller to delete a Programmer in the table programmer.
     * @param programmer Programmer object to delete
     * @throws SQLException when fails in the query transaction
     * @return Optional<Programmer> of Programmer object deleted
     */
    @Override
    public Optional<Programmer> delete(Programmer programmer) throws SQLException {
        HibernateController hb = HibernateController.getInstance();
        try {
            hb.open();
            hb.getTransaction().begin();
            programmer = hb.getManager().find(Programmer.class, programmer.getId());
            hb.getManager().remove(programmer);
            hb.getTransaction().commit();
            hb.close();
        }catch (Exception ex) {
            throw new SQLException("Error al remove ProjectAssignment");
        }finally {
            if (hb.getTransaction().isActive())
                hb.getTransaction().rollback();
            hb.close();
        }

        return Optional.of(programmer);
    }
}
