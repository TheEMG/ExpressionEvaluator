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

class ExpressionEvaluator{
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





class Polynomial
{
	private Term head;
	public  Polynomial() 
	{
		head = null;
	}


	//Method takes in a coefficient and exponent and begun to add terms of the polynomial 
	public void addTerms(int coefficient,int exponent ) 
	{
		Term newTerm  = new Term(coefficient,exponent);

		//if the polynomial is empty set the term to the head 
		if (head ==null) 
		{
			head = newTerm;
			return;
		}
		//Inserting new terms in descending order of exponents
		Term currentTerm  =head;
		Term previousTerm = null;


		while(currentTerm != null && currentTerm.getExponent() > exponent) 
		{
			previousTerm = currentTerm;
			currentTerm = currentTerm.getNext();
		}

		if(currentTerm != null && currentTerm.getExponent() == exponent) 
		{
			//We add the coefficent if the condition above is met 
			currentTerm.setCoefficient(currentTerm.getCoefficent()+coefficient);
		}
		else 
		{
			//Insert the term in the correct position 
			newTerm.setNext(currentTerm);
			if(previousTerm != null) 
			{
				previousTerm.setNext(newTerm);
			}
			else 
			{
				head= newTerm;
			}
		}



	}

	//Method is used to evaluate "X". 
	public String evaluate(int x) {
		int answer = 0;

		StringBuilder builder = new StringBuilder();
		
		/*Traversing the list; starting with the head  
		 * Gets the current coefficient and multiplies it by the value of the last digit  in the line file 
		 * and the last digit will then multiplied by the current exponent
		 * The output will also be modified 
		 */

		while( head != null) {
			answer += head.getCoefficent() *Math.pow(x,  head.getExponent());
			builder.append( head.toString());
			if( head.getNext() !=null &&  head.getNext().getCoefficent()>0 ) {
				builder.append("+");
			}
			else if( head.getNext() !=null &&  head.getNext().getCoefficent()<0)
			{
				builder.append("+");
			}
			else if( head.getNext() !=null &&  head.getNext().getCoefficent()==0) {
				builder.append("");
			}
			head =  head.getNext();
			
		}
		//Puts all the pieces of the output together 

		builder.append("=").append(answer).append(" FOR X = ").append(x);
		return builder.toString();
	}



	//Method is used to overrde the object toString because if it's not 
	//Overriden then It would get a hash location 
	//This Method is used for formatting 
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		if (head != null) {
			stringBuilder.append(head.toString());
			Term current = head.getNext();

			//Assuming the polynomial is not empty (because that would mean that the head is referencing null)then then
			//the first term is appended to a string representation 
			while (current != null) {
				//append + if the coefficient is > 0
				//append - if the coefficient is < 0
				if(current.getCoefficent()>0 || current.getCoefficent()<0) {
					stringBuilder.append(" + ");
				}

				//After the first term is appended to the string builder then 
				//we start to traverse the list , and based on the coefficient value 
				//then the appropriate sign will be appended to the coefficient
				stringBuilder.append(current.toString());
				current = current.getNext();
			}
		}
		return stringBuilder.toString();

	}

}

class Term{


	//Data Fields 
	private int coefficient;
	private int exponent;
	private Term next;

	//Creates a new object "Term "with two int parameters , coefficent and exponent 
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
		this.next = null;

	}




	/*Gets the coefficient of the term 
	 * returns coefficient of the term 
	 */
	public int getCoefficent() {
		return coefficient;
	}

	/*Gets the exponent of the term 
	 * returns the exponent of the term
	 */
	public int getExponent() {
		return exponent;
	}

	/*Sets the Coefficient of the term
	 * @param coefficient 
	 */
	public void setCoefficient (int coefficient) {
		this.coefficient = coefficient;
	}

	/*Sets the exponent of the term 
	 * @param exponent
	 * */
	public void setExponent (int exponent) {
		this.exponent = exponent;
	}

	//Gets the next term in the polynomial 
	//Returns next
	public Term getNext() {
		return next;
	}

	/*Sets the next term 
	 * @param Term next 
	 */
	public void setNext(Term next) {
		this.next = next;
	}


	//Method is used to overrde the object toString because if it's not 
	//Overriden then I would get a hash location 
	//This Method is used for formatting 
	public String toString() 
	{
		if(exponent == 0 && coefficient < 0) {
			String placeHolder = "";
			placeHolder = Integer.toString(coefficient);
			return "("+placeHolder +")";

		}
		else if(exponent  == 0 && coefficient > 0) {
			String placeHolder = "";
			placeHolder = Integer.toString(coefficient);
			return placeHolder;
		}else if (exponent  == 1 && coefficient == 0){
			return "";

		}
		else if (exponent == 1) {
			if (coefficient == 1) {
				return "x";
			} 
			else if (coefficient == -1) {
				return "-x";
			} 
			else {
				return coefficient + "x";
			}
		} 
		else {
			if (coefficient == 1) {
				return "x^" + exponent;
			} 
			else if (coefficient == -1) {
				return "-x^" + exponent;
			} 
			else if (coefficient < 0){
				return "("+coefficient +")"+ "x^" + exponent;
			}
			else {
				return coefficient + "x^" + exponent;
			}
		}
	}
}






