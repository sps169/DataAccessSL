package Manual.controllers;

import Manual.dtos.IssueAssignmentDTO;
import Manual.repositories.IssueAssigmentRepo;
import Manual.services.IssueAssignmentService;
import Manual.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of IssueAssignment
 * @author sps169, FedericoTB
 */
public class IssueAssignmentController {

    private static IssueAssignmentController controller;

    private IssueAssignmentService issueAssignmentService;
    private IssueAssignmentController(IssueAssignmentService issueAssignmentService) {
        this.issueAssignmentService = issueAssignmentService;
    }

    public static IssueAssignmentController getInstance() {
        if (controller == null) {
            controller = new IssueAssignmentController(new IssueAssignmentService(new IssueAssigmentRepo()));
        }
        return controller;
    }
    /**
     * Method that query to database using IssueAssignmentService and GSON to obtain all IssueAssignments as JSON String.
     * @return String of JSON All IssueAssignments
     */
    public String getAllIssueAssignmentsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.getAllIssuesAssignment());
        }catch(SQLException e) {
            System.err.println("Error al obtener los IssueAssignment: " + e.getMessage());
            return "Error al obtener los IssueAssignment: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueAssignmentService and GSON to obtain a IssueAssignment by ID as JSON String.
     * @param id Long of ID IssueAssignment to find
     * @return String of JSON of finded IssueAssignment
     */
    public String getIssueAssignmentByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.getIssueAssignmentById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener IssueAssignment con id " + id + ": " + e.getMessage());
            return "Error al obtener IssueAssignment con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueAssignmentService and GSON to insert a IssueAssignment by ID as JSON String.
     * @param  issueAssignment IssueAssignmentDTO to insert
     * @return String of JSON of inserted IssueAssignment
     */
    public String insertIssueAssignmentJSON(IssueAssignmentDTO issueAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.updateIssueAssignment(issueAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueAssignmentService and GSON to update a IssueAssignment by ID as JSON String.
     * @param  issueAssignment IssueAssignmentDTO to update
     * @return String of JSON of updated IssueAssignment
     */
    public String updateIssueAssignmentJSON(IssueAssignmentDTO issueAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.insertIssueAssignment(issueAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueAssignmentService and GSON to delete a IssueAssignment by ID as JSON String.
     * @param  issueAssignment IssueAssignmentDTO to delete
     * @return String of JSON of deleted IssueAssignment
     */
    public String deleteIssueAssignmentJSON(IssueAssignmentDTO issueAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.deleteIssueAssignment(issueAssignment));
        }catch(SQLException e) {
            System.err.println("Error al borrar Commit con id " + issueAssignment.getId() + ": " + e.getMessage());
            return "Error al borrar Commit con id " + issueAssignment.getId() + ": " + e.getMessage();
        }
    }
}
