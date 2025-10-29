package edu.kirkwood.model;
import java.util.ArrayList;

public class Polynomial {
	/**
	 * Instance variable of an ArrayList of PolynomialTerm(s) that represents the
	 * terms within the Polynomial.
	 */
	private ArrayList<PolynomialTerm> terms;
	/**
	 * Default constructor, creates an empty Polynomial.
	 */
	public Polynomial()
	{
		terms = new ArrayList<PolynomialTerm>();
	}
	/**
	 * Constructor for building a generalized Polynomial.
	 * @param input List of terms in the Polynomial.
	 */
	public Polynomial(ArrayList<PolynomialTerm> input)
	{
		terms = input;
	}
	/**
	 * Method for adding a new term to the Polynomial.
	 * Intended for building a Polynomial piecemeal.
	 * @param newTerm The term to add.
	 */
	public void addTerm(PolynomialTerm newTerm)
	{
		terms.add(newTerm);
	}
	/**
	 * Accessor method for the list of terms.
	 * @return The list of terms in this Polynomial.
	 */
	public ArrayList<PolynomialTerm> listTerms()
	{
		return terms;
	}
	/**
	 * A method to find the derivative of the Polynomial.
	 * Calls PolynomialTerm's differentiate() method.
	 */
	public void differentiate()
	{
		for (PolynomialTerm term : terms)
		{
			System.out.print("The derivative of " + term + " is: ");
			term.differentiate();
			System.out.println(term);
		}
	}
	
	/**
	 * Override for Object's toString method.
	 */
	@Override
	public String toString()
	{
		String result = "";
		result += terms.get(0);
		for (int i = 1; i < terms.size(); i++)
		{
			PolynomialTerm t = terms.get(i);
			if (!t.toString().equals("0"))
			{
				if (t.sign().equals("+"))
				{
					result += " + " + t;
				}
				else
				{
					result += " - " + t.toString().substring(1);
				}
			}
		}
		return result;
	}
	
	/**
	 * Override for Object's equals method.
	 */
	@Override
	public boolean equals(Object other)
	{
		boolean result = true;
		
		if (this == null ^ other == null) // XOR null
		{
			result = false;
		}
		else if (this instanceof Polynomial && other instanceof Polynomial)
		{
			// If the number of terms is different, they cannot be equal.
			if (this.listTerms().size() == ((Polynomial) other).listTerms().size())
			{
				ArrayList<PolynomialTerm> thisList = this.listTerms();
				ArrayList<PolynomialTerm> otherList = ((Polynomial) other).listTerms();
				for (int t = 0; t < thisList.size(); t++)
				{
					// If at any point the terms are not equal, result is set to false.
					if (!(thisList.get(t).equals(otherList.get(t))))
					{
						result = false;
					}
				}
			}
			else
			{
				result = false;
			}
		}
		
		return result;
	}
}
