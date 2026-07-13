/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.MaterialFileRepository;
import track.b.desktop.application.Model.Material;

import java.util.ArrayList;

public class MaterialController {

    private final ArrayList<Material> materials;
    private final MaterialFileRepository repository;

    public MaterialController() {
        repository = new MaterialFileRepository();
        materials = repository.loadMaterials();
    }

    // Returns all materials
    public ArrayList<Material> getAllMaterials() {
        return new ArrayList<>(materials);
    }

    // Adds a new material
    public Material addMaterial(
            String name,
            String category,
            int quantity,
            int reorderLevel,
            String supplier) {

        validateMaterialDetails(
                name,
                category,
                quantity,
                reorderLevel,
                supplier
        );

        int newId = generateNextId();

        Material material = new Material(
                newId,
                name.trim(),
                category.trim(),
                quantity,
                reorderLevel,
                supplier.trim()
        );

        materials.add(material);
        saveMaterials();

        return material;
    }

    // Updates an existing material
    public boolean updateMaterial(
            int materialId,
            String name,
            String category,
            int quantity,
            int reorderLevel,
            String supplier) {

        validateMaterialDetails(
                name,
                category,
                quantity,
                reorderLevel,
                supplier
        );

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        material.setName(name.trim());
        material.setCategory(category.trim());
        material.setQuantity(quantity);
        material.setReorderLevel(reorderLevel);
        material.setSupplier(supplier.trim());

        saveMaterials();

        return true;
    }

    // Deletes a material using its ID
    public boolean deleteMaterial(int materialId) {

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        materials.remove(material);
        saveMaterials();

        return true;
    }

    // Finds one material by its ID
    public Material findMaterialById(int materialId) {

        for (Material material : materials) {

            if (material.getMaterialId() == materialId) {
                return material;
            }
        }

        return null;
    }

    // Searches by ID, name, category or supplier
    public ArrayList<Material> searchMaterials(String searchText) {

        ArrayList<Material> searchResults = new ArrayList<>();

        if (searchText == null || searchText.trim().isEmpty()) {
            return getAllMaterials();
        }

        String searchValue = searchText.trim().toLowerCase();

        for (Material material : materials) {

            boolean idMatches =
                    String.valueOf(material.getMaterialId())
                            .contains(searchValue);

            boolean nameMatches =
                    material.getName()
                            .toLowerCase()
                            .contains(searchValue);

            boolean categoryMatches =
                    material.getCategory()
                            .toLowerCase()
                            .contains(searchValue);

            boolean supplierMatches =
                    material.getSupplier()
                            .toLowerCase()
                            .contains(searchValue);

            if (idMatches
                    || nameMatches
                    || categoryMatches
                    || supplierMatches) {

                searchResults.add(material);
            }
        }

        return searchResults;
    }

    // Filters materials according to the selected filter
    public ArrayList<Material> filterMaterials(String filter) {

        ArrayList<Material> filteredMaterials = new ArrayList<>();

        if (filter == null
                || filter.trim().isEmpty()
                || filter.equalsIgnoreCase("All")) {

            return getAllMaterials();
        }

        for (Material material : materials) {

            if (filter.equalsIgnoreCase("Low Stock")
                    && material.isLowStock()
                    && !material.isOutOfStock()) {

                filteredMaterials.add(material);

            } else if (filter.equalsIgnoreCase("Out of Stock")
                    && material.isOutOfStock()) {

                filteredMaterials.add(material);

            } else if (material.getCategory()
                    .equalsIgnoreCase(filter)) {

                filteredMaterials.add(material);
            }
        }

        return filteredMaterials;
    }

    // Adds stock to a material
    public boolean increaseStock(int materialId, int amount) {

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        material.increaseStock(amount);
        saveMaterials();

        return true;
    }

    // Removes stock from a material
    public boolean decreaseStock(int materialId, int amount) {

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        material.decreaseStock(amount);
        saveMaterials();

        return true;
    }

    // Generates the next available material ID
    private int generateNextId() {

        int highestId = 0;

        for (Material material : materials) {

            if (material.getMaterialId() > highestId) {
                highestId = material.getMaterialId();
            }
        }

        return highestId + 1;
    }

    // Saves the current list using the repository
    private void saveMaterials() {
        repository.saveMaterials(materials);
    }

    // Validates all input before creating or updating a material
    private void validateMaterialDetails(
            String name,
            String category,
            int quantity,
            int reorderLevel,
            String supplier) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Material name cannot be empty."
            );
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Category cannot be empty."
            );
        }

        if (quantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative."
            );
        }

        if (reorderLevel < 0) {
            throw new IllegalArgumentException(
                    "Reorder level cannot be negative."
            );
        }

        if (supplier == null || supplier.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Supplier cannot be empty."
            );
        }
    }
}
