package Manual.services;

import Manual.daos.*;
import Manual.dtos.ProgrammerDTO;
import Manual.mappers.ProgrammerMapper;
import Manual.repositories.*;
import Manual.utils.Cifrate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProgrammerService extends BaseService<Programmer, Long, ProgrammerRepo>{
    private final ProgrammerMapper mapper = new ProgrammerMapper();

    public ProgrammerService(ProgrammerRepo repository) {
        super(repository);
    }

    public List<ProgrammerDTO> getAllProgrammers() throws SQLException {
        List<Programmer> programmers = this.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los programadores"));
		List<ProgrammerDTO> dtos = new ArrayList<>();
        for (Programmer programmer : programmers) {
            dtos.add(fillProgrammerDTO(programmer));
        }
        return dtos;
    }

    public ProgrammerDTO getProgrammerById(Long id) throws SQLException {
        Programmer programmer = this.getById(id).orElseThrow(() ->new SQLException("Error al obtener el programador con id: " + id));
        return fillProgrammerDTO(programmer);
    }

    public ProgrammerDTO insertProgrammer (ProgrammerDTO programmer) throws SQLException {
        programmer.setPassword(Cifrate.SHA256(programmer.getPassword()));
        Programmer result = this.insert(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al insertar el programador con id: "+ programmer.getId()));
        return fillProgrammerDTO(result);
    }

    public ProgrammerDTO updateProgrammer (ProgrammerDTO programmer) throws SQLException {
        Programmer result = this.update(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al actualizar el programador con id: "+ programmer.getId()));
        return fillProgrammerDTO(result);
    }

    public ProgrammerDTO deleteProgrammer (ProgrammerDTO programmer) throws SQLException {
        Programmer result = this.delete(mapper.fromDTO(programmer)).orElseThrow(() -> new SQLException("Error al eliminar el programador con id: "+ programmer.getId()));
        return fillProgrammerDTO(result);
    }

    private ProgrammerDTO fillProgrammerDTO(Programmer result) throws SQLException {
        ProgrammerDTO dto = mapper.toDTO(result);
        dto.setCommits(this.getCommitsOfProgrammer(result.getId()));
        dto.setIssuesAssigned(this.getIssuesOfProgrammer(result.getId()));
        dto.setProjects(this.getProjectsOfProgrammer(result.getId()));
        return dto;
    }

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

    private Set<Commit> getCommitsOfProgrammer(long id) throws SQLException {
        CommitService commitsService = new CommitService(new CommitRepo());
        return commitsService.findAll().orElseThrow(() -> new SQLException("Error al obtener todos los commits de un programador"))
                .stream().filter(s -> s.getProgrammer().getId() == id).collect(Collectors.toSet());

    }
}
