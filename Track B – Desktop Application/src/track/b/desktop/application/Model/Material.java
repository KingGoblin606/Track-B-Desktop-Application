package track.b.desktop.application.Model;

public class Material {

    // Fields that store the details of one material
    private int materialId;
    private String name;
    private String category;
    private int quantity;
    private int reorderLevel;
    private int cost;
    private int supplierId;
    private String supplierName;

    // Empty constructor
    public Material() {
    }

    // Constructor used when creating a complete Material object
    public Material(
            int materialId,
            String name,
            String category,
            int quantity,
            int reorderLevel,
            int cost,
            int supplierId) {

        this.materialId = materialId;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.cost = cost;
        this.supplierId = supplierId;
    }

    // Getters

    public int getMaterialId() {
        return materialId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }
    
    public int getCost() {
        return cost;
    }

    public int getSupplierId() {
        return supplierId;
    }
    
    public String getSupplierName() {
        return supplierName;
    }

    // Setters

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    // Returns true when the quantity has reached or fallen below
    // the level at which more stock should be ordered
    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    // Returns true when no stock remains
    public boolean isOutOfStock() {
        return quantity == 0;
    }

    // Returns a readable stock status
    public String getStockStatus() {
        if (isOutOfStock()) {
            return "Out of Stock";
        }

        if (isLowStock()) {
            return "Low Stock";
        }

        return "In Stock";
    }

    // Adds stock to the current quantity
    public void increaseStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "The stock amount cannot be negative."
            );
        }

        quantity += amount;
    }

    // Removes stock from the current quantity
    public void decreaseStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "The stock amount cannot be negative."
            );
        }

        if (amount > quantity) {
            throw new IllegalArgumentException(
                    "There is not enough stock available."
            );
        }

        quantity -= amount;
    }

    @Override
    public String toString() {
        return materialId + " - " + name;
    }
}