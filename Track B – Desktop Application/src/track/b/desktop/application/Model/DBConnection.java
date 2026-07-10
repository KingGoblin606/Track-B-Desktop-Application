package track.b.desktop.application.Model;
import java.sql.*;

public class DBConnection implement AutoCloseable{
private static final String JDBC_URL = "jdbc:derby:resouceDB; create=true";
private Connection con;
}