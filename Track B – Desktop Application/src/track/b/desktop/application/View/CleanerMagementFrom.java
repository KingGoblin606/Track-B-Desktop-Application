/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package track.b.desktop.application.View;

import track.b.desktop.application.Controller.CleanerController;
import track.b.desktop.application.Controller.DepartmentController;
import track.b.desktop.application.Model.Cleaner;
import track.b.desktop.application.Model.Department;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author louis
 */
public class CleanerMagementFrom extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CleanerMagementFrom.class.getName());

    private final CleanerController cleanerController = new CleanerController();
    private final DepartmentController departmentController = new DepartmentController();

    // -1 means "no selection" -> btnUpdate will ADD a new cleaner instead of updating one.
    private int selectedCleanerId = -1;

    /**
     * Creates new form MaterialManagementForm
     */
    public CleanerMagementFrom() {
        initComponents();

        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        btnClear.addActionListener(this::btnClearActionPerformed);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        tblCleaners.getSelectionModel().addListSelectionListener(this::tblCleanersRowSelected);

        Mbar_Dashboard.addActionListener(e -> {
            // Dashboard requires a UserController from the login session;
            // full navigation wiring needs the session passed through from LoginForm.
        });
        Mbar_Cleaners.addActionListener(e -> { /* already here */ });
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

        refreshTable(cleanerController.getAllCleaners());
    }

    // ------------------------------------------------------------------
    // Data loading / table helpers
    // ------------------------------------------------------------------

    private void refreshTable(ArrayList<Cleaner> cleaners) {
        DefaultTableModel model = (DefaultTableModel) tblCleaners.getModel();
        model.setRowCount(0);

        for (Cleaner cleaner : cleaners) {
            model.addRow(new Object[]{
                cleaner.getCleanerId(),
                cleaner.getName(),
                cleaner.getSurname(),
                cleaner.getDepartmentName() // may be null/blank if no department assigned
            });
        }
    }

    private void clearFields() {
        textNames.setText("");
        textSurname.setText("");
        textDepartment.setText("");
        selectedCleanerId = -1;
        tblCleaners.clearSelection();
    }

    // Looks up a department by name (case-insensitive). If it doesn't exist
    // yet and a name was typed, creates it. Returns null if the field was left blank.
    private Integer resolveDepartmentId(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            return null;
        }

        String trimmed = departmentName.trim();

        for (Department department : departmentController.getAllDepartments()) {
            if (department.getDepartmentName().equalsIgnoreCase(trimmed)) {
                return department.getDepartmentId();
            }
        }

        // Department doesn't exist yet - create it automatically
        Department newDepartment = departmentController.addDepartment(trimmed);
        return newDepartment.getDepartmentId();
    }

    // ------------------------------------------------------------------
    // Event handlers
    // ------------------------------------------------------------------

    private void tblCleanersRowSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }

        int row = tblCleaners.getSelectedRow();
        if (row == -1) {
            return;
        }

        selectedCleanerId = (int) tblCleaners.getValueAt(row, 0);
        Cleaner cleaner = cleanerController.findCleanerById(selectedCleanerId);

        if (cleaner == null) {
            return;
        }

        textNames.setText(cleaner.getName());
        textSurname.setText(cleaner.getSurname());
        textDepartment.setText(cleaner.getDepartmentName() != null ? cleaner.getDepartmentName() : "");
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        String name = textNames.getText();
        String surname = textSurname.getText();

        // TODO: this form has no field for Phone Number or Email, but
        // CleanerController.addCleaner()/updateCleaner() require both.
        // Add label+textfield pairs for Phone Number and Email in the
        // GUI Builder, then read their values here instead of these placeholders.
        String phoneNumber = "N/A";
        String email = "cleaner" + System.currentTimeMillis() + "@placeholder.com";

        Integer departmentId;
        try {
            departmentId = resolveDepartmentId(textDepartment.getText());
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Invalid Department",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            if (selectedCleanerId == -1) {
                cleanerController.addCleaner(name, surname, phoneNumber, email, departmentId);
                javax.swing.JOptionPane.showMessageDialog(this, "Cleaner added.");
            } else {
                cleanerController.updateCleaner(selectedCleanerId, name, surname, phoneNumber, email, departmentId);
                javax.swing.JOptionPane.showMessageDialog(this, "Cleaner updated.");
            }

            clearFields();
            refreshTable(cleanerController.getAllCleaners());

        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Could Not Save Cleaner",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String searchText = javax.swing.JOptionPane.showInputDialog(
                this,
                "Search by ID, name, surname or department:",
                "Search Cleaners",
                javax.swing.JOptionPane.PLAIN_MESSAGE
        );

        if (searchText == null) {
            return; // user cancelled
        }

        refreshTable(cleanerController.searchCleaners(searchText));
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
        refreshTable(cleanerController.getAllCleaners());
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedCleanerId == -1) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Select a cleaner in the table first.",
                    "No Selection",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Delete this cleaner? This cannot be undone.\n"
                + "Note: any stock issuance records for this cleaner must be removed first.",
                "Confirm Delete",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        try {
            cleanerController.deleteCleaner(selectedCleanerId);
            clearFields();
            refreshTable(cleanerController.getAllCleaners());
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Could not delete cleaner. They likely still have stock issuance records linked to them.",
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
        textDepartment = new javax.swing.JTextField();
        MenuBar_Cleaners = new javax.swing.JMenuBar();
        Mbar_Dashboard = new javax.swing.JMenu();
        Mbar_Cleaners = new javax.swing.JMenu();
        Mbar_Material = new javax.swing.JMenu();
        Mbar_Reports = new javax.swing.JMenu();
        Mbar_Logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCleaners.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblCleaners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Surname", "Department"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanel_Cleaners.setViewportView(tblCleaners);
        tblCleaners.getAccessibleContext().setAccessibleName("tblMaterial");

        lbl_TableName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl_TableName.setText("Cleaners Table");

        lblTitel.setText("Cleaner Details:");

        lblNames.setText("Names:");

        lblSurname.setText("Surname:");

        lblDepartment.setText("Deparntment:");

        btnUpdate.setText("Update");

        btnSearch.setText("Search");

        btnClear.setText("Clear");

        btnDelete.setText("Delete");

        Mbar_Dashboard.setText("Dashboard");
        MenuBar_Cleaners.add(Mbar_Dashboard);

        Mbar_Cleaners.setText("Cleaners");
        MenuBar_Cleaners.add(Mbar_Cleaners);

        Mbar_Material.setText("Materials");
        MenuBar_Cleaners.add(Mbar_Material);

        Mbar_Reports.setText("Reports");
        MenuBar_Cleaners.add(Mbar_Reports);

        Mbar_Logout.setText("Logout");
        MenuBar_Cleaners.add(Mbar_Logout);

        setJMenuBar(MenuBar_Cleaners);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrollPanel_Cleaners, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblDepartment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSurname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblNames))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNames, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_TableName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanel_Cleaners, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(textDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnDelete))
                .addContainerGap(16, Short.MAX_VALUE))
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
        java.awt.EventQueue.invokeLater(() -> new CleanerMagementFrom().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Mbar_Cleaners;
    private javax.swing.JMenu Mbar_Dashboard;
    private javax.swing.JMenu Mbar_Logout;
    private javax.swing.JMenu Mbar_Material;
    private javax.swing.JMenu Mbar_Reports;
    private javax.swing.JMenuBar MenuBar_Cleaners;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblNames;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lbl_TableName;
    private javax.swing.JScrollPane scrollPanel_Cleaners;
    private javax.swing.JTable tblCleaners;
    private javax.swing.JTextField textDepartment;
    private javax.swing.JTextField textNames;
    private javax.swing.JTextField textSurname;
    // End of variables declaration//GEN-END:variables
}
