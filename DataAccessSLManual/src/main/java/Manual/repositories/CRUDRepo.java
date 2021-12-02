package Manual.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUDRepo<T, ID>{
        // Operaciones CRUD

        // Obtiene todos
        Optional<List<T>> findAll() throws SQLException;

        // Obtiene por ID
        Optional<T> getById(ID id) throws SQLException;

        // Salva
        Optional<T> insert(T t) throws SQLException;

        // Actualiza
        Optional<T> update(T t) throws SQLException;

        // Elimina
        Optional<T> delete(T t) throws SQLException;
}
