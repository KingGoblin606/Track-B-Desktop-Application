
package track.b.desktop.application.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    
    public static void initialize () {
        try (DBConnection db = new DBConnection()) {
            Connection conn = db.getConnection();
            
            
        } catch (SQLException e) {
            if(!"X0Y32".equals(e.getSQLState())) {
                e.printStackTrace();
            }
        }
    }
}
