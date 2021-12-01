package Manual.repositories;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CRUDMultRepo <T, ID>{
    // Operaciones CRUD

    // Obtiene todos
    Optional<List<T>> findAll() throws SQLException;

    // Obtiene por ID
    Optional<T> getById(ID t) throws SQLException;

    // Salva
    Optional<T> insert(T t) throws SQLException;

    // Actualiza
    Optional<T> update(T t) throws SQLException;

    // Elimina
    Optional<T> delete(T t) throws SQLException;
}
