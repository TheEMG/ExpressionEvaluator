
/**
 * Represents a polynomial as a linked list of terms. Each term in the polynomial
 * has a coefficient and an exponent. The terms are stored in descending order of
 * exponent value.
 */
class Polynomial
{

  /**
  * Constructs an empty Polynomial with no terms.
  */
	private Term head;
	public  Polynomial() 
	{
		head = null;
	}

  /**
  * Adds a term to the polynomial with the given coefficient and exponent.
  * If a term with the same exponent already exists, its coefficient is updated.
  * Otherwise, a new term is inserted in its proper place to maintain order.
  *
  * @param coefficient the coefficient of the term to add
  * @param exponent    the exponent of the term to add
	*/
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

 /**
 * Evaluates the polynomial for a given value of x.
 * 
 * @param x the value at which to evaluate the polynomial
 * @return a string representation of the evaluation process and result
 */
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



	

	   /**
		 * Method is used to overrde the object toString because if it's not 
	   * Overriden then It would get a hash location 
	   * This Method is used for formatting 
     * Returns a string representation of the polynomial.
     * 
     * @return the string form of the polynomial with terms in descending order
     */
  @Override
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