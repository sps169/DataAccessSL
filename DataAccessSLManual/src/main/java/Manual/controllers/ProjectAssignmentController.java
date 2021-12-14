package Manual.controllers;

import Manual.dtos.IssueAssignmentDTO;
import Manual.dtos.ProjectAssignmentDTO;
import Manual.repositories.ProjectAssignmentRepo;
import Manual.services.ProjectAssignmentService;
import Manual.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of ProjectAssignment
 * @author sps169, FedericoTB
 */
public class ProjectAssignmentController {
    private static ProjectAssignmentController controller;

    private ProjectAssignmentService projectAssignmentService;
    private ProjectAssignmentController(ProjectAssignmentService projectAssignmentService) {
        this.projectAssignmentService = projectAssignmentService;
    }

    public static ProjectAssignmentController getInstance() {
        if (controller == null) {
            controller = new ProjectAssignmentController(new ProjectAssignmentService(new ProjectAssignmentRepo()));
        }
        return controller;
    }
    /**
     * Method that query to database using ProjectAssignmentService and GSON to obtain all ProjectAssignments as JSON String.
     * @return String of JSON All ProjectAssignments
     */
    public String getAllProjectAssignmentsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.getAllProjectAssignments());
        }catch(SQLException e) {
            System.err.println("Error al obtener los ProjectAssignments: " + e.getMessage());
            return "Error al obtener los ProjectAssignments: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectAssignmentService and GSON to obtain a ProjectAssignment by ID as JSON String.
     * @param id Long of ID ProjectAssignment to find
     * @return String of JSON of finded ProjectAssignment
     */
    public String getProjectAssignmentByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.getProjectAssignmentById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener ProjectAssignment con id " + id + ": " + e.getMessage());
            return "Error al obtener ProjectAssignment con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectAssignmentService and GSON to insert a ProjectAssignment by ID as JSON String.
     * @param  projectAssignment ProjectAssignmentDTO to insert
     * @return String of JSON of inserted ProjectAssignment
     */
    public String insertProjectAssignmentJSON(ProjectAssignmentDTO projectAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.updateProjectAssignment(projectAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectAssignmentService and GSON to update a ProjectAssignment by ID as JSON String.
     * @param  projectAssignment ProjectAssignmentDTO to update
     * @return String of JSON of updated ProjectAssignment
     */
    public String updateProjectAssignmentJSON(ProjectAssignmentDTO projectAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.insertProjectAssignment(projectAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProjectAssignmentService and GSON to delete a ProjectAssignment by ID as JSON String.
     * @param  projectAssignment ProjectAssignmentDTO to delete
     * @return String of JSON of deleted ProjectAssignment
     */
    public String deleteProjectAssignmentJSON(ProjectAssignmentDTO projectAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.deleteProjectAssignment(projectAssignment));
        }catch(SQLException e) {
            System.err.println("Error al borrar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage());
            return "Error al borrar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage();
        }
    }
}
