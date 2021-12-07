import Manual.repositories.CommitRepo;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        try {
            CommitRepo commitRepo = new CommitRepo();
            System.out.println(commitRepo.finddAll());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
