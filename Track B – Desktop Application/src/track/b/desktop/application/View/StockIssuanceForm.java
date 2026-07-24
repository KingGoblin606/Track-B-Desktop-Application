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
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png")).getImage());
        setLocationRelativeTo(null);

        jMenuBar1.add(javax.swing.Box.createHorizontalGlue(), 2);
        jMenuBar1.add(javax.swing.Box.createHorizontalGlue(), 9);
        
        tbl_History.getTableHeader().setBackground(new java.awt.Color(23, 34, 45));
        tbl_History.getTableHeader().setForeground(new java.awt.Color(140, 160, 175));
        tbl_History.getTableHeader().setFont(new java.awt.Font("Consolas", 0, 18));

        tbl_History.setBackground(new java.awt.Color(18, 28, 39));
        jScrollPane1.getViewport().setBackground(new java.awt.Color(18, 28, 39));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        
        
        loadMaterialsIntoComboBox();
        loadCleanersIntoComboBox();
        updateAvailableStockLabel();
        
        tf_quantity.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() 
        {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateAvailableStockLabel(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateAvailableStockLabel(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateAvailableStockLabel(); }
        });
        
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

            if (material == null) 
            {
                lbl_AvailableStock.setText(" 0");
                pb_CurrentStock.setValue(0);
                pb_Requested.setValue(0);
                pb_StockPreview.setValue(0);
                return;
            }

            int available = material.getQuantity();
            int reorderLevel = Math.max(material.getReorderLevel(), 1);

            int entered;
            try 
            {
                entered = Integer.parseInt(tf_quantity.getText().trim());
            } 
            catch (NumberFormatException ex) 
            {
                entered = 0;
            }

            int remaining = available - entered;
            lbl_AvailableStock.setText(" " + remaining);


            int healthyMax = reorderLevel * 3;

            // Bar 1: Current Stock, before this transaction
            pb_CurrentStock.setMaximum(healthyMax);
            pb_CurrentStock.setValue(Math.min(available, healthyMax));
            pb_CurrentStock.setForeground(stockColor(available, reorderLevel));

            // Bar 2: Requested, how much of current stock this transaction uses
            pb_Requested.setMaximum(available == 0 ? 1 : available);
            pb_Requested.setValue(Math.min(entered, available == 0 ? 1 : available));
            pb_Requested.setForeground(entered > available ? new java.awt.Color(255, 92, 122): new java.awt.Color(139, 124, 246));

            // Bar 3: Remaining After Issue, same scale as Bar 1 so they're directly comparable
            pb_StockPreview.setMaximum(healthyMax);
            pb_StockPreview.setValue(Math.max(Math.min(remaining, healthyMax), 0));
            pb_StockPreview.setForeground(stockColor(remaining, reorderLevel));

            lbl_AvailableStock.setForeground(stockColor(remaining, reorderLevel));
        } 
        catch (IllegalArgumentException ex) 
        {
            lbl_AvailableStock.setText(" 0");
            pb_CurrentStock.setValue(0);
            pb_Requested.setValue(0);
            pb_StockPreview.setValue(0);
        }
    }

    private java.awt.Color stockColor(int quantity, int reorderLevel) 
    {
        if (quantity <= 0) 
        {
            return new java.awt.Color(255, 92, 122);
        } 
        else if (quantity <= reorderLevel) 
        {
            return new java.awt.Color(255, 176, 32);
        } 
        else 
        {
            return new java.awt.Color(23, 232, 200);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_History = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tf_quantity = new javax.swing.JTextField();
        cb_material = new javax.swing.JComboBox<>();
        cb_cleaner = new javax.swing.JComboBox<>();
        lbl_AvailableStock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_Submit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pb_StockPreview = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pb_Requested = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        pb_CurrentStock = new javax.swing.JProgressBar();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(11, 18, 26));

        tbl_History.setBackground(new java.awt.Color(18, 28, 39));
        tbl_History.setForeground(new java.awt.Color(231, 238, 243));
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
        tbl_History.setGridColor(new java.awt.Color(36, 51, 63));
        tbl_History.setRowHeight(36);
        tbl_History.setSelectionBackground(new java.awt.Color(29, 42, 54));
        tbl_History.setSelectionForeground(new java.awt.Color(23, 232, 200));
        jScrollPane1.setViewportView(tbl_History);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(140, 160, 175));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Stock Issuance History");

        jPanel1.setBackground(new java.awt.Color(18, 28, 39));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));

        tf_quantity.setBackground(new java.awt.Color(29, 42, 54));
        tf_quantity.setForeground(new java.awt.Color(231, 238, 243));
        tf_quantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(36, 51, 63), 2, true));

        cb_material.setBackground(new java.awt.Color(29, 42, 54));
        cb_material.setForeground(new java.awt.Color(231, 238, 243));
        cb_material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_material.addActionListener(this::cb_materialActionPerformed);

        cb_cleaner.setBackground(new java.awt.Color(29, 42, 54));
        cb_cleaner.setForeground(new java.awt.Color(231, 238, 243));
        cb_cleaner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_AvailableStock.setBackground(new java.awt.Color(29, 42, 54));
        lbl_AvailableStock.setForeground(new java.awt.Color(231, 238, 243));
        lbl_AvailableStock.setText("0");
        lbl_AvailableStock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(36, 51, 63), 2, true));
        lbl_AvailableStock.setOpaque(true);

        jLabel3.setForeground(new java.awt.Color(140, 160, 175));
        jLabel3.setText("Stock:");

        jLabel2.setForeground(new java.awt.Color(140, 160, 175));
        jLabel2.setText("Cleaner:");

        jLabel1.setForeground(new java.awt.Color(140, 160, 175));
        jLabel1.setText("Material:");

        jLabel5.setForeground(new java.awt.Color(140, 160, 175));
        jLabel5.setText("Quantity:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(231, 238, 243));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Issue Stock");

        btn_Submit.setBackground(new java.awt.Color(23, 232, 200));
        btn_Submit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Submit.setForeground(new java.awt.Color(11, 18, 26));
        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(this::btn_SubmitActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_material, javax.swing.GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE)
                                .addComponent(tf_quantity, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_AvailableStock, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_cleaner, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_Submit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_material, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cleaner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_AvailableStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(18, 28, 39));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        jPanel2.setPreferredSize(new java.awt.Dimension(598, 0));

        pb_StockPreview.setBackground(new java.awt.Color(36, 51, 63));

        jLabel7.setForeground(new java.awt.Color(140, 160, 175));
        jLabel7.setText("CURRENT STOCK");

        jLabel8.setForeground(new java.awt.Color(140, 160, 175));
        jLabel8.setText("REQUESTED STOCK");

        pb_Requested.setBackground(new java.awt.Color(36, 51, 63));

        jLabel9.setForeground(new java.awt.Color(140, 160, 175));
        jLabel9.setText("REMAINING AFTER ISSUE");

        pb_CurrentStock.setBackground(new java.awt.Color(36, 51, 63));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pb_CurrentStock, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addComponent(pb_Requested, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pb_StockPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pb_CurrentStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pb_Requested, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pb_StockPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jMenuBar1.setBackground(new java.awt.Color(18, 28, 39));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(651, 52));

        jMenu1.setForeground(new java.awt.Color(23, 232, 200));
        jMenu1.setText("●");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        Mbar_Stock.setForeground(new java.awt.Color(23, 232, 200));
        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuBar1.add(Mbar_Stock);

        Mbar_reports.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_reports.setText("Reports");
        Mbar_reports.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_reports.addActionListener(this::Mbar_reportsActionPerformed);
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
    private javax.swing.JPanel MainPanel;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_AvailableStock;
    private javax.swing.JProgressBar pb_CurrentStock;
    private javax.swing.JProgressBar pb_Requested;
    private javax.swing.JProgressBar pb_StockPreview;
    private javax.swing.JTable tbl_History;
    private javax.swing.JTextField tf_quantity;
    // End of variables declaration//GEN-END:variables
}
