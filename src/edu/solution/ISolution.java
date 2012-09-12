package edu.solution;

import edu.function.IFunction;

public interface ISolution
{
	public double solve (IFunction f, double leftInterval, double rightInterval, double error) throws NoEquationException;
}
