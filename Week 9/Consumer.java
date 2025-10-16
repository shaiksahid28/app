import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Consumer extends JFrame {

    private JPanel jPanel1;
    private JLabel jLabel1;
    private JTable table;
    private JScrollPane jScrollPane2;
    private JButton jButton1;

    public Consumer() {
        setTitle("Available Crops");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadCropData(); // Load data from DB
        setVisible(true);
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jScrollPane2 = new JScrollPane();
        table = new JTable();
        jButton1 = new JButton("Back");

        jPanel1.setBackground(new Color(1, 78, 189));
        jPanel1.setLayout(null);

        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 24));
        jLabel1.setForeground(new Color(255, 204, 51));
        jLabel1.setText("Available Crops to Purchase");
        jLabel1.setBounds(250, 20, 400, 40);
        jPanel1.add(jLabel1);

        // Set up table model with column headers
        DefaultTableModel model = new DefaultTableModel(
            new String[] {
                "FARMER_NAME", "CROP_NAME", "QUANTITY", "QUANTITY_IN",
                "PRICE", "PRICE_PER", "PLACE", "PHONE_NUMBER"
            }, 0
        );
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);

        jScrollPane2.setViewportView(table);
        jScrollPane2.setBounds(50, 80, 780, 350);
        jPanel1.add(jScrollPane2);

        jButton1.setBounds(380, 450, 100, 30);
        jButton1.addActionListener(e -> {
            new Home().setVisible(true);
            this.setVisible(false);
        });
        jPanel1.add(jButton1);

        add(jPanel1);
    }

    private void loadCropData() {
        try {
        	Connection con = DriverManager.getConnection(
        			"jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "Phalgun@9"
            );

            String sql = "SELECT FARMER_NAME, CROP_NAME, QUANTITY, QUANTITY_INT, PRICE, PRICE_PER, PLACE, PHONE_NUMBER FROM crops1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Object[] row = {
                    rs.getString("FARMER_NAME"),
                    rs.getString("CROP_NAME"),
                    rs.getFloat("QUANTITY"),
                    rs.getString("QUANTITY_INT"),
                    rs.getFloat("PRICE"),
                    rs.getString("PRICE_PER"),
                    rs.getString("PLACE"),
                    rs.getString("PHONE_NUMBER")
                };
                model.addRow(row);
            }

            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading crop data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Consumer();
        }
}
    	
    