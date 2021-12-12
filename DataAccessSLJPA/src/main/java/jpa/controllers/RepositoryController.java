package jpa.controllers;

import jpa.dtos.CommitDTO;
import jpa.dtos.RepositoryDTO;
import jpa.repositories.ProgrammerRepo;
import jpa.repositories.RepositoryRepo;
import jpa.services.CommitService;
import jpa.services.ProgrammerService;
import jpa.services.RepositoryService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

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

    public String getAllRepositoriesJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.getAllRepositories());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Repositories: " + e.getMessage());
            return "Error al obtener los Repositories: " + e.getMessage();
        }
    }

    public String getRepositoryByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.getRepositoryById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Repository con id " + id + ": " + e.getMessage());
            return "Error al obtener Repository con id " + id + ": " + e.getMessage();
        }
    }

    public String insertRepositoryJSON(RepositoryDTO repository) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.updateRepository(repository));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Repository con id " + repository.getId() + ": " + e.getMessage());
            return "Error al actualizar Repository con id " + repository.getId() + ": " + e.getMessage();
        }
    }

    public String updateCommitJSON(RepositoryDTO repository) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.insertRepository(repository));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Commit con id " + repository.getId() + ": " + e.getMessage());
            return "Error al actualizar Commit con id " + repository.getId() + ": " + e.getMessage();
        }
    }

    public String deleteCommitJSON(RepositoryDTO repository) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(repositoryService.deleteRepository(repository));
        }catch(SQLException e) {
            System.err.println("Error al borrar Commit con id " + repository.getId() + ": " + e.getMessage());
            return "Error al borrar Commit con id " + repository.getId() + ": " + e.getMessage();
        }
    }
}
