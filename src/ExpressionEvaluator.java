/*Eric Gutierrez 
 *COSC 2336.001
 *Description: Program builds polynomial and combines like terms,using a linked list. The linked 
 *list traverses the linked list in order to display the appropriate output and in order to 
 *insert terms into descending order.The last digit of a line of the input file will then be used to
 *evaluate the polynomial. 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class is responsible for reading and evaluating polynomial expressions from a file.
 * It parses expressions into terms, builds a polynomial representation, and then evaluates 
 * the polynomial for a specific value of 'x' provided at the end of each line in the input file.
 * The results are written to an output file.
 * 
 * <p>Usage: Place an input file 'input-1.txt' in the program's directory with lines of 
 * coefficients and exponents. The last two numbers in each line should be negative to signal 
 * the end of the polynomial, followed by the value of 'x' to evaluate the polynomial.</p>
 * 
 */
class ExpressionEvaluator{
	  /**
     * The main method reads the input file 'input-1.txt', processes each line to create 
     * polynomial objects, evaluates them, and writes the result to 'output.txt'.
     * 
     * @param args Command-line arguments (not used).
     * @throws IOException If there is an error reading the file or writing to the output file.
     */
	public static void main(String[] args)throws IOException{

		// Create a Scanner object to read input from file
		Scanner input = new Scanner(new File("input-1.txt"));
		//Creates a Print writer object 
		PrintWriter outFile = new PrintWriter(new FileWriter("output.txt"));
		// Loop through input file until the end
		while (input.hasNextLine()) {
			//Creates a new polynomial object through each iteration 
			Polynomial poly = new Polynomial();

			//reads the line of the file and stores it into a string 
			String token = input.nextLine();

			//"Breaks" the individual integers of the file ; each integer would be a token.
			//It will then store the individual tokens into an array
			String [] parts = token.split("\\s+");

			//Variable used to keep track of position of the tokens in the parts array
			int i = 0 ;

			//As long as token is getting the nextLine of the file it will be true, otherwise it would be false
			while (true) {
				//Coeff and exp gets the int value of the string 
				//i will start at position 0(coefficient) and it will get the position after which is 1 (i+1)(exponent)
				int coeff = Integer.parseInt(parts[i]);
				int exp = Integer.parseInt(parts[i+1]);

				//Termination condition - - 
				if (coeff < 0 && exp < 0) {
					break;
				}

				//Add terms
				poly.addTerms(coeff, exp);

				// Gets the last digit after termination condition.
				i = i + 2;
			}

			//Last digit of the end of the line is stored in x 
			int x = Integer.parseInt(parts[i+2]);

			// "X" is evaluated as well as displayed in the output file.
			outFile.print(poly.evaluate(x));
			outFile.print("\n");
		}
		// close scanner and file Writer
		outFile.close();
		input.close();
	}
}