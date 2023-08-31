import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data extends JFrame implements ActionListener
{
    String userId;
    JLabel headerLabel, sortLabel, locationLabel, desiredBHKLabel, rentOrPurchLabel, furnOrUnfurnLabel;
    JComboBox sortCBox, locationCBox, desiredBHKCBox, rentOrPurchCBox, furnOrUnfurnCBox;
    JButton applyButton;
    String sort[] = {"Low to High", "High to Low"};
    String location[] = {"Andheri", "Bandra", "Borivali", "Churchgate", "Dadar"};
    String bhk[] = {"1 BHK", "2 BHK", "3 BHK", "4 BHK"};
    String rent[] = {"Purchase", "Rent"};
    String fur[] = {"Furnished","Unfurnished"};
    Data(String usernameOrEmail)
    {
        userId = usernameOrEmail;
        headerLabel = new JLabel("Data");
        sortLabel = new JLabel("Sort by:");
        locationLabel = new JLabel("Location:");
        desiredBHKLabel = new JLabel("BHK:");
        rentOrPurchLabel = new JLabel("Rent or Purchase:");
        furnOrUnfurnLabel = new JLabel("Furnished or Unfurnished:");
        sortCBox = new JComboBox(sort);
        locationCBox = new JComboBox(location);
        desiredBHKCBox = new JComboBox(bhk);
        rentOrPurchCBox = new JComboBox(rent);
        furnOrUnfurnCBox = new JComboBox(fur);
        applyButton = new JButton("Apply");
        headerLabel.setForeground(Color.blue);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 25));
        headerLabel.setBounds(760, 40, 400, 30);
        applyButton.setBounds(730, 600, 100, 30);
        sortLabel.setBounds(579, 110, 200, 30);
        sortCBox.setBounds(579, 140, 400, 30);
        locationLabel.setBounds(579, 190, 200, 30);
        locationCBox.setBounds(579, 220, 400, 30);
        desiredBHKLabel.setBounds(579, 270, 400, 30);
        desiredBHKCBox.setBounds(579, 300, 400, 30);
        rentOrPurchLabel.setBounds(579, 350, 400, 30);
        rentOrPurchCBox.setBounds(579, 380, 400, 30);
        furnOrUnfurnLabel.setBounds(579, 430, 400, 30);
        furnOrUnfurnCBox.setBounds(579, 460, 400, 30);
        applyButton.addActionListener(this);
        add(headerLabel);
        add(sortLabel);
        add(locationLabel);
        add(desiredBHKLabel);
        add(rentOrPurchLabel);
        add(furnOrUnfurnLabel);
        add(sortCBox);
        add(locationCBox);
        add(desiredBHKCBox);
        add(rentOrPurchCBox);
        add(furnOrUnfurnCBox);
        add(applyButton);
        setTitle("Data - Real Estate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==applyButton)
        {
            String sort = "order by price", sortDesc = "order by price desc";
            int sortSelected = sortCBox.getSelectedIndex(), locationSelected = locationCBox.getSelectedIndex(),
                    desiredBHKSelected = desiredBHKCBox.getSelectedIndex()+1, rentOrPurchSelected = rentOrPurchCBox.getSelectedIndex(),
                    furnOrUnfurnSelected = furnOrUnfurnCBox.getSelectedIndex();

            String rentorPurchFinal = rentOrPurchSelected==0?"purchase":"rent",
                    furnOrUnfurnFinal = furnOrUnfurnSelected==0?"furnished":"unfurnished",
                    sortFinal = sortSelected==0?"order by price":"order by price desc";
            String mainQuery = "select * from property where location='"+location[locationSelected]+
                    "' and bhk='"+desiredBHKSelected+"' and rentorpurchase='"+rentorPurchFinal+
                    "' and furnishing='"+furnOrUnfurnFinal+"' and availability='yes' "+sortFinal;
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate", "root", "Root@123");
                PreparedStatement filterStatement = con.prepareStatement(mainQuery);
                ResultSet filterResult = filterStatement.executeQuery();
                JOptionPane.showMessageDialog(this, " Data entered successfully.");
                dispose();
                new Dashboard(userId, filterResult);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Filter not applied.");

            }
        }
    }
}
