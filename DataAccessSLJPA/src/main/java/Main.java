import Manual.controllers.ProgrammerController;
import Manual.daos.BossHistory;
import Manual.repositories.BossHistoryRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.services.ProgrammerService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        BossHistoryRepo bossHistoryRepo = new BossHistoryRepo();
        ProgrammerRepo progRepo = new ProgrammerRepo();
        System.out.println(ProgrammerController.getInstance().getAllProgrammersJSON());
        try {
            System.out.println(ProgrammerController.getInstance().deleteProgrammerJSON(new ProgrammerService(new ProgrammerRepo()).getProgrammerById(14L)));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
