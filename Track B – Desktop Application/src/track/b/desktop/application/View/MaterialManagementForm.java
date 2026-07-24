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
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png")).getImage());
        setLocationRelativeTo(null);
        
        MenuBar_Material.add(javax.swing.Box.createHorizontalGlue(), 2);
        MenuBar_Material.add(javax.swing.Box.createHorizontalGlue(), 9);
        
        tblMaterial.getTableHeader().setBackground(new java.awt.Color(23, 34, 45));
        tblMaterial.getTableHeader().setForeground(new java.awt.Color(140, 160, 175));
        tblMaterial.getTableHeader().setFont(new java.awt.Font("Consolas", 0, 11));

        tblMaterial.setBackground(new java.awt.Color(18, 28, 39));
        scrollPanel_Material.getViewport().setBackground(new java.awt.Color(18, 28, 39));
        scrollPanel_Material.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        
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

        MainPanel = new javax.swing.JPanel();
        scrollPanel_Material = new javax.swing.JScrollPane();
        tblMaterial = new javax.swing.JTable();
        lbl_TableName = new javax.swing.JLabel();
        lbl_Filter = new javax.swing.JLabel();
        comboBox_Material = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        tb_Cost = new javax.swing.JTextField();
        lblSupplier1 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        lblSupplier = new javax.swing.JLabel();
        Cb_Supplier = new javax.swing.JComboBox<>();
        tb_Reorder = new javax.swing.JTextField();
        lblReorder = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        tb_quantity = new javax.swing.JTextField();
        tb_category = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        lblNames = new javax.swing.JLabel();
        tb_names = new javax.swing.JTextField();
        lblTitel = new javax.swing.JLabel();
        MenuBar_Material = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        btn_M_Suppliers = new javax.swing.JMenu();
        Mbar_Stock = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(11, 18, 26));

        tblMaterial.setBackground(new java.awt.Color(18, 28, 39));
        tblMaterial.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblMaterial.setForeground(new java.awt.Color(231, 238, 243));
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
        tblMaterial.setGridColor(new java.awt.Color(36, 51, 63));
        tblMaterial.setRowHeight(36);
        tblMaterial.setSelectionBackground(new java.awt.Color(29, 42, 54));
        scrollPanel_Material.setViewportView(tblMaterial);
        tblMaterial.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbl_TableName.setForeground(new java.awt.Color(140, 160, 175));
        lbl_TableName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_TableName.setText("Materials");

        lbl_Filter.setForeground(new java.awt.Color(140, 160, 175));
        lbl_Filter.setText("FILTER BY CATEGORY");

        comboBox_Material.setBackground(new java.awt.Color(29, 42, 54));
        comboBox_Material.setForeground(new java.awt.Color(231, 238, 243));
        comboBox_Material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox_Material.addActionListener(this::comboBox_MaterialActionPerformed);

        jPanel1.setBackground(new java.awt.Color(18, 28, 39));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63), 2));

        btnDelete.setForeground(new java.awt.Color(255, 92, 122));
        btnDelete.setText("Delete");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 92, 122)));
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        tb_Cost.setBackground(new java.awt.Color(29, 42, 54));
        tb_Cost.setForeground(new java.awt.Color(231, 238, 243));
        tb_Cost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));

        lblSupplier1.setForeground(new java.awt.Color(140, 160, 175));
        lblSupplier1.setText("Cost:");

        btnClear.setForeground(new java.awt.Color(140, 160, 175));
        btnClear.setText("Clear");
        btnClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        btnClear.setContentAreaFilled(false);
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnSearch.setForeground(new java.awt.Color(140, 160, 175));
        btnSearch.setText("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        btnSearch.setContentAreaFilled(false);
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(29, 42, 54));
        btnUpdate.setForeground(new java.awt.Color(231, 238, 243));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btn_add.setBackground(new java.awt.Color(23, 232, 200));
        btn_add.setForeground(new java.awt.Color(11, 18, 26));
        btn_add.setText("Add");
        btn_add.addActionListener(this::btn_addActionPerformed);

        lblSupplier.setForeground(new java.awt.Color(140, 160, 175));
        lblSupplier.setText("Supplier:");

        Cb_Supplier.setBackground(new java.awt.Color(29, 42, 54));
        Cb_Supplier.setForeground(new java.awt.Color(231, 238, 243));
        Cb_Supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Cb_Supplier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));

        tb_Reorder.setBackground(new java.awt.Color(29, 42, 54));
        tb_Reorder.setForeground(new java.awt.Color(231, 238, 243));
        tb_Reorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_Reorder.addActionListener(this::tb_ReorderActionPerformed);

        lblReorder.setForeground(new java.awt.Color(140, 160, 175));
        lblReorder.setText("Reorder Level:");

        lblQuantity.setForeground(new java.awt.Color(140, 160, 175));
        lblQuantity.setText("Quantity:");

        tb_quantity.setBackground(new java.awt.Color(29, 42, 54));
        tb_quantity.setForeground(new java.awt.Color(231, 238, 243));
        tb_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_quantity.addActionListener(this::tb_quantityActionPerformed);

        tb_category.setBackground(new java.awt.Color(29, 42, 54));
        tb_category.setForeground(new java.awt.Color(231, 238, 243));
        tb_category.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_category.addActionListener(this::tb_categoryActionPerformed);

        lblCategory.setForeground(new java.awt.Color(140, 160, 175));
        lblCategory.setText("Category:");

        lblNames.setForeground(new java.awt.Color(140, 160, 175));
        lblNames.setText("Names:");

        tb_names.setBackground(new java.awt.Color(29, 42, 54));
        tb_names.setForeground(new java.awt.Color(231, 238, 243));
        tb_names.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));

        lblTitel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitel.setForeground(new java.awt.Color(231, 238, 243));
        lblTitel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitel.setText("Material Details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Cb_Supplier, javax.swing.GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE)
                                        .addComponent(tb_quantity, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tb_names, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNames))
                                .addGap(112, 112, 112)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tb_category, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tb_Reorder)
                                    .addComponent(tb_Cost)
                                    .addComponent(lblSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblReorder)))
                            .addComponent(lblTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNames)
                    .addComponent(lblCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_names, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_category, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(lblReorder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_Reorder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplier)
                    .addComponent(lblSupplier1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_Cost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(lbl_TableName, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBox_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_Filter)))
                            .addComponent(scrollPanel_Material, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_TableName)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(lbl_Filter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBox_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(scrollPanel_Material, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        comboBox_Material.getAccessibleContext().setAccessibleName("cmbMaterial");
        comboBox_Material.getAccessibleContext().setAccessibleDescription("");

        MenuBar_Material.setBackground(new java.awt.Color(18, 28, 39));
        MenuBar_Material.setPreferredSize(new java.awt.Dimension(465, 52));

        jMenu1.setForeground(new java.awt.Color(23, 232, 200));
        jMenu1.setText("●");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBar_Material.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("CampusClean OS");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBar_Material.add(jMenu2);

        Mbar_Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        MenuBar_Material.add(Mbar_Dashboard);

        Mbar_Cleaners.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Cleaners.setText("Cleaners");
        Mbar_Cleaners.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Cleaners.addActionListener(this::Mbar_CleanersActionPerformed);
        MenuBar_Material.add(Mbar_Cleaners);

        Mbar_Material.setForeground(new java.awt.Color(23, 232, 200));
        Mbar_Material.setText("Materials");
        Mbar_Material.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        MenuBar_Material.add(Mbar_Material);

        btn_M_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        MenuBar_Material.add(btn_M_Suppliers);

        Mbar_Stock.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        MenuBar_Material.add(Mbar_Stock);

        Mbar_Reports.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Reports.setText("Reports");
        Mbar_Reports.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Reports.addActionListener(this::Mbar_ReportsActionPerformed);
        MenuBar_Material.add(Mbar_Reports);

        Mbar_Logout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 16));
        Mbar_Logout.setForeground(new java.awt.Color(255, 92, 122));
        Mbar_Logout.setText("Logout");
        Mbar_Logout.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        MenuBar_Material.add(Mbar_Logout);

        setJMenuBar(MenuBar_Material);

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
    private javax.swing.JPanel MainPanel;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
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
