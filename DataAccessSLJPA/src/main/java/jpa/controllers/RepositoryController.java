package jpa.controllers;


import jpa.dtos.RepositoryDTO;
import jpa.repositories.RepositoryRepo;
import jpa.services.RepositoryService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of Repository
 * @author sps169, FedericoTB
 */
public class RepositoryController {
    private static RepositoryController controller;

    private RepositoryService repositoryService;
    private RepositoryController (RepositoryService programmerService) {
        this.repositoryService = repositoryService;
    }

    public static RepositoryController getInstance() {
        if (controller == null) {
            controller = new RepositoryController(new RepositoryService(new RepositoryRepo()));
        }
        return controller;
    }
    /**
     * Method that query to database using RepositoryService and GSON to obtain all Repositorys as JSON String.
     * @return String of JSON All Repositorys
     */
    public String getAllRepositoriesJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.getAllRepositories());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Repositories: " + e.getMessage());
            return "Error al obtener los Repositories: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using RepositoryService and GSON to obtain a Repository by ID as JSON String.
     * @param id Long of ID Repository to find
     * @return String of JSON of finded Repository
     */
    public String getRepositoryByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.getRepositoryById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Repository con id " + id + ": " + e.getMessage());
            return "Error al obtener Repository con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using RepositoryService and GSON to insert a Repository by ID as JSON String.
     * @param  repository RepositoryDTO to insert
     * @return String of JSON of inserted Repository
     */
    public String insertRepositoryJSON(RepositoryDTO repository) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.updateRepository(repository));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Repository con id " + repository.getId() + ": " + e.getMessage());
            return "Error al actualizar Repository con id " + repository.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using RepositoryService and GSON to update a Repository by ID as JSON String.
     * @param  repository RepositoryDTO to update
     * @return String of JSON of updated Repository
     */
    public String updateRepositoryJSON(RepositoryDTO repository) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.insertRepository(repository));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Repository con id " + repository.getId() + ": " + e.getMessage());
            return "Error al actualizar Repository con id " + repository.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using RepositoryService and GSON to delete a Repository by ID as JSON String.
     * @param  repository RepositoryDTO to delete
     * @return String of JSON of deleted Repository
     */
    public String deleteRepositoryJSON(RepositoryDTO repository) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.deleteRepository(repository));
        }catch(SQLException e) {
            System.err.println("Error al borrar Repository con id " + repository.getId() + ": " + e.getMessage());
            return "Error al borrar Repository con id " + repository.getId() + ": " + e.getMessage();
        }
    }
}
