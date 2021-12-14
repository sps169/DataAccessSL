package jpa.controllers;

import jpa.dtos.CommitDTO;
import jpa.repositories.CommitRepo;
import jpa.services.CommitService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of Commit
 * @author sps169, FedericoTB
 */
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
    /**
     * Method that query to database using CommitService and GSON to obtain all Commits as JSON String.
     * @return String of JSON All Commits
     */
    public String getAllCommitsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.getAllCommits());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Commits: " + e.getMessage());
            return "Error al obtener los Commits: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using CommitService and GSON to obtain a Commit by ID as JSON String.
     * @param id Long of ID Commit to find
     * @return String of JSON of finded Commit
     */
    public String getCommitByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.getCommitById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Commit con id " + id + ": " + e.getMessage());
            return "Error al obtener Commit con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using CommitService and GSON to insert a Commit by ID as JSON String.
     * @param  commit CommitDTO to insert
     * @return String of JSON of inserted Commit
     */
    public String insertCommitJSON(CommitDTO commit) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.updateCommit(commit));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage());
            return "Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using CommitService and GSON to update a Commit by ID as JSON String.
     * @param  commit CommitDTO to update
     * @return String of JSON of updated Commit
     */
    public String updateCommitJSON(CommitDTO commit) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(commitService.insertCommit(commit));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage());
            return "Error al actualizar Commit con id " + commit.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using CommitService and GSON to delete a Commit by ID as JSON String.
     * @param  commit CommitDTO to delete
     * @return String of JSON of deleted Commit
     */
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
