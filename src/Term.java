class Term{

	//Data Fields 
	private int coefficient;
	private int exponent;
	private Term next;

/**
* Constructs a new Term with the specified coefficient and exponent.
*
* @param coefficient The coefficient of the term.
* @param exponent The exponent of the term.
*/
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


	 /**
		* 
	   * Method is used to overrde the object toString because if it's not 
	   * Overriden then I would get a hash location 
	   * This Method is used for formatting 
     * Provides a string representation of this term, formatted for display.
     * For example, a term with a coefficient of 3 and an exponent of 2 would be represented as "3x^2".
     *
     * @return A string representation of the term.
     */
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