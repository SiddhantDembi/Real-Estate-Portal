import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agent extends JFrame
{
    JLabel details, name, agname, phno, agphno, email, agemail, agid, aagid;
    Agent(String agentId)
    {
        String s1, s2, s3;
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
            PreparedStatement stm1 = con.prepareStatement("Select * from agent where agent_id=?");
            stm1.setString(1, agentId);
            ResultSet rs = stm1.executeQuery();
            rs.next();
            s1 = rs.getString(2);
            s2 = rs.getString(3);
            s3 = rs.getString(4);
            details = new JLabel("Agent Details");
            details.setForeground(Color.blue);
            details.setFont(new Font("Serif", Font.BOLD, 20));
            details.setBounds(115, 25, 400, 30);
            agid = new JLabel("Agent Id:");
            aagid = new JLabel(agentId);
            name = new JLabel("Name:");
            agname = new JLabel(s1);
            phno = new JLabel("Phone no:");
            agphno = new JLabel(s2);
            email = new JLabel("Email ID:");
            agemail = new JLabel(s3);
            agid.setBounds(70, 65, 400, 30);
            aagid.setBounds(175, 65, 400, 30);
            name.setBounds(70, 95, 400, 30);
            agname.setBounds(175, 95, 400, 30);
            phno.setBounds(70, 125, 400, 30);
            agphno.setBounds(175, 125, 400, 30);
            email.setBounds(70, 155, 400, 30);
            agemail.setBounds(175, 155, 400, 30);
            add(details);
            add(name);
            add(agname);
            add(phno);
            add(agphno);
            add(email);
            add(agemail);
            setTitle("Agent - Real Estate");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(600, 290, 350, 250);
            setLayout(null);
            setVisible(true);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Unable to show agent details.");
        }
    }
}