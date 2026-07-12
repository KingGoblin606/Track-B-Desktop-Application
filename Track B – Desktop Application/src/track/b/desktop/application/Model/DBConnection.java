package track.b.desktop.application.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {//handles db connection

    //connection string
    private static final String JDBC_URL = "jdbc:derby:ResourceManagementDB;create=true";
    private Connection con;
    
    public DBConnection() throws SQLException {//opens connection
        con = DriverManager.getConnection(JDBC_URL);
    }
    
    public Connection getConnection() {//reurns active db connection
        return con;
    }
    
    @Override
    public void close() throws SQLException {//automatically closes connection
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}