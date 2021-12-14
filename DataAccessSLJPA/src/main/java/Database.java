import jpa.database.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
/**
 * Class to check the connection to the database
 * @author joseluisgs
 */
public class Database {
    /**
     * Method that check the connection with the database.
     */
    public static void checkService() {
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT * FROM commit WHERE id = 1");
            if (rs.isPresent()) {
                System.out.println(rs.get().next());
                controller.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }
}
