package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.MaterialRepository;
import track.b.desktop.application.Model.Material;

import java.util.ArrayList;

public class MaterialController {

    private final MaterialRepository repository;

    public MaterialController() {
        repository = new MaterialRepository();
    }

    // Returns all materials
    public ArrayList<Material> getAllMaterials() {
        return repository.findAll();
    }

    // Adds a new material
    public Material addMaterial(
            String name,
            String category,
            int quantity,
            int reorderLevel,
            int cost,
            int supplierId) {

        validateMaterialDetails(
                name,
                category,
                quantity,
                reorderLevel,
                cost
        );

        int newId = repository.findMaxId() + 1;

        Material material = new Material(
                newId,
                name.trim(),
                category.trim(),
                quantity,
                reorderLevel,
                cost,
                supplierId
        );

        repository.insert(material);

        return material;
    }

    // Updates an existing material
    public boolean updateMaterial(
            int materialId,
            String name,
            String category,
            int quantity,
            int reorderLevel,
            int cost,
            int supplierId) {

        validateMaterialDetails(
                name,
                category,
                quantity,
                reorderLevel,
                cost
        );

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        material.setName(name.trim());
        material.setCategory(category.trim());
        material.setQuantity(quantity);
        material.setReorderLevel(reorderLevel);
        material.setCost(cost);
        material.setSupplierId(supplierId);

        repository.update(material);

        return true;
    }

    // Deletes a material using its ID
    public boolean deleteMaterial(int materialId) {

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        repository.delete(materialId);

        return true;
    }

    // Finds one material by its ID
    public Material findMaterialById(int materialId) {

        for (Material material : getAllMaterials()) {

            if (material.getMaterialId() == materialId) {
                return material;
            }
        }

        return null;
    }

    // Searches by ID, name, category or supplier name
    public ArrayList<Material> searchMaterials(String searchText) {

        ArrayList<Material> searchResults = new ArrayList<>();

        if (searchText == null || searchText.trim().isEmpty()) {
            return getAllMaterials();
        }

        String searchValue = searchText.trim().toLowerCase();

        for (Material material : getAllMaterials()) {

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
                    material.getSupplierName() != null
                            && material.getSupplierName()
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

        for (Material material : getAllMaterials()) {

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
        repository.update(material);

        return true;
    }

    // Removes stock from a material
    public boolean decreaseStock(int materialId, int amount) {

        Material material = findMaterialById(materialId);

        if (material == null) {
            return false;
        }

        material.decreaseStock(amount);
        repository.update(material);

        return true;
    }

    // Validates all input before creating or updating a material
    private void validateMaterialDetails(
            String name,
            String category,
            int quantity,
            int reorderLevel,
            int cost) {

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

        if (cost < 0) {
            throw new IllegalArgumentException(
                    "Cost cannot be negative."
            );
        }
    }
}