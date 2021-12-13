import jpa.controllers.*;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        CommitController commitController = CommitController.getInstance();
        System.out.println(commitController.getAllCommitsJSON());

        BossHistoryController bossHistoryController = BossHistoryController.getInstance();
        System.out.println(bossHistoryController.getAllBossHistoriesJSON());

        DepartmentController departmentController = DepartmentController.getInstance();
        System.out.println(departmentController.getAllDepartmentsJSON());

        IssueAssignmentController issueAssignmentController = IssueAssignmentController.getInstance();
        System.out.println(issueAssignmentController.getAllIssueAssignmentsJSON());
    }
}
