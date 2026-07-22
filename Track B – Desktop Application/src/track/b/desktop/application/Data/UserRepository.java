package track.b.desktop.application.Data;

import track.b.desktop.application.Model.DBConnection;
import track.b.desktop.application.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository 
{

    public ArrayList<User> findAll() 
    {
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT UserID, UserName, UserPassword, UserEmail, UserRole FROM Users ORDER BY UserID";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {

            while (rs.next()) 
            {
                users.add(mapRow(rs));
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to load users: " + e.getMessage(), e);
        }

        return users;
    }

    public User findByEmail(String email) 
    {
        String sql = "SELECT UserID, UserName, UserPassword, UserEmail, UserRole FROM Users WHERE UserEmail = ?";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to look up user by email: " + e.getMessage(), e);
        }

        return null;
    }

    public void insert(User user) 
    {
        String sql = "INSERT INTO Users (UserID, UserName, UserPassword, UserEmail, UserRole) VALUES (?, ?, ?, ?, ?)";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to insert user: " + e.getMessage(), e);
        }
    }

    public int findMaxId() 
    {
        String sql = "SELECT MAX(UserID) AS MaxId FROM Users";
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
            throw new RuntimeException("Failed to read max user id: " + e.getMessage(), e);
        }

        return maxId;
    }

    private User mapRow(ResultSet rs) throws SQLException 
    {
        return new User(
                rs.getInt("UserID"),
                rs.getString("UserName"),
                rs.getString("UserPassword"),
                rs.getString("UserEmail"),
                rs.getString("UserRole")
        );
    }
}