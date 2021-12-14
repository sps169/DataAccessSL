package jpa.services;

import jpa.daos.BossHistory;

import jpa.dtos.BossHistoryDTO;
import jpa.mappers.BossHistoryMapper;
import jpa.repositories.BossHistoryRepo;

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
        return dto;
    }
}
