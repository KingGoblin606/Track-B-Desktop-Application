package track.b.desktop.application.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {
    private static final String JDBC_URL = "jdbc:derby:ResourceManagementDB;create=true";
    private Connection con;
    
    public DBConnection() throws SQLException {
        con = DriverManager.getConnection(JDBC_URL);
    }
    
    public Connection getConnection() {
        return con;
    }
    
    @Override
    public void close() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}