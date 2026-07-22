package track.b.desktop.application.Data;

import track.b.desktop.application.Model.DBConnection;
import track.b.desktop.application.Model.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentRepository 
{

    public ArrayList<Department> findAll() 
    {
        ArrayList<Department> departments = new ArrayList<>();

        String sql = "SELECT DepartmentID, DepartmentName FROM Department ORDER BY DepartmentName";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {

            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("DepartmentID"),
                        rs.getString("DepartmentName")
                ));
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to load departments: " + e.getMessage(), e);
        }

        return departments;
    }

    public void insert(Department department) 
    {
        String sql = "INSERT INTO Department (DepartmentID, DepartmentName) VALUES (?, ?)";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) 
        {

            ps.setInt(1, department.getDepartmentId());
            ps.setString(2, department.getDepartmentName());

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to insert department: " + e.getMessage(), e);
        }
    }

    public int findMaxId() 
    {
        String sql = "SELECT MAX(DepartmentID) AS MaxId FROM Department";
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
            throw new RuntimeException("Failed to read max department id: " + e.getMessage(), e);
        }

        return maxId;
    }
    
        public void update(track.b.desktop.application.Model.Department department) 
        {
        String sql = "UPDATE Department SET DepartmentName = ? WHERE DepartmentID = ?";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) 
        {

            ps.setString(1, department.getDepartmentName());
            ps.setInt(2, department.getDepartmentId());

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to update department: " + e.getMessage(), e);
        }
    }
}