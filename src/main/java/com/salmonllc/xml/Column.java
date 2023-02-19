package com.salmonllc.xml;

/////////////////////////
//$Archive: /JADE/SourceCode/com/salmonllc/xml/Column.java $
//$Author: Srufle $ 
//$Revision: 9 $ 
//$Modtime: 7/31/02 7:13p $ 
/////////////////////////

/**
 * This object contains a cell of a datastore. It is stored in a Vector of Columns in a Row Object.
 * Creation date: (8/7/01 11:59:45 AM)
 * @author: Administrator
 */
public class Column {
	private String fieldTableName = "";
	private String fieldColumnName = "";
	private String fieldValue = "";
/**
 * Column constructor comment.
 */
public Column() {
	super();
}
/**
 * Column constructor comment.
 */
public Column(String table, String column, String value) {
	super();
	this.fieldTableName = table;
	this.fieldColumnName = column;
	this.fieldValue = value;
}
/**
 * Gets the Column Name
 * Creation date: (8/7/01 12:00:24 PM)
 * @return java.lang.String
 */
public String getColumnName() {
	return fieldColumnName;
}
/**
 * Gets the table name used by the column
 * Creation date: (8/7/01 12:00:24 PM)
 * @return java.lang.String
 */
public String getTableName() {
	return fieldTableName;
}
/**
 * Gets the value of the column
 * Creation date: (8/7/01 12:00:24 PM)
 * @return java.lang.String
 */
public String getValue() {
	return fieldValue;
}
/**
 * Sets the column name 
 * Creation date: (8/7/01 12:00:24 PM)
 * @param newColumnName java.lang.String
 */
public void setColumnName(String newColumnName) {
	fieldColumnName = newColumnName;
}
/**
 * Sets the table name for the column
 * Creation date: (8/7/01 12:00:24 PM)
 * @param newTableName java.lang.String
 */
public void setTableName(String newTableName) {
	fieldTableName = newTableName;
}
/**
 * Sets the value of a option
 * Creation date: (8/7/01 12:00:24 PM)
 * @param newValue java.lang.String
 */
public void setValue(String newValue) {
	fieldValue = newValue;
}
}
