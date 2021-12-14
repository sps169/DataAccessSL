package jpa.controllers;

import jpa.dtos.ProgrammerDTO;
import jpa.repositories.ProgrammerRepo;
import jpa.services.ProgrammerService;
import jpa.utils.GsonConverter;

import java.sql.SQLException;
/**
 * Singleton Class that models ToJSON the CRUD Operations of Programmer
 * @author sps169, FedericoTB
 */
public class ProgrammerController {
    private static ProgrammerController controller;

    private ProgrammerService programmerService;
    private ProgrammerController (ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    public static ProgrammerController getInstance() {
        if (controller == null) {
            controller = new ProgrammerController(new ProgrammerService(new ProgrammerRepo()));
        }
        return controller;
    }
    /**
     * Method that query to database using ProgrammerService and GSON to obtain all Programmers as JSON String.
     * @return String of JSON All Programmers
     */
    public String getAllProgrammersJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.getAllProgrammers());
        }catch(SQLException e) {
            System.err.println("Error al obtener los programadores: " + e.getMessage());
            return "Error al obtener los programadores: " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProgrammerService and GSON to obtain a Programmer by ID as JSON String.
     * @param id Long of ID Programmer to find
     * @return String of JSON of finded Programmer
     */
    public String getProgrammerByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.getProgrammerById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener programador con id " + id + ": " + e.getMessage());
            return "Error al obtener programador con id " + id + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProgrammerService and GSON to insert a Programmer by ID as JSON String.
     * @param  programmer ProgrammerDTO to insert
     * @return String of JSON of inserted Programmer
     */
    public String insertProgrammerJSON(ProgrammerDTO programmer) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.updateProgrammer(programmer));
        }catch(SQLException e) {
            System.err.println("Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage());
            return "Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProgrammerService and GSON to update a Programmer by ID as JSON String.
     * @param  programmer ProgrammerDTO to update
     * @return String of JSON of updated Programmer
     */
    public String updateProgrammerJSON(ProgrammerDTO programmer) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.insertProgrammer(programmer));
        }catch(SQLException e) {
            System.err.println("Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage());
            return "Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage();
        }
    }
    /**
     * Method that query to database using ProgrammerService and GSON to delete a Programmer by ID as JSON String.
     * @param  programmer ProgrammerDTO to delete
     * @return String of JSON of deleted Programmer
     */
    public String deleteProgrammerJSON(ProgrammerDTO programmer) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.deleteProgrammer(programmer));
        }catch(SQLException e) {
            System.err.println("Error al borrar programador con id " + programmer.getId() + ": " + e.getMessage());
            return "Error al borrar programador con id " + programmer.getId() + ": " + e.getMessage();
        }
    }
}
