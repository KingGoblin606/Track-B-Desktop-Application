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
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png")).getImage());
        setLocationRelativeTo(null);
        
        
        jMenuBar1.add(javax.swing.Box.createHorizontalGlue(), 2);
        jMenuBar1.add(javax.swing.Box.createHorizontalGlue(), 9);
        
        tbl_Reports.getTableHeader().setBackground(new java.awt.Color(23, 34, 45));
        tbl_Reports.getTableHeader().setForeground(new java.awt.Color(140, 160, 175));
        tbl_Reports.getTableHeader().setFont(new java.awt.Font("Consolas", 0, 18));

        tbl_Reports.setBackground(new java.awt.Color(18, 28, 39));
        jScrollPane2.getViewport().setBackground(new java.awt.Color(18, 28, 39));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        
        
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
        MainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Reports = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cb_ReportType = new javax.swing.JComboBox<>();
        btn_CSV = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Materials = new javax.swing.JMenu();
        Mbar_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(11, 18, 26));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 160, 175));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REPORTS");

        tbl_Reports.setBackground(new java.awt.Color(18, 28, 39));
        tbl_Reports.setForeground(new java.awt.Color(231, 238, 243));
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
        tbl_Reports.setGridColor(new java.awt.Color(36, 51, 63));
        tbl_Reports.setRowHeight(36);
        tbl_Reports.setSelectionBackground(new java.awt.Color(29, 42, 54));
        tbl_Reports.setSelectionForeground(new java.awt.Color(23, 232, 200));
        jScrollPane2.setViewportView(tbl_Reports);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(140, 160, 175));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REPORT TYPE:");

        cb_ReportType.setBackground(new java.awt.Color(29, 42, 54));
        cb_ReportType.setForeground(new java.awt.Color(231, 238, 243));
        cb_ReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_CSV.setBackground(new java.awt.Color(23, 232, 200));
        btn_CSV.setForeground(new java.awt.Color(11, 18, 26));
        btn_CSV.setText("EXPORT TO CSV");
        btn_CSV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(36, 51, 63), 2, true));
        btn_CSV.addActionListener(this::btn_CSVActionPerformed);

        btn_Print.setBackground(new java.awt.Color(23, 232, 200));
        btn_Print.setForeground(new java.awt.Color(11, 18, 26));
        btn_Print.setText("PRINT");
        btn_Print.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(36, 51, 63), 2, true));
        btn_Print.addActionListener(this::btn_PrintActionPerformed);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MainPanelLayout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(btn_CSV, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btn_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cb_ReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_ReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_CSV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))))
        );

        jMenuBar1.setBackground(new java.awt.Color(18, 28, 39));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(465, 52));

        jMenu1.setForeground(new java.awt.Color(23, 232, 200));
        jMenu1.setText("●");
        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("CampusClean OS");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu2);

        Mbar_Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        jMenuBar1.add(Mbar_Dashboard);

        Mbar_Cleaners.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Cleaners.setText("Cleaners");
        Mbar_Cleaners.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Cleaners.addActionListener(this::Mbar_CleanersActionPerformed);
        jMenuBar1.add(Mbar_Cleaners);

        Mbar_Materials.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Materials.setText("Materials");
        Mbar_Materials.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Materials.addActionListener(this::Mbar_MaterialsActionPerformed);
        jMenuBar1.add(Mbar_Materials);

        Mbar_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Suppliers.setText("Suppliers");
        Mbar_Suppliers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Suppliers.addActionListener(this::Mbar_SuppliersActionPerformed);
        jMenuBar1.add(Mbar_Suppliers);

        Mbar_Stock.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        jMenuBar1.add(Mbar_Stock);

        Mbar_reports.setForeground(new java.awt.Color(23, 232, 200));
        Mbar_reports.setText("Reports");
        Mbar_reports.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuBar1.add(Mbar_reports);

        Mbar_Logout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 16));
        Mbar_Logout.setForeground(new java.awt.Color(255, 92, 122));
        Mbar_Logout.setText("Logout");
        Mbar_Logout.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        jMenuBar1.add(Mbar_Logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
       try 
       {
            tbl_Reports.print();
       } 
       catch (java.awt.print.PrinterException ex) 
       {
           javax.swing.JOptionPane.showMessageDialog(this,"Could not print: " + ex.getMessage(),"Print Error",javax.swing.JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void btn_CSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CSVActionPerformed

            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setSelectedFile(new java.io.File("report.csv"));
            int result = fileChooser.showSaveDialog(this);

            if (result != javax.swing.JFileChooser.APPROVE_OPTION) 
            {
                return;
            }

            java.io.File file = fileChooser.getSelectedFile();

            try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(file))) 
            {
                javax.swing.table.TableModel model = tbl_Reports.getModel();

                // header row
                StringBuilder header = new StringBuilder();
                for (int col = 0; col < model.getColumnCount(); col++) 
                {
                    header.append(model.getColumnName(col));
                    if (col < model.getColumnCount() - 1) header.append(",");
                }
                writer.println(header);

                // data rows
                for (int row = 0; row < model.getRowCount(); row++) 
                {
                    StringBuilder line = new StringBuilder();
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Object value = model.getValueAt(row, col);
                        line.append(value == null ? "" : value.toString());
                        if (col < model.getColumnCount() - 1) line.append(",");
                    }
                    writer.println(line);
                }

                javax.swing.JOptionPane.showMessageDialog(this, "Exported to " + file.getName());

            } 
            catch (java.io.IOException ex) 
            {
                javax.swing.JOptionPane.showMessageDialog(this,"Could not export: " + ex.getMessage(),"Export Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        
    }//GEN-LAST:event_btn_CSVActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Materials;
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenu Mbar_Suppliers;
    private javax.swing.JMenu Mbar_reports;
    private javax.swing.JButton btn_CSV;
    private javax.swing.JButton btn_Print;
    private javax.swing.JComboBox<String> cb_ReportType;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_Reports;
    // End of variables declaration//GEN-END:variables
}
