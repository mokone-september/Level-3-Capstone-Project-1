package capstoneProject;

import java.awt.HeadlessException;
//Importing necessary classes for use in the main program.
import java.sql.*;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class PoisedEngMainMenu extends PoisedEngHelp {

	public static void main(String[] args) throws ParseException {

		// Code section for initializing existingProjectDetails methods from the
		// ExistingProjects class.
		ExistingProjects existingProjectDetails = new ExistingProjects();

		// Code section for the welcome message displayed to user from terminal.
		System.out.println("Welcome to the Poised Engineering Project Management Software.");

		// Code section for the information message displayed to user from
		// MessageDialog.
		JOptionPane.showMessageDialog(null, "Poised Engineering Project Management Software Version 3.");

		// Code section for the while loop for the PoisedEngMainMenu
		while (true) {

			// Code section for PoisedEngMainMenu display with user options.
			try {
				System.out
						.println("\nPlease select an option from the menu below: " + "\n1. To View Projects (Existing)"
								+ "\n2. To Add Project (New)" + "\n3. To Update Existing Project Details"
								+ "\n4. To Finalize a Project" + "\n5. Incomplete Projects" + "\n6. Overdue Projects"
								+ "\n7. Find a Project" + "\n8. Exit program");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Exception" + e1);
				e1.printStackTrace();
			}

			// Code section for the user's selection verified and saved in an integer
			// variable, to ensure input selection is the correct type input.
			int poisedMainMenuUserSelection = intVerifier("menu choice");

			// Code section for a try-catch block is used to connect to the MySQL server and
			// access the PoisePMS database.
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
						"otheruser", "123456");

				// Code section for Statement object creation.
				Statement statement = connection.createStatement();

				if (poisedMainMenuUserSelection == 1) {

					try {
						existingProjectDetails.printAllFromTable(statement);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}

				} else if (poisedMainMenuUserSelection == 2) {

					try {
						existingProjectDetails.addNewProjectMenu(statement);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}

				} else if (poisedMainMenuUserSelection == 3) {

					existingProjectDetails.findAndUpdateProjectMenu(statement);

				} else if (poisedMainMenuUserSelection == 4) {

					try {
						existingProjectDetails.existingProjectFinalizationUpdate(statement);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}

				} else if (poisedMainMenuUserSelection == 5) {

					try {
						existingProjectDetails.viewAllIncompleteProjects(statement);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}

				} else if (poisedMainMenuUserSelection == 6) {

					try {
						existingProjectDetails.viewAllOverdueProjects(statement);
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}

					// Code section for '7' selected from the PoisedEngMainMenu, they are given the
					// option to locate a project by number or name.
				} else if (poisedMainMenuUserSelection == 7) {

					try {
						existingProjectDetails.findProject(statement);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}

					// Code section for the last option in the PoisedEngMainMenu, number '8', which
					// allows the user to log out of the database.
				} else if (poisedMainMenuUserSelection == 8) {

					// Code section for if selected, a successful log out message is displayed and
					// the PoisedEngMainMenu while loop menu is exited.
					try {
						JOptionPane.showMessageDialog(null, "Logged out, successfully.");
					} catch (HeadlessException e) {
						JOptionPane.showMessageDialog(null, "Exception" + e);
						e.printStackTrace();
					}
					break;

				}
				// Code section to catch SQL exception.
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}
}
