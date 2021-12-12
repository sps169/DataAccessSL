package jpa.controllers;

import jpa.dtos.IssueDTO;
import jpa.dtos.ProjectDTO;
import jpa.repositories.IssueRepo;
import jpa.repositories.ProjectRepo;
import jpa.services.IssueService;
import jpa.services.ProjectService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

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

    public String getAllIssuesJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.getAllIssues());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Issues: " + e.getMessage());
            return "Error al obtener los Issues: " + e.getMessage();
        }
    }

    public String getIssueByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.getIssueById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener Issue con id " + id + ": " + e.getMessage());
            return "Error al obtener Issue con id " + id + ": " + e.getMessage();
        }
    }

    public String insertIssueJSON(IssueDTO issue) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.updateIssue(issue));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage());
            return "Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage();
        }
    }

    public String updateIssueJSON(IssueDTO issue) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueService.insertIssue(issue));
        }catch(SQLException e) {
            System.err.println("Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage());
            return "Error al actualizar Issue con id " + issue.getId() + ": " + e.getMessage();
        }
    }

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
