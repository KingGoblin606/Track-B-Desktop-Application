package track.b.desktop.application.Data;

import track.b.desktop.application.Model.DBConnection;
import track.b.desktop.application.Model.Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialRepository 
{
    public ArrayList<Material> findAll() {
        ArrayList<Material> materials = new ArrayList<>();
        
        String sql = """
                    SELECT m.MaterialsID, m.MaterialName, m.Category,
                           m.Quantity, m.ReorderLevel, m.Cost,
                           m.SupplierID, s.SupplierName
                    FROM Materials m
                    JOIN Supplier s ON m.SupplierID = s.SupplierID
                    ORDER BY m.MaterialsID
                     """;
        
    try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("MaterialsID"),
                        rs.getString("MaterialName"),
                        rs.getString("Category"),
                        rs.getInt("Quantity"),
                        rs.getInt("ReorderLevel"),
                        rs.getInt("Cost"),
                        rs.getInt("SupplierID")
                );
                material.setSupplierName(rs.getString("SupplierName"));
                materials.add(material);
            }

    } 
    catch (SQLException e) 
    {
        throw new RuntimeException("Failed to load materials: " + e.getMessage(), e);
    }
    
    return materials;
    }


public void insert(Material material)
{
    String sql = 
    """
    INSERT INTO Materials
                         (MaterialsID, SupplierID, MaterialName, Category, Quantity, ReorderLevel, Cost)
                         VALUES (?, ?, ?, ?, ?, ?, ?)
    """;
    
    try(DBConnection db = new DBConnection();
        PreparedStatement ps = db.getConnection().prepareStatement(sql))
    {
        ps.setInt(1, material.getMaterialId());
        ps.setInt(2, material.getSupplierId());
        ps.setString(3, material.getName());
        ps.setString(4, material.getCategory());
        ps.setInt(5, material.getQuantity());
        ps.setInt(6, material.getReorderLevel());
        ps.setInt(7, material.getCost());
        
        ps.executeUpdate();
    }
    catch (SQLException e) 
    {
        throw new RuntimeException("Failed to insert materials: " + e.getMessage(), e);
    }
}

public void update(Material material)
{
    String sql = 
            """
            UPDATE Materials
            SET SupplierID = ?, MaterialName = ?, Category = ?,
                Quantity = ?, ReorderLevel = ?, Cost = ?
            WHERE MaterialsID = ?
            """;
    
    try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setInt(1, material.getSupplierId());
            ps.setString(2, material.getName());
            ps.setString(3, material.getCategory());
            ps.setInt(4, material.getQuantity());
            ps.setInt(5, material.getReorderLevel());
            ps.setInt(6, material.getCost());
            ps.setInt(7, material.getMaterialId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update material: " + e.getMessage(), e);
        }
}

public void delete(int materialId) {
        String sql = "DELETE FROM Materials WHERE MaterialsID = ?";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setInt(1, materialId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete material: " + e.getMessage(), e);
        }
    }

public int findMaxId() {
    String sql = "SELECT MAX(MaterialsID) AS MaxId FROM Materials";
    int maxId = 0;

    try (DBConnection db = new DBConnection();
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) 
    {

        if (rs.next()) 
        {
          maxId = rs.getInt("MaxId");
        }
    } 
    
    catch (SQLException e) 
    {
        throw new RuntimeException("Failed to read max material id: " + e.getMessage(), e);
    }

        return maxId;
    }
}   