package track.b.desktop.application.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bulk dummy data seeder. Run this once (right-click -> Run File in
 * NetBeans), check your app, then delete this class. Reuses your existing
 * DBConnection, so it writes straight into the same database your app
 * already reads from.
 *
 * Generates: 15 suppliers, 10 departments, ~28 materials, 50 cleaners,
 * 200 stock issuance records.
 */
public class Dymmydataseeder {

    // Set this to a UserID that already exists in your Users table.
    private static final int EXISTING_USER_ID = 1;

    private static final Random RNG = new Random(42); // fixed seed = repeatable data

    public static void main(String[] args) {
        try (DBConnection db = new DBConnection()) {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();

            List<Integer> supplierIds = seedSuppliers(stmt);
            List<Integer> departmentIds = seedDepartments(stmt);
            List<Integer> materialIds = seedMaterials(stmt, supplierIds);
            List<Integer> cleanerIds = seedCleaners(stmt, departmentIds);
            seedStockIssuance(stmt, materialIds, cleanerIds);

            System.out.println("Bulk seeding complete.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------- Suppliers ----------
    private static List<Integer> seedSuppliers(Statement stmt) {
        String[][] suppliers = {
            {"Idwala Supplies", "P. Naidoo", "orders@idwala.co.za", "Cape Town"},
            {"SafetyFirst SA", "K. Botha", "sales@safetyfirst.co.za", "Johannesburg"},
            {"CleanCo Distributors", "M. Naidoo", "info@cleanco.co.za", "Durban"},
            {"HygienePro", "L. Adams", "sales@hygienepro.co.za", "Pretoria"},
            {"Sparkle Supplies", "R. Govender", "orders@sparkle.co.za", "Port Elizabeth"},
            {"Janitorial Direct", "T. Nkosi", "info@janitorialdirect.co.za", "Bloemfontein"},
            {"PureClean Wholesale", "S. Petersen", "sales@purecleanwholesale.co.za", "Cape Town"},
            {"MaxHygiene", "J. Mokoena", "orders@maxhygiene.co.za", "Johannesburg"},
            {"EcoWash Supplies", "A. van der Merwe", "info@ecowash.co.za", "Stellenbosch"},
            {"CampusCare Products", "N. Mahlangu", "sales@campuscare.co.za", "Durban"},
            {"TotalClean SA", "D. Isaacs", "orders@totalclean.co.za", "Cape Town"},
            {"BrightSpace Supplies", "F. Khumalo", "info@brightspace.co.za", "Pretoria"},
            {"ShineWorks", "C. Abrahams", "sales@shineworks.co.za", "Johannesburg"},
            {"ProSanitize", "W. Bothma", "orders@prosanitize.co.za", "Centurion"},
            {"CleanEdge Wholesalers", "H. Fortuin", "info@cleanedge.co.za", "Cape Town"}
        };

        List<Integer> ids = new ArrayList<>();
        int id = 9001;
        for (String[] s : suppliers) {
            String phone = "08" + (10000000 + RNG.nextInt(89999999));
            String postal = String.valueOf(1000 + RNG.nextInt(8999));
            run(stmt, String.format(
                "INSERT INTO Supplier VALUES (%d, '%s', '%s', '%s', '%s', '%d %s Street', '%s', '%s')",
                id, esc(s[0]), esc(s[1]), phone, s[2], 1 + RNG.nextInt(200), streetName(), s[3], postal
            ));
            ids.add(id);
            id++;
        }
        return ids;
    }

    // ---------- Departments ----------
    private static List<Integer> seedDepartments(Statement stmt) {
        String[] names = {
            "Residence Block A", "Residence Block B", "Residence Block C",
            "Library", "Admin Block", "Sports Complex", "Science Building",
            "Student Centre", "Cafeteria", "IT Building"
        };
        List<Integer> ids = new ArrayList<>();
        int id = 9201;
        for (String name : names) {
            run(stmt, String.format("INSERT INTO Department VALUES (%d, '%s')", id, esc(name)));
            ids.add(id);
            id++;
        }
        return ids;
    }

    // ---------- Materials ----------
    private static List<Integer> seedMaterials(Statement stmt, List<Integer> supplierIds) {
        String[][] materials = {
            {"Floor Detergent 5L", "Chemicals"},
            {"Glass Cleaner 1L", "Chemicals"},
            {"Bleach 5L", "Chemicals"},
            {"Disinfectant Spray", "Chemicals"},
            {"Multi-Surface Cleaner 1L", "Chemicals"},
            {"Toilet Bowl Cleaner", "Chemicals"},
            {"Degreaser 5L", "Chemicals"},
            {"Carpet Shampoo 5L", "Chemicals"},
            {"Latex Gloves (Box of 100)", "PPE"},
            {"Nitrile Gloves (Box of 100)", "PPE"},
            {"Dust Masks (Box of 50)", "PPE"},
            {"Safety Goggles", "PPE"},
            {"Aprons", "PPE"},
            {"Mop Head - Cotton", "Equipment"},
            {"Mop Head - Microfiber", "Equipment"},
            {"Broom - Industrial", "Equipment"},
            {"Bucket 20L", "Equipment"},
            {"Squeegee", "Equipment"},
            {"Scrub Brush", "Equipment"},
            {"Vacuum Cleaner Bags", "Equipment"},
            {"Bin Liners (Roll of 50)", "Consumables"},
            {"Paper Towels (Pack of 12)", "Consumables"},
            {"Toilet Paper (Pack of 24)", "Consumables"},
            {"Hand Sanitizer 500ml", "Consumables"},
            {"Hand Soap Refill 5L", "Consumables"},
            {"Air Freshener Spray", "Consumables"},
            {"Microfiber Cloths (Pack of 10)", "Consumables"},
            {"Sponges (Pack of 10)", "Consumables"}
        };

        List<Integer> ids = new ArrayList<>();
        int id = 9101;
        for (String[] m : materials) {
            // spread stock levels: ~20% critical, ~25% low, rest healthy
            int reorderLevel = 10 + RNG.nextInt(20);
            int quantity;
            int roll = RNG.nextInt(100);
            if (roll < 20) {
                quantity = Math.max(0, reorderLevel - 1 - RNG.nextInt(reorderLevel)); // critical
            } else if (roll < 45) {
                quantity = reorderLevel + RNG.nextInt(5); // low
            } else {
                quantity = reorderLevel + 15 + RNG.nextInt(80); // healthy
            }
            int cost = 20 + RNG.nextInt(180);
            int supplierId = supplierIds.get(RNG.nextInt(supplierIds.size()));

            run(stmt, String.format(
                "INSERT INTO Materials VALUES (%d, %d, '%s', %d, %d, %d, '%s')",
                id, supplierId, esc(m[0]), quantity, reorderLevel, cost, m[1]
            ));
            ids.add(id);
            id++;
        }
        return ids;
    }

    // ---------- Cleaners ----------
    private static List<Integer> seedCleaners(Statement stmt, List<Integer> departmentIds) {
        String[] firstNames = {
            "Nomvula", "Thabo", "Sipho", "Anna", "Kagiso", "Given", "Lerato", "Bongani",
            "Zanele", "Mpho", "Themba", "Nokuthula", "Karabo", "Sibusiso", "Palesa",
            "Andile", "Refilwe", "Musa", "Thandiwe", "Katlego", "Tebogo", "Nomsa",
            "Sizwe", "Bontle", "Dumisani"
        };
        String[] lastNames = {
            "Dlamini", "van Wyk", "Khumalo", "Botha", "Molefe", "Chirwa", "Zulu",
            "Mahlangu", "Nkosi", "Mokoena", "Naidoo", "Adams", "Govender", "Petersen",
            "Isaacs", "Fortuin", "Abrahams", "Bothma", "van der Merwe", "Mokwena"
        };

        List<Integer> ids = new ArrayList<>();
        int id = 9301;
        for (int i = 0; i < 50; i++) {
            String first = firstNames[RNG.nextInt(firstNames.length)];
            String last = lastNames[RNG.nextInt(lastNames.length)];
            String phone = "08" + (10000000 + RNG.nextInt(89999999));
            String email = (first + "." + last + id).toLowerCase().replace(" ", "") + "@campus.co.za";
            int deptId = departmentIds.get(RNG.nextInt(departmentIds.size()));

            run(stmt, String.format(
                "INSERT INTO Cleaner VALUES (%d, '%s', '%s', '%s', '%s', %d)",
                id, esc(first), esc(last), phone, email, deptId
            ));
            ids.add(id);
            id++;
        }
        return ids;
    }

    // ---------- Stock Issuance ----------
    private static void seedStockIssuance(Statement stmt, List<Integer> materialIds, List<Integer> cleanerIds) {
        LocalDate start = LocalDate.of(2026, 6, 1);
        int id = 9401;
        for (int i = 0; i < 200; i++) {
            int materialId = materialIds.get(RNG.nextInt(materialIds.size()));
            int cleanerId = cleanerIds.get(RNG.nextInt(cleanerIds.size()));
            int quantity = 1 + RNG.nextInt(10);
            LocalDate date = start.plusDays(RNG.nextInt(53)); // spread across ~June-July 2026

            run(stmt, String.format(
                "INSERT INTO StockIssuance VALUES (%d, %d, %d, %d, %d, DATE('%s'))",
                id, materialId, cleanerId, EXISTING_USER_ID, quantity, date
            ));
            id++;
        }
    }

    // ---------- helpers ----------
    private static String streetName() {
        String[] streets = {"Marine", "Industrial", "Warehouse", "Commerce", "Main", "Church", "Voortrekker", "Long"};
        return streets[RNG.nextInt(streets.length)];
    }

    // escapes single quotes so names like "van der Merwe" don't break the SQL string
    private static String esc(String s) {
        return s.replace("'", "''");
    }

    private static void run(Statement stmt, String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Skipped (likely already exists): " + sql);
            System.out.println("  -> " + e.getMessage());
        }
    }
}