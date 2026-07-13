package track.b.desktop.application.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    
    public static void initialize () {
        try (DBConnection db = new DBConnection()) {
            Connection conn = db.getConnection();
            
            createUserTable(conn);
            createSuppliersTable(conn);
            createMaterialTable(conn);
            createDepartmentTable(conn);
            createCleanerTable(conn);
            createCleanerDepartmentTable(conn);
            createStockIssuance(conn);
            
            
        } catch (SQLException e) {
            if(!"X0Y32".equals(e.getSQLState())) {
                e.printStackTrace();
            }
        }
    }
    
    private static void createUserTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE Users (
                           UserID INT PRIMARY KEY,
                           UserName VARCHAR(50) NOT NULL,
                           UserPassword VARCHAR(255) NOT NULL,
                           UserEmail VARCHAR(100) NOT NULL UNIQUE,
                           UserRole VARCHAR(10) NOT NULL
                           CHECK (UserRole IN ('User', 'Admin'))
                           )
                           """);
    }
    
    private static void createSuppliersTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE Supplier (
                           SupplierID int PRIMARY KEY,
                           SupplierName VARCHAR(50) NOT NULL,
                           ContactPerson VARCHAR(50) NOT NULL,
                           SupplierPhoneNumber VARCHAR(20) NOT NULL,
                           SupplierEmail VARCHAR(100) NOT NULL UNIQUE,
                           StreetAddress VARCHAR(100) NOT NULL,
                           City VARCHAR(50) NOT NULL,
                           PostalCode VARCHAR(10) NOT NULL
                           )
                           """);
    }
    
    private static void createMaterialTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE Materials (
                           MaterialsID INT PRIMARY KEY,
                           SupplierID INT NOT NULL,
                           MaterialName VARCHAR(50) NOT NULL,
                           Quantity INT NOT NULL,
                           ReorderLevel INT NOT NULL,
                           Cost INT NOT NULL,
                           Category VARCHAR(50) NOT NULL,
                           FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
                           )
                           """);
    }
    
    private static void createDepartmentTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE Department (
                           DepartmentID INT PRIMARY KEY,
                           DepartmentName VARCHAR(50)
                           )
                           """);
    }
    
    private static void createCleanerTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE Cleaner (
                           CleanerID INT PRIMARY KEY,
                           CleanerName VARCHAR(50) NOT NULL,
                           CleanerSurname VARCHAR(50) NOT NULL,
                           CleanerPhoneNumber VARCHAR(20) NOT NULL,
                           CleanerEmail VARCHAR(100) NOT NULL UNIQUE
                           )
                           """);
    }
    
    private static void createCleanerDepartmentTable(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE CleanerDepartment (
                           DepartmentID INT NOT NULL,
                           CleanerID INT NOT NULL,
                           
                           PRIMARY KEY (DepartmentID, CleanerID),
                           
                           FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID),
                           FOREIGN KEY (CleanerID) REFERENCES Cleaner(CleanerID)
                           )
                           """);
    }
    
    private static void createStockIssuance(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE StockIssuance (
                           IssueID INT PRIMARY KEY,
                           MaterialsID INT NOT NULL,
                           CleanerID INT NOT NULL,
                           UserID INT NOT NULL,
                           Quantity INT NOT NULL,
                           DateIssued DATE,                           
                           FOREIGN KEY (MaterialsID) REFERENCES Materials(MaterialsID),
                           FOREIGN KEY (CleanerID) REFERENCES Cleaner(CleanerID),
                           FOREIGN KEY (UserID) REFERENCES Users(UserID)
                           )
                           """);
    }
}