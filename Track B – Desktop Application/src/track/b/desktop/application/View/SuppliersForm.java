/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package track.b.desktop.application.View;

import track.b.desktop.application.Controller.SupplierController;
import track.b.desktop.application.Model.Supplier;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author louis
 */
public class SuppliersForm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SuppliersForm.class.getName());

    private final SupplierController supplierController = new SupplierController();

    // -1 means "no selection" -> btnUpdate will ADD a new supplier instead of updating one.
    private int selectedSupplierId = -1;

    /**
     * Creates new form MaterialManagementForm
     */
    public SuppliersForm() {
        initComponents();

        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        btnClear.addActionListener(this::btnClearActionPerformed);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        tblSuppliers.getSelectionModel().addListSelectionListener(this::tblSuppliersRowSelected);

        Mbar_Dashboard.addActionListener(e -> {
            // Dashboard requires a UserController from the login session;
            // full navigation wiring needs the session passed through from LoginForm.
        });
        Mbar_Cleaners.addActionListener(e -> {
            new CleanerMagementFrom().setVisible(true);
            this.dispose();
        });
        Mbar_Material.addActionListener(e -> {
            new MaterialManagementForm().setVisible(true);
            this.dispose();
        });
        Mbar_Reports.addActionListener(e -> {
            // Reports form not built yet
        });
        Mbar_Logout.addActionListener(e -> {
            new LoginForm().setVisible(true);
            this.dispose();
        });

        refreshTable(supplierController.getAllSuppliers());
    }

    // ------------------------------------------------------------------
    // Data loading / table helpers
    // ------------------------------------------------------------------

    private void refreshTable(ArrayList<Supplier> suppliers) {
        DefaultTableModel model = (DefaultTableModel) tblSuppliers.getModel();
        model.setRowCount(0);

        for (Supplier supplier : suppliers) {
            model.addRow(new Object[]{
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getContactPerson(),
                supplier.getEmail(),
                supplier.getPhoneNumber()
            });
        }
    }

    private void clearFields() {
        textCompanyName.setText("");
        textSurname.setText("");
        textSupplierEmail.setText("");
        textCellNumber.setText("");
        selectedSupplierId = -1;
        tblSuppliers.clearSelection();
    }

    // ------------------------------------------------------------------
    // Event handlers
    // ------------------------------------------------------------------

    private void tblSuppliersRowSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }

        int row = tblSuppliers.getSelectedRow();
        if (row == -1) {
            return;
        }

        selectedSupplierId = (int) tblSuppliers.getValueAt(row, 0);
        Supplier supplier = supplierController.findSupplierById(selectedSupplierId);

        if (supplier == null) {
            return;
        }

        textCompanyName.setText(supplier.getSupplierName());
        textSurname.setText(supplier.getContactPerson());
        textSupplierEmail.setText(supplier.getEmail());
        textCellNumber.setText(supplier.getPhoneNumber());
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        String supplierName = textCompanyName.getText();
        String contactPerson = textSurname.getText();
        String email = textSupplierEmail.getText();
        String phoneNumber = textCellNumber.getText();

        // TODO: this form has no fields for Street Address, City or Postal Code,
        // but SupplierController.addSupplier()/updateSupplier() require them.
        // Add three more label+textfield pairs in the GUI Builder, then read
        // their values here instead of these placeholders.
        String streetAddress = "N/A";
        String city = "N/A";
        String postalCode = "N/A";

        try {
            if (selectedSupplierId == -1) {
                supplierController.addSupplier(supplierName, contactPerson, phoneNumber, email, streetAddress, city, postalCode);
                javax.swing.JOptionPane.showMessageDialog(this, "Supplier added.");
            } else {
                supplierController.updateSupplier(selectedSupplierId, supplierName, contactPerson, phoneNumber, email, streetAddress, city, postalCode);
                javax.swing.JOptionPane.showMessageDialog(this, "Supplier updated.");
            }

            clearFields();
            refreshTable(supplierController.getAllSuppliers());

        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Could Not Save Supplier",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String searchText = javax.swing.JOptionPane.showInputDialog(
                this,
                "Search by ID, company name, contact person or email:",
                "Search Suppliers",
                javax.swing.JOptionPane.PLAIN_MESSAGE
        );

        if (searchText == null) {
            return; // user cancelled
        }

        refreshTable(supplierController.searchSuppliers(searchText));
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
        refreshTable(supplierController.getAllSuppliers());
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedSupplierId == -1) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Select a supplier in the table first.",
                    "No Selection",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Delete this supplier? This cannot be undone.\n"
                + "Note: any materials linked to this supplier must be removed first.",
                "Confirm Delete",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        try {
            supplierController.deleteSupplier(selectedSupplierId);
            clearFields();
            refreshTable(supplierController.getAllSuppliers());
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Could not delete supplier. It is likely still linked to one or more materials.",
                    "Delete Failed",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        textCompanyName = new javax.swing.JTextField();
        textSurname = new javax.swing.JTextField();
        textSupplierEmail = new javax.swing.JTextField();
        lblCellNumber = new javax.swing.JLabel();
        textCellNumber = new javax.swing.JTextField();
        MenuBar_Suppliers = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSuppliers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Company Name", "Name of Contact", "Email", "Cell Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanel_Suppliers.setViewportView(tblSuppliers);
        tblSuppliers.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl_TableName.setText("Suppliers Table");

        lblTitel.setText("Supplier Details:");

        lblCompanyName.setText("Company Name:");

        lblNameOfContact.setText("Name Of Contact:");

        lblEmail.setText("Email:");

        btnUpdate.setText("Update");

        btnSearch.setText("Search");

        btnClear.setText("Clear");

        btnDelete.setText("Delete");

        lblCellNumber.setText("Cell Number:");

        Mbar_Dashboard.setText("Dashboard");
        MenuBar_Suppliers.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        MenuBar_Suppliers.add(Mbar_Cleaners);

        Mbar_Material.setText("Materials");
        MenuBar_Suppliers.add(Mbar_Material);

        Mbar_Reports.setText("Reports");
        MenuBar_Suppliers.add(Mbar_Reports);

        Mbar_Logout.setText("Logout");
        MenuBar_Suppliers.add(Mbar_Logout);

        setJMenuBar(MenuBar_Suppliers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrollPanel_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbl_TableName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblNameOfContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblCompanyName)
                                    .addComponent(lblCellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textCellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textSupplierEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_TableName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanel_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompanyName)
                    .addComponent(textCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNameOfContact)
                    .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(textSupplierEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCellNumber)
                    .addComponent(textCellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SuppliersForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Material;
    private javax.swing.JMenu Mbar_Reports;
    private javax.swing.JMenuBar MenuBar_Suppliers;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblCellNumber;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNameOfContact;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lbl_TableName;
    private javax.swing.JScrollPane scrollPanel_Suppliers;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField textCellNumber;
    private javax.swing.JTextField textCompanyName;
    private javax.swing.JTextField textSupplierEmail;
    private javax.swing.JTextField textSurname;
    // End of variables declaration//GEN-END:variables
}
