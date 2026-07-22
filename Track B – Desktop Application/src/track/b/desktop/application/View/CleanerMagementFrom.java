package track.b.desktop.application.View;

public class CleanerMagementFrom extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CleanerMagementFrom.class.getName());
    private final track.b.desktop.application.Controller.UserController userController;
    private final track.b.desktop.application.Controller.CleanerController cleanerController = new track.b.desktop.application.Controller.CleanerController();
    private final track.b.desktop.application.Controller.DepartmentController departmentController = new track.b.desktop.application.Controller.DepartmentController();
    

    public CleanerMagementFrom(track.b.desktop.application.Controller.UserController userController) {
        this.userController = userController;
        initComponents();
        setLocationRelativeTo(null);
        setupTable();
        loadDepartmentsIntoComboBox();
        loadCleaners(cleanerController.getAllCleaners());
        setupSelectionListener();
        updateButtonStates(false);
        
        Mbar_Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_DashboardActionPerformed(null);
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
                new Object[]{"ID", "Name", "Surname", "Department"}, 0
        ) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };
        tblCleaners.setModel(model);
    }

    private void loadCleaners(java.util.ArrayList<track.b.desktop.application.Model.Cleaner> cleaners) 
    {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblCleaners.getModel();
        model.setRowCount(0);

        for (track.b.desktop.application.Model.Cleaner cleaner : cleaners) 
        {
            model.addRow(new Object[]
            {
                    cleaner.getCleanerId(),
                    cleaner.getName(),
                    cleaner.getSurname(),
                    cleaner.getDepartmentName() == null ? "—" : cleaner.getDepartmentName()
            });
        }
    }
    
    private void loadDepartmentsIntoComboBox() 
    {
        drp_Dept.removeAllItems();
        drp_Dept.addItem("— None —");
        for (track.b.desktop.application.Model.Department department : departmentController.getAllDepartments()) 
        {
            drp_Dept.addItem(department.getDepartmentId() + " - " + department.getDepartmentName());
        }
    }

    private Integer getSelectedDepartmentId() 
    {
        String selected = (String) drp_Dept.getSelectedItem();
        if (selected == null || selected.equals("— None —")) 
        {
            return null;
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void selectDepartmentInComboBox(Integer departmentId) 
    {
        if (departmentId == null) 
        {
            drp_Dept.setSelectedIndex(0);
            return;
        }
        for (int i = 0; i < drp_Dept.getItemCount(); i++) {
            if (drp_Dept.getItemAt(i).startsWith(departmentId + " - ")) 
            {
                drp_Dept.setSelectedIndex(i);
                return;
            }
        }
        drp_Dept.setSelectedIndex(0);
    }
    
    
    private void setupSelectionListener() 
    {
        tblCleaners.getSelectionModel().addListSelectionListener(evt -> 
        {
            if (evt.getValueIsAdjusting()) 
            {
                return;
            }

            int selectedRow = tblCleaners.getSelectedRow();

            if (selectedRow == -1) {
                updateButtonStates(false);
                return;
            }

            int cleanerId = (int) tblCleaners.getValueAt(selectedRow, 0);
            track.b.desktop.application.Model.Cleaner cleaner = cleanerController.findCleanerById(cleanerId);

            if (cleaner == null) 
            {
                updateButtonStates(false);
                return;
            }

            textNames.setText(cleaner.getName());
            textSurname.setText(cleaner.getSurname());
            textPhoneNum.setText(cleaner.getPhoneNumber());
            textEmail.setText(cleaner.getEmail());
            selectDepartmentInComboBox(cleaner.getDepartmentId());

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
        textNames.setText("");
        textSurname.setText("");
        textPhoneNum.setText("");
        textEmail.setText("");
        if (drp_Dept.getItemCount() > 0) 
        {
            drp_Dept.setSelectedIndex(0);
        }
        tblCleaners.clearSelection();
        updateButtonStates(false);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel_Cleaners = new javax.swing.JScrollPane();
        tblCleaners = new javax.swing.JTable();
        lbl_TableName = new javax.swing.JLabel();
        lblTitel = new javax.swing.JLabel();
        lblNames = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblDepartment = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        textNames = new javax.swing.JTextField();
        textSurname = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblDepartment1 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        textPhoneNum = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        drp_Dept = new javax.swing.JComboBox<>();
        btn_Dept = new javax.swing.JButton();
        MenuBar_Cleaners = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        btn_M_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCleaners.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblCleaners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Surname", "Department", "Phone Number", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanel_Cleaners.setViewportView(tblCleaners);
        tblCleaners.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lbl_TableName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TableName.setText("Cleaners Table");

        lblTitel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitel.setText("Cleaner Details:");

        lblNames.setText("Names:");

        lblSurname.setText("Surname:");

        lblDepartment.setText("Deparntment:");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        textSurname.addActionListener(this::textSurnameActionPerformed);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        lblDepartment1.setText("Phone Number:");

        textEmail.addActionListener(this::textEmailActionPerformed);

        textPhoneNum.addActionListener(this::textPhoneNumActionPerformed);

        jLabel1.setText("Email:");

        drp_Dept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_Dept.setText("Manage Departments");
        btn_Dept.addActionListener(this::btn_DeptActionPerformed);

        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        MenuBar_Cleaners.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        MenuBar_Cleaners.add(Mbar_Cleaners);

        Mbar_Material.setText("Materials");
        Mbar_Material.addActionListener(this::Mbar_MaterialActionPerformed);
        MenuBar_Cleaners.add(Mbar_Material);

        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        MenuBar_Cleaners.add(btn_M_Suppliers);

        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        MenuBar_Cleaners.add(Mbar_Stock);

        Mbar_Reports.setText("Reports");
        Mbar_Reports.addActionListener(this::Mbar_ReportsActionPerformed);
        MenuBar_Cleaners.add(Mbar_Reports);

        Mbar_Logout.setText("Logout");
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        MenuBar_Cleaners.add(Mbar_Logout);

        setJMenuBar(MenuBar_Cleaners);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbl_TableName, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanel_Cleaners))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblDepartment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSurname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDepartment1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(lblNames)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNames)
                            .addComponent(textSurname)
                            .addComponent(textPhoneNum)
                            .addComponent(textEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(drp_Dept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Dept)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_TableName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanel_Cleaners, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNames)
                    .addComponent(textNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSurname)
                    .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartment)
                    .addComponent(drp_Dept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Dept))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDepartment1)
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnSearch)
                            .addComponent(btnClear)
                            .addComponent(btnDelete)
                            .addComponent(btnAdd)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSurnameActionPerformed

    private void textEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEmailActionPerformed

    private void textPhoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPhoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPhoneNumActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try 
        {
              Integer departmentId = getSelectedDepartmentId();

              cleanerController.addCleaner(textNames.getText(), textSurname.getText(), textPhoneNum.getText(), textEmail.getText(), departmentId);

              loadCleaners(cleanerController.getAllCleaners());
              clearFields();
              javax.swing.JOptionPane.showMessageDialog(this, "Cleaner added successfully.");

        } 
        catch (IllegalArgumentException ex) 
        {
              javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),
              "Could Not Add Cleaner", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       
        int selectedRow = tblCleaners.getSelectedRow();
        if (selectedRow == -1) 
        {
            return;
        }

        int cleanerId = (int) tblCleaners.getValueAt(selectedRow, 0);

        try 
        {
            Integer departmentId = getSelectedDepartmentId();

            cleanerController.updateCleaner(cleanerId, textNames.getText(), textSurname.getText(), textPhoneNum.getText(), textEmail.getText(), departmentId);

            loadCleaners(cleanerController.getAllCleaners());
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Cleaner updated successfully.");

        } 
        catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Could Not Update Cleaner", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        int selectedRow = tblCleaners.getSelectedRow();
        if (selectedRow == -1) 
        {
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, "Are you sure you want to delete this cleaner?",
                "Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) 
        {
            return;
        }

        int cleanerId = (int) tblCleaners.getValueAt(selectedRow, 0);
        cleanerController.deleteCleaner(cleanerId);
        loadCleaners(cleanerController.getAllCleaners());
        clearFields();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
       String searchText = javax.swing.JOptionPane.showInputDialog(this, "Search by ID, name, surname or department:");

        if (searchText == null) 
        {
            return;
        }

        loadCleaners(cleanerController.searchCleaners(searchText));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
        loadCleaners(cleanerController.getAllCleaners());
    }//GEN-LAST:event_btnClearActionPerformed

    private void Mbar_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_DashboardActionPerformed
       new DashboardForm(userController).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_DashboardActionPerformed

    private void Mbar_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_MaterialActionPerformed
       new MaterialManagementForm(userController).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_MaterialActionPerformed

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

    private void btn_DeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeptActionPerformed
      DepartmentDialog dialog = new DepartmentDialog(this, true);
      dialog.setVisible(true);
      loadDepartmentsIntoComboBox();
    }//GEN-LAST:event_btn_DeptActionPerformed

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Material;
    private javax.swing.JMenu Mbar_Reports;
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenuBar MenuBar_Cleaners;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btn_Dept;
    private javax.swing.JMenu btn_M_Suppliers;
    private javax.swing.JComboBox<String> drp_Dept;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblDepartment1;
    private javax.swing.JLabel lblNames;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lbl_TableName;
    private javax.swing.JScrollPane scrollPanel_Cleaners;
    private javax.swing.JTable tblCleaners;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNames;
    private javax.swing.JTextField textPhoneNum;
    private javax.swing.JTextField textSurname;
    // End of variables declaration//GEN-END:variables
}
