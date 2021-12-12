package jpa.controllers;

import jpa.dtos.CommitDTO;
import jpa.repositories.CommitRepo;
import jpa.services.CommitService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

public class CommitController {
    private static CommitController controller;

    private CommitService commitService;
    private CommitController (CommitService commitService) {
        this.commitService = commitService;
    }

    public static CommitController getInstance() {
        if (controller == null) {
            controller = new CommitController(new CommitService(new CommitRepo()));
        }
        return controller;
    }

    public String getAllCommitsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.getAllCommits());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Commits: " + e.getMessage());
            return "Error al obtener los Commits: " + e.getMessage();
        }
    }

    public String getCommitByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.getCommitById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Commit con id " + id + ": " + e.getMessage());
            return "Error al obtener Commit con id " + id + ": " + e.getMessage();
        }
    }

    public String insertCommitJSON(CommitDTO commit) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.updateCommit(commit));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage());
            return "Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage();
        }
    }

    public String updateCommitJSON(CommitDTO commit) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.insertCommit(commit));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage());
            return "Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage();
        }
    }

    public String deleteCommitJSON(CommitDTO commit) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.deleteCommit(commit));
        }catch(SQLException e) {
            System.err.println("Error al borrar Commit con id " + commit.getId() + ": " + e.getMessage());
            return "Error al borrar Commit con id " + commit.getId() + ": " + e.getMessage();
        }
    }
}
