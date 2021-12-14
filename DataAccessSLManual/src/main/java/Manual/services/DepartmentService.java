package Manual.services;

import Manual.daos.*;
import Manual.dtos.BossHistoryDTO;
import Manual.dtos.DepartmentDTO;
import Manual.mappers.DepartmentMapper;
import Manual.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Class that models from DepartmentDTO using DepartmentMapper to be intermediary between the DepartmentController and
 * the database
 * @author sps169, FedericoTB
 */
public class DepartmentService extends BaseService<Department,Long, DepartmentRepo> {
    private final DepartmentMapper mapper = new DepartmentMapper();
    public DepartmentService(DepartmentRepo repository) {
        super(repository);
    }
    /**
     * Method that query to database using Deparment DAO to obtain all BossHistories in the table deparment.
     * @throws SQLException when fails in the query transaction
     * @return List<DeparmentDto>
     */
    public List<DepartmentDTO> findAllDepartments() throws SQLException {
        List<Department> departments = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los departamentos"));
        List<DepartmentDTO> dtos = new ArrayList<>();
        for (Department department : departments) {
            dtos.add(fillDepartment(department));
        }
        return dtos;
    }
    /**
     * Method that query to database using Deparment DAO to obtain a Deparment in the table deparment by an ID.
     * @param id Long of the deparment to find
     * @throws SQLException when fails in the query transaction
     * @return DeparmentDTO
     */
    public DepartmentDTO getDepartmentById(Long id) throws SQLException {
        Department department = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener departamento con id" + id));
        return fillDepartment(department);
    }
    /**
     * Method that query to database using Deparment DAO to insert a Deparment in the table deparment.
     * @param department DeparmentDTO object to insert
     * @throws SQLException when fails in the query transaction
     * @return DeparmentDTO of Deparment object inserted
     */
    public DepartmentDTO insertDepartment(DepartmentDTO department) throws SQLException {
        if (this.isBossWorkingAtProyects(department.getDepartmentBoss().getId()))
            throw new SQLException("Error al insertar departamento: el jefe participa en proyectos activos");
        Department result = this.insert(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al insertar departamento con id" + department.getId()));
        this.insertNewBossInHistory(department);
        return fillDepartment(result);
    }
    /**
     * Method that query to database using BossHistory DAO to insert a Boss in BossHistory in the table boss_history.
     * @param departmentDTO DepartmentDTO object to insert a Boss in BossHistory
     * @throws SQLException when fails in the query transaction
     */
    private void insertNewBossInHistory(DepartmentDTO departmentDTO) throws SQLException {
        BossHistoryService bossHistoryService = new BossHistoryService(new BossHistoryRepo());
        bossHistoryService.insertBossHistory(new BossHistoryDTO(departmentDTO.getDepartmentBoss(), mapper.fromDTO(departmentDTO), LocalDateTime.now(), null));
    }
    /**
     * Method that query to database using ProjectAssignment DAO to find if the Boss is working in the project.
     * @param id_boss Long of Id of the Boss to check
     * @throws SQLException when fails in the query transaction
     * @return Boolean if Boss is Working in the project
     */
    private boolean isBossWorkingAtProyects(Long id_boss) throws SQLException {
        ProjectAssignmentService projectAssignmentService = new ProjectAssignmentService(new ProjectAssignmentRepo());
        return projectAssignmentService.getAllProjectAssignments().stream().anyMatch(s -> s.getProject().getState().equals("active") && s.getProgrammer().getId() == id_boss);
    }
    /**
     * Method that query to database using Deparment DAO to update a Deparment in the table deparment.
     * @param department DeparmentDTO object to update
     * @throws SQLException when fails in the query transaction
     * @return DeparmentDTO of Deparment object updated
     */
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
    /**
     * Method that query to database using BossHistory DAO to update the old Boss in the boss_history.
     * @param department DeparmentDTO object to update the old Boss
     * @throws SQLException when fails in the query transaction
     * @return Boolean if Boss is Working in the project
     */
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
    /**
     * Method that query to database using Deparment DAO to delete a Deparment in the table deparment.
     * @param department DeparmentDTO object to delete
     * @throws SQLException when fails in the query transaction
     * @return DeparmentDTO of Deparment object deleted
     */
    public DepartmentDTO deleteDepartment(DepartmentDTO department) throws SQLException {
        Department result = this.delete(mapper.fromDTO(department)).orElseThrow(() -> new SQLException("Error al borrar departamento con id" + department.getId()));
        return fillDepartment(result);
    }
    /**
     * Method that query to database using Deparment DAO to complete a Deparment with his Projects and Bosses.
     * @param department DeparmentDTO object to delete
     * @throws SQLException when fails in the query transaction
     * @return DeparmentDTO of Deparment object deleted
     */
    private DepartmentDTO fillDepartment(Department department) throws SQLException {
        DepartmentDTO dto = mapper.toDTO(department);
        dto.setHistoryBosses(this.getBossesOfDepartment(department.getId()));
        dto.setOngoingProjects(this.getProjects(department.getId(), "active"));
        dto.setFinishedProjects(this.getProjects(department.getId(), "ended"));
        return dto;
    }
    /**
     * Method that query to database using Project Dao to obtain a Set of Projects with the ID and status.
     * @param id Long of the Commit with the project ID to find
     * @param status String of the project Status to find
     * @throws SQLException when fails in the query transaction
     * @return Set<Project>
     */
    private Set<Project> getProjects(long id, String status) throws SQLException {
        ProjectService projectService = new ProjectService(new ProjectRepo());
        return projectService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los projectos activos del departamento con id " + id))
                .stream().filter(s -> s.getDepartmentId() == id && s.getState().equals(status)).collect(Collectors.toSet());
    }
    /**
     * Method that query to database using Project Dao and BossHistory DAO to obtain a Set of Bosses of Deparment with the ID department.
     * @param id Long of the Commit with the department ID to find
     * @throws SQLException when fails in the query transaction
     * @return Set<Programmer>
     */
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
