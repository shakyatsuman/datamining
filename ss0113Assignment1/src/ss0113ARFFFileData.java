/* 
 * ss0113ARFFFileData.java
 * Suman Shakya
 * 09/09/2013
 */

import java.util.ArrayList;

/* 
 * Model class for storing the file contents into Object
 * Stores relation name, list of attribute names, datatypes,
 * and the values for the attributes
 */

public class ss0113ARFFFileData {
	
	private String relationName;
	private ArrayList<String> attributeNames;
	private ArrayList<String> attributeTypes;
	private ArrayList<ArrayList<Double>> attributeValues;
	
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public ArrayList<String> getAttributeNames() {
		return attributeNames;
	}
	public void setAttributeNames(ArrayList<String> attributeNames) {
		this.attributeNames = attributeNames;
	}
	public ArrayList<String> getAttributeTypes() {
		return attributeTypes;
	}
	public void setAttributeTypes(ArrayList<String> attributeTypes) {
		this.attributeTypes = attributeTypes;
	}
	public ArrayList<ArrayList<Double>> getAttributeValues() {
		return attributeValues;
	}
	public void setAttributeValues(ArrayList<ArrayList<Double>> attributeValues) {
		this.attributeValues = attributeValues;
	}
}
