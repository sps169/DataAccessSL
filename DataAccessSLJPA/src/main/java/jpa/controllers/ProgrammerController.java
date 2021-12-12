package jpa.controllers;

import jpa.dtos.ProgrammerDTO;
import jpa.repositories.ProgrammerRepo;
import jpa.services.ProgrammerService;
import jpa.utils.GsonConverter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLException;

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

    public String getAllProgrammersJSON() {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.getAllProgrammers());
        }catch(SQLException e) {
            System.err.println("Error al obtener los programadores: " + e.getMessage());
            return "Error al obtener los programadores: " + e.getMessage();
        }
    }

    public String getProgrammerByIdJSON(Long id) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.getProgrammerById(id));
        }catch(SQLException e) {
            System.err.println("Error al obtener programador con id " + id + ": " + e.getMessage());
            return "Error al obtener programador con id " + id + ": " + e.getMessage();
        }
    }

    public String insertProgrammerJSON(ProgrammerDTO programmer) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.updateProgrammer(programmer));
        }catch(SQLException e) {
            System.err.println("Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage());
            return "Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage();
        }
    }

    public String updateProgrammerJSON(ProgrammerDTO programmer) {
        try {
            GsonConverter gsonConverter = new GsonConverter();
            return gsonConverter.toJson(programmerService.insertProgrammer(programmer));
        }catch(SQLException e) {
            System.err.println("Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage());
            return "Error al actualizar programador con id " + programmer.getId() + ": " + e.getMessage();
        }
    }

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
