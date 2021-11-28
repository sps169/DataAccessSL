import Manual.daos.BossHistory;
import Manual.repositories.BossHistoryRepo;
import Manual.repositories.ProgrammerRepo;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        BossHistoryRepo bossHistoryRepo = new BossHistoryRepo();
        ProgrammerRepo progRepo = new ProgrammerRepo();
        try {
            System.out.println(progRepo.findAll());
            System.out.println(progRepo.getById(1L));
            System.out.println(bossHistoryRepo.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
