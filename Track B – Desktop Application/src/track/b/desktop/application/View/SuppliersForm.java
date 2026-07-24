package track.b.desktop.application.View;


public class SuppliersForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SuppliersForm.class.getName());
    
    private final track.b.desktop.application.Controller.UserController userController;
    private final track.b.desktop.application.Controller.SupplierController supplierController = new track.b.desktop.application.Controller.SupplierController();

    public SuppliersForm(track.b.desktop.application.Controller.UserController userController) 
    {
        this.userController = userController;
        initComponents();
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png")).getImage());
        setLocationRelativeTo(null);
        
        MenuBar_Suppliers.add(javax.swing.Box.createHorizontalGlue(), 2);
        MenuBar_Suppliers.add(javax.swing.Box.createHorizontalGlue(), 9);
        
        tblSuppliers.getTableHeader().setBackground(new java.awt.Color(23, 34, 45));
        tblSuppliers.getTableHeader().setForeground(new java.awt.Color(140, 160, 175));
        tblSuppliers.getTableHeader().setFont(new java.awt.Font("Consolas", 0, 18));

        tblSuppliers.setBackground(new java.awt.Color(18, 28, 39));
        scrollPanel_Suppliers.getViewport().setBackground(new java.awt.Color(18, 28, 39));
        scrollPanel_Suppliers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        
        
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

        MainPanel = new javax.swing.JPanel();
        scrollPanel_Suppliers = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        lbl_TableName = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTitel = new javax.swing.JLabel();
        tb_CompanyName = new javax.swing.JTextField();
        lblCompanyName = new javax.swing.JLabel();
        lblNameOfContact = new javax.swing.JLabel();
        tb_nameOfContact = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        tb_Email = new javax.swing.JTextField();
        lblCellNumber = new javax.swing.JLabel();
        tb_CellNumber = new javax.swing.JTextField();
        tb_address = new javax.swing.JTextField();
        lblCellNumber2 = new javax.swing.JLabel();
        tb_City = new javax.swing.JTextField();
        lblCellNumber3 = new javax.swing.JLabel();
        tf_postalCode = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblCellNumber1 = new javax.swing.JLabel();
        MenuBar_Suppliers = new javax.swing.JMenuBar();
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

        tblSuppliers.setBackground(new java.awt.Color(18, 28, 39));
        tblSuppliers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSuppliers.setForeground(new java.awt.Color(231, 238, 243));
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
        tblSuppliers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSuppliers.setGridColor(new java.awt.Color(36, 51, 63));
        tblSuppliers.setRowHeight(36);
        tblSuppliers.setSelectionBackground(new java.awt.Color(29, 42, 54));
        tblSuppliers.setSelectionForeground(new java.awt.Color(23, 232, 200));
        scrollPanel_Suppliers.setViewportView(tblSuppliers);
        tblSuppliers.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbl_TableName.setForeground(new java.awt.Color(140, 160, 175));
        lbl_TableName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_TableName.setText("Suppliers");

        jPanel1.setBackground(new java.awt.Color(18, 28, 39));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(36, 51, 63), 2, true));

        lblTitel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitel.setForeground(new java.awt.Color(231, 238, 243));
        lblTitel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitel.setText("Supplier Details");

        tb_CompanyName.setBackground(new java.awt.Color(29, 42, 54));
        tb_CompanyName.setForeground(new java.awt.Color(231, 238, 243));
        tb_CompanyName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));

        lblCompanyName.setForeground(new java.awt.Color(140, 160, 175));
        lblCompanyName.setText("Company Name:");

        lblNameOfContact.setForeground(new java.awt.Color(140, 160, 175));
        lblNameOfContact.setText("Name Of Contact:");

        tb_nameOfContact.setBackground(new java.awt.Color(29, 42, 54));
        tb_nameOfContact.setForeground(new java.awt.Color(231, 238, 243));
        tb_nameOfContact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_nameOfContact.addActionListener(this::tb_nameOfContactActionPerformed);

        lblEmail.setForeground(new java.awt.Color(140, 160, 175));
        lblEmail.setText("Email:");

        tb_Email.setBackground(new java.awt.Color(29, 42, 54));
        tb_Email.setForeground(new java.awt.Color(231, 238, 243));
        tb_Email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_Email.addActionListener(this::tb_EmailActionPerformed);

        lblCellNumber.setForeground(new java.awt.Color(140, 160, 175));
        lblCellNumber.setText("Cell Number:");

        tb_CellNumber.setBackground(new java.awt.Color(29, 42, 54));
        tb_CellNumber.setForeground(new java.awt.Color(231, 238, 243));
        tb_CellNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_CellNumber.addActionListener(this::tb_CellNumberActionPerformed);

        tb_address.setBackground(new java.awt.Color(29, 42, 54));
        tb_address.setForeground(new java.awt.Color(231, 238, 243));
        tb_address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_address.addActionListener(this::tb_addressActionPerformed);

        lblCellNumber2.setForeground(new java.awt.Color(140, 160, 175));
        lblCellNumber2.setText("City:");

        tb_City.setBackground(new java.awt.Color(29, 42, 54));
        tb_City.setForeground(new java.awt.Color(231, 238, 243));
        tb_City.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        tb_City.addActionListener(this::tb_CityActionPerformed);

        lblCellNumber3.setForeground(new java.awt.Color(140, 160, 175));
        lblCellNumber3.setText("Postal Code:");

        tf_postalCode.setBackground(new java.awt.Color(29, 42, 54));
        tf_postalCode.setForeground(new java.awt.Color(231, 238, 243));
        tf_postalCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));

        btnAdd.setBackground(new java.awt.Color(23, 232, 200));
        btnAdd.setForeground(new java.awt.Color(11, 18, 26));
        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(29, 42, 54));
        btnUpdate.setForeground(new java.awt.Color(231, 238, 243));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnSearch.setForeground(new java.awt.Color(140, 160, 175));
        btnSearch.setText("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        btnSearch.setContentAreaFilled(false);
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setForeground(new java.awt.Color(140, 160, 175));
        btnClear.setText("Clear");
        btnClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 51, 63)));
        btnClear.setContentAreaFilled(false);
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnDelete.setForeground(new java.awt.Color(255, 92, 122));
        btnDelete.setText("Delete");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 92, 122)));
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        lblCellNumber1.setForeground(new java.awt.Color(140, 160, 175));
        lblCellNumber1.setText("Street Address:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tf_postalCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(tb_address, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCellNumber1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCellNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tb_City, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tb_CompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCompanyName))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNameOfContact)
                                .addComponent(tb_nameOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(tb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tb_CellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblTitel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCellNumber3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitel)
                        .addGap(240, 240, 240))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNameOfContact)
                                .addComponent(lblCompanyName))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tb_CompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tb_nameOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(lblCellNumber))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tb_CellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCellNumber1)
                                .addComponent(lblCellNumber2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tb_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tb_City, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCellNumber3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPanel_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TableName)))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lbl_TableName)
                .addGap(18, 18, 18)
                .addComponent(scrollPanel_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        MenuBar_Suppliers.setBackground(new java.awt.Color(18, 28, 39));
        MenuBar_Suppliers.setPreferredSize(new java.awt.Dimension(596, 52));

        jMenu1.setForeground(new java.awt.Color(23, 232, 200));
        jMenu1.setText("●");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBar_Suppliers.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("CampusClean OS");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBar_Suppliers.add(jMenu2);

        Mbar_Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Dashboard.setText("Dashboard");
        Mbar_Dashboard.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Dashboard.addActionListener(this::Mbar_DashboardActionPerformed);
        MenuBar_Suppliers.add(Mbar_Dashboard);

        Mbar_Cleaners.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Cleaners.setText("Cleaners");
        Mbar_Cleaners.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Cleaners.addActionListener(this::Mbar_CleanersActionPerformed);
        MenuBar_Suppliers.add(Mbar_Cleaners);

        Mbar_Material.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Material.setText("Materials");
        Mbar_Material.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Material.addActionListener(this::Mbar_MaterialActionPerformed);
        MenuBar_Suppliers.add(Mbar_Material);

        btn_M_Suppliers.setForeground(new java.awt.Color(23, 232, 200));
        btn_M_Suppliers.setText("Suppliers");
        btn_M_Suppliers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_M_Suppliers.addActionListener(this::btn_M_SuppliersActionPerformed);
        MenuBar_Suppliers.add(btn_M_Suppliers);

        Mbar_Stock.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Stock.setText("Stock Issuance");
        Mbar_Stock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Stock.addActionListener(this::Mbar_StockActionPerformed);
        MenuBar_Suppliers.add(Mbar_Stock);

        Mbar_Reports.setForeground(new java.awt.Color(255, 255, 255));
        Mbar_Reports.setText("Reports");
        Mbar_Reports.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Reports.addActionListener(this::Mbar_ReportsActionPerformed);
        MenuBar_Suppliers.add(Mbar_Reports);

        Mbar_Logout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 16));
        Mbar_Logout.setForeground(new java.awt.Color(255, 92, 122));
        Mbar_Logout.setText("Logout");
        Mbar_Logout.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Mbar_Logout.addActionListener(this::Mbar_LogoutActionPerformed);
        MenuBar_Suppliers.add(Mbar_Logout);

        setJMenuBar(MenuBar_Suppliers);

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
    private javax.swing.JPanel MainPanel;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
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
