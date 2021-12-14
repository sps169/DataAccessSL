package Manual.controllers;

import Manual.dtos.BossHistoryDTO;
import Manual.dtos.CommitDTO;
import Manual.repositories.BossHistoryRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.services.BossHistoryService;
import Manual.services.CommitService;
import Manual.services.ProgrammerService;
import Manual.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of BossHistory
 * @author sps169, FedericoTB
 */
public class BossHistoryController {
    private static BossHistoryController controller;

    private BossHistoryService bossHistoryService;
    private BossHistoryController (BossHistoryService bossHistoryService) {
        this.bossHistoryService = bossHistoryService;
    }

    public static BossHistoryController getInstance() {
        if (controller == null) {
            controller = new BossHistoryController(new BossHistoryService(new BossHistoryRepo()));
        }
        return controller;
    }
    /**
     * Method that query to database using BossHistoryService and GSON to obtain all BossHistories as JSON String.
     * @return String of JSON All BossHistories
     */
    public String getAllBossHistoriesJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.getAllBossHistory());
        }catch(SQLException e) {
            System.err.println("Error al obtener los BossHistories: " + e.getMessage());
            return "Error al obtener los BossHistories: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using BossHistoryService and GSON to obtain a BossHistory by ID as JSON String.
     * @param id Long of ID BossHistory to find
     * @return String of JSON of finded BossHistory
     */
    public String getBossHistoryByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.getBossHistoryById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener BossHistory con id " + id + ": " + e.getMessage());
            return "Error al obtener BossHistory con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using BossHistoryService and GSON to insert a BossHistory by ID as JSON String.
     * @param  bossHistory BossHistoryDTO to insert
     * @return String of JSON of inserted BossHistory
     */
    public String insertBossHistoryJSON(BossHistoryDTO bossHistory) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.updateBossHistory(bossHistory));
        }catch(SQLException e) {
            System.err.println("Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage());
            return "Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using BossHistoryService and GSON to update a BossHistory by ID as JSON String.
     * @param  bossHistory BossHistoryDTO to update
     * @return String of JSON of updated BossHistory
     */
    public String updateBossHistoryJSON(BossHistoryDTO bossHistory) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.insertBossHistory(bossHistory));
        }catch(SQLException e) {
            System.err.println("Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage());
            return "Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using BossHistoryService and GSON to delete a BossHistory by ID as JSON String.
     * @param  bossHistory BossHistoryDTO to delete
     * @return String of JSON of deleted BossHistory
     */
    public String deleteBossHistoryJSON(BossHistoryDTO bossHistory) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.deleteBossHistory(bossHistory));
        }catch(SQLException e) {
            System.err.println("Error al borrar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage());
            return "Error al borrar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage();
        }
    }
}
