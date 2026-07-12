
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
    
    private static void createUserTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE User (
                           UserID INT PRIMARY KEY,
                           UserName VARCHAR(50) NOT NULL,
                           UserPassword VARCHAR(255) NOT NULL,
                           UserEmail VARCHAR(100) NOT NULL UNIQUE,
                           UserRole VARCHAR(10) NOT NULL
                                CHECK (UserRole IN ('User', 'Admin'))
                           )
                           """);
    }
    
    
}
