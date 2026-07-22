package track.b.desktop.application.View;

import javax.swing.DefaultComboBoxModel;

public class ReportsForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReportsForm.class.getName());
    
    private final track.b.desktop.application.Controller.UserController userController;
    private final track.b.desktop.application.Controller.MaterialController materialController = new track.b.desktop.application.Controller.MaterialController();
    private final track.b.desktop.application.Controller.StockIssuanceController stockIssuanceController = new track.b.desktop.application.Controller.StockIssuanceController();

    public ReportsForm(track.b.desktop.application.Controller.UserController userController) {
        this.userController = userController;
        initComponents();
        setLocationRelativeTo(null);
        setupReportTypeComboBox();
        loadReport();
        
        cb_ReportType.addActionListener(this::cb_ReportTypeActionPerformed);

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
        Mbar_Stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_StockActionPerformed(null);
            }
        });
        Mbar_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mbar_LogoutActionPerformed(null);
            }
        });
        
        

    }
    
    private void setupReportTypeComboBox() 
    {
        cb_ReportType.setModel(new DefaultComboBoxModel<>(new String[]{"Inventory Report", "Low-Stock Report", "Issuance History", "Material Usage Report"}));
    }

    private void cb_ReportTypeActionPerformed(java.awt.event.ActionEvent evt) {
        loadReport();
    }

    private void loadReport() 
    {
        String selected = (String) cb_ReportType.getSelectedItem();

        if (selected == null) 
        {
            return;
        }

        switch (selected) 
        {
            case "Inventory Report":
                loadInventoryReport();
                break;
            case "Low-Stock Report":
                loadLowStockReport();
                break;
            case "Issuance History":
                loadIssuanceHistoryReport();
                break;
            case "Material Usage Report":
                loadMaterialUsageReport();
                break;
        }
    }
    
        private void loadInventoryReport() 
        {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[]{"ID", "Name", "Category", "Quantity", "Reorder", "Cost", "Supplier", "Status"}, 0) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        for (track.b.desktop.application.Model.Material material : materialController.getAllMaterials()) 
        {
            model.addRow(new Object[]
            {
                    material.getMaterialId(), material.getName(), material.getCategory(),
                    material.getQuantity(), material.getReorderLevel(), material.getCost(),
                    material.getSupplierName(), material.getStockStatus()
            });
        }

        tbl_Reports.setModel(model);
    }

    private void loadLowStockReport() 
    {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[]{"ID", "Name", "Category", "Quantity", "Reorder", "Cost", "Supplier", "Status"}, 0) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        for (track.b.desktop.application.Model.Material material : materialController.getAllMaterials()) 
        {
            if (material.isLowStock()) 
            {
                model.addRow(new Object[]
                {
                        material.getMaterialId(), material.getName(), material.getCategory(),
                        material.getQuantity(), material.getReorderLevel(), material.getCost(),
                        material.getSupplierName(), material.getStockStatus()
                });
            }
        }

        tbl_Reports.setModel(model);
    }

    private void loadIssuanceHistoryReport() 
    {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[]{"Material", "Cleaner", "Quantity", "Date"}, 0) 
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
                    issuance.getMaterialName(), issuance.getCleanerName(),
                    issuance.getQuantity(), issuance.getDateIssued()
            });
        }

        tbl_Reports.setModel(model);
    }

    private void loadMaterialUsageReport() 
    {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[]{"Material", "Total Quantity Issued"}, 0) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        for (java.util.Map.Entry<String, Integer> entry : stockIssuanceController.getMaterialUsageSummary().entrySet()) 
        {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        tbl_Reports.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Reports = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cb_ReportType = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Materials = new javax.swing.JMenu();
        Mbar_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reports");

        tbl_Reports.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_Reports);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose Report Type:");

        cb_ReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        jMenuBar1.add(Mbar_Stock);

        Mbar_reports.setText("Reports");
        jMenuBar1.add(Mbar_reports);

        Mbar_Logout.setText("Logout");
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        jMenuBar1.add(Mbar_Logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(cb_ReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cb_ReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void Mbar_StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mbar_StockActionPerformed
      new StockIssuanceForm(userController).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_Mbar_StockActionPerformed

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
    private javax.swing.JComboBox<String> cb_ReportType;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_Reports;
    // End of variables declaration//GEN-END:variables
}
