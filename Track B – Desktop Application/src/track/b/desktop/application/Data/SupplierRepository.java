package track.b.desktop.application.Data;

import track.b.desktop.application.Model.DBConnection;
import track.b.desktop.application.Model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierRepository 
{

    public ArrayList<Supplier> findAll() 
    {
        ArrayList<Supplier> suppliers = new ArrayList<>();

        String sql = """
                     SELECT SupplierID, SupplierName, ContactPerson,
                            SupplierPhoneNumber, SupplierEmail,
                            StreetAddress, City, PostalCode
                     FROM Supplier
                     ORDER BY SupplierID
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("SupplierID"),
                        rs.getString("SupplierName"),
                        rs.getString("ContactPerson"),
                        rs.getString("SupplierPhoneNumber"),
                        rs.getString("SupplierEmail"),
                        rs.getString("StreetAddress"),
                        rs.getString("City"),
                        rs.getString("PostalCode")
                );
                suppliers.add(supplier);
            }

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to load suppliers: " + e.getMessage(), e);
        }

        return suppliers;
    }

    public void insert(Supplier supplier) 
    {
        String sql = """
                     INSERT INTO Supplier
                     (SupplierID, SupplierName, ContactPerson, SupplierPhoneNumber,
                      SupplierEmail, StreetAddress, City, PostalCode)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) 
        {

            ps.setInt(1, supplier.getSupplierId());
            ps.setString(2, supplier.getSupplierName());
            ps.setString(3, supplier.getContactPerson());
            ps.setString(4, supplier.getPhoneNumber());
            ps.setString(5, supplier.getEmail());
            ps.setString(6, supplier.getStreetAddress());
            ps.setString(7, supplier.getCity());
            ps.setString(8, supplier.getPostalCode());

            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to insert supplier: " + e.getMessage(), e);
        }
    }

    public void update(Supplier supplier) 
    {
        String sql = """
                     UPDATE Supplier
                     SET SupplierName = ?, ContactPerson = ?, SupplierPhoneNumber = ?,
                         SupplierEmail = ?, StreetAddress = ?, City = ?, PostalCode = ?
                     WHERE SupplierID = ?
                     """;

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getPhoneNumber());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getStreetAddress());
            ps.setString(6, supplier.getCity());
            ps.setString(7, supplier.getPostalCode());
            ps.setInt(8, supplier.getSupplierId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update supplier: " + e.getMessage(), e);
        }
    }

    public void delete(int supplierId) 
    {
        String sql = "DELETE FROM Supplier WHERE SupplierID = ?";

        try (DBConnection db = new DBConnection();
             PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setInt(1, supplierId);
            ps.executeUpdate();

        } catch (SQLException e) 
        {
            throw new RuntimeException("Failed to delete supplier: " + e.getMessage(), e);
        }
    }

    public int findMaxId() 
    {
        String sql = "SELECT MAX(SupplierID) AS MaxId FROM Supplier";
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
            throw new RuntimeException("Failed to read max supplier id: " + e.getMessage(), e);
        }

        return maxId;
    }
}