import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.*;

public class Property extends JFrame implements ActionListener
{
    String agentId, pId;
    JLabel propertyIdLabel, bhkLabel, locationLabel, nofPastOwnerLabel, rentOrPurchLabel, furnOrUnfurnLabel, priceLabel;
    JButton agentButton, rentOrPurchButton;
    Property(ResultSet specificPropertyResult) throws Exception
    {
        specificPropertyResult.next();
        agentId = specificPropertyResult.getString(10);
        String propertyId = specificPropertyResult.getString(1), bhk = specificPropertyResult.getString(3),
                location = specificPropertyResult.getString(6), nofPastOwner = specificPropertyResult.getString(5),
                rentOrPurch = specificPropertyResult.getString(8)=="rent"?"Rent":"Sale",
                furnOrUnfurn = specificPropertyResult.getString(9)=="furnished"?"Furnished":"Unfurnished",
                price = specificPropertyResult.getString(2), rentOrPurchButtonText = rentOrPurch=="Rent"?"Rent":"Purchase";
        pId = propertyId;
        propertyIdLabel = new JLabel("Property Id :    "+propertyId);
        bhkLabel = new JLabel("BHK :    "+bhk+" BHK");
        locationLabel = new JLabel("Location :    "+location);
        nofPastOwnerLabel = new JLabel("No. of past owners :    "+nofPastOwner);
        rentOrPurchLabel = new JLabel("Status :    For "+rentOrPurch);
        furnOrUnfurnLabel = new JLabel("Furnishing :    "+furnOrUnfurn);
        priceLabel = new JLabel("Price :    ₹"+price);
        agentButton = new JButton("Agent Info");
        rentOrPurchButton = new JButton(rentOrPurchButtonText);
        BufferedImage propertyImageBuffer = ImageIO.read(specificPropertyResult.getBinaryStream(7));
        ImageIcon propertyImageIcon = new ImageIcon(propertyImageBuffer);
        JLabel propertyImageLabel = new JLabel(propertyImageIcon);
        propertyImageLabel.setBounds(405-propertyImageBuffer.getWidth()/2, 20, propertyImageBuffer.getWidth(), 300);
        propertyImageLabel.setIcon(propertyImageIcon);
        propertyIdLabel.setBounds(145, 400, 200, 20);
        bhkLabel.setBounds(480, 400, 200, 20);
        locationLabel.setBounds(145, 450, 200, 20);
        nofPastOwnerLabel.setBounds(480, 450, 200, 20);
        rentOrPurchLabel.setBounds(145, 500, 200, 20);
        furnOrUnfurnLabel.setBounds(480, 500, 200, 20);
        priceLabel.setBounds(315, 550, 250, 50);
        agentButton.setBounds(200, 650, 100, 30);
        rentOrPurchButton.setBounds(450,650, 100, 30);
        add(propertyImageLabel);
        add(propertyIdLabel);
        add(bhkLabel);
        add(locationLabel);
        add(nofPastOwnerLabel);
        add(rentOrPurchLabel);
        add(furnOrUnfurnLabel);
        add(priceLabel);
        add(agentButton);
        add(rentOrPurchButton);
        agentButton.addActionListener(this);
        rentOrPurchButton.addActionListener(this);
        setTitle("Property Details - Real Estate");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(390, 0, 779, 1080);
        setLayout(null);
        setVisible(true);
        setResizable(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==agentButton)
        {
            try
            {
                new Agent(agentId);
            }catch(Exception ex){}
        }
        else if(ae.getSource()==rentOrPurchButton)
        {
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                Statement bookQuery = con.createStatement();
                bookQuery.executeUpdate("update property set availability='no' where property_id='"+pId+"'");
                JOptionPane.showMessageDialog(this, "Property reserved, contact agent to confirm booking.");
            }catch(Exception ex){}
        }
    }
}
