/**
 * 
 */
package ar.com.avaco.sheetapi;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Claudio
 *
 */
public class ExcelSheetReader extends AbstractSheetReader{

	private Sheet sheet;
	private ExcelRow currentRow;
	private Predicate<Row> predicate;

	private Workbook workbook;
	private String fileUrl;
	
	public ExcelSheetReader(String fileUrl) throws IOException {
		super();
		this.fileUrl = fileUrl;
		this.resetData();
		this.currentRow = new ExcelRow(null);
	}
	
	public void resetData(String fileUrl) throws IOException {
		workbook = new XSSFWorkbook(fileUrl);
		sheet = workbook.getSheetAt(0);
	}

	@Override
	public void posRow(int rowNum) throws SheetRowNotFoundException {
		this.currentRow.setRow(sheet.getRow(rowNum - 1));
		if(!this.predicate.test(this.currentRow)) {
			throw new SheetRowNotFoundException();
		}
	}

	@Override
	public int nextRow() throws SheetRowNotFoundException {
		int number = this.currentRow.getRow().getRowNum() + 2;
		this.posRow(number);
		return number;
	}
	
	@Override
	public void nextRowControlCut(Predicate<Row> predicate) {
		this.predicate = predicate;
	}

	@Override
	public String getStringValueByColumn(String columnName) {
		return this.currentRow.getStringValueByColumn(columnName);
	}

	@Override
	public String getStringValueByRowAndColumn(int rowNum, String columnName) throws SheetRowNotFoundException {
		this.posRow(rowNum);
		return this.currentRow.getStringValueByColumn(columnName);
	}

	@Override
	public String getStringValueByRowAndColumn(String columnRowName) throws SheetRowNotFoundException {
		Pattern p = Pattern.compile("([a-zA-Z]*)([0-9]*)");
		Matcher m = p.matcher(columnRowName);
		if (m.find()) {			
			int rowNum = Integer.parseInt(m.replaceAll("$2"));
			String columnName = m.replaceAll("$1");
			return getStringValueByRowAndColumn(rowNum, columnName);
		}
		return null;
	}


	@Override
	public void closeData() {
		try {
			this.workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void resetData() {
		try {
			this.resetData(this.fileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {
		try {
			ExcelRow temp = new ExcelRow(currentRow.getRow());
			this.nextRow();
			this.currentRow = temp;
			return true;
		} catch (SheetRowNotFoundException e) {
			return false;
		}
	}
	
}
