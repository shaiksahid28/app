import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UserApp extends JFrame {
    JTextField email, name;
    JPasswordField pass;
    JButton loginBtn, regBtn, switchBtn;
    boolean isLogin = true;

    UserApp() {
        setTitle("User App");
        setSize(300, 250);
        setLayout(null);

        add(new JLabel("Email:")).setBounds(30,30,80,25);
        email = new JTextField(); email.setBounds(110,30,140,25); add(email);

        add(new JLabel("Password:")).setBounds(30,70,80,25);
        pass = new JPasswordField(); pass.setBounds(110,70,140,25); add(pass);

        add(new JLabel("Name:")).setBounds(30,110,80,25);
        name = new JTextField(); name.setBounds(110,110,140,25); add(name);
        name.setVisible(false);

        loginBtn = new JButton("Login");
        regBtn = new JButton("Register");
        switchBtn = new JButton("Go to Register");

        loginBtn.setBounds(30,150,100,25);
        regBtn.setBounds(30,150,100,25);
        switchBtn.setBounds(140,150,120,25);
        add(loginBtn); add(regBtn); add(switchBtn);

        regBtn.setVisible(false);

        loginBtn.addActionListener(e -> login());
        regBtn.addActionListener(e -> register());
        switchBtn.addActionListener(e -> toggle());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void toggle() {
        isLogin = !isLogin;
        name.setVisible(!isLogin);
        loginBtn.setVisible(isLogin);
        regBtn.setVisible(!isLogin);
        switchBtn.setText(isLogin ? "Go to Register" : "Go to Login");
    }

    void login() {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Phalgun@9")) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM REGISTRATION WHERE EMAIL=? AND PASSWORDS=?");
            ps.setString(1, email.getText());
            ps.setString(2, new String(pass.getPassword()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) JOptionPane.showMessageDialog(this,"Login Successful!");
            else JOptionPane.showMessageDialog(this,"Invalid Credentials");
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }

    void register() {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Phalgun@9")) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO REGISTRATION VALUES(?,?,?)");
            ps.setString(1, email.getText());
            ps.setString(2, new String(pass.getPassword()));
            ps.setString(3, name.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Registered Successfully!");
            toggle();
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }

    public static void main(String[] args) {
        new UserApp();
    }
}