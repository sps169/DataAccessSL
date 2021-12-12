package jpa.controllers;

import jpa.dtos.ProjectDTO;
import jpa.dtos.RepositoryDTO;
import jpa.repositories.ProjectRepo;
import jpa.repositories.RepositoryRepo;
import jpa.services.ProjectService;
import jpa.services.RepositoryService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

public class ProjectController {
    private static ProjectController controller;

    private ProjectService projectService;
    private ProjectController (ProjectService projectService) {
        this.projectService = projectService;
    }

    public static ProjectController getInstance() {
        if (controller == null) {
            controller = new ProjectController(new ProjectService(new ProjectRepo()));
        }
        return controller;
    }

    public String getAllProjectsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.findAllProjects());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Projects: " + e.getMessage());
            return "Error al obtener los Projects: " + e.getMessage();
        }
    }

    public String getProjectByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.getProjectById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Project con id " + id + ": " + e.getMessage());
            return "Error al obtener Project con id " + id + ": " + e.getMessage();
        }
    }

    public String insertProjectJSON(ProjectDTO project) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.updateProject(project));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Project con id " + project.getId() + ": " + e.getMessage());
            return "Error al actualizar Project con id " + project.getId() + ": " + e.getMessage();
        }
    }

    public String updateProjectJSON(ProjectDTO project) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.insertProject(project));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Project con id " + project.getId() + ": " + e.getMessage());
            return "Error al actualizar Project con id " + project.getId() + ": " + e.getMessage();
        }
    }

    public String deleteProjectJSON(ProjectDTO project) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.deleteProject(project));
        }catch(SQLException e) {
            System.err.println("Error al borrar Project con id " + project.getId() + ": " + e.getMessage());
            return "Error al borrar Project con id " + project.getId() + ": " + e.getMessage();
        }
    }
}
