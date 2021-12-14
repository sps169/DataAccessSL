package Manual.controllers;

import Manual.dtos.DepartmentDTO;
import Manual.repositories.DepartmentRepo;
import Manual.services.DepartmentService;
import Manual.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of Department
 * @author sps169, FedericoTB
 */
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
    /**
     * Method that query to database using DepartmentService and GSON to obtain all Departments as JSON String.
     * @return String of JSON All Departments
     */
    public String getAllDepartmentsJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.findAllDepartments());
        }catch(SQLException e) {
            System.err.println("Error al obtener los Departments: " + e.getMessage());
            return "Error al obtener los Departments: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using DepartmentService and GSON to obtain a Department by ID as JSON String.
     * @param id Long of ID Department to find
     * @return String of JSON of finded Department
     */
    public String getDepartmentByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.getDepartmentById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener department con id " + id + ": " + e.getMessage());
            return "Error al obtener department con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using DepartmentService and GSON to insert a Department by ID as JSON String.
     * @param  department DepartmentDTO to insert
     * @return String of JSON of inserted Department
     */
    public String insertDepartmentJSON(DepartmentDTO department) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.updateDepartment(department));
        }catch(SQLException e) {
            System.err.println("Error al actualizar department con id " + department.getId() + ": " + e.getMessage());
            return "Error al actualizar department con id " + department.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using DepartmentService and GSON to update a Department by ID as JSON String.
     * @param  department DepartmentDTO to update
     * @return String of JSON of updated Department
     */
    public String updateDepartmentJSON(DepartmentDTO department) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(departmentService.insertDepartment(department));
        }catch(SQLException e) {
            System.err.println("Error al actualizar department con id " + department.getId() + ": " + e.getMessage());
            return "Error al actualizar department con id " + department.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using DepartmentService and GSON to delete a Department by ID as JSON String.
     * @param  department DepartmentDTO to delete
     * @return String of JSON of deleted Department
     */
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
