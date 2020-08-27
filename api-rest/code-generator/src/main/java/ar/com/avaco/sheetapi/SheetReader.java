/**
 * 
 */
package ar.com.avaco.sheetapi;

import java.util.function.Predicate;

/**
 * @author Claudio
 *
 */
public interface SheetReader {
	
	void resetData();
	void closeData();
	void posRow(int rowNum) throws SheetRowNotFoundException;
	boolean hasNext();
	int nextRow() throws SheetRowNotFoundException;
	void nextRowControlCut(Predicate<Row> predicado);
	String getStringValueByColumn(String columnName);
	String getStringValueByRowAndColumn(int rowNum, String columunName) throws SheetRowNotFoundException;
	String getStringValueByRowAndColumn(String columnRowName) throws SheetRowNotFoundException;
}
