package Manual.services;

import Manual.daos.BossHistory;

import Manual.dtos.BossHistoryDTO;
import Manual.mappers.BossHistoryMapper;
import Manual.repositories.BossHistoryRepo;

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
            bossHistoryDTOS.add(dto);
        }
        return bossHistoryDTOS;
    }

    public BossHistoryDTO getBossHistoryById (Long bossHistoryID) throws SQLException {
        BossHistory bossHistory = this.getById(bossHistoryID).orElseThrow(() -> new SQLException("Error al obtener boss history con id " + bossHistoryID));
        BossHistoryDTO dto = mapper.toDTO(bossHistory);
        return dto;
    }

    public BossHistoryDTO insertBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.insert(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al insertar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        return dto;
    }

    public BossHistoryDTO updateBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.update(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al actualizar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        return dto;
    }

    public BossHistoryDTO deleteBossHistory (BossHistoryDTO bossHistory) throws SQLException{
        BossHistory result = this.delete(mapper.fromDTO(bossHistory)).orElseThrow(() -> new SQLException("Error al borrar boss history"));
        BossHistoryDTO dto = mapper.toDTO(result);
        return dto;
    }
}
