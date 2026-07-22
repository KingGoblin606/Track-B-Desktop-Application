package track.b.desktop.application.View;
import track.b.desktop.application.Controller.UserController;

public class MaterialManagementForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MaterialManagementForm.class.getName());
    private final track.b.desktop.application.Controller.UserController userController;
    private final track.b.desktop.application.Controller.MaterialController materialController = new track.b.desktop.application.Controller.MaterialController();
    private final track.b.desktop.application.Controller.SupplierController supplierController = new track.b.desktop.application.Controller.SupplierController();
    
    
    

    public MaterialManagementForm(track.b.desktop.application.Controller.UserController userController) 
    { 
       this.userController = userController;
        initComponents();
        setLocationRelativeTo(null);
        setupTable();
        setupFilterComboBox();
        loadSuppliersIntoComboBox();
        loadMaterials(materialController.getAllMaterials());
        setupSelectionListener();
        updateButtonStates(false);
        
        Mbar_Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_DashboardActionPerformed(null);
            }
        });
        Mbar_Cleaners.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_CleanersActionPerformed(null);
            }
        });
        btn_M_Suppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_M_SuppliersActionPerformed(null);
            }
        });
        Mbar_Stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_StockActionPerformed(null);
            }
        });
        Mbar_Reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_ReportsActionPerformed(null);
            }
        });
        Mbar_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_LogoutActionPerformed(null);
            }
        });
    }
    
    private void setupTable() 
    {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[]{"ID", "Name", "Category", "Quantity", "Reorder", "Cost", "Supplier", "Status"}, 0
        ) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };
        tblMaterial.setModel(model);
    }
    
    private void loadMaterials(java.util.ArrayList<track.b.desktop.application.Model.Material> materials) 
    {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblMaterial.getModel();
        model.setRowCount(0);

        for (track.b.desktop.application.Model.Material material : materials) 
        {
            model.addRow(new Object[]{
                    material.getMaterialId(),
                    material.getName(),
                    material.getCategory(),
                    material.getQuantity(),
                    material.getReorderLevel(),
                    material.getCost(),
                    material.getSupplierName(),
                    material.getStockStatus()
            });
        }
    }
    
    private void loadSuppliersIntoComboBox() 
    {
        Cb_Supplier.removeAllItems();
        for (track.b.desktop.application.Model.Supplier supplier : supplierController.getAllSuppliers()) 
        {
            Cb_Supplier.addItem(supplier.getSupplierId() + " - " + supplier.getSupplierName());
        }
    }

    private int getSelectedSupplierId() 
    {
        String selected = (String) Cb_Supplier.getSelectedItem();
        if (selected == null) 
        {
            throw new IllegalArgumentException("Please select a supplier.");
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void selectSupplierInComboBox(int supplierId) 
    {
        for (int i = 0; i < Cb_Supplier.getItemCount(); i++) 
        {
            if (Cb_Supplier.getItemAt(i).startsWith(supplierId + " - ")) 
            {
                Cb_Supplier.setSelectedIndex(i);
                return;
            }
        }
    }
    
    private void setupFilterComboBox() 
    {
        comboBox_Material.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"All", "Low Stock", "Out of Stock", "Chemicals", "Equipment", "Consumables", "PPE/Safety"}
        ));
    }
    
    private void setupSelectionListener() 
    {
        tblMaterial.getSelectionModel().addListSelectionListener(evt -> 
        {
            if (evt.getValueIsAdjusting()) 
            {
                return;
            }

            int selectedRow = tblMaterial.getSelectedRow();

            if (selectedRow == -1) 
            {
                updateButtonStates(false);
                return;
            }

            int materialId = (int) tblMaterial.getValueAt(selectedRow, 0);
            track.b.desktop.application.Model.Material material = materialController.findMaterialById(materialId);

            if (material == null) 
            {
                updateButtonStates(false);
                return;
            }

            tb_names.setText(material.getName());
            tb_category.setText(material.getCategory());
            tb_quantity.setText(String.valueOf(material.getQuantity()));
            tb_Reorder.setText(String.valueOf(material.getReorderLevel()));
            tb_Cost.setText(String.valueOf(material.getCost()));
            selectSupplierInComboBox(material.getSupplierId());

            updateButtonStates(true);
        });
    }

    private void updateButtonStates(boolean rowSelected) 
    {
        btn_add.setEnabled(!rowSelected);
        btnUpdate.setEnabled(rowSelected);
        btnDelete.setEnabled(rowSelected);
    }

    private void clearFields() 
    {
        tb_names.setText("");
        tb_category.setText("");
        tb_quantity.setText("");
        tb_Reorder.setText("");
        tb_Cost.setText("");
        if (Cb_Supplier.getItemCount() > 0) {
            Cb_Supplier.setSelectedIndex(0);
        }
        tblMaterial.clearSelection();
        updateButtonStates(false);
    }
    
    
    
    
    

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
        tb_names = new javax.swing.JTextField();
        tb_category = new javax.swing.JTextField();
        tb_quantity = new javax.swing.JTextField();
        tb_Reorder = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        lblSupplier1 = new javax.swing.JLabel();
        tb_Cost = new javax.swing.JTextField();
        Cb_Supplier = new javax.swing.JComboBox<>();
        MenuBar_Material = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        btn_M_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMaterial.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Category", "Quantity", "Reorder", "Cost", "Supplier", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanel_Material.setViewportView(tblMaterial);
        tblMaterial.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lbl_TableName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TableName.setText("Material Table");

        lbl_Filter.setText("Filter:");

        comboBox_Material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox_Material.addActionListener(this::comboBox_MaterialActionPerformed);

        lblTitel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitel.setText("Material Details");

        lblNames.setText("Names:");

        lblCategory.setText("Category:");

        lblQuantity.setText("Quantity:");

        lblReorder.setText("Reorder Level:");

        lblSupplier.setText("Supplier:");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        tb_category.addActionListener(this::tb_categoryActionPerformed);

        tb_quantity.addActionListener(this::tb_quantityActionPerformed);

        tb_Reorder.addActionListener(this::tb_ReorderActionPerformed);

        btn_add.setText("Add");
        btn_add.addActionListener(this::btn_addActionPerformed);

        lblSupplier1.setText("Cost:");

        Cb_Supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        MenuBar_Material.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        Mbar_Cleaners.addActionListener(this::Mbar_CleanersActionPerformed);
        MenuBar_Material.add(Mbar_Cleaners);

        Mbar_Material.setText("Materials");
        MenuBar_Material.add(Mbar_Material);

        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        MenuBar_Material.add(btn_M_Suppliers);

        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        MenuBar_Material.add(Mbar_Stock);

        Mbar_Reports.setText("Reports");
        Mbar_Reports.addActionListener(this::Mbar_ReportsActionPerformed);
        MenuBar_Material.add(Mbar_Reports);

        Mbar_Logout.setText("Logout");
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        MenuBar_Material.add(Mbar_Logout);

        setJMenuBar(MenuBar_Material);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(185, 185, 185))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tb_Cost, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblSupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblReorder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblNames))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tb_names, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(tb_category, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(tb_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(tb_Reorder, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(Cb_Supplier, 0, 1, Short.MAX_VALUE))
                                .addGap(325, 325, 325))))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbl_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBox_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPanel_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_TableName, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNames)
                    .addComponent(tb_names, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategory)
                    .addComponent(tb_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(tb_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReorder)
                    .addComponent(tb_Reorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplier)
                    .addComponent(Cb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSupplier1)
                    .addComponent(tb_Cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnDelete))
                .addGap(14, 14, 14))
        );

        comboBox_Material.getAccessibleContext().setAccessibleName("cmbMaterial");
        comboBox_Material.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBox_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_MaterialActionPerformed
       String filter = (String) comboBox_Material.getSelectedItem();
       loadMaterials(materialController.filterMaterials(filter));
    }//GEN-LAST:event_comboBox_MaterialActionPerformed

    private void tb_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_quantityActionPerformed

    private void tb_ReorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_ReorderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReorderActionPerformed

    private void tb_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_categoryActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        try 
        {
            int quantity = Integer.parseInt(tb_quantity.getText().trim());
            int reorderLevel = Integer.parseInt(tb_Reorder.getText().trim());
            int cost = Integer.parseInt(tb_Cost.getText().trim());
            int supplierId = getSelectedSupplierId();

            materialController.addMaterial(
                    tb_names.getText(), tb_category.getText(), quantity, reorderLevel, cost, supplierId
            );

            loadMaterials(materialController.getAllMaterials());
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Material added successfully.");

        } 
        catch (NumberFormatException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Quantity, Reorder Level and Cost must be whole numbers.",
                    "Invalid Input", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        } 
        catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Could Not Add Material", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblMaterial.getSelectedRow();
        if (selectedRow == -1) 
        {
            return; // button is disabled with nothing selected
        }

        int materialId = (int) tblMaterial.getValueAt(selectedRow, 0);

        try 
        {
            int quantity = Integer.parseInt(tb_quantity.getText().trim());
            int reorderLevel = Integer.parseInt(tb_Reorder.getText().trim());
            int cost = Integer.parseInt(tb_Cost.getText().trim());
            int supplierId = getSelectedSupplierId();

            materialController.updateMaterial(
                    materialId, tb_names.getText(), tb_category.getText(), quantity, reorderLevel, cost, supplierId
            );

            loadMaterials(materialController.getAllMaterials());
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Material updated successfully.");

        } 
        catch (NumberFormatException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Quantity, Reorder Level and Cost must be whole numbers.",
                    "Invalid Input", javax.swing.JOptionPane.ERROR_MESSAGE);
        } 
        catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Could Not Update Material", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblMaterial.getSelectedRow();
        if (selectedRow == -1) 
        {
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, "Are you sure you want to delete this material?",
                "Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) 
        {
            return;
        }

        int materialId = (int) tblMaterial.getValueAt(selectedRow, 0);
        materialController.deleteMaterial(materialId);
        loadMaterials(materialController.getAllMaterials());
        clearFields();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = javax.swing.JOptionPane.showInputDialog(
                this, "Search by ID, name, category or supplier:"
        );

        if (searchText == null) 
        {
            return;
        }

        loadMaterials(materialController.searchMaterials(searchText));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       clearFields();
        loadMaterials(materialController.getAllMaterials());
    }//GEN-LAST:event_btnClearActionPerformed

    private void Mbar_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_DashboardActionPerformed
        new DashboardForm(userController).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Mbar_DashboardActionPerformed

    private void Mbar_CleanersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_CleanersActionPerformed
       new CleanerMagementFrom(userController).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_CleanersActionPerformed

    private void Mbar_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_LogoutActionPerformed
       userController.logout();
       new LoginForm().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_LogoutActionPerformed

    private void btn_M_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_M_SuppliersActionPerformed
      new SuppliersForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btn_M_SuppliersActionPerformed

    private void Mbar_StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_StockActionPerformed
       new StockIssuanceForm(userController).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_StockActionPerformed

    private void Mbar_ReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_ReportsActionPerformed
      new ReportsForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_ReportsActionPerformed

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Cb_Supplier;
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Material;
    private javax.swing.JMenu Mbar_Reports;
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenuBar MenuBar_Material;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JMenu btn_M_Suppliers;
    private javax.swing.JButton btn_add;
    private javax.swing.JComboBox<String> comboBox_Material;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblNames;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblReorder;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JLabel lblSupplier1;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lbl_Filter;
    private javax.swing.JLabel lbl_TableName;
    private javax.swing.JScrollPane scrollPanel_Material;
    private javax.swing.JTextField tb_Cost;
    private javax.swing.JTextField tb_Reorder;
    private javax.swing.JTextField tb_category;
    private javax.swing.JTextField tb_names;
    private javax.swing.JTextField tb_quantity;
    private javax.swing.JTable tblMaterial;
    // End of variables declaration//GEN-END:variables
}
