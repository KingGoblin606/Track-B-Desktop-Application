package track.b.desktop.application.View;

public class DashboardForm extends javax.swing.JFrame {
    
    private final track.b.desktop.application.Controller.UserController userController;

    public DashboardForm(track.b.desktop.application.Controller.UserController userController) 
    {
      this.userController = userController;
      initComponents();
      setLocationRelativeTo(null);
      loadDashboardData();
      
      btn_M_Cleaners.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_M_CleanersActionPerformed(null);
            }
        });
        btn_M_Materials.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_M_MaterialsActionPerformed(null);
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
        btn_M_Reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_M_ReportsActionPerformed(null);
            }
        });
        btn_M_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_M_LogoutActionPerformed(null);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_Username = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_TotalMaterials1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_LowStock = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTotalCleaners = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_RecentIssuances = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        btn_M_Dashboard = new javax.swing.JMenu();
        btn_M_Cleaners = new javax.swing.JMenu();
        btn_M_Materials = new javax.swing.JMenu();
        btn_M_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        btn_M_Reports = new javax.swing.JMenu();
        btn_M_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_Username.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_Username.setText("Welcome , {Username}");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        lbl_TotalMaterials1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_TotalMaterials1.setText("Total Materials");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbl_TotalMaterials1)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_TotalMaterials1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        lbl_LowStock.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_LowStock.setText("Low Stock");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbl_LowStock)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_LowStock)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        lblTotalCleaners.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalCleaners.setText("Total Cleaners");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblTotalCleaners)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalCleaners)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        tbl_RecentIssuances.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Material", "Cleaner", "Quantity", "Date"
            }
        ));
        jScrollPane1.setViewportView(tbl_RecentIssuances);

        btn_M_Dashboard.setText("Dashboard");
        jMenuBar1.add(btn_M_Dashboard);

        btn_M_Cleaners.setText("Cleaners");
        btn_M_Cleaners.setToolTipText("");
        btn_M_Cleaners.addActionListener(this::btn_M_CleanersActionPerformed);
        jMenuBar1.add(btn_M_Cleaners);

        btn_M_Materials.setText("Materials");
        btn_M_Materials.addActionListener(this::btn_M_MaterialsActionPerformed);
        jMenuBar1.add(btn_M_Materials);

        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        jMenuBar1.add(btn_M_Suppliers);

        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        jMenuBar1.add(Mbar_Stock);

        btn_M_Reports.setText("Reports");
        btn_M_Reports.addActionListener(this::btn_M_ReportsActionPerformed);
        jMenuBar1.add(btn_M_Reports);

        btn_M_Logout.setText("Logout");
        btn_M_Logout.addActionListener(this::btn_M_LogoutActionPerformed);
        jMenuBar1.add(btn_M_Logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_Username)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Username)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_M_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_M_LogoutActionPerformed
        userController.logout();
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_M_LogoutActionPerformed

    private void btn_M_MaterialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_M_MaterialsActionPerformed
        new MaterialManagementForm(userController).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_M_MaterialsActionPerformed

    private void btn_M_CleanersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_M_CleanersActionPerformed
        new CleanerMagementFrom(userController).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_M_CleanersActionPerformed

    private void btn_M_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_M_SuppliersActionPerformed
      new SuppliersForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btn_M_SuppliersActionPerformed

    private void Mbar_StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_StockActionPerformed
       new StockIssuanceForm(userController).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_Mbar_StockActionPerformed

    private void btn_M_ReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_M_ReportsActionPerformed
      new ReportsForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btn_M_ReportsActionPerformed

    private void loadDashboardData() 
    {

        lbl_Username.setText("Welcome, " + userController.getCurrentUser().getUserName());

        track.b.desktop.application.Controller.MaterialController materialController = new track.b.desktop.application.Controller.MaterialController();
        track.b.desktop.application.Controller.CleanerController cleanerController = new track.b.desktop.application.Controller.CleanerController();
        track.b.desktop.application.Controller.StockIssuanceController stockIssuanceController = new track.b.desktop.application.Controller.StockIssuanceController();

        java.util.ArrayList<track.b.desktop.application.Model.Material> materials =
            materialController.getAllMaterials();

        int lowStockCount = 0;
        for (track.b.desktop.application.Model.Material material : materials) 
        {
            if (material.isLowStock()) 
            {
                lowStockCount++;
            }
        }

        lbl_TotalMaterials1.setText("Total Materials: " + materials.size());
        lbl_LowStock.setText("Low Stock Items: " + lowStockCount);
        lblTotalCleaners.setText("Total Cleaners: " + cleanerController.getAllCleaners().size());

        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[]{"Material", "Cleaner", "Quantity", "Date"}, 0);

        java.util.ArrayList<track.b.desktop.application.Model.StockIssuance> issuances =
                stockIssuanceController.getAllIssuances();

        int limit = Math.min(10, issuances.size());

        for (int i = 0; i < limit; i++) 
        {
            track.b.desktop.application.Model.StockIssuance issuance = issuances.get(i);
            model.addRow(new Object[]{
                    issuance.getMaterialName(),
                    issuance.getCleanerName(),
                    issuance.getQuantity(),
                    issuance.getDateIssued()
            });
        }

        tbl_RecentIssuances.setModel(model);
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenu btn_M_Cleaners;
    private javax.swing.JMenu btn_M_Dashboard;
    private javax.swing.JMenu btn_M_Logout;
    private javax.swing.JMenu btn_M_Materials;
    private javax.swing.JMenu btn_M_Reports;
    private javax.swing.JMenu btn_M_Suppliers;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalCleaners;
    private javax.swing.JLabel lbl_LowStock;
    private javax.swing.JLabel lbl_TotalMaterials1;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTable tbl_RecentIssuances;
    // End of variables declaration//GEN-END:variables
}
