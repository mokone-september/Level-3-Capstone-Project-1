package capstoneProject;

import java.awt.HeadlessException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class PoisedEngHelp {

	public static int intVerifier(String type) {

		// Code section for a while loop that repeatedly asks for input until correct
		// type is provided.
		while (true) {
			@SuppressWarnings("resource")
			Scanner numberInput = new Scanner(System.in);
			String userNumber = numberInput.nextLine();

			try {
				// Code section for attempting to parse the input to an integer and ensuring
				// that it is verified and correct.
				int userOutput = Integer.parseInt(userNumber);
				return userOutput;

			} catch (NumberFormatException ex) {
				try {
					JOptionPane.showMessageDialog(null, "Incorrect entry. Please re-enter the " + type + ": \n");

					// Code section for error message displayed if parsing is not possible and
					// verification has failed.
				} catch (HeadlessException e) {
					JOptionPane.showMessageDialog(null, "HeadlessException" + e);
					e.printStackTrace();
				}

			}
		}
	}

	@SuppressWarnings("resource")
	public static String stringVerifier(String type) {

		// Code section for a while loop that repeatedly asks for input until correct
		// type is provided.
		while (true) {
			@SuppressWarnings("resource")
			Scanner userInput = new Scanner(System.in);
			String userInputedRepeat = userInput.nextLine();

			// Code section to verify if user input matches the required specification.
			if ((userInputedRepeat == null) || (userInputedRepeat.length() > 150)) {
				try {
					JOptionPane.showMessageDialog(null, "Incorrect entry. Try agin with" + type);
				} catch (HeadlessException e) {
					JOptionPane.showMessageDialog(null, "HeadlessException" + e);
					e.printStackTrace();
				}

			} else {

				// Code section for returning the user's properly inputed string.
				return userInputedRepeat;

			}
		}
	}

	public static float floatVerifier(String type) {

		// Code section for a while loop that repeatedly asks for input until correct
		// type is provided.
		while (true) {
			@SuppressWarnings("resource")
			Scanner floatVerificatioInput = new Scanner(System.in);
			String number = floatVerificatioInput.nextLine();

			try {
				// Code section for attempting to parse the input to an integer and ensuring
				// that it is verified and correct..
				float userInputedRepeat = Float.parseFloat(number);
				return userInputedRepeat;

				// Code section for error message displayed if parsing is not possible and
				// verification has failed
			} catch (NumberFormatException ex) {
				try {
					JOptionPane.showMessageDialog(null, "Incorrect entry. Try agin with" + type);
				} catch (HeadlessException e) {
					JOptionPane.showMessageDialog(null, "HeadlessException" + e);
					e.printStackTrace();
				}

			}
		}
	}

}
