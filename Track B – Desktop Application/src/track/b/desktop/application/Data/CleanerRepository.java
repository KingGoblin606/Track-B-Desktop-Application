package track.b.desktop.application.Data;

import track.b.desktop.application.Model.Cleaner;
import track.b.desktop.application.Model.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class CleanerRepository 
{

    public ArrayList<Cleaner> findAll() 
    {
        ArrayList<Cleaner> cleaners = new ArrayList<>();

        String sql = """
                     SELECT c.CleanerID, c.CleanerName, c.CleanerSurname,
                            c.CleanerPhoneNumber, c.CleanerEmail,
                            c.DepartmentID, d.DepartmentName
                     FROM Cleaner c
                     LEFT JOIN Department d ON c.DepartmentID = d.DepartmentID
                     ORDER BY c.CleanerID
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {

            while (rs.next()) 
            {

                int rawDepartmentId = rs.getInt("DepartmentID");
                Integer departmentId = rs.wasNull() ? null : rawDepartmentId;

                Cleaner cleaner = new Cleaner(
                        rs.getInt("CleanerID"),
                        rs.getString("CleanerName"),
                        rs.getString("CleanerSurname"),
                        rs.getString("CleanerPhoneNumber"),
                        rs.getString("CleanerEmail"),
                        departmentId
                );
                cleaner.setDepartmentName(rs.getString("DepartmentName"));
                cleaners.add(cleaner);
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to load cleaners: " + e.getMessage(), e);
        }

        return cleaners;
    }

    public void insert(Cleaner cleaner) {
        String sql = """
                     INSERT INTO Cleaner
                     (CleanerID, CleanerName, CleanerSurname, CleanerPhoneNumber, CleanerEmail, DepartmentID)
                     VALUES (?, ?, ?, ?, ?, ?)
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) 
        {

            ps.setInt(1, cleaner.getCleanerId());
            ps.setString(2, cleaner.getName());
            ps.setString(3, cleaner.getSurname());
            ps.setString(4, cleaner.getPhoneNumber());
            ps.setString(5, cleaner.getEmail());

            if (cleaner.getDepartmentId() == null) 
            {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, cleaner.getDepartmentId());
            }

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to insert cleaner: " + e.getMessage(), e);
        }
    }

    public void update(Cleaner cleaner) 
    {
        String sql = """
                     UPDATE Cleaner
                     SET CleanerName = ?, CleanerSurname = ?, CleanerPhoneNumber = ?,
                         CleanerEmail = ?, DepartmentID = ?
                     WHERE CleanerID = ?
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setString(1, cleaner.getName());
            ps.setString(2, cleaner.getSurname());
            ps.setString(3, cleaner.getPhoneNumber());
            ps.setString(4, cleaner.getEmail());

            if (cleaner.getDepartmentId() == null) 
            {
                ps.setNull(5, Types.INTEGER);
            } else 
            {
                ps.setInt(5, cleaner.getDepartmentId());
            }

            ps.setInt(6, cleaner.getCleanerId());

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to update cleaner: " + e.getMessage(), e);
        }
    }

    public void delete(int cleanerId) 
    {
        String sql = "DELETE FROM Cleaner WHERE CleanerID = ?";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) 
        {
            ps.setInt(1, cleanerId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete cleaner: " + e.getMessage(), e);
        }
    }

    public int findMaxId() {
        String sql = "SELECT MAX(CleanerID) AS MaxId FROM Cleaner";
        int maxId = 0;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {

            if (rs.next()) 
            {
                maxId = rs.getInt("MaxId");
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to read max cleaner id: " + e.getMessage(), e);
        }

        return maxId;
    }
}