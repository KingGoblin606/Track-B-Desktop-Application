package track.b.desktop.application.View;

public class DepartmentDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DepartmentDialog.class.getName());
    private final track.b.desktop.application.Controller.DepartmentController departmentController = new track.b.desktop.application.Controller.DepartmentController();

    public DepartmentDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setupTable();
        loadDepartments();
        setupSelectionListener();
        updateButtonStates(false);
    }
    
    private void setupTable() 
    {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[]{"ID", "Department"}, 0) 
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };
        tbl_Dept.setModel(model);
    }

    private void loadDepartments() 
    {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tbl_Dept.getModel();
        model.setRowCount(0);

        for (track.b.desktop.application.Model.Department department : departmentController.getAllDepartments()) 
        {
            model.addRow(new Object[]
            {
                    department.getDepartmentId(),
                    department.getDepartmentName()
            });
        }
    }

    private void setupSelectionListener() 
    {
        tbl_Dept.getSelectionModel().addListSelectionListener(evt -> 
        {
            if (evt.getValueIsAdjusting()) 
            {
                return;
            }

            int selectedRow = tbl_Dept.getSelectedRow();

            if (selectedRow == -1) 
            {
                updateButtonStates(false);
                return;
            }

            int departmentId = (int) tbl_Dept.getValueAt(selectedRow, 0);
            track.b.desktop.application.Model.Department department = departmentController.findDepartmentById(departmentId);

            if (department == null) 
            {
                updateButtonStates(false);
                return;
            }

            tf_DeptName.setText(department.getDepartmentName());
            updateButtonStates(true);
        });
    }

    private void updateButtonStates(boolean rowSelected) 
    {
        btn_Add.setEnabled(!rowSelected);
        btn_Update.setEnabled(rowSelected);
    }

    private void clearFields() 
    {
        tf_DeptName.setText("");
        tbl_Dept.clearSelection();
        updateButtonStates(false);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tf_DeptName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_Add = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Dept = new javax.swing.JTable();
        btn_Update = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Department Name:");

        btn_Add.setText("Add");
        btn_Add.addActionListener(this::btn_AddActionPerformed);

        tbl_Dept.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_Dept);

        btn_Update.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Update.setText("Update Department");
        btn_Update.addActionListener(this::btn_UpdateActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Department");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(btn_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_DeptName, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Add))
                    .addComponent(jLabel1))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_DeptName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Add))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Update)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        try 
        {
            departmentController.addDepartment(tf_DeptName.getText());
            loadDepartments();
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Department added successfully.");
        } 
        catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),"Could Not Add Department", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
      int selectedRow = tbl_Dept.getSelectedRow();
        if (selectedRow == -1) 
        {
            return;
        }

        int departmentId = (int) tbl_Dept.getValueAt(selectedRow, 0);

        try 
        {
            departmentController.updateDepartment(departmentId, tf_DeptName.getText());
            loadDepartments();
            clearFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Department updated successfully.");
        } 
        catch (IllegalArgumentException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(),"Could Not Update Department", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                DepartmentDialog dialog = new DepartmentDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_Dept;
    private javax.swing.JTextField tf_DeptName;
    // End of variables declaration//GEN-END:variables
}
