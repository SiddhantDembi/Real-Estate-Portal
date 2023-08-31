import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
    JLabel loginLabel;
    JTextField usernameTextfield;
    JButton loginButton, signUpButton;
    JPasswordField passwordField;
    JPanel pan;
    Login()
    {

        pan = new JPanel();
        loginLabel = new JLabel("LOGIN");
        usernameTextfield = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("LOGIN");
        signUpButton = new JButton("Sign Up");
        swingComponents.PanelBorder pan2 = new swingComponents.PanelBorder();
        pan.setBackground(new java.awt.Color(16, 133, 30));
        pan.setBounds(0,0,1920,1080);
        pan.setLayout(null);
        pan.setVisible(true);
        pan2.setLayout(null);
        pan2.setVisible(true);
        pan2.setBackground(Color.white);
        loginLabel.setBounds(720, 210, 400, 50);
        loginLabel.setForeground(Color.black);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        passwordField.setBounds(650, 400, 250, 55);
        loginButton.setBounds(620, 492, 300, 40);
        loginButton.setBackground(new java.awt.Color(14, 125, 29));
        loginButton.setForeground(new java.awt.Color(255, 225, 255));
        signUpButton.setBounds(620, 562, 300, 40);
        signUpButton.setBackground(new java.awt.Color(14, 125, 29));
        signUpButton.setForeground(new java.awt.Color(255, 225, 255));
        usernameTextfield.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Enter Username or Email Id:", TitledBorder.LEFT, TitledBorder.TOP));
        passwordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Enter Password:", TitledBorder.LEFT, TitledBorder.TOP));
        usernameTextfield.setBounds(650, 310, 250, 50);
        pan2.setBounds(570,150,400,500);
        add(loginLabel);
        add(usernameTextfield);
        add(passwordField);
        add(loginButton);
        add(signUpButton);
        add(pan2);
        add(pan);
        getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
        setTitle("Login - Real Estate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        setLayout(null);
        setVisible(true);
        setResizable(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==loginButton)
        {
            String userId = usernameTextfield.getText();
            char[] passwordArr = passwordField.getPassword();
            String password = new String(passwordArr);
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                PreparedStatement loginCheck = con.prepareStatement("select username from user where (email_id=? or username=?) and password=?");
                PreparedStatement dataCheck = con.prepareStatement("select username from user where dob is not null and (email_id=? or username=?)");
                loginCheck.setString(1, userId);
                loginCheck.setString(2, userId);
                loginCheck.setString(3, password);
                dataCheck.setString(1, userId);
                dataCheck.setString(2, userId);
                ResultSet loginCheckResult = loginCheck.executeQuery();
                ResultSet dataCheckResult = dataCheck.executeQuery();
                Statement propertyQuery = con.createStatement();
                ResultSet propertySet = propertyQuery.executeQuery("select * from property where availability='yes'");
                if(userId.isEmpty())
                    JOptionPane.showMessageDialog(this, "Username or Email Id not entered.");
                else if(password.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Password not entered");
                }
                else
                {
                    if(loginCheckResult.next())
                    {
                        dispose();
                        if (dataCheckResult.next())
                            new Dashboard(userId, propertySet);
                        else
                            new Data(userId);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Incorrect Username or Password.");
                    }
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Login unsuccessful.");
            }
        }
        else if(ae.getSource()==signUpButton)
        {
            dispose();
            new Registration();
        }
    }
}
