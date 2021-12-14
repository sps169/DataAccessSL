package Manual.services;

import Manual.repositories.CRUDRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/**
 * Abstract Class that models the CRUD operations for all Services class
 * @author sps169, FedericoTB
 */
public abstract class BaseService<T, ID, R extends CRUDRepo<T, ID>> {
    protected final R repository;

    public BaseService (R repository) {
        this.repository = repository;
    }

    // Obtiene todos
    public Optional<List<T>> findAll() throws SQLException {
        return repository.findAll();
    }

    // Obtiene por ID
    public Optional<T> getById(ID id) throws SQLException {
        return repository.getById(id);
    }

    // Salva
    public Optional<T> insert(T t) throws SQLException {
        return repository.insert(t);
    }

    // Actualiza
    public Optional<T> update(T t) throws SQLException {
        return repository.update(t);
    }

    // Elimina
    public Optional<T> delete(T t) throws SQLException {
        return repository.delete(t);
    }
}
