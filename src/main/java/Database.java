import Manual.database.DatabaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Database {
    public static void checkService() {
        DatabaseController controller = DatabaseController.getInstance();
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
