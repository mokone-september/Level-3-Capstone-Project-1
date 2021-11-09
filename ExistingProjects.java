package capstoneProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import java.text.ParseException;

public class ExistingProjects extends PoisedEngHelp {

	// Code section for capturing and storing a new project information on the database
	// The following addNewProjectMenu method adds all necessary information accordingly to the respective field
	public void addNewProjectMenu(Statement statement) throws SQLException {

		try {
			System.out.println("\nEnter a new project number: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		int projectNumber = intVerifier("new project number");

		try {
			System.out.println("\nEnter a new project name: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		String projectName = stringVerifier("new project name: ");

		try {
			System.out.println("\nEnter a building type: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		String buildingType = stringVerifier("building type");

		try {
			System.out.println("\nEnter a address for the project: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		String address = stringVerifier("project address");

		try {
			System.out.println("\nEnter an ERF number: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		String erfNumber = stringVerifier("ERF number");

		try {
			System.out.println("\nEnter a total fee for the project: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		float totalChargedFee = floatVerifier("total fee");

		try {
			System.out.println("\nEnter the current amount paid for the project: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		float amountPaidToDate = floatVerifier("amount paid");

		try {
			System.out.println("Enter a deadline for the project (e.g. YYYY-MM-DD): ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		String projectDeadline = stringVerifier("deadline");

		String projectFinalize = "Awaiting Finalization";
		String projectCompletion = "None Provided";

		try {
			statement.executeUpdate("INSERT INTO project_information VALUES (" + projectNumber + ", " + "'" + projectName
					+ "'" + ", " + "'" + buildingType + "'" + ", " + "'" + address + "'" + ", " + "'" + erfNumber + "'"
					+ ", " + totalChargedFee + ", " + amountPaidToDate + ", " + "'" + projectDeadline + "'" + ", " + "'"
					+ projectFinalize + "'" + ", " + "'" + projectCompletion + "'" + ");");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQLException" + e);
			e.printStackTrace();
		}

		// A successful message is displayed and the user can then view the updated
		// project list.
		try {
			System.out.println("\nProject successfully added. Updated project details below: \n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		printAllFromTable(statement);

	}
	//Code section to findAndUpdateProjectMenu, is a method made to search, find and provide the user
	// the ability to alter project information
	public void findAndUpdateProjectMenu(Statement statement) throws SQLException {

		
		try {
			// Code section for a try-catch block, throwing an exception 
			System.out.println("Enter the number of the project to be updated: \n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		int projectNumber = intVerifier("project number");
		
		// Code section for user now being prompted to enter a project number to edit.
		try {
			//Code section for poisedMenuEdit options displayed.
			System.out.println("Please enter the number corresponding to the action you would like to take:" + "\n1. Edit the project due date or"
					+ "\n2. Edit the total amount paid of the fee to date?" + 
					"\nChoose either 1 or 2");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}

		int poisedMenuEditSelection = intVerifier("edit choice");
		
		
		//Code section for the poisedMenuEdit section where user can select and edit information about a project
		if (poisedMenuEditSelection == 1) {
			try {
				System.out.println("Enter a new project deadline: ");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			String projectDeadlineUpdated = stringVerifier("new project deadline");

			try {
				statement.executeUpdate("UPDATE project_information SET PROJ_DEADLINE = '" + projectDeadlineUpdated + "'"
						+ " WHERE PROJECT_NUM = " + projectNumber);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}

			
			try {
				// Code section for successful completion of updating details of project
				System.out.println("\nProject successfully added. Updated project details below: \\n");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			printAllFromTable(statement);

		} else if (poisedMenuEditSelection == 2) {
			System.out.println("Please enter a new total amount paid: ");
			float amountToBePaidUpdated = floatVerifier("new amount paid");

			statement.executeUpdate("UPDATE project_information SET AMOUNT_PAID_TO_DATE = " + amountToBePaidUpdated
					+ " WHERE PROJECT_NUM = " + projectNumber);

			
			try {
				// Code section for successful completion of updating details of project
				System.out.println("\\nProject successfully added. Updated project details below: \\n");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			printAllFromTable(statement);

		}
	}

	public void existingProjectFinalizationUpdate(Statement statement) throws SQLException {

		// Code section for the user to enter a project number to finalize project status.
		try {
			System.out.println("Enter the number of the project to be finalized: ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		int projectNumber = intVerifier("project number");

		// Code section for selecting the TotalFeeCharged and AmountToBePaid columns from project_information table.
		ResultSet resultsForUpdate = statement.executeQuery(
				"SELECT TOTAL_FEE_CHARGED, AMOUNT_PAID_TO_DATE FROM project_information WHERE PROJ_NUM = " + projectNumber);
		float totalChargedFee = 0;
		float amountPaidToDate = 0;

		// Code section storing the two float numbers into corresponding variables.
		while (resultsForUpdate.next()) {
			totalChargedFee = resultsForUpdate.getFloat("Total_Fee");
			amountPaidToDate = resultsForUpdate.getFloat("Amount_Paid");

		}
		// Code section to check if project has been paid in full, the amount paid will equal the total
		// fee for the project. This means no invoice needs to be generated.
		if (totalChargedFee == amountPaidToDate) {
			try {
				System.out.println("Project already paid in full. No invoice to be generated.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}

			// Code section if the user is prompted to enter a completion date, which is written into
			// the 'CompletionDate' column  in the project_information table.
			try {
				System.out.println("Add a completion date for the project: ");
			} catch (Exception e) {
				//Code section for exception handling
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			String projectCompletion = stringVerifier("completion date");

			// Code section for the Completion date to be added by the user's chosen project through project number selection.
			statement.executeUpdate("UPDATE project_information SET COMPLETION_DATE = " + "'" + projectCompletion + "'"
					+ " WHERE PROJ_NUM = " + projectNumber);

			// Code section for the project being marked as finalized by writing 'Finalization Complete' to the finalize project
			statement.executeUpdate(
					"UPDATE project_information SET FINALIZED = 'Finalization Complete' WHERE PROJECT_NUM = " + projectNumber);

			// Code section for successful completion of updating details of project
			try {
				System.out.println("Project successfully finalised. View projects below: ");
			} catch (Exception e) {
				//Code section for exception handling
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			printAllFromTable(statement);

		} else if (totalChargedFee != amountPaidToDate) {
			try {
				System.out.println("An outstanding amount to be paid for this project is still pending. View invoice below: \n");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}

			Persons customer = new Persons();
			customer.displayCustomer(statement, projectNumber);

			// Code section to add information to the customer detail from a financial prospective.
			try {
				System.out.println("\nAmount Outstanding: R" + (totalChargedFee - amountPaidToDate));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}

			// Code section for if the user is prompted to enter a completion dated for the project.
			System.out.println("\nAdd a completion date for the project: ");
			String projectCompletion = stringVerifier("completion date");

			// The date entered by the user is written to the main_project_info table under
			// the 'Completion_Date' column.
			try {
				statement.executeUpdate("UPDATE project_information SET COMPLETION_DATE = " + "'" + projectCompletion + "'"
						+ " WHERE PROJECT_NUM = " + projectNumber);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "SQLException" + e);
				e.printStackTrace();
			}

			// Code section for if the project is then marked as finalized by writing 'Finalization Complete' to the finalize
			// column in the table.
			statement.executeUpdate(
					"UPDATE project_information SET Finalised = 'Finalization Complete' WHERE PROJECT_NUM = " + projectNumber);

			// A successful message is displayed and the user is able to view the updated
			// project list.
			try {
				System.out.println("Project successfully finalized. View projects below: ");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			printAllFromTable(statement);

		}
	}

	public void viewAllIncompleteProjects(Statement statement) throws SQLException {

		try {
			System.out.println("\nView all incomplete projects below: \n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}

		ResultSet resultsForDueDate = statement
				.executeQuery("SELECT * FROM project_information WHERE FINALIZED = 'Awaiting Finalization' AND COMPLETION_DATE = 'None Provided'");

		// Code section for when all incomplete projects are displayed.
		while (resultsForDueDate.next()) {
			try {
				System.out.println("Project Number: \t" + resultsForDueDate.getInt("Project_Num") + "\nProject Name: \t"
						+ resultsForDueDate.getString("Project_Name") + "\nBuilding Type: \t"
						+ resultsForDueDate.getString("Building_Type") + "\nPhysical Address: "
						+ resultsForDueDate.getString("Address") + "\nERF Number: \t"
						+ resultsForDueDate.getString("ERF_Num") + "\nTotal Fee: \tR"
						+ resultsForDueDate.getFloat("Total_Fee") + "\nAmount Paid: \t"
						+ resultsForDueDate.getFloat("Amount_Paid") + "\nDeadline: \t"
						+ resultsForDueDate.getString("Deadline") + "\nFinalised: \t"
						+ resultsForDueDate.getString("Finalised") + "\nCompletion Date: "
						+ resultsForDueDate.getString("Completion_Date") + "\n");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "SQLException" + e);
				e.printStackTrace();
			}
		}
	}

	public void viewAllOverdueProjects(Statement statement) throws SQLException, ParseException {

		boolean projectVerifier = false;
		String[] info;
		int[] viewAllMonthsDigit = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		String[] viewAllMonthsWords = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
				"Dec" };
		int numberOfMonth = 0;

		// code section for all overdue projects will be incomplete, meaning that only the deadline date information
		// from columns of incomplete projects are located.
		ResultSet resultsForDate = statement.executeQuery(
				"SELECT Deadline FROM project_information WHERE FINALIZED = 'Finalization Complete' AND COMPLETION_DATE = 'None Provided");

		// Code section for iterating through the deadline dates in the incomplete projects to check if
		// they are overdue.
		while (resultsForDate.next()) {

			// Code section for the date
			String dateInformation = resultsForDate.getString("Deadline");
			info = dateInformation.split("-");
			int day = Integer.parseInt(info[0]);

			String monthInfomation = info[1];
			String monthProvided = (monthInfomation.substring(0, 2));
			int yearProvided = Integer.parseInt(info[2]);

			for (int index = 0; index < viewAllMonthsWords.length; index++) {
				if (monthProvided.equalsIgnoreCase(viewAllMonthsWords[index])) {
					numberOfMonth = viewAllMonthsDigit[index];

				}
			}
			// Code section for obtaining the current date and storing it as a string.
			String current = "" + java.time.LocalDate.now();

			// Code section for creating a new simple date format object.
			SimpleDateFormat dateStylingForm = new SimpleDateFormat("yyyy-MM-dd");

			// Code section for dates primaryDate and secondaryDate are then created by parsing string info from 'current' date
			Date primaryDate = dateStylingForm.parse(current);

			Date secondaryDate = dateStylingForm.parse(day + "-" + numberOfMonth + "-" + yearProvided);

			// Code section for date comparison and use case
			if (primaryDate.compareTo(secondaryDate) < 0) {
				projectVerifier = true;

				System.out.println("\nview all overdue projects below: \n");
				ResultSet resultsForOverdue = statement
						.executeQuery("SELECT * from project_information WHERE PROJ_DEADLINE = '" + dateInformation + "'");

				// Code section for displaying all information related to the overdue project.
				while (resultsForOverdue.next()) {
					try {
						System.out.println("Project Number: \t" + resultsForOverdue.getInt("Project_Num")
								+ "\nProject Name: \t" + resultsForOverdue.getString("Project_Name") + "\nBuilding Type: \t"
								+ resultsForOverdue.getString("BUILDING_TYPE") + "\nPhysical Address: "
								+ resultsForOverdue.getString("ADDRESS") + "\nERF Number: \t"
								+ resultsForOverdue.getString("ERF_NUM") + "\nTotal Fee: \tR"
								+ resultsForOverdue.getFloat("TOTAL_FEE_CHARGED") + "\nAmount Paid: \t"
								+ resultsForOverdue.getFloat("AMOUNT_PAID_TO_DATE") + "\nDeadline: \t"
								+ resultsForOverdue.getString("PROJ_DEADLINE") + "\nFinalised: \t"
								+ resultsForOverdue.getString("FINALIZED") + "\nCompletion Date: "
								+ resultsForOverdue.getString("COMPLETION_DATE") + "\n");
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "SQLException" + e);
						e.printStackTrace();
					}
				}
				// Code section for when there is no overdue project on the database
			} else {
				projectVerifier = false;
			}
			// Code section to notify user of no over due projects on terminal or display
		}
		if (projectVerifier == false) {
			try {
				System.out.println("No overdue projects listed on the database.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
		}
	}

	public void findProject(Statement statement) throws SQLException {

		try {
			// Code section to find a project by either project number or project name
			System.out.println("Please enter the number corresponding to the action you would like to take, Search for the project by: "
			+ "\n1. Project number or"
			+ "\n2. Project name?" + // Edit options displayed.
			"\nSelect either 1 or 2");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception" + e);
			e.printStackTrace();
		}
		int poisedEngSearchMenu = intVerifier("Number search option");

		if (poisedEngSearchMenu == 1) {
			try {
				System.out.println("\nEnter the number of the project to locate: ");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			int projectNumber = intVerifier("project number");

			try {
				System.out.println("\nView your project details below: \n");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}

			ResultSet resultsForPojectNumber = statement
					.executeQuery("SELECT * from project_information WHERE PROJ_NUM = " + projectNumber);

			// Code section to return user specified details
			while (resultsForPojectNumber.next()) {
				try {
					System.out.println("Project Number: \t" + resultsForPojectNumber.getInt("Project_Num")
							+ "\nProject Name: \t" + resultsForPojectNumber.getString("Project_Name")
							+ "\nBuilding Type: \t" + resultsForPojectNumber.getString("Building_Type")
							+ "\nPhysical Address: " + resultsForPojectNumber.getString("Address") + "\nERF Number: \t"
							+ resultsForPojectNumber.getString("ERF_Num") + "\nTotal Fee: \tR"
							+ resultsForPojectNumber.getFloat("Total_Fee") + "\nAmount Paid: \t"
							+ resultsForPojectNumber.getFloat("Amount_Paid") + "\nDeadline: \t"
							+ resultsForPojectNumber.getString("Deadline") + "\nFinalised: \t"
							+ resultsForPojectNumber.getString("Finalised") + "\nCompletion Date: "
							+ resultsForPojectNumber.getString("Completion_Date") + "\n");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "SQLException" + e);
					e.printStackTrace();
				}
			}

		} else if (poisedEngSearchMenu == 2) {
			try {
				System.out.println("\nEnter the name of the project to locate: ");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}
			String projectName = stringVerifier("project name");

			try {
				System.out.println("\nView your project details below: \n");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Exception" + e);
				e.printStackTrace();
			}

			ResultSet resultsForProjectName = statement
					.executeQuery("SELECT * from project_informatio WHERE PROJ_NUM = '" + projectName + "'");

			// Code section to return user specified details
			while (resultsForProjectName.next()) {
				try {
					System.out.println("Project Number: \t" + resultsForProjectName.getInt("Project_Num")
							+ "\nProject Name: \t" + resultsForProjectName.getString("Project_Name") + "\nBuilding Type: \t"
							+ resultsForProjectName.getString("Building_Type") + "\nPhysical Address: "
							+ resultsForProjectName.getString("Address") + "\nERF Number: \t"
							+ resultsForProjectName.getString("ERF_Num") + "\nTotal Fee: \tR"
							+ resultsForProjectName.getFloat("Total_Fee") + "\nAmount Paid: \t"
							+ resultsForProjectName.getFloat("Amount_Paid") + "\nDeadline: \t"
							+ resultsForProjectName.getString("Deadline") + "\nFinalised: \t"
							+ resultsForProjectName.getString("Finalised") + "\nCompletion Date: "
							+ resultsForProjectName.getString("Completion_Date") + "\n");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "SQLException" + e);
					e.printStackTrace();
				}
			}
		}
	}

	public void printAllFromTable(Statement statement) throws SQLException {

		// Code section for Selecting all information from the ptoject_information.
		ResultSet totalResults = statement.executeQuery("SELECT * FROM project_information");

		// Code section to return user specified details
		while (totalResults.next()) {
			System.out.println("Project Number: \t" + totalResults.getInt("Project_Num") + "\nProject Name: \t"
					+ totalResults.getString("Project_Name") + "\nBuilding Type: \t"
					+ totalResults.getString("Building_Type") + "\nPhysical Address: "
					+ totalResults.getString("Address") + "\nERF Number: \t" + totalResults.getString("ERF_Num")
					+ "\nTotal Fee: \tR" + totalResults.getFloat("Total_Fee") + "\nAmount Paid: \t"
					+ totalResults.getFloat("Amount_Paid") + "\nDeadline: \t" + totalResults.getString("Deadline")
					+ "\nFinalised: \t" + totalResults.getString("Finalised") + "\nCompletion Date: "
					+ totalResults.getString("Completion_Date") + "\n");
		}
	}
}
