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

    public String getAllBossHistoriesJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.getAllBossHistory());
        }catch(SQLException e) {
            System.err.println("Error al obtener los BossHistories: " + e.getMessage());
            return "Error al obtener los BossHistories: " + e.getMessage();
        }
    }

    public String getBossHistoryByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.getBossHistoryById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener BossHistory con id " + id + ": " + e.getMessage());
            return "Error al obtener BossHistory con id " + id + ": " + e.getMessage();
        }
    }

    public String insertBossHistoryJSON(BossHistoryDTO bossHistory) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.updateBossHistory(bossHistory));
        }catch(SQLException e) {
            System.err.println("Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage());
            return "Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage();
        }
    }

    public String updateBossHistoryJSON(BossHistoryDTO bossHistory) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(bossHistoryService.insertBossHistory(bossHistory));
        }catch(SQLException e) {
            System.err.println("Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage());
            return "Error al actualizar BossHistory con id " + bossHistory.getId() + ": " + e.getMessage();
        }
    }

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
