/**
 * 
 */
package ar.com.avaco.sheetapi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;

/**
 * @author Claudio
 *
 */
public class ExcelRow implements Row{

	private org.apache.poi.ss.usermodel.Row row;
	
	public ExcelRow(org.apache.poi.ss.usermodel.Row row) {
		super();
		this.row = row;
	}

	public org.apache.poi.ss.usermodel.Row getRow() {
		return row;
	}

	public void setRow(org.apache.poi.ss.usermodel.Row row) {
		this.row = row;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getStringValueByColumn(String columnName) {
		int columnIndex = CellReference.convertColStringToIndex(columnName);
		Cell cell =  CellUtil.getCell(row, columnIndex);
		
		if(cell == null) {
			return null;
		}
		
		Object value = null;
		switch(cell.getCellTypeEnum()) {
		case BLANK:
			break;
		case BOOLEAN: value = cell.getBooleanCellValue(); break;
		case ERROR:
			break;
		case FORMULA: 
			break;
		case NUMERIC: value = cell.getNumericCellValue(); break;
		case STRING: value = cell.getStringCellValue(); break;
		case _NONE:
			break;
		default:
			break;
		}
		if(value == null) {
			return "";
		}
		return String.valueOf(value);
	}

}
