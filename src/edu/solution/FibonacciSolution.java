package edu.solution;

import edu.function.IFunction;

public class FibonacciSolution implements ISolution
{
	/*
	 * Fibonacci numbers until long-type maximum
	 */
	private static final long [] numbers =
	    {
			0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
			233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946,
			17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229,
			832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352,
			24157817, 39088169, 63245986, 102334155, 165580141, 267914296
	    };
	
    public static long getNumber(int i)
    {
        return numbers[i];
    }
    
    public static int getLength()
    {
        return numbers.length;
    }
	    
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
		
    	int j = 1, m;

    	while(!(getNumber(j + 1) < (1/error)*(rightInterval - leftInterval) 
    			&& (1/error)*(rightInterval - leftInterval) <= getNumber(j + 2)))
    	{
    		j++;
			if(j + 2 >= getLength())
    			throw new NoEquationException();	
    	}
    	m = j;
    	
    	double x1 = leftInterval + ((double)getNumber(m - 2)/getNumber(m))*(rightInterval - leftInterval);
    	double x2 = leftInterval + ((double)getNumber(m - 1)/getNumber(m))*(rightInterval - leftInterval);
    	double y1 = f.substitute(x1);
    	double y2 = f.substitute(x2);
    	int k = 1;
    	do 
    	{
	    	if(y1 > y2)
	    	{
	    		leftInterval = x1;
	    		x1 = x2;
	    		x2 = rightInterval - (x1 - leftInterval);
	    		y1 = y2;
	    		y2 = f.substitute(x2);
	    	}
	    	else 
	    	{
	    		rightInterval = x2;
	    		x2 = x1;
	    		x1 = leftInterval + (rightInterval - x2);
	    		y2 = y1;
	    		y1 = f.substitute(x1);
	    	}
	    	k++;
    	}
    	while(k < m - 1);
    	
    	return x1; 	
	}
}
