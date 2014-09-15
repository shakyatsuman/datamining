/*
 * ss0113Normalize.java
 * Suman Shakya
 * 09/09/2013
 */

import java.util.ArrayList;

/*
 * Class containing the main method of the project
 * Reads the input arff file, parses it, calculates mean and standard deviation
 * and normalize the data except class attributes and writes to file
 */

public class ss0113Normalize {

	private static final String RELATION_TAG = "@relation";
	private static final String ATTRIBUTE_TAG = "@attribute";
	private static final String DATA_TAG = "@data";
	
	public static void main(String[] args) {
		
		if(args.length < 3){ // complete list of parameters not passed
			System.out.println("Incomplete parameters");
			System.out.println("<usage> $java mainclass -c classattribute");
			System.exit(0);
		}
		
		String inputFile = args[0];
		String classAttr = args[2];
		
		ss0113ARFFFileData data = new ss0113ARFFFileParser().parse(inputFile);
		ArrayList<String> attrNames = data.getAttributeNames();
		ArrayList<String> attrTypes = data.getAttributeTypes();
		ArrayList<ArrayList<Double>> rowValues = data.getAttributeValues();
		int attrCount = attrNames.size();
		int classIndex = -1;

		for(int i = 0; i < attrNames.size(); i++){
			if(classAttr.equals(attrNames.get(i))){
				classIndex = i;
				break;
			}
		}
		
		if(classIndex == -1){ // class attribute not found
			System.out.println("Class attribute not found");
			System.exit(0);
		}
		
		ArrayList<ArrayList<Double>> normValues = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> means = new ArrayList<Double>();
		ArrayList<Double> sds = new ArrayList<Double>();

		for (int i = 0; i < attrCount; i++) {
			ArrayList<Double> values = new ArrayList<Double>();
			for (ArrayList<Double> row : rowValues) {
				values.add(row.get(i));
			}
			means.add(ss0113NormalizationUtil.mean(values));
			sds.add(ss0113NormalizationUtil.sd(values));
		}

		String outputFile1 = "ss0113MeanStd" + inputFile;
		ss0113FileUtil arffWriter1 = new ss0113FileUtil(outputFile1);
		arffWriter1.writeLine(RELATION_TAG + " " + data.getRelationName());
		arffWriter1.writeBlankLine();
		for(int i =0; i < attrCount; i++){
			arffWriter1.writeLine(ATTRIBUTE_TAG + " " + attrNames.get(i) + " " + attrTypes.get(i));
		}
		arffWriter1.writeBlankLine();
		arffWriter1.writeLine(DATA_TAG);
		arffWriter1.writeLine(arrayListString(means));
		arffWriter1.writeLine(arrayListString(sds));
		arffWriter1.close();
		
		for (ArrayList<Double> row : rowValues) { 
			ArrayList<Double> normRow = new ArrayList<Double>();
			for (int i = 0; i < attrCount; i++) { 
				Double val = row.get(i); 
				normRow.add((i == classIndex) ? val : ss0113NormalizationUtil.normalize(val, means.get(i), sds.get(i)));
			}
			normValues.add(normRow);
		}
		
		String outputFile2 = "ss0113Normalize" + inputFile;
		ss0113FileUtil arffWriter2 = new ss0113FileUtil(outputFile2);
		arffWriter2.writeLine(RELATION_TAG + " " + data.getRelationName());
		arffWriter2.writeBlankLine();
		for(int i =0; i < attrCount; i++){
			arffWriter2.writeLine(ATTRIBUTE_TAG + " " + attrNames.get(i) + " " + attrTypes.get(i));
		}
		arffWriter2.writeBlankLine();
		arffWriter2.writeLine(DATA_TAG);
		for(ArrayList<Double> row:normValues){
			arffWriter2.writeLine(arrayListString(row));
		}
		arffWriter2.close();

	}
	
	/*
	 * Return a string value by appending all the values of a list
	 */
	public static String arrayListString(ArrayList<Double> row){
		String rowString = "";
		for(int i = 0; i < row.size(); i++){
			rowString += String.format("%11.6f", row.get(i)) + " ";
		}
		return rowString;
	}
}
