package track.b.desktop.application.View;

public class DashboardForm extends javax.swing.JFrame {
    
    private final track.b.desktop.application.Controller.UserController userController;

    public DashboardForm(track.b.desktop.application.Controller.UserController userController) 
    {
      this.userController = userController;
      initComponents();
      setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png")).getImage());
      setLocationRelativeTo(null);

      
      jMenuBar1.add(javax.swing.Box.createHorizontalGlue(), 2);
      jMenuBar1.add(javax.swing.Box.createHorizontalGlue(), 9);
      
      tbl_RecentIssuances.getTableHeader().setOpaque(true);
      tbl_RecentIssuances.getTableHeader().setBackground(new java.awt.Color(23, 34, 45)); 
      tbl_RecentIssuances.getTableHeader().setForeground(new java.awt.Color(140, 160, 175)); 
      tbl_RecentIssuances.getTableHeader().setFont(new java.awt.Font("Consolas", 0, 18));
      
      tbl_RecentIssuances.setBackground(new java.awt.Color(18, 28, 39)); 
      jScrollPane1.getViewport().setBackground(new java.awt.Color(18, 28, 39)); 
      jScrollPane1.setBackground(new java.awt.Color(18, 28, 39)); 
      jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63))); 
      
      javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

      for (int i = 0; i < tbl_RecentIssuances.getColumnCount(); i++) 
      {
          tbl_RecentIssuances.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
      }
      
      loadDashboardData();
      
      btn_M_Cleaners.addMouseListener(new java.awt.event.MouseAdapter() 
      {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                btn_M_CleanersActionPerformed(null);
            }
        });
        btn_M_Materials.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                btn_M_MaterialsActionPerformed(null);
            }
        });
        btn_M_Suppliers.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                btn_M_SuppliersActionPerformed(null);
            }
        });
        Mbar_Stock.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                Mbar_StockActionPerformed(null);
            }
        });
        btn_M_Reports.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                btn_M_ReportsActionPerformed(null);
            }
        });
        btn_M_Logout.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                btn_M_LogoutActionPerformed(null);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        MainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_RecentIssuances = new javax.swing.JTable();
        lbl_Username = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl_TotalMaterials1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pb_totalMaterials = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        lbl_LowStock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pb_LowStock = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        lblTotalCleaners = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pb_TotalCleaners = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        btn_M_Dashboard = new javax.swing.JMenu();
        btn_M_Cleaners = new javax.swing.JMenu();
        btn_M_Materials = new javax.swing.JMenu();
        btn_M_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        btn_M_Reports = new javax.swing.JMenu();
        btn_M_Logout = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(11, 18, 26));
        setForeground(java.awt.Color.white);
        setResizable(false);

        MainPanel.setBackground(new java.awt.Color(11, 18, 26));

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 160, 175));
        jLabel1.setText("WELCOME BACK");

        tbl_RecentIssuances.setBackground(new java.awt.Color(18, 28, 39));
        tbl_RecentIssuances.setForeground(new java.awt.Color(231, 238, 243));
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
        tbl_RecentIssuances.setGridColor(new java.awt.Color(36, 51, 63));
        tbl_RecentIssuances.setRowHeight(36);
        tbl_RecentIssuances.setSelectionBackground(new java.awt.Color(29, 42, 54));
        tbl_RecentIssuances.setSelectionForeground(new java.awt.Color(23, 232, 200));
        jScrollPane1.setViewportView(tbl_RecentIssuances);

        lbl_Username.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_Username.setForeground(new java.awt.Color(231, 238, 243));
        lbl_Username.setText("{Username}");

        jPanel4.setBackground(new java.awt.Color(11, 18, 26));

        jPanel1.setBackground(new java.awt.Color(18, 28, 39));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(23, 232, 200)));

        lbl_TotalMaterials1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_TotalMaterials1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TotalMaterials1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TotalMaterials1.setText("Total Materials");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(234, 234, 234));
        jLabel2.setText("Total Materials");

        pb_totalMaterials.setBackground(new java.awt.Color(36, 51, 63));
        pb_totalMaterials.setForeground(new java.awt.Color(23, 232, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_TotalMaterials1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(pb_totalMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(lbl_TotalMaterials1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pb_totalMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(18, 28, 39));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 176, 32)));

        lbl_LowStock.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_LowStock.setForeground(new java.awt.Color(255, 255, 255));
        lbl_LowStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_LowStock.setText("Low Stock");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(234, 234, 234));
        jLabel3.setText("Low Stock");

        pb_LowStock.setBackground(new java.awt.Color(36, 51, 63));
        pb_LowStock.setForeground(new java.awt.Color(255, 176, 32));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_LowStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(pb_LowStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(lbl_LowStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pb_LowStock, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(18, 28, 39));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 124, 246)));

        lblTotalCleaners.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalCleaners.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalCleaners.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalCleaners.setText("Total Cleaners");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(234, 234, 234));
        jLabel4.setText("Total Cleaners");

        pb_TotalCleaners.setBackground(new java.awt.Color(36, 51, 63));
        pb_TotalCleaners.setForeground(new java.awt.Color(139, 124, 246));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotalCleaners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(pb_TotalCleaners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(lblTotalCleaners)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pb_TotalCleaners, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2, jPanel3});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel1, jPanel2, jPanel3});

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Recent Issuance");

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Username)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Username)
                .addGap(44, 44, 44)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jMenuBar1.setBackground(new java.awt.Color(18, 28, 39));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(465, 52));

        jMenu1.setForeground(new java.awt.Color(23, 232, 200));
        jMenu1.setText("●");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("CampusClean OS");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu2);

        btn_M_Dashboard.setForeground(new java.awt.Color(23, 232, 200));
        btn_M_Dashboard.setText("Dashboard");
        btn_M_Dashboard.setBorderPainted(false);
        btn_M_Dashboard.setContentAreaFilled(false);
        btn_M_Dashboard.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuBar1.add(btn_M_Dashboard);

        btn_M_Cleaners.setForeground(new java.awt.Color(255, 255, 255));
        btn_M_Cleaners.setText("Cleaners");
        btn_M_Cleaners.setToolTipText("");
        btn_M_Cleaners.setBorderPainted(false);
        btn_M_Cleaners.setContentAreaFilled(false);
        btn_M_Cleaners.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Cleaners.addActionListener(this::btn_M_CleanersActionPerformed);
        jMenuBar1.add(btn_M_Cleaners);

        btn_M_Materials.setForeground(new java.awt.Color(255, 255, 255));
        btn_M_Materials.setText("Materials");
        btn_M_Materials.setBorderPainted(false);
        btn_M_Materials.setContentAreaFilled(false);
        btn_M_Materials.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Materials.addActionListener(this::btn_M_MaterialsActionPerformed);
        jMenuBar1.add(btn_M_Materials);

        btn_M_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.setBorderPainted(false);
        btn_M_Suppliers.setContentAreaFilled(false);
        btn_M_Suppliers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        jMenuBar1.add(btn_M_Suppliers);

        Mbar_Stock.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.setBorderPainted(false);
        Mbar_Stock.setContentAreaFilled(false);
        Mbar_Stock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        jMenuBar1.add(Mbar_Stock);

        btn_M_Reports.setForeground(new java.awt.Color(255, 255, 255));
        btn_M_Reports.setText("Reports");
        btn_M_Reports.setBorderPainted(false);
        btn_M_Reports.setContentAreaFilled(false);
        btn_M_Reports.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Reports.addActionListener(this::btn_M_ReportsActionPerformed);
        jMenuBar1.add(btn_M_Reports);

        btn_M_Logout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 16));
        btn_M_Logout.setForeground(new java.awt.Color(255, 92, 122));
        btn_M_Logout.setText("Logout");
        btn_M_Logout.setBorderPainted(false);
        btn_M_Logout.setContentAreaFilled(false);
        btn_M_Logout.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Logout.addActionListener(this::btn_M_LogoutActionPerformed);
        jMenuBar1.add(btn_M_Logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lbl_Username.setText(userController.getCurrentUser().getUserName());

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

        lbl_TotalMaterials1.setText("" + materials.size());
        lbl_LowStock.setText("" + lowStockCount);
        lblTotalCleaners.setText("" + cleanerController.getAllCleaners().size());
        
        
        int materialTarget = 10;
        pb_totalMaterials.setMaximum(materialTarget);
        pb_totalMaterials.setValue(Math.min(materials.size(), materialTarget));
        
        int totalMaterials = materials.size();
        int lowStockPercent = totalMaterials == 0 ? 0 : (lowStockCount * 100) / totalMaterials;
        pb_LowStock.setMaximum(100);
        pb_LowStock.setValue(lowStockPercent);
        
        int cleanersTarget = 10; 
        int totalCleaners = cleanerController.getAllCleaners().size();
        pb_TotalCleaners.setMaximum(cleanersTarget);
        pb_TotalCleaners.setValue(Math.min(totalCleaners, cleanersTarget));
        

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
    private javax.swing.JPanel MainPanel;
    private javax.swing.JMenu Mbar_Stock;
    private javax.swing.JMenu btn_M_Cleaners;
    private javax.swing.JMenu btn_M_Dashboard;
    private javax.swing.JMenu btn_M_Logout;
    private javax.swing.JMenu btn_M_Materials;
    private javax.swing.JMenu btn_M_Reports;
    private javax.swing.JMenu btn_M_Suppliers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalCleaners;
    private javax.swing.JLabel lbl_LowStock;
    private javax.swing.JLabel lbl_TotalMaterials1;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JProgressBar pb_LowStock;
    private javax.swing.JProgressBar pb_TotalCleaners;
    private javax.swing.JProgressBar pb_totalMaterials;
    private javax.swing.JTable tbl_RecentIssuances;
    // End of variables declaration//GEN-END:variables
}
