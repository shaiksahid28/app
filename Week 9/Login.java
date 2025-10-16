import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {

    private JTextField t1;         // Username field
    private JPasswordField t2;     // Password field
    private JButton signBtn, resetBtn, registerBtn;

    public Login() {
        setTitle("Login Portal");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        t1 = new JTextField();
        t1.setBounds(150, 50, 200, 30);
        add(t1);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        t2 = new JPasswordField();
        t2.setBounds(150, 100, 200, 30);
        add(t2);

        signBtn = new JButton("Sign In");
        signBtn.setBounds(50, 160, 100, 30);
        signBtn.addActionListener(this::signActionPerformed);
        add(signBtn);

        resetBtn = new JButton("Reset");
        resetBtn.setBounds(160, 160, 100, 30);
        resetBtn.addActionListener(this::resetActionPerformed);
        add(resetBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(270, 160, 100, 30);
        registerBtn.addActionListener(this::registerActionPerformed);
        add(registerBtn);

        setVisible(true);
    }

    private void signActionPerformed(ActionEvent e) {
        String user = t1.getText();
        String password = new String(t2.getPassword());

        if (user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ALL FIELDS ARE MANDATORY");
            return;
        }

        try {
            Connection con = DriverManager.getConnection(
            		"jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "Phalgun@9"
            );

            String sql = "SELECT * FROM REGISTRATION WHERE USER_NAME = ? AND PASSWORDS = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "SIGN IN SUCCESSFULLY");
                new Home().setVisible(true); // You must define Home.java separately
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "INVALID CREDENTIALS");
            }

            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    private void registerActionPerformed(ActionEvent e) {
        new Registration().setVisible(true);
        this.setVisible(false);
    }

    private void resetActionPerformed(ActionEvent e) {
        t1.setText("");
        t2.setText("");
    }

    public static void main(String[] args) {
        new Login();
    }
}
