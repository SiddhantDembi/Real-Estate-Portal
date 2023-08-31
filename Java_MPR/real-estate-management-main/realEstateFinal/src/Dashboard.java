import swingComponents.MyButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import  javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dashboard extends JFrame implements ActionListener
{
    String userId;
    JLabel heyThereLabel,PropImgLabel,Welcome;
    swingComponents.MyButton homeButton, purchaseButton, rentButton, filterButton, logoutButton;
    Dashboard(String usernameOrEmail, ResultSet propertySet) throws Exception
    {
        userId = usernameOrEmail;
        homeButton = new MyButton();
        purchaseButton = new MyButton();
        rentButton = new MyButton();
        filterButton = new MyButton();
        logoutButton = new MyButton();
        homeButton.setText("Home");
        purchaseButton.setText("Purchase");
        rentButton.setText("Rent");
        filterButton.setText("Filter");
        logoutButton.setText("Logout");
        heyThereLabel = new JLabel("Hey there, "+ userId);
        JPanel panel=new JPanel();
        panel.setBounds(0,0,1920,120);
        panel.setBackground(new java.awt.Color(16, 133, 30));
        JPanel panel2=new JPanel();
        panel2.setBounds(0,80,380,1080);
        panel2.setBackground(new java.awt.Color(16, 133, 30));
        PropImgLabel = new JLabel();
        Welcome = new JLabel("Real Estate Portal");
        Welcome.setBounds(750,40,600,30);
        Welcome.setFont(new Font("Arial",Font.ITALIC,35));
        Welcome.setForeground(new java.awt.Color(255, 215, 0));
//        BufferedImage buffimg = ImageIO.read(new File("realEstateFinal/src/PIctures/stock2.jpg"));
//        ImageIcon propimg = new ImageIcon(buffimg);
//        PropImgLabel.setIcon(propimg);
//        PropImgLabel.setBounds(45,20, buffimg.getWidth(), buffimg.getHeight());
//        add(PropImgLabel);
        heyThereLabel.setForeground(new Color(255,255,255));
        heyThereLabel.setBackground(new Color(255,0,0));
        heyThereLabel.setBounds(1350, 15, 200, 80);
        heyThereLabel.setFont(new Font("Sans serif",Font.LAYOUT_LEFT_TO_RIGHT,18));
        heyThereLabel.setForeground((new java.awt.Color(255, 255, 255)));
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setForeground(new Color(255,255,255));
        homeButton.setBackground(new Color(16, 133, 30));
        purchaseButton.setContentAreaFilled(false);
        purchaseButton.setBorderPainted(false);
        purchaseButton.setFocusPainted(false);
        purchaseButton.setForeground(new Color(255,255,255));
        purchaseButton.setBackground(new Color(16, 133, 30));
        rentButton.setContentAreaFilled(false);
        rentButton.setBorderPainted(false);
        rentButton.setFocusPainted(false);
        rentButton.setForeground(new Color(255,255,255));
        rentButton.setBackground(new Color(16, 133, 30));
        filterButton.setContentAreaFilled(false);
        filterButton.setBorderPainted(false);
        filterButton.setFocusPainted(false);
        filterButton.setForeground(new Color(255,255,255));
        filterButton.setBackground(new Color(16, 133, 30));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setForeground(new Color(255,255,255));
        logoutButton.setBackground(new Color(16, 133, 30));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        purchaseButton.setContentAreaFilled(false);
        purchaseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rentButton.setContentAreaFilled(false);
        rentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        filterButton.setContentAreaFilled(false);
        filterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(heyThereLabel);
        add(Welcome);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel2.add(Box.createRigidArea(new Dimension(0,200)));
        homeButton.setFont(new Font("Sans serif",Font.BOLD,18));
        rentButton.setFont(new Font("Sans serif",Font.BOLD,18));
        purchaseButton.setFont(new Font("Sans serif",Font.BOLD,18));
        filterButton.setFont(new Font("Sans serif",Font.BOLD,18));
        logoutButton.setFont(new Font("Sans serif",Font.BOLD,18));
        homeButton.setAlignmentX(panel2.CENTER_ALIGNMENT);
        purchaseButton.setAlignmentX(panel2.CENTER_ALIGNMENT);
        rentButton.setAlignmentX(panel2.CENTER_ALIGNMENT);
        filterButton.setAlignmentX(panel2.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(panel2.CENTER_ALIGNMENT);
        panel2.add(homeButton);
        panel2.add(Box.createRigidArea(new Dimension(0,80)));
        panel2.add(purchaseButton);
        panel2.add(Box.createRigidArea(new Dimension(0,80)));
        panel2.add(rentButton);
        panel2.add(Box.createRigidArea(new Dimension(0,80)));
        panel2.add(filterButton);
        panel2.add(Box.createRigidArea(new Dimension(0,80)));
        panel2.add(logoutButton);
        homeButton.setBounds(50, 150, 300, 30);
        purchaseButton.setBounds(50,200, 300, 30);
        rentButton.setBounds(50, 90, 100, 30);
        filterButton.setBounds(50, 110, 100, 30);
        filterButton.addActionListener(this);
        logoutButton.setBounds(50, 110, 100, 30);
        logoutButton.addActionListener(this);
        purchaseButton.addActionListener(this);
        rentButton.addActionListener(this);
        homeButton.addActionListener(this);
        String columnNames[] = { "Property Id", "Location", "BHK", "Price"};

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
            String[] data = new String[4];
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            JTable propertyTable = new JTable()
            {
                public boolean isCellEditable(int row,int column){
                    return false;
                }
                public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
                {
                    Component comp = super.prepareRenderer(renderer, row, col);
                    ((JLabel) comp).setHorizontalAlignment(JLabel.CENTER);
                    return comp;
                }
            };
            propertyTable.setFocusable(false);
            propertyTable.setModel(model);
            while (propertySet.next())
            {
                data[0] = propertySet.getString(1);
                data[1] = propertySet.getString(6);
                data[2] = propertySet.getString(3);
                data[3] = propertySet.getString(2);
                model.addRow(data);
            }
            add(panel);
            add(panel2);
            propertyTable.setRowHeight(80);
            JScrollPane sp = new JScrollPane(propertyTable);
            sp.setBounds(380, 120, 1150, 800);
            propertyTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,18));
            propertyTable.getTableHeader().setBackground(new java.awt.Color(82, 206, 136));
            propertyTable.setBackground(new java.awt.Color(201, 204, 203));
            propertyTable.getTableHeader().setPreferredSize(
                    new Dimension(287,60)
            );
            propertyTable.addMouseListener(new MouseListener()
            {
                public void mouseClicked(MouseEvent e)
                {
                    try
                    {
                        int tableRow = propertyTable.rowAtPoint(e.getPoint());
                        String propertyId = "" + propertyTable.getValueAt(tableRow, 0);
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                        Statement specificPropertyQuery = con.createStatement();
                        ResultSet specificPropertyResult = specificPropertyQuery.executeQuery("select * from property where property_id='"+propertyId+"'");
                        new Property(specificPropertyResult);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Dashboard.super.getParent(), "Please try again.");
                    }
                }
                public void mousePressed(MouseEvent e){}
                public void mouseReleased(MouseEvent e){}
                public void mouseEntered(MouseEvent e){}
                public void mouseExited(MouseEvent e){}
            });
            add(sp);
            setTitle("Home");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(0, 0, 1920, 1080);
            setLayout(null);
            setVisible(true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==homeButton)
        {
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                Statement propertyQuery = con.createStatement();
                ResultSet propertySet = propertyQuery.executeQuery("select * from property where availability='yes'");
                dispose();
                new Dashboard(userId, propertySet);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        }
        else if(ae.getSource()==filterButton)
        {
            dispose();
            new Filter(userId);
        }
        else if(ae.getSource()==purchaseButton)
        {
            dispose();
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                Statement propertyQuery = con.createStatement();
                ResultSet propertySet = propertyQuery.executeQuery("select * from property where rentorpurchase='purchase' and availability='yes'");
                new Dashboard(userId, propertySet);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if(ae.getSource()==rentButton)
        {
            dispose();
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                Statement propertyQuery = con.createStatement();
                ResultSet propertySet = propertyQuery.executeQuery("select * from property where rentorpurchase='rent' and availability='yes'");
                new Dashboard(userId, propertySet);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if(ae.getSource()==logoutButton)
        {
            dispose();
            new Login();
        }
    }
    /*public static void main(String arg[]) throws Exception
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
        Statement propertyQuery = con.createStatement();
        ResultSet propertySet = propertyQuery.executeQuery("select * from property where availability='yes'");
        new Dashboard("Shaunak", propertySet);
    }*/
}
