package jpa.controllers;

import jpa.dtos.IssueAssignmentDTO;
import jpa.repositories.IssueAssigmentRepo;
import jpa.services.IssueAssignmentService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

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

    public String getAllIssueAssignmentsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.getAllIssuesAssignment());
        }catch(SQLException e) {
            System.err.println("Error al obtener los IssueAssignment: " + e.getMessage());
            return "Error al obtener los IssueAssignment: " + e.getMessage();
        }
    }

    public String getIssueAssignmentByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.getIssueAssignmentById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener IssueAssignment con id " + id + ": " + e.getMessage());
            return "Error al obtener IssueAssignment con id " + id + ": " + e.getMessage();
        }
    }

    public String insertIssueAssignmentJSON(IssueAssignmentDTO issueAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.updateIssueAssignment(issueAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage();
        }
    }

    public String updateIssueAssignmentJSON(IssueAssignmentDTO issueAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(issueAssignmentService.insertIssueAssignment(issueAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar IssueAssignment con id " + issueAssignment.getId() + ": " + e.getMessage();
        }
    }

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
