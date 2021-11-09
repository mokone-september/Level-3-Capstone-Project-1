package capstoneProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Persons {

	public void displayCustomer(Statement statement, int projectNumber) throws SQLException {
		// Code section for resultsSet to display customer information
		ResultSet resultsForCustomer = statement.executeQuery(
				"SELECT NAME, CONTACT_NUM, HOME_ADDRESS, EMAIL_ADDRESS FROM person_information WHERE PROJECT_NUM = "
						+ projectNumber + " AND PERSON_TYPE = 'Customer'");

		// Code section for customer details to be displayed on the terminal.
		while (resultsForCustomer.next()) {
			try {
				System.out.println("\nCustomer Name: " + resultsForCustomer.getString("Name") + "\nContact Number: "
						+ resultsForCustomer.getInt("Contact_Num") + "\nHome Address: "
						+ resultsForCustomer.getString("Home_Address") + "\nEmail Address: "
						+ resultsForCustomer.getString("Email_Address"));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "SQLException" + e);
				e.printStackTrace();
			}

		}
	}
}
