/* 
 * ss0113NormalizationUtil.java
 * Suman Shakya
 * 09/09/2013
 */

import java.util.ArrayList;

/* 
 * Utility class that provides functions for calculating mean,
 * standard deviation and the z-score normalized value for given data
 */

public class ss0113NormalizationUtil {

	/* 
	 * Returns the mean of list of Double values
	 */
	public static Double mean(ArrayList<Double> values){
		double sum = 0;
		int size = values.size();
		for(int i = 0; i < size; i++){
			sum += values.get(i);
		}
		return sum/size;
	}
	
	/* 
	 * Returns the standard deviation of list of Double values
	 */
	
	public static Double sd(ArrayList<Double> values){
		double sum = 0;
		double xBar = mean(values);
		int size = values.size();
		for(int i = 0; i < size; i++){
			sum += Math.pow((values.get(i) - xBar), 2.0);
		}
		
		return (Math.sqrt(sum/(size - 1)));
	}
	
	/*
	 * Returns the z-score normalized value of given data
	 */
	public static Double normalize(Double value, Double mean,Double sd){
		return ((value - mean)/sd);
	}
	
}
