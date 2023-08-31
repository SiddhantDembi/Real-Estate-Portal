import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Registration extends JFrame implements ActionListener
{
    JLabel headerLabel, nameLabel, usernameLabel, emailLabel, passwordLabel, confirmPasswordLabel, cityLabel, phnoLabel;
    JTextField nameTextField, usernameTextField, emailTextField, cityTextField, phnoTextField;
    JButton registerButton, clearButton;
    JPasswordField passwordConfirmField, passwordField;
    Registration()
    {
        swingComponents.PanelBorder pane = new swingComponents.PanelBorder();
        pane.setLayout(null);
        pane.setVisible(true);
        pane.setBackground(new java.awt.Color(16, 133, 30));
        pane.setBounds(0,0,500,1080);
        swingComponents.PanelBorder pane2 = new swingComponents.PanelBorder();
        pane2.setLayout(null);
        pane2.setVisible(true);
        pane2.setBackground(new java.awt.Color(16, 133, 30));
        pane2.setBounds(1105,0,450,1080);
        swingComponents.PanelBorder s1 = new swingComponents.PanelBorder();
        s1.setLayout(null);
        s1.setVisible(true);
        s1.setBackground(new java.awt.Color(16, 133, 30));
        s1.setBounds(540,147,220,80);
        swingComponents.PanelBorder s2 = new swingComponents.PanelBorder();
        s2.setLayout(null);
        s2.setVisible(true);
        s2.setBackground(new java.awt.Color(16, 133, 30));
        s2.setBounds(850,147,220,80);
        swingComponents.PanelBorder s3 = new swingComponents.PanelBorder();
        s3.setLayout(null);
        s3.setVisible(true);
        s3.setBackground(new java.awt.Color(16, 133, 30));
        s3.setBounds(540,300,220,80);
        swingComponents.PanelBorder s4 = new swingComponents.PanelBorder();
        s4.setLayout(null);
        s4.setVisible(true);
        s4.setBackground(new java.awt.Color(16, 133, 30));
        s4.setBounds(850,300,220,80);
        swingComponents.PanelBorder s5 = new swingComponents.PanelBorder();
        s5.setLayout(null);
        s5.setVisible(true);
        s5.setBackground(new java.awt.Color(16, 133, 30));
        s5.setBounds(540,460,220,80);
        swingComponents.PanelBorder s6 = new swingComponents.PanelBorder();
        s6.setLayout(null);
        s6.setVisible(true);
        s6.setBackground(new java.awt.Color(16, 133, 30));
        s6.setBounds(850,460,220,80);
        swingComponents.PanelBorder s7 = new swingComponents.PanelBorder();
        s7.setLayout(null);
        s7.setVisible(true);
        s7.setBackground(new java.awt.Color(16, 133, 30));
        s7.setBounds(691,578,220,80);
        headerLabel = new JLabel("REGISTRATION");
        nameLabel = new JLabel("Name");
        usernameLabel = new JLabel("Username");
        emailLabel = new JLabel("Email ID");
        passwordLabel = new JLabel("Create Password");
        confirmPasswordLabel = new JLabel("Confirm Password");
        cityLabel = new JLabel("City");
        phnoLabel = new JLabel("Phone No");
        nameTextField = new JTextField();
        usernameTextField = new JTextField();
        passwordConfirmField = new JPasswordField();
        passwordField = new JPasswordField();
        emailTextField = new JTextField();
        cityTextField = new JTextField();
        phnoTextField = new JTextField();
        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");
        headerLabel.setBounds(680, 30, 400, 120);
        nameLabel.setBounds(630, 157, 200, 30);
        nameTextField.setBounds(550, 187, 200, 30);
        nameTextField.setBorder(BorderFactory.createEmptyBorder());
        usernameLabel.setBounds(932, 157, 200, 30);
        usernameTextField.setBounds(860, 187, 200, 30);
        usernameTextField.setBorder(BorderFactory.createEmptyBorder());
        emailLabel.setBounds(627, 310, 200, 30);
        emailTextField.setBounds(550, 340, 200, 30);
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        passwordLabel.setBounds(913, 310, 200, 30);
        passwordField.setBounds(860, 340, 200, 30);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        confirmPasswordLabel.setBounds(600, 470, 200, 30);
        passwordConfirmField.setBounds(550, 500, 200, 30);
        passwordConfirmField.setBorder(BorderFactory.createEmptyBorder());
        cityLabel.setBounds(945, 470, 200, 30);
        cityTextField.setBounds(860, 500, 200, 30);
        cityTextField.setBorder(BorderFactory.createEmptyBorder());
        phnoLabel.setBounds(770, 587, 290, 30);
        phnoTextField.setBounds(700, 617, 202, 30);
        phnoTextField.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setBounds(635, 700, 150, 40);
        clearButton.setBounds(835, 700, 150, 40);
        registerButton.setBackground(new java.awt.Color(16, 133, 30));
        clearButton.setBackground(new java.awt.Color(16, 133, 30));
        registerButton.setForeground(new java.awt.Color(255,255,255));
        clearButton.setForeground(new java.awt.Color(255,255,255));
        headerLabel.setForeground(Color.black);
        nameLabel.setForeground(new java.awt.Color(255,255,255));
        usernameLabel.setForeground(new java.awt.Color(255,255,255));
        emailLabel.setForeground(new java.awt.Color(255,255,255));
        passwordLabel.setForeground(new java.awt.Color(255,255,255));
        confirmPasswordLabel.setForeground(new java.awt.Color(255,255,255));
        cityLabel.setForeground(new java.awt.Color(255,255,255));
        phnoLabel.setForeground(new java.awt.Color(255,255,255));
        headerLabel.setFont(new Font("Arial", Font.BOLD, 35));
        registerButton.addActionListener(this);
        clearButton.addActionListener(this);
        getRootPane().setDefaultButton(registerButton);
        add(pane);
        add(pane2);
        add(headerLabel);
        add(nameLabel);
        add(nameTextField);
        add(usernameLabel);
        add(usernameTextField);
        add(emailLabel);
        add(passwordConfirmField);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(emailTextField);
        add(cityLabel);
        add(cityTextField);
        add(phnoLabel);
        add(phnoTextField);
        add(registerButton);
        add(clearButton);
        add(s1);
        add(s2);
        add(s3);
        add(s4);
        add(s5);
        add(s6);
        add(s7);
        setTitle("Registration - Real Estate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == registerButton)
        {
            String name = nameTextField.getText();
            String username = usernameTextField.getText();
            char[] passwordArr = passwordField.getPassword();
            char[] confirmPasswordArr = passwordConfirmField.getPassword();
            String password = new String(passwordArr);
            String confirmPassword = new String(confirmPasswordArr);
            String email = emailTextField.getText();
            String city = cityTextField.getText();
            String phno = phnoTextField.getText();
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                if(confirmPassword.equals(password))
                {
                    PreparedStatement registerUser = con.prepareStatement("insert into user(name, username, email_id, password, city, phno) values(?, ?, ?, ?, ?, ?)");
                    registerUser.setString(1, name);
                    registerUser.setString(2, username);
                    registerUser.setString(3, email);
                    registerUser.setString(4, confirmPassword);
                    registerUser.setString(5, city);
                    registerUser.setString(6, phno);
                    registerUser.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Registered successfully.");
                    dispose();
                    new Login();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Password doesn't match.");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration unsuccessful.");
                dispose();
                new Login();
            }
        }
        else if(ae.getSource()==clearButton)
        {
            nameTextField.setText("");
            usernameTextField.setText("");
            passwordConfirmField.setText("");
            passwordField.setText("");
            emailTextField.setText("");
            cityTextField.setText("");
            phnoTextField.setText("");
        }
    }
}
