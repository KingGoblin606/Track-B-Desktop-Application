package track.b.desktop.application.View;


public class SuppliersForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SuppliersForm.class.getName());
    
    private final track.b.desktop.application.Controller.UserController userController;
    private final track.b.desktop.application.Controller.SupplierController supplierController = new track.b.desktop.application.Controller.SupplierController();

    public SuppliersForm(track.b.desktop.application.Controller.UserController userController) 
    {
        this.userController = userController;
        initComponents();
        setLocationRelativeTo(null);
        setupTable();
        loadSuppliers(supplierController.getAllSuppliers());
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
        Mbar_Material.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_MaterialActionPerformed(null);
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
                new Object[]{"ID", "Company Name", "Name of Contact", "Email", "Cell Number"}, 0
        ) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };
        tblSuppliers.setModel(model);
    }

    private void loadSuppliers(java.util.ArrayList<track.b.desktop.application.Model.Supplier> suppliers) 
    {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblSuppliers.getModel();
        model.setRowCount(0);

        for (track.b.desktop.application.Model.Supplier supplier : suppliers) 
        {
            model.addRow(new Object[]{
                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getContactPerson(),
                    supplier.getEmail(),
                    supplier.getPhoneNumber()
            });
        }
    }  
    
    private void setupSelectionListener() 
    {
        tblSuppliers.getSelectionModel().addListSelectionListener(evt -> 
        {
            if (evt.getValueIsAdjusting()) 
            {
                return;
            }

            int selectedRow = tblSuppliers.getSelectedRow();

            if (selectedRow == -1) 
            {
                updateButtonStates(false);
                return;
            }

            int supplierId = (int) tblSuppliers.getValueAt(selectedRow, 0);
            track.b.desktop.application.Model.Supplier supplier = supplierController.findSupplierById(supplierId);

            if (supplier == null) 
            {
                updateButtonStates(false);
                return;
            }

            tb_CompanyName.setText(supplier.getSupplierName());
            tb_nameOfContact.setText(supplier.getContactPerson());
            tb_Email.setText(supplier.getEmail());
            tb_CellNumber.setText(supplier.getPhoneNumber());
            tb_address.setText(supplier.getStreetAddress());
            tb_City.setText(supplier.getCity());
            tf_postalCode.setText(supplier.getPostalCode());

            updateButtonStates(true);
        });
    }

    private void updateButtonStates(boolean rowSelected) 
    {
        btnAdd.setEnabled(!rowSelected);
        btnUpdate.setEnabled(rowSelected);
        btnDelete.setEnabled(rowSelected);
    }

    private void clearFields() 
    {
        tb_CompanyName.setText("");
        tb_nameOfContact.setText("");
        tb_Email.setText("");
        tb_CellNumber.setText("");
        tb_address.setText("");
        tb_City.setText("");
        tf_postalCode.setText("");
        tblSuppliers.clearSelection();
        updateButtonStates(false);
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel_Suppliers = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        lbl_TableName = new javax.swing.JLabel();
        lblTitel = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        lblNameOfContact = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tb_CompanyName = new javax.swing.JTextField();
        tb_nameOfContact = new javax.swing.JTextField();
        tb_Email = new javax.swing.JTextField();
        lblCellNumber = new javax.swing.JLabel();
        tb_CellNumber = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblCellNumber1 = new javax.swing.JLabel();
        tb_City = new javax.swing.JTextField();
        lblCellNumber2 = new javax.swing.JLabel();
        tb_address = new javax.swing.JTextField();
        lblCellNumber3 = new javax.swing.JLabel();
        tf_postalCode = new javax.swing.JTextField();
        MenuBar_Suppliers = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        btn_M_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSuppliers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID", "Company Name", "Name of Contact", "Email", "Cell Number", "Street Address", "City", "Postal Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanel_Suppliers.setViewportView(tblSuppliers);
        tblSuppliers.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lbl_TableName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TableName.setText("Suppliers Table");

        lblTitel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitel.setText("Supplier Details");

        lblCompanyName.setText("Company Name:");

        lblNameOfContact.setText("Name Of Contact:");

        lblEmail.setText("Email:");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        tb_nameOfContact.addActionListener(this::tb_nameOfContactActionPerformed);

        tb_Email.addActionListener(this::tb_EmailActionPerformed);

        lblCellNumber.setText("Cell Number:");

        tb_CellNumber.addActionListener(this::tb_CellNumberActionPerformed);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        lblCellNumber1.setText("Street Address:");

        tb_City.addActionListener(this::tb_CityActionPerformed);

        lblCellNumber2.setText("City:");

        tb_address.addActionListener(this::tb_addressActionPerformed);

        lblCellNumber3.setText("Postal Code:");

        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        MenuBar_Suppliers.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        Mbar_Cleaners.addActionListener(this::Mbar_CleanersActionPerformed);
        MenuBar_Suppliers.add(Mbar_Cleaners);

        Mbar_Material.setText("Materials");
        Mbar_Material.addActionListener(this::Mbar_MaterialActionPerformed);
        MenuBar_Suppliers.add(Mbar_Material);

        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        MenuBar_Suppliers.add(btn_M_Suppliers);

        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        MenuBar_Suppliers.add(Mbar_Stock);

        Mbar_Reports.setText("Reports");
        Mbar_Reports.addActionListener(this::Mbar_ReportsActionPerformed);
        MenuBar_Suppliers.add(Mbar_Reports);

        Mbar_Logout.setText("Logout");
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        MenuBar_Suppliers.add(Mbar_Logout);

        setJMenuBar(MenuBar_Suppliers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPanel_Suppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addComponent(lbl_TableName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCellNumber1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNameOfContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCompanyName)
                        .addComponent(lblCellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCellNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCellNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_CellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_CompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_nameOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_City, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_address, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(190, 190, 190))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_TableName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanel_Suppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblTitel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompanyName)
                    .addComponent(tb_CompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNameOfContact)
                    .addComponent(tb_nameOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCellNumber)
                    .addComponent(tb_CellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCellNumber1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCellNumber2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tb_City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCellNumber3)))))
                    .addComponent(tb_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnDelete)
                    .addComponent(btnAdd))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_EmailActionPerformed

    private void tb_nameOfContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_nameOfContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_nameOfContactActionPerformed

    private void tb_CellNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_CellNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CellNumberActionPerformed

    private void tb_CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_CityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CityActionPerformed

    private void tb_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_addressActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
       try 
       {
            supplierController.addSupplier(
                    tb_CompanyName.getText(), tb_nameOfContact.getText(), tb_CellNumber.getText(),
                    tb_Email.getText(), tb_address.getText(), tb_City.getText(), tf_postalCode.getText()
            );

            loadSuppliers(supplierController.getAllSuppliers());
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Supplier added successfully.");

        } catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Could Not Add Supplier", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow == -1) 
        {
            return;
        }

        int supplierId = (int) tblSuppliers.getValueAt(selectedRow, 0);

        try 
        {
            supplierController.updateSupplier(
                    supplierId, tb_CompanyName.getText(), tb_nameOfContact.getText(), tb_CellNumber.getText(),
                    tb_Email.getText(), tb_address.getText(), tb_City.getText(), tf_postalCode.getText()
            );

            loadSuppliers(supplierController.getAllSuppliers());
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Supplier updated successfully.");

        } catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Could Not Update Supplier", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow == -1) 
        {
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, "Are you sure you want to delete this supplier?",
                "Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) 
        {
            return;
        }

        int supplierId = (int) tblSuppliers.getValueAt(selectedRow, 0);
        supplierController.deleteSupplier(supplierId);
        loadSuppliers(supplierController.getAllSuppliers());
        clearFields();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = javax.swing.JOptionPane.showInputDialog(
                this, "Search by ID, company name, contact or email:"
        );

        if (searchText == null) 
        {
            return;
        }

        loadSuppliers(supplierController.searchSuppliers(searchText));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       clearFields();
       loadSuppliers(supplierController.getAllSuppliers());
    }//GEN-LAST:event_btnClearActionPerformed

    private void Mbar_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_DashboardActionPerformed
      new DashboardForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_DashboardActionPerformed

    private void Mbar_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_MaterialActionPerformed
      new MaterialManagementForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_MaterialActionPerformed

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
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Material;
    private javax.swing.JMenu Mbar_Reports;
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenuBar MenuBar_Suppliers;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JMenu btn_M_Suppliers;
    private javax.swing.JLabel lblCellNumber;
    private javax.swing.JLabel lblCellNumber1;
    private javax.swing.JLabel lblCellNumber2;
    private javax.swing.JLabel lblCellNumber3;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNameOfContact;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lbl_TableName;
    private javax.swing.JScrollPane scrollPanel_Suppliers;
    private javax.swing.JTextField tb_CellNumber;
    private javax.swing.JTextField tb_City;
    private javax.swing.JTextField tb_CompanyName;
    private javax.swing.JTextField tb_Email;
    private javax.swing.JTextField tb_address;
    private javax.swing.JTextField tb_nameOfContact;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField tf_postalCode;
    // End of variables declaration//GEN-END:variables
}
