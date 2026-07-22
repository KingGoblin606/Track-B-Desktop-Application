package track.b.desktop.application.View;

public class StockIssuanceForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StockIssuanceForm.class.getName());
    private final track.b.desktop.application.Controller.UserController userController;
    private final track.b.desktop.application.Controller.MaterialController materialController = new track.b.desktop.application.Controller.MaterialController();
    private final track.b.desktop.application.Controller.CleanerController cleanerController = new track.b.desktop.application.Controller.CleanerController();
    private final track.b.desktop.application.Controller.StockIssuanceController stockIssuanceController = new track.b.desktop.application.Controller.StockIssuanceController();

    public StockIssuanceForm(track.b.desktop.application.Controller.UserController userController) 
    {
        this.userController = userController;
        initComponents();
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        loadMaterialsIntoComboBox();
        loadCleanersIntoComboBox();
        updateAvailableStockLabel();
        loadHistory();
        
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
        Mbar_Materials.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_MaterialsActionPerformed(null);
            }
        });
        Mbar_Suppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_SuppliersActionPerformed(null);
            }
        });
        Mbar_reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_reportsActionPerformed(null);
            }
        });
        Mbar_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_LogoutActionPerformed(null);
            }
        });
    }
    
    private void loadMaterialsIntoComboBox() 
    {
        cb_material.removeAllItems();
        for (track.b.desktop.application.Model.Material material : materialController.getAllMaterials()) 
        {
            cb_material.addItem(material.getMaterialId() + " - " + material.getName());
        }
    }

    private void loadCleanersIntoComboBox() 
    {
        cb_cleaner.removeAllItems();
        for (track.b.desktop.application.Model.Cleaner cleaner : cleanerController.getAllCleaners()) 
        {
            cb_cleaner.addItem(cleaner.getCleanerId() + " - " + cleaner.getName() + " " + cleaner.getSurname());
        }
    }

    private int getSelectedMaterialId() 
    {
        String selected = (String) cb_material.getSelectedItem();
        if (selected == null) 
        {
            throw new IllegalArgumentException("Please select a material.");
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private int getSelectedCleanerId() 
    {
        String selected = (String) cb_cleaner.getSelectedItem();
        if (selected == null) 
        {
            throw new IllegalArgumentException("Please select a cleaner.");
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }
    
    private void updateAvailableStockLabel() 
    {
        try 
        {
            int materialId = getSelectedMaterialId();
            track.b.desktop.application.Model.Material material = materialController.findMaterialById(materialId);
            lbl_AvailableStock.setText(material == null ? "0" : String.valueOf(material.getQuantity()));
        } 
        catch (IllegalArgumentException ex) 
        {
            lbl_AvailableStock.setText("0");
        }
    }
    
    private void loadHistory() 
    {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[]{"Material", "Cleaner", "Quantity", "Date"}, 0
        ) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        for (track.b.desktop.application.Model.StockIssuance issuance : stockIssuanceController.getAllIssuances()) 
        {
            model.addRow(new Object[]
            {
                    issuance.getMaterialName(),
                    issuance.getCleanerName(),
                    issuance.getQuantity(),
                    issuance.getDateIssued()
            });
        }

        tbl_History.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cb_material = new javax.swing.JComboBox<>();
        cb_cleaner = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_AvailableStock = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_History = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_quantity = new javax.swing.JTextField();
        btn_Submit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Materials = new javax.swing.JMenu();
        Mbar_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cb_material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_material.addActionListener(this::cb_materialActionPerformed);

        cb_cleaner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Material:");

        jLabel2.setText("Cleaner:");

        jLabel3.setText("Stock:");

        lbl_AvailableStock.setText("0");

        tbl_History.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_History);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Stock Information");

        jLabel5.setText("Quantity:");

        btn_Submit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(this::btn_SubmitActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Stock Issuance History");

        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        jMenuBar1.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        Mbar_Cleaners.addActionListener(this::Mbar_CleanersActionPerformed);
        jMenuBar1.add(Mbar_Cleaners);

        Mbar_Materials.setText("Materials");
        Mbar_Materials.addActionListener(this::Mbar_MaterialsActionPerformed);
        jMenuBar1.add(Mbar_Materials);

        Mbar_Suppliers.setText("Suppliers");
        Mbar_Suppliers.addActionListener(this::Mbar_SuppliersActionPerformed);
        jMenuBar1.add(Mbar_Suppliers);

        Mbar_Stock.setText("Stock Issuance");
        jMenuBar1.add(Mbar_Stock);

        Mbar_reports.setText("Reports");
        Mbar_reports.addActionListener(this::Mbar_reportsActionPerformed);
        jMenuBar1.add(Mbar_reports);

        Mbar_Logout.setText("Logout");
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        jMenuBar1.add(Mbar_Logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_AvailableStock)
                            .addComponent(cb_cleaner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(350, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_cleaner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_AvailableStock))
                .addGap(28, 28, 28)
                .addComponent(btn_Submit)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_materialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_materialActionPerformed
      updateAvailableStockLabel();
    }//GEN-LAST:event_cb_materialActionPerformed

    private void btn_SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubmitActionPerformed
        try 
        {
            int materialId = getSelectedMaterialId();
            int cleanerId = getSelectedCleanerId();
            int quantity = Integer.parseInt(tf_quantity.getText().trim());
            int userId = userController.getCurrentUser().getUserId();

            stockIssuanceController.issueStock(materialId, cleanerId, userId, quantity);

            tf_quantity.setText("");
            loadHistory();
            updateAvailableStockLabel();

            javax.swing.JOptionPane.showMessageDialog(this, "Stock issued successfully.");

        } 
        catch (NumberFormatException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Quantity must be a whole number.", "Invalid Input", javax.swing.JOptionPane.ERROR_MESSAGE);
        } 
        catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,ex.getMessage(), "Could Not Issue Stock", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_SubmitActionPerformed

    private void Mbar_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_DashboardActionPerformed
        new DashboardForm(userController).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Mbar_DashboardActionPerformed

    private void Mbar_CleanersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_CleanersActionPerformed
      new CleanerMagementFrom(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_CleanersActionPerformed

    private void Mbar_MaterialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_MaterialsActionPerformed
       new MaterialManagementForm(userController).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_MaterialsActionPerformed

    private void Mbar_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_SuppliersActionPerformed
      new SuppliersForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_SuppliersActionPerformed

    private void Mbar_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_LogoutActionPerformed
       userController.logout();
       new LoginForm().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_LogoutActionPerformed

    private void Mbar_reportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_reportsActionPerformed
      new ReportsForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_reportsActionPerformed

    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Materials;
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenu Mbar_Suppliers;
    private javax.swing.JMenu Mbar_reports;
    private javax.swing.JButton btn_Submit;
    private javax.swing.JComboBox<String> cb_cleaner;
    private javax.swing.JComboBox<String> cb_material;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_AvailableStock;
    private javax.swing.JTable tbl_History;
    private javax.swing.JTextField tf_quantity;
    // End of variables declaration//GEN-END:variables
}
