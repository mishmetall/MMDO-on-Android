package edu.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Round
{
	public static Double roundResult (double d, int precise) {
		return new BigDecimal(d).setScale(precise, RoundingMode.UP).doubleValue();
	}
}
