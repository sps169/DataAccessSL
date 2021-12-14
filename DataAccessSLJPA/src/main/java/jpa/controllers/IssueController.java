package jpa.controllers;

import jpa.dtos.IssueDTO;
import jpa.repositories.IssueRepo;
import jpa.services.IssueService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of Issue
 * @author sps169, FedericoTB
 */
public class IssueController {
    private static IssueController controller;

    private IssueService issueService;
    private IssueController (IssueService issueService) {
        this.issueService = issueService;
    }

    public static IssueController getInstance() {
        if (controller == null) {
            controller = new IssueController(new IssueService(new IssueRepo()));
        }
        return controller;
    }
    /**
     * Method that query to database using IssueService and GSON to obtain all Issues as JSON String.
     * @return String of JSON All Issues
     */
    public String getAllIssuesJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.getAllIssues());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Issues: " + e.getMessage());
            return "Error al obtener los Issues: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueService and GSON to obtain a Issue by ID as JSON String.
     * @param id Long of ID Issue to find
     * @return String of JSON of finded Issue
     */
    public String getIssueByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.getIssueById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Issue con id " + id + ": " + e.getMessage());
            return "Error al obtener Issue con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueService and GSON to insert a Issue by ID as JSON String.
     * @param  issue IssueDTO to insert
     * @return String of JSON of inserted Issue
     */
    public String insertIssueJSON(IssueDTO issue) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.updateIssue(issue));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage());
            return "Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueService and GSON to update a Issue by ID as JSON String.
     * @param  issue IssueDTO to update
     * @return String of JSON of updated Issue
     */
    public String updateIssueJSON(IssueDTO issue) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.insertIssue(issue));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage());
            return "Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using IssueService and GSON to delete a Issue by ID as JSON String.
     * @param  issue IssueDTO to delete
     * @return String of JSON of deleted Issue
     */
    public String deleteIssueJSON(IssueDTO issue) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.deleteIssue(issue));
        }catch(SQLException e) {
            System.err.println("Error al borrar Issue con id " + issue.getId() + ": " + e.getMessage());
            return "Error al borrar Issue con id " + issue.getId() + ": " + e.getMessage();
        }
    }
}
