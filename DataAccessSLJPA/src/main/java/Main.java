import jpa.controllers.*;
import jpa.daos.ProjectAssignment;
import jpa.repositories.CommitRepo;
import jpa.repositories.IssueRepo;
import jpa.repositories.RepositoryRepo;
import jpa.services.CommitService;
import jpa.services.IssueService;
import jpa.services.RepositoryService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        CommitController commitController = CommitController.getInstance();
        System.out.println("Commits");
        System.out.println(commitController.getAllCommitsJSON());

        BossHistoryController bossHistoryController = BossHistoryController.getInstance();
        System.out.println("Boss Histories");
        System.out.println(bossHistoryController.getAllBossHistoriesJSON());

        DepartmentController departmentController = DepartmentController.getInstance();
        System.out.println("Departments");
        System.out.println(departmentController.getAllDepartmentsJSON());

        IssueAssignmentController issueAssignmentController = IssueAssignmentController.getInstance();
        System.out.println("Issue Assignments");
        System.out.println(issueAssignmentController.getAllIssueAssignmentsJSON());

        IssueController issueController = IssueController.getInstance();
        System.out.println("Issues");
        System.out.println(issueController.getAllIssuesJSON());

        ProgrammerController programmerController = ProgrammerController.getInstance();
        System.out.println("Programmers");
        System.out.println(programmerController.getAllProgrammersJSON());

        ProjectAssignmentController projectAssignmentController = ProjectAssignmentController.getInstance();
        System.out.println("Project Assignments");
        System.out.println(projectAssignmentController.getAllProjectAssignmentsJSON());

        ProjectController projectController = ProjectController.getInstance();
        System.out.println("Projects");
        System.out.println(projectController.getAllProjectsJSON());

        RepositoryController repositoryController = RepositoryController.getInstance();
        System.out.println("Repos");
        System.out.println(repositoryController.getRepositoryByIdJSON(1L));
    }
}
