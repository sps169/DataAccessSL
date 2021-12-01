package Manual.services;

import Manual.daos.Programmer;
import Manual.dtos.ProgrammerDTO;
import Manual.mappers.ProgrammerMapper;
import Manual.repositories.ProgrammerRepo;
import Manual.utils.Cifrate;

import java.sql.SQLException;
import java.util.List;

public class ProgrammerService extends BaseService<Programmer, Long, ProgrammerRepo>{
    private final ProgrammerMapper mapper = new ProgrammerMapper();

    public ProgrammerService(ProgrammerRepo repository) {
        super(repository);
    }

    public List<ProgrammerDTO> getAllProgrammers() throws SQLException {
        return mapper.toDTO(this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los programadores")));
    }

    public ProgrammerDTO getProgrammerById(Long id) throws SQLException {
        return mapper.toDTO(this.getById(id).orElseThrow(() -> new SQLException("Error al obtener todos los programadores")));
    }

    public ProgrammerDTO insertProgrammer (ProgrammerDTO programmer) throws SQLException {
        programmer.setPassword(Cifrate.SHA256(programmer.getPassword()));
        Programmer result = this.insert(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al obtener todos los programadores"));
        return mapper.toDTO(result);
    }

    public ProgrammerDTO updateProgrammer (ProgrammerDTO programmer) throws SQLException {
        Programmer result = this.update(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al obtener todos los programadores"));
        return mapper.toDTO(result);
    }

    public ProgrammerDTO deleteProgrammer (ProgrammerDTO programmer) throws SQLException {
        Programmer result = this.delete(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al obtener todos los programadores"));
        return mapper.toDTO(result);
    }
}
