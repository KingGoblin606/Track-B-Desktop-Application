/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package track.b.desktop.application.View;

import track.b.desktop.application.Controller.MaterialController;
import track.b.desktop.application.Model.Material;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author louis
 */
public class MaterialManagementForm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MaterialManagementForm.class.getName());

    private final MaterialController materialController = new MaterialController();

    // Tracks which material is currently selected in the table.
    // -1 means "no selection" -> btnUpdate will ADD a new material instead of updating one.
    private int selectedMaterialId = -1;

    /**
     * Creates new form MaterialManagementForm
     */
    public MaterialManagementForm() {
        initComponents();

        // Wire up listeners that the Form Editor doesn't attach automatically
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        btnClear.addActionListener(this::btnClearActionPerformed);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        tblMaterial.getSelectionModel().addListSelectionListener(this::tblMaterialRowSelected);

        // Basic menu bar navigation
        Mbar_Dashboard.addActionListener(e -> {
            // Dashboard currently requires a UserController from login session;
            // wiring this properly needs the logged-in UserController passed through.
        });
        Mbar_Cleaners.addActionListener(e -> {
            new CleanerMagementFrom().setVisible(true);
            this.dispose();
        });
        Mbar_Material.addActionListener(e -> { /* already here */ });
        Mbar_Reports.addActionListener(e -> {
            // Reports form not built yet
        });
        Mbar_Logout.addActionListener(e -> {
            new LoginForm().setVisible(true);
            this.dispose();
        });

        loadFilterOptions();
        refreshTable(materialController.getAllMaterials());
    }

    // ------------------------------------------------------------------
    // Data loading / table helpers
    // ------------------------------------------------------------------

    private void refreshTable(ArrayList<Material> materials) {
        DefaultTableModel model = (DefaultTableModel) tblMaterial.getModel();
        model.setRowCount(0);

        for (Material material : materials) {
            model.addRow(new Object[]{
                material.getMaterialId(),
                material.getName(),
                material.getCategory(),
                material.getQuantity(),
                material.getReorderLevel(),
                material.getStockStatus()
            });
        }
    }

    private void loadFilterOptions() {
        LinkedHashSet<String> options = new LinkedHashSet<>();
        options.add("All");
        options.add("Low Stock");
        options.add("Out of Stock");

        for (Material material : materialController.getAllMaterials()) {
            options.add(material.getCategory());
        }

        comboBox_Material.setModel(
                new javax.swing.DefaultComboBoxModel<>(options.toArray(new String[0]))
        );
    }

    private void clearFields() {
        textNames.setText("");
        textCategory.setText("");
        textQuantity.setText("");
        textReorderlvl.setText("");
        textSupplier.setText("");
        selectedMaterialId = -1;
        tblMaterial.clearSelection();
    }

    // ------------------------------------------------------------------
    // Event handlers
    // ------------------------------------------------------------------

    private void tblMaterialRowSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }

        int row = tblMaterial.getSelectedRow();
        if (row == -1) {
            return;
        }

        selectedMaterialId = (int) tblMaterial.getValueAt(row, 0);
        Material material = materialController.findMaterialById(selectedMaterialId);

        if (material == null) {
            return;
        }

        textNames.setText(material.getName());
        textCategory.setText(material.getCategory());
        textQuantity.setText(String.valueOf(material.getQuantity()));
        textReorderlvl.setText(String.valueOf(material.getReorderLevel()));
        textSupplier.setText(String.valueOf(material.getSupplierId()));
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        String name = textNames.getText();
        String category = textCategory.getText();

        int quantity;
        int reorderLevel;
        int supplierId;

        try {
            quantity = Integer.parseInt(textQuantity.getText().trim());
            reorderLevel = Integer.parseInt(textReorderlvl.getText().trim());
            supplierId = Integer.parseInt(textSupplier.getText().trim());
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Quantity, Reorder Level and Supplier must be numbers.",
                    "Invalid Input",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // TODO: cost is not yet a field on this form. Add a Cost label + JTextField
        // in the GUI Builder, then read it here instead of hardcoding 0.
        int cost = 0;

        try {
            if (selectedMaterialId == -1) {
                materialController.addMaterial(name, category, quantity, reorderLevel, cost, supplierId);
                javax.swing.JOptionPane.showMessageDialog(this, "Material added.");
            } else {
                materialController.updateMaterial(selectedMaterialId, name, category, quantity, reorderLevel, cost, supplierId);
                javax.swing.JOptionPane.showMessageDialog(this, "Material updated.");
            }

            clearFields();
            loadFilterOptions();
            refreshTable(materialController.getAllMaterials());

        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Could Not Save Material",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String searchText = javax.swing.JOptionPane.showInputDialog(
                this,
                "Search by ID, name, category or supplier:",
                "Search Materials",
                javax.swing.JOptionPane.PLAIN_MESSAGE
        );

        if (searchText == null) {
            return; // user cancelled
        }

        refreshTable(materialController.searchMaterials(searchText));
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
        refreshTable(materialController.getAllMaterials());
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedMaterialId == -1) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Select a material in the table first.",
                    "No Selection",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Delete this material? This cannot be undone.",
                "Confirm Delete",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        materialController.deleteMaterial(selectedMaterialId);
        clearFields();
        loadFilterOptions();
        refreshTable(materialController.getAllMaterials());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel_Material = new javax.swing.JScrollPane();
        tblMaterial = new javax.swing.JTable();
        lbl_TableName = new javax.swing.JLabel();
        lbl_Filter = new javax.swing.JLabel();
        comboBox_Material = new javax.swing.JComboBox<>();
        lblTitel = new javax.swing.JLabel();
        lblNames = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        lblReorder = new javax.swing.JLabel();
        lblSupplier = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        textNames = new javax.swing.JTextField();
        textCategory = new javax.swing.JTextField();
        textQuantity = new javax.swing.JTextField();
        textReorderlvl = new javax.swing.JTextField();
        textSupplier = new javax.swing.JTextField();
        MenuBar_Material = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMaterial.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Category", "Quantity", "Reorder", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanel_Material.setViewportView(tblMaterial);
        tblMaterial.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl_TableName.setText("Material Table");

        lbl_Filter.setText("Filter:");

        comboBox_Material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Low Stock", "Out of Stock" }));
        comboBox_Material.addActionListener(this::comboBox_MaterialActionPerformed);

        lblTitel.setText("Material Details:");

        lblNames.setText("Names:");

        lblCategory.setText("Category:");

        lblQuantity.setText("Quantity:");

        lblReorder.setText("Reorder Level:");

        lblSupplier.setText("Supplier:");

        btnUpdate.setText("Update");

        btnSearch.setText("Search");

        btnClear.setText("Clear");

        btnDelete.setText("Delete");

        Mbar_Dashboard.setText("Dashboard");
        MenuBar_Material.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        MenuBar_Material.add(Mbar_Cleaners);

        Mbar_Material.setText("Materials");
        MenuBar_Material.add(Mbar_Material);

        Mbar_Reports.setText("Reports");
        MenuBar_Material.add(Mbar_Reports);

        Mbar_Logout.setText("Logout");
        MenuBar_Material.add(Mbar_Logout);

        setJMenuBar(MenuBar_Material);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbl_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBox_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPanel_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbl_TableName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblSupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblReorder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblNames))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNames, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textReorderlvl, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBox_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Filter))
                .addGap(12, 12, 12)
                .addComponent(lbl_TableName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanel_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNames)
                    .addComponent(textNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategory)
                    .addComponent(textCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(textQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReorder)
                    .addComponent(textReorderlvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplier)
                    .addComponent(textSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        comboBox_Material.getAccessibleContext().setAccessibleName("cmbMaterial");
        comboBox_Material.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBox_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_MaterialActionPerformed
        Object selected = comboBox_Material.getSelectedItem();
        if (selected != null) {
            refreshTable(materialController.filterMaterials(selected.toString()));
        }
    }//GEN-LAST:event_comboBox_MaterialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MaterialManagementForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Material;
    private javax.swing.JMenu Mbar_Reports;
    private javax.swing.JMenuBar MenuBar_Material;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboBox_Material;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblNames;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblReorder;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lbl_Filter;
    private javax.swing.JLabel lbl_TableName;
    private javax.swing.JScrollPane scrollPanel_Material;
    private javax.swing.JTable tblMaterial;
    private javax.swing.JTextField textCategory;
    private javax.swing.JTextField textNames;
    private javax.swing.JTextField textQuantity;
    private javax.swing.JTextField textReorderlvl;
    private javax.swing.JTextField textSupplier;
    // End of variables declaration//GEN-END:variables
}
