package jpa.services;

import jpa.daos.*;
import jpa.dtos.ProgrammerDTO;
import jpa.mappers.ProgrammerMapper;
import jpa.repositories.*;
import jpa.utils.Cifrate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Class that models from ProgrammerDTO using ProgrammerMapper to be intermediary between the ProgrammerController and
 * the database
 * @author sps169, FedericoTB
 */
public class ProgrammerService extends BaseService<Programmer, Long, ProgrammerRepo>{
    private final ProgrammerMapper mapper = new ProgrammerMapper();

    public ProgrammerService(ProgrammerRepo repository) {
        super(repository);
    }
    /**
     * Method that query to database using Programmer DAO to obtain all Programmers in the table programmer.
     * @throws SQLException when fails in the query transaction
     * @return List<ProgrammerDto>
     */
    public List<ProgrammerDTO> getAllProgrammers() throws SQLException {
        List<Programmer> programmers = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los programadores"));
		List<ProgrammerDTO> dtos = new ArrayList<>();
        for (Programmer programmer : programmers) {
            dtos.add(fillProgrammerDTO(programmer));
        }
        return dtos;
    }
    /**
     * Method that query to database using Programmer DAO to obtain a Programmer in the table programmer by an ID.
     * @param id Long of the programmer to find
     * @throws SQLException when fails in the query transaction
     * @return ProgrammerDTO
     */
    public ProgrammerDTO getProgrammerById(Long id) throws SQLException {
        Programmer programmer = this.getById(id).orElseThrow(() ->new SQLException("Error al obtener el programador con id: " + id));
        return fillProgrammerDTO(programmer);
    }
    /**
     * Method that query to database using Programmer DAO to insert a Programmer in the table programmer.
     * @param programmer Programmer object to insert
     * @throws SQLException when fails in the query transaction
     * @return ProgrammerDTO of Programmer object inserted
     */
    public ProgrammerDTO insertProgrammer (ProgrammerDTO programmer) throws SQLException {
        programmer.setPassword(Cifrate.SHA256(programmer.getPassword()));
        Programmer result = this.insert(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al insertar el programador con id: "+ programmer.getId()));
        return fillProgrammerDTO(result);
    }
    /**
     * Method that query to database using Programmer DAO to update a Programmer in the table programmer.
     * @param programmer Programmer object to update
     * @throws SQLException when fails in the query transaction
     * @return ProgrammerDTO of Programmer object updated
     */
    public ProgrammerDTO updateProgrammer (ProgrammerDTO programmer) throws SQLException {
        Programmer result = this.update(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al actualizar el programador con id: "+ programmer.getId()));
        return fillProgrammerDTO(result);
    }
    /**
     * Method that query to database using Programmer DAO to delete a Programmer in the table programmer.
     * @param programmer Programmer object to delete
     * @throws SQLException when fails in the query transaction
     * @return ProgrammerDTO of Programmer object deleted
     */
    public ProgrammerDTO deleteProgrammer (ProgrammerDTO programmer) throws SQLException {
        Programmer result = this.delete(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al eliminar el programador con id: "+ programmer.getId()));
        return fillProgrammerDTO(result);
    }
    /**
     * Method that query to database using Programmer DTO to complete a Programmer with his Sets of Commits, Issues
     * and Projects.
     * @param programmer Programmer object to complete his Sets
     * @throws SQLException when fails in the query transaction
     * @return ProgrammerDTO of Programmer object deleted
     */
    private ProgrammerDTO fillProgrammerDTO(Programmer programmer) throws SQLException {
        ProgrammerDTO dto = mapper.toDTO(programmer);
        dto.setCommits(this.getCommitsOfProgrammer(programmer.getId()));
        dto.setIssuesAssigned(this.getIssuesOfProgrammer(programmer.getId()));
        dto.setProjects(this.getProjectsOfProgrammer(programmer.getId()));
        return dto;
    }
    /**
     * Method that query to database using ProjectService to obtain a Set of Projects in the with the programmer ID.
     * @param id Long of  the programmer ID to find his Projects
     * @throws SQLException when fails in the query transaction
     * @return Set<Project>
     */
    private Set<Project> getProjectsOfProgrammer(long id) throws SQLException {
        ProjectAssignmentService projectAssignmentService = new ProjectAssignmentService(new ProjectAssignmentRepo());
        ProjectService projectService = new ProjectService(new ProjectRepo());
        List<ProjectAssignment> projectAssignments = projectAssignmentService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los project assignments de programador"))
                .stream().filter(s -> s.getProgrammer().getId() == id).collect(Collectors.toList());
        Set<Project> projects = new HashSet<>();
        for (ProjectAssignment projectAssignment : projectAssignments) {
            projects.add(projectService.getById(projectAssignment.getProject().getId()).orElseThrow(() -> new SQLException("Error al obtener todos los project de programador")));
        }
        return projects;
    }
    /**
     * Method that query to database using IssueService to obtain a Set of Issues in the with the programmer ID.
     * @param id Long of  the programmer ID to find his Issues
     * @throws SQLException when fails in the query transaction
     * @return Set<Issue>
     */
    private Set<Issue> getIssuesOfProgrammer(long id) throws SQLException {
        IssueAssignmentService issueAssignmentService = new IssueAssignmentService(new IssueAssigmentRepo());
        List<IssueAssignment> issueAssignments = issueAssignmentService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los issueAssignment de un programador"))
                .stream().filter(s -> s.getProgrammer().getId() == id).collect(Collectors.toList());
        IssueService issueService = new IssueService(new IssueRepo());
        Set<Issue> result = new HashSet<>();
        for(IssueAssignment issueAssignment : issueAssignments) {
            result.add(issueService.getById(issueAssignment.getIssue().getId()).orElseThrow(() -> new SQLException("Error al obtener todos los issues de programaddor")));
        }
        return result;
    }
    /**
     * Method that query to database using CommitService to obtain a Set of Commits with the programmer ID.
     * @param id Long of the programmer ID to find his Commits
     * @throws SQLException when fails in the query transaction
     * @return Set<Commit>
     */
    private Set<Commit> getCommitsOfProgrammer(long id) throws SQLException {
        CommitService commitsService = new CommitService(new CommitRepo());
        return commitsService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los commits de un programador"))
                .stream().filter(s -> s.getProgrammerId() == id).collect(Collectors.toSet());

    }
}
