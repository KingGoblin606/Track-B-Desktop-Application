package track.b.desktop.application.Data;

import track.b.desktop.application.Model.DBConnection;
import track.b.desktop.application.Model.StockIssuance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockIssuanceRepository 
{

    public ArrayList<StockIssuance> findAll() 
    {
        ArrayList<StockIssuance> issuances = new ArrayList<>();

        String sql = """
                     SELECT si.IssueID, si.MaterialsID, si.CleanerID, si.UserID,
                            si.Quantity, si.DateIssued,
                            m.MaterialName,
                            c.CleanerName, c.CleanerSurname,
                            u.UserName
                     FROM StockIssuance si
                     JOIN Materials m ON si.MaterialsID = m.MaterialsID
                     JOIN Cleaner c ON si.CleanerID = c.CleanerID
                     JOIN Users u ON si.UserID = u.UserID
                     ORDER BY si.DateIssued DESC, si.IssueID DESC
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {

            while (rs.next()) 
            {
                StockIssuance issuance = new StockIssuance(
                        rs.getInt("IssueID"),
                        rs.getInt("MaterialsID"),
                        rs.getInt("CleanerID"),
                        rs.getInt("UserID"),
                        rs.getInt("Quantity"),
                        rs.getDate("DateIssued")
                );
                issuance.setMaterialName(rs.getString("MaterialName"));
                issuance.setCleanerName(rs.getString("CleanerName") + " " + rs.getString("CleanerSurname"));
                issuance.setUserName(rs.getString("UserName"));
                issuances.add(issuance);
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to load stock issuances: " + e.getMessage(), e);
        }

        return issuances;
    }

    public void insert(StockIssuance issuance) 
    {
        String sql = """
                     INSERT INTO StockIssuance
                     (IssueID, MaterialsID, CleanerID, UserID, Quantity, DateIssued)
                     VALUES (?, ?, ?, ?, ?, ?)
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setInt(1, issuance.getIssueId());
            ps.setInt(2, issuance.getMaterialsId());
            ps.setInt(3, issuance.getCleanerId());
            ps.setInt(4, issuance.getUserId());
            ps.setInt(5, issuance.getQuantity());
            ps.setDate(6, issuance.getDateIssued());

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to insert stock issuance: " + e.getMessage(), e);
        }
    }

    public int findMaxId() 
    {
        String sql = "SELECT MAX(IssueID) AS MaxId FROM StockIssuance";
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
            throw new RuntimeException("Failed to read max issue id: " + e.getMessage(), e);
        }

        return maxId;
    }
}
