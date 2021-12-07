import Manual.controllers.ProgrammerController;
import Manual.daos.BossHistory;
import Manual.repositories.BossHistoryRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.services.ProgrammerService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Database.checkService();
        try {

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
