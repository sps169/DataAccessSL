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

public class BossHistoryService extends BaseService<BossHistory, Long, BossHistoryRepo>{
    private BossHistoryMapper mapper = new BossHistoryMapper();


    public BossHistoryService(BossHistoryRepo repository) {
        super(repository);
    }

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

    public BossHistoryDTO getBossHistoryById (Long bossHistoryID) throws SQLException {
        BossHistory bossHistory = this.getById(bossHistoryID).orElseThrow(() -> new SQLException("Error al obtener boss history con id " + bossHistoryID));
        BossHistoryDTO dto = mapper.toDTO(bossHistory);
        dto.setProgrammer(this.getProgrammerById(bossHistory.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(bossHistory.getDepartmentId()));
        return dto;
    }

    public BossHistoryDTO insertBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.insert(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al insertar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(result.getDepartmentId()));
        return dto;
    }

    public BossHistoryDTO updateBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.update(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al actualizar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(result.getDepartmentId()));
        return dto;
    }

    public BossHistoryDTO deleteBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.delete(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al borrar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        dto.setProgrammer(this.getProgrammerById(result.getProgrammerId()));
        dto.setDepartment(this.getDepartmentById(result.getDepartmentId()));
        return dto;
    }

    private Department getDepartmentById(long departmentId) throws SQLException {
        DepartmentService departmentService = new DepartmentService(new DepartmentRepo());
        return departmentService.getById(departmentId).orElseThrow(() -> new SQLException("Error al obtener department de boss history"));
    }

    private Programmer getProgrammerById(long programmerId) throws SQLException{
        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
        return programmerService.getById(programmerId).orElseThrow(() -> new SQLException("Error al obtener programmer de boss history"));
    }
}
