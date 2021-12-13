package jpa.controllers;

import jpa.dtos.DepartmentDTO;
import jpa.repositories.DepartmentRepo;
import jpa.services.DepartmentService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;

public class DepartmentController {
    private static DepartmentController controller;

    private DepartmentService departmentService;
    private DepartmentController (DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public static DepartmentController getInstance() {
        if (controller == null) {
            controller = new DepartmentController(new DepartmentService(new DepartmentRepo()));
        }
        return controller;
    }

    public String getAllDepartmentsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.findAllDepartments());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Departments: " + e.getMessage());
            return "Error al obtener los Departments: " + e.getMessage();
        }
    }

    public String getDepartmentByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.getDepartmentById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener department con id " + id + ": " + e.getMessage());
            return "Error al obtener department con id " + id + ": " + e.getMessage();
        }
    }

    public String insertDepartmentJSON(DepartmentDTO department) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.updateDepartment(department));
        }catch(SQLException e) {
            System.err.println("Error al actualizar department con id " + department.getId() + ": " + e.getMessage());
            return "Error al actualizar department con id " + department.getId() + ": " + e.getMessage();
        }
    }

    public String updateDepartmentJSON(DepartmentDTO department) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.insertDepartment(department));
        }catch(SQLException e) {
            System.err.println("Error al actualizar department con id " + department.getId() + ": " + e.getMessage());
            return "Error al actualizar department con id " + department.getId() + ": " + e.getMessage();
        }
    }

    public String deleteDepartmentJSON(DepartmentDTO department) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.deleteDepartment(department));
        }catch(SQLException e) {
            System.err.println("Error al borrar department con id " + department.getId() + ": " + e.getMessage());
            return "Error al borrar department con id " + department.getId() + ": " + e.getMessage();
        }
    }
}
