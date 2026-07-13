/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package track.b.desktop.application.Test;



import java.util.ArrayList;
import track.b.desktop.application.Controller.MaterialController;
import track.b.desktop.application.Model.Material;

public class MaterialModuleTest {

    public static void main(String[] args) {

        try {
            MaterialController controller = new MaterialController();

            System.out.println("=== MATERIAL MODULE TEST ===");

            // Add materials
            Material material1 = controller.addMaterial(
                    "Bleach",
                    "Chemicals",
                    20,
                    5,
                    "ABC Cleaning Supplies"
            );

            Material material2 = controller.addMaterial(
                    "Mop",
                    "Equipment",
                    3,
                    5,
                    "Campus Supplies"
            );

            Material material3 = controller.addMaterial(
                    "Bin Bags",
                    "Consumables",
                    0,
                    10,
                    "Packaging Warehouse"
            );

            System.out.println("\nMaterials added successfully.");

            // Display all materials
            System.out.println("\n=== ALL MATERIALS ===");

            ArrayList<Material> allMaterials =
                    controller.getAllMaterials();

            displayMaterials(allMaterials);

            // Test searching
            System.out.println("\n=== SEARCH: BLEACH ===");

            ArrayList<Material> searchResults =
                    controller.searchMaterials("Bleach");

            displayMaterials(searchResults);

            // Test low-stock filter
            System.out.println("\n=== LOW-STOCK MATERIALS ===");

            ArrayList<Material> lowStockMaterials =
                    controller.filterMaterials("Low Stock");

            displayMaterials(lowStockMaterials);

            // Test out-of-stock filter
            System.out.println("\n=== OUT-OF-STOCK MATERIALS ===");

            ArrayList<Material> outOfStockMaterials =
                    controller.filterMaterials("Out of Stock");

            displayMaterials(outOfStockMaterials);

            // Test update
            boolean updated = controller.updateMaterial(
                    material1.getMaterialId(),
                    "Bleach",
                    "Chemicals",
                    30,
                    8,
                    "ABC Cleaning Supplies"
            );

            System.out.println(
                    "\nMaterial updated: " + updated
            );

            Material updatedMaterial =
                    controller.findMaterialById(
                            material1.getMaterialId()
                    );

            System.out.println(
                    "Updated quantity: "
                    + updatedMaterial.getQuantity()
            );

            // Test stock increase
            controller.increaseStock(
                    material2.getMaterialId(),
                    10
            );

            System.out.println(
                    "\nMop quantity after stock increase: "
                    + controller.findMaterialById(
                            material2.getMaterialId()
                    ).getQuantity()
            );

            // Test stock decrease
            controller.decreaseStock(
                    material2.getMaterialId(),
                    2
            );

            System.out.println(
                    "Mop quantity after stock decrease: "
                    + controller.findMaterialById(
                            material2.getMaterialId()
                    ).getQuantity()
            );

            // Test deletion
            boolean deleted = controller.deleteMaterial(
                    material3.getMaterialId()
            );

            System.out.println(
                    "\nMaterial deleted: " + deleted
            );

            // Show final list
            System.out.println("\n=== FINAL MATERIAL LIST ===");

            displayMaterials(
                    controller.getAllMaterials()
            );

            System.out.println(
                    "\nAll tests completed successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Test failed: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    private static void displayMaterials(
            ArrayList<Material> materials) {

        if (materials.isEmpty()) {
            System.out.println("No materials found.");
            return;
        }

        for (Material material : materials) {

            System.out.println(
                    "ID: " + material.getMaterialId()
                    + " | Name: " + material.getName()
                    + " | Category: " + material.getCategory()
                    + " | Quantity: " + material.getQuantity()
                    + " | Reorder Level: "
                    + material.getReorderLevel()
                    + " | Supplier: "
                    + material.getSupplier()
                    + " | Status: "
                    + material.getStockStatus()
            );
        }
    }
}