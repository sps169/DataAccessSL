import Manual.repositories.ProgrammerRepo;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        ProgrammerRepo progRepo = new ProgrammerRepo();
        try {
            System.out.println(progRepo.findAll());
            System.out.println(progRepo.getById(1L));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
