package Manual.services;

import Manual.daos.BossHistory;
import Manual.daos.Department;
import Manual.daos.Programmer;
import Manual.daos.Project;
import Manual.dtos.DepartmentDTO;
import Manual.dtos.ProjectDTO;
import Manual.mappers.DepartmentMapper;
import Manual.repositories.BossHistoryRepo;
import Manual.repositories.DepartmentRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.repositories.ProjectRepo;
import jdk.internal.org.jline.reader.History;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentService extends BaseService<Department,Long, DepartmentRepo> {
    private final DepartmentMapper mapper = new DepartmentMapper();
    public DepartmentService(DepartmentRepo repository) {
        super(repository);
    }

    public List<DepartmentDTO> findAllDepartments() throws SQLException {
        List<Department> departments = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los departamentos"));
        List<DepartmentDTO> dtos = new ArrayList<>();
        for (Department department : departments) {
            dtos.add(fillDepartment(department));
        }
        return dtos;
    }

    public DepartmentDTO getDepartmentById(Long id) throws SQLException {
        Department department = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener departamento con id" + id));
        return fillDepartment(department);
    }

    public DepartmentDTO insertDepartment(DepartmentDTO department) throws SQLException {
        Department result = this.insert(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al insertar departamento con id" + department.getId()));
        return fillDepartment(result);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO department) throws SQLException {
        Department result = this.update(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al actualizar departamento con id" + department.getId()));
        return fillDepartment(result);
    }

    public DepartmentDTO deleteDepartment(DepartmentDTO department) throws SQLException {
        Department result = this.delete(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al borrar departamento con id" + department.getId()));
        return fillDepartment(result);
    }

    private DepartmentDTO fillDepartment(Department department) throws SQLException {
        DepartmentDTO dto = mapper.toDTO(department);
        dto.setHistoryBosses(this.getBossesOfDepartment(department.getId()));
        dto.setOngoingProjects(this.getProjects(department.getId(), "active"));
        dto.setFinishedProjects(this.getProjects(department.getId(), "ended"));
        return dto;
    }

    private Set<Project> getProjects(long id, String status) throws SQLException {
        ProjectService projectService = new ProjectService(new ProjectRepo());
        return projectService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los projectos activos del departamento con id " + id))
                .stream().filter(s -> s.getDepartmentId() == id && s.getState().equals(status)).collect(Collectors.toSet());
    }

    private List<Programmer> getBossesOfDepartment(long id) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        BossHistoryService bossHistoryService = new BossHistoryService(new BossHistoryRepo());
        List<BossHistory> bossHistories = bossHistoryService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los boss history"))
                .stream().filter(s -> s.getDepartmentId() == id).collect(Collectors.toList());
        List<Programmer> programmers = new ArrayList<>();
        for (BossHistory bossHistory : bossHistories) {
            programmers.add(programmerService.getById(bossHistory.getProgrammerId()).orElseThrow(() -> new SQLException("Error al obtener jefes de departamento")));
        }
        return programmers;
    }
}
