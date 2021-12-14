package Manual.services;

import Manual.daos.BossHistory;
import Manual.daos.Department;
import Manual.daos.Programmer;
import Manual.dtos.BossHistoryDTO;
import Manual.mappers.BossHistoryMapper;
import Manual.repositories.BossHistoryRepo;
import Manual.repositories.DepartmentRepo;
import Manual.repositories.ProgrammerRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that models from BossHistoryDTO using BossHistoryMapper to be intermediary between the BossHistoryController and
 * the database
 * @author sps169, FedericoTB
 */
public class BossHistoryService extends BaseService<BossHistory, Long, BossHistoryRepo>{
    private BossHistoryMapper mapper = new BossHistoryMapper();


    public BossHistoryService(BossHistoryRepo repository) {
        super(repository);
    }
    /**
     * Method that query to database using BossHistory DAO to obtain all BossHistories in the table boss_history.
     * @throws SQLException when fails in the query transaction
     * @return List<BossHistoryDto>
     */
    public List<BossHistoryDTO> getAllBossHistory () throws SQLException {
        List<BossHistory> bossHistories = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los boss histories"));
        List<BossHistoryDTO> bossHistoryDTOS = new ArrayList<>();
        for (BossHistory bossHistory : bossHistories) {
            BossHistoryDTO dto = mapper.toDTO(bossHistory);
            dto.setProgrammer(this.getProgrammerById(bossHistory.getProgrammerId()));
            dto.setDepartment(this.getDepartmentById(bossHistory.getDepartmentId()));
            bossHistoryDTOS.add(dto);
        }
        return bossHistoryDTOS;
    }
    /**
     * Method that query to database using BossHistory DAO to obtain a BossHistory in the table boss_history by an ID.
     * @param bossHistoryID Long of the bossHistory to find
     * @throws SQLException when fails in the query transaction
     * @return BossHistoryDTO
     */
    public BossHistoryDTO getBossHistoryById (Long bossHistoryID) throws SQLException {
        BossHistory bossHistory = this.getById(bossHistoryID).orElseThrow(() -> new SQLException("Error al obtener boss history con id " + bossHistoryID));
        BossHistoryDTO dto = mapper.toDTO(bossHistory);
        dto.setProgrammer(this.getProgrammerById(bossHistory.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(bossHistory.getDepartmentId()));
        return dto;
    }
    /**
     * Method that query to database using BossHistory DAO to insert a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to insert
     * @throws SQLException when fails in the query transaction
     * @return BossHistoryDTO of BossHistory object inserted
     */
    public BossHistoryDTO insertBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.insert(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al insertar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(result.getDepartmentId()));
        return dto;
    }
    /**
     * Method that query to database using BossHistory DAO to update a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to update
     * @throws SQLException when fails in the query transaction
     * @return BossHistoryDTO of BossHistory object updated
     */
    public BossHistoryDTO updateBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.update(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al actualizar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(result.getDepartmentId()));
        return dto;
    }
    /**
     * Method that query to database using BossHistory DAO to delete a BossHistory in the table boss_history.
     * @param bossHistory BossHistory object to delete
     * @throws SQLException when fails in the query transaction
     * @return BossHistoryDTO of BossHistory object deleted
     */
    public BossHistoryDTO deleteBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.delete(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al borrar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(result.getDepartmentId()));
        return dto;
    }
    /**
     * Method that query to database using Department DAO to find a Department in the table department by id.
     * @param departmentId Long id to find Department
     * @throws SQLException when fails in the query transaction
     * @return Department
     */
    private Department getDepartmentById(long departmentId) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        return departmentService.getById(departmentId).orElseThrow(() -> new SQLException("Error al obtener department de boss history"));
    }
    /**
     * Method that query to database using Programmer DAO to find a Programmer in the table programmer by id.
     * @param programmerId Long id to find Programmer
     * @throws SQLException when fails in the query transaction
     * @return Programmer
     */
    private Programmer getProgrammerById(long programmerId) throws SQLException{
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(programmerId).orElseThrow(() -> new SQLException("Error al obtener programmer de boss history"));
    }
}
