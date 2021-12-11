import Manual.controllers.CommitController;
import Manual.repositories.CommitRepo;
import Manual.services.CommitService;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        try {
            CommitController controller = CommitController.getInstance();
            System.out.println(controller.getAllCommitsJSON());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
