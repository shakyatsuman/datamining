/*
 * ss0113ARFFFileParser.java
 * Suman Shakya
 * 09/09/2013
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ss0113ARFFFileParser {

	private static final String RELATION_TAG = "@relation";
	private static final String ATTRIBUTE_TAG = "@attribute";
	private static final String DATA_TAG = "@data";

	/*
	 * Parses the given file into ss0113ARFFFileData object
	 */
	public ss0113ARFFFileData parse(String filename) {
		
		ss0113ARFFFileData data = new ss0113ARFFFileData();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
		ArrayList<ArrayList<Double>> rowValues = new ArrayList<ArrayList<Double>>();

		try {
			Scanner scanner = new Scanner(new File(filename));
			String currentTag = "";
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Scanner linescanner = new Scanner(line);
				linescanner.useDelimiter(" ");
				String first = "";
				if (linescanner.hasNext()) {
					first = linescanner.next();
					while (first.isEmpty()) {
						first = linescanner.next();
					}
					if (RELATION_TAG.equals(first)) {
						currentTag = RELATION_TAG;
						data.setRelationName(linescanner.next());
					}

					else if (ATTRIBUTE_TAG.equals(first)) {
						if (RELATION_TAG.equals(currentTag))
							currentTag = ATTRIBUTE_TAG;
						names.add(linescanner.next());
						types.add(linescanner.next());
						
					} else if (DATA_TAG.equals(first)) {
						currentTag = DATA_TAG;
						
					} else {
						if (DATA_TAG.equals(currentTag)) {
							ArrayList<Double> values = new ArrayList<Double>();
							values.add(Double.parseDouble(first));
							
							while (linescanner.hasNext()) {
								String next = linescanner.next();
								if (!next.isEmpty()) {
									values.add(Double.parseDouble(next));
								}
							}
							rowValues.add(values);
						}
					}
				}
				linescanner.close();
			}
			
			scanner.close();
			data.setAttributeNames(names);
			data.setAttributeTypes(types);
			data.setAttributeValues(rowValues);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

}