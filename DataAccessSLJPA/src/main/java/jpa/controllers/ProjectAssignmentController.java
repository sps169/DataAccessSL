package jpa.controllers;

import jpa.dtos.IssueAssignmentDTO;
import jpa.dtos.ProjectAssignmentDTO;
import jpa.repositories.ProjectAssignmentRepo;
import jpa.services.ProjectAssignmentService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

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

    public String getAllProjectAssignmentsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.getAllProjectAssignments());
        }catch(SQLException e) {
            System.err.println("Error al obtener los ProjectAssignments: " + e.getMessage());
            return "Error al obtener los ProjectAssignments: " + e.getMessage();
        }
    }

    public String getProjectAssignmentByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.getProjectAssignmentById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener ProjectAssignment con id " + id + ": " + e.getMessage());
            return "Error al obtener ProjectAssignment con id " + id + ": " + e.getMessage();
        }
    }

    public String insertProjectAssignmentJSON(ProjectAssignmentDTO projectAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.updateProjectAssignment(projectAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage();
        }
    }

    public String updateProjectAssignmentJSON(ProjectAssignmentDTO projectAssignment) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(projectAssignmentService.insertProjectAssignment(projectAssignment));
        }catch(SQLException e) {
            System.err.println("Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage());
            return "Error al actualizar ProjectAssignment con id " + projectAssignment.getId() + ": " + e.getMessage();
        }
    }

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
