package edu.solution;

import java.util.ArrayList;

import edu.function.IFunction;

public class DichotomySolution implements ISolution, IIteraionTable
{
	private ArrayList<Double> iterTable = new ArrayList<Double>();
	
	@Override
	public double solve(IFunction f, double leftInterval, double rightInterval,
			double error) throws NoEquationException
	{
		if (leftInterval>rightInterval)
			{
				double c = leftInterval;
				leftInterval = rightInterval;
				rightInterval = c;
			}
		
		if (error <= 0)
			throw new NoEquationException();
		
		iterTable.clear();
		
		double x1, f1, f2;
		
		do
			{
				x1 = (leftInterval + rightInterval)/2;
				f1 = f.substitute(x1 - error/2);
				f2 = f.substitute(x1 + error/2);
				if (f1>f2)
					{
						leftInterval = x1;
					}
				else if (f1<f2)
					{
						rightInterval = x1;
					}
				iterTable.add((leftInterval + rightInterval)/2);
			} while (rightInterval-leftInterval > error);
		
		return (leftInterval + rightInterval)/2;
	}

	@Override
	public ArrayList<Double> getIterTable()
	{
		if (iterTable.size() < 1)
			iterTable.add(0.0);
		return iterTable;
	}
}
