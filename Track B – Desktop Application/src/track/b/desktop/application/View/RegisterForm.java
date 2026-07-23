package track.b.desktop.application.View;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class RegisterForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegisterForm.class.getName());
    private final track.b.desktop.application.Controller.UserController userController =
            new track.b.desktop.application.Controller.UserController();
    
    public RegisterForm() {
        initComponents();
        setLocationRelativeTo(null);
        
                FocusAdapter fa;
        fa = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JButton button = (JButton) e.getSource();
                getRootPane().setDefaultButton(button);
            }
        };
        
        btn_Register.addFocusListener(fa);
        btn_Back.addFocusListener(fa);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_Username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_Email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pf_ConfirmPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        pf_Password = new javax.swing.JPasswordField();
        btn_Register = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Register");

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        jLabel4.setText("Email Address");

        jLabel5.setText("Comfirm Password");

        btn_Register.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Register.setText("Register");
        btn_Register.addActionListener(this::btn_RegisterActionPerformed);

        btn_Back.setText("Back");
        btn_Back.addActionListener(this::btn_BackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(tf_Username)
                    .addComponent(tf_Email)
                    .addComponent(pf_ConfirmPassword)
                    .addComponent(pf_Password)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_ConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        String username = tf_Username.getText();
        String email = tf_Email.getText();
        String password = new String(pf_Password.getPassword());
        String confirmPassword = new String(pf_ConfirmPassword.getPassword());

        if (!password.equals(confirmPassword)) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration Failed",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            userController.register(username, password, email, "User");

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Registration successful. You can now log in.",
                    "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

            new LoginForm().setVisible(true);
            this.dispose();

        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Registration Failed",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_BackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pf_ConfirmPassword;
    private javax.swing.JPasswordField pf_Password;
    private javax.swing.JTextField tf_Email;
    private javax.swing.JTextField tf_Username;
    // End of variables declaration//GEN-END:variables
}
