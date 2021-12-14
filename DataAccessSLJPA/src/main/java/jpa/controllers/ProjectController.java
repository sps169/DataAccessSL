package jpa.controllers;

import jpa.dtos.ProjectDTO;
import jpa.repositories.ProjectRepo;
import jpa.services.ProjectService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of Project
 * @author sps169, FedericoTB
 */
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
    /**
     * Method that query to database using ProjectService and GSON to obtain all Projects as JSON String.
     * @return String of JSON All Projects
     */
    public String getAllProjectsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.findAllProjects());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Projects: " + e.getMessage());
            return "Error al obtener los Projects: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectService and GSON to obtain a Project by ID as JSON String.
     * @param id Long of ID Project to find
     * @return String of JSON of finded Project
     */
    public String getProjectByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.getProjectById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Project con id " + id + ": " + e.getMessage());
            return "Error al obtener Project con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectService and GSON to insert a Project by ID as JSON String.
     * @param  project ProjectDTO to insert
     * @return String of JSON of inserted Project
     */
    public String insertProjectJSON(ProjectDTO project) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.updateProject(project));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Project con id " + project.getId() + ": " + e.getMessage());
            return "Error al actualizar Project con id " + project.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectService and GSON to update a Project by ID as JSON String.
     * @param  project ProjectDTO to update
     * @return String of JSON of updated Project
     */
    public String updateProjectJSON(ProjectDTO project) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectService.insertProject(project));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Project con id " + project.getId() + ": " + e.getMessage());
            return "Error al actualizar Project con id " + project.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectService and GSON to delete a Project by ID as JSON String.
     * @param  project ProjectDTO to delete
     * @return String of JSON of deleted Project
     */
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
