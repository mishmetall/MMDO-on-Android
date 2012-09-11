package edu.function;

public interface IFunction 
{
	/**
	 * Substitute in a function value
	 * @param argv - value of argument
	 * @return result of substitution
	 * @throws ArithmeticException
	 */
	public double substitude (double argv ) throws ArithmeticException;
}