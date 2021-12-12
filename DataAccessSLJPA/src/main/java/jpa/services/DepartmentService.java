package jpa.services;

import jpa.daos.*;
import jpa.dtos.BossHistoryDTO;
import jpa.dtos.DepartmentDTO;
import jpa.mappers.DepartmentMapper;
import jpa.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
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
        if (this.isBossWorkingAtProyects(department.getDepartmentBoss().getId()))
            throw new SQLException("Error al insertar departamento: el jefe participa en proyectos activos");
        Department result = this.insert(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al insertar departamento con id" + department.getId()));
        this.insertNewBossInHistory(department);
        return fillDepartment(result);
    }

    private void insertNewBossInHistory(DepartmentDTO departmentDTO) throws SQLException {
        BossHistoryService bossHistoryService = new BossHistoryService(new BossHistoryRepo());
        bossHistoryService.insertBossHistory(new BossHistoryDTO(departmentDTO.getDepartmentBoss(), mapper.fromDTO(departmentDTO), LocalDateTime.now(), null));
    }

    private boolean isBossWorkingAtProyects(Long id_boss) throws SQLException {
        ProjectAssignmentService projectAssignmentService = new ProjectAssignmentService(new ProjectAssignmentRepo());
        return projectAssignmentService.getAllProjectAssignments().stream().anyMatch(s -> s.getProject().getState().equals("active") && s.getProgrammer().getId() == id_boss);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO department) throws SQLException {
        boolean hasNewBoss = false;
        if (this.isBossWorkingAtProyects(department.getDepartmentBoss().getId()))
            throw new SQLException("Error al actualizar departamento: el jefe participa en proyectos activos");
        hasNewBoss = updateOldBoss(department);
        Department result = this.update(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al actualizar departamento con id" + department.getId()));
        DepartmentDTO resultDTO = fillDepartment(result);
        if (hasNewBoss)
            insertNewBossInHistory(department);
        return resultDTO;
    }

    private boolean updateOldBoss(DepartmentDTO department) throws SQLException {
        BossHistoryService bossHistoryService = new BossHistoryService(new BossHistoryRepo());
        BossHistoryDTO bossHistoryLastBoss = bossHistoryService.getAllBossHistory().stream().filter(s -> s.getDepartment().getId() == department.getId()).max((o1, o2) -> {
            if (o1.getEntryDate().isBefore(o2.getEntryDate()))
                return -1;
            else if(o1.getEntryDate().isAfter(o2.getEntryDate()))
                return 1;
            else
                return 0;
        }).orElseThrow(() ->new SQLException("No existe ultimo jefe"));
        if (!bossHistoryLastBoss.getProgrammer().equals(department.getDepartmentBoss())) {
            bossHistoryLastBoss.setLeaveDate(LocalDateTime.now());
            bossHistoryService.updateBossHistory(bossHistoryLastBoss);
            return true;
        }
        return false;
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
                .stream().filter(s -> s.getDepartment().getId() == id && s.getState().equals(status)).collect(Collectors.toSet());
    }

    private Set<Programmer> getBossesOfDepartment(long id) throws SQLException {
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        BossHistoryService bossHistoryService = new BossHistoryService(new BossHistoryRepo());
        Set<BossHistory> bossHistories = bossHistoryService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los boss history"))
                .stream().filter(s -> s.getDepartment().getId() == id).collect(Collectors.toSet());
        Set<Programmer> programmers = new HashSet<>();
        for (BossHistory bossHistory : bossHistories) {
            programmers.add(programmerService.getById(bossHistory.getProgrammer().getId()).orElseThrow(() -> new SQLException("Error al obtener jefes de departamento")));
        }
        return programmers;
    }
}
