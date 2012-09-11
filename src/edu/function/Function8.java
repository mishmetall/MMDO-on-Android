package edu.function;

public class Function8 implements IFunction
{

	@Override
	public double substitude(double argv) throws ArithmeticException
	{
		return Math.pow(Math.pow(argv-1, 2), 1.0/3.0)/(Math.pow(argv, 2) +1);
	}

}
