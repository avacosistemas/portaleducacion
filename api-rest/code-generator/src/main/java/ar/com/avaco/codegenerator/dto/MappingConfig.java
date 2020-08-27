/**
 * 
 */
package ar.com.avaco.codegenerator.dto;

import java.util.Map;

/**
 * @author Claudio
 *
 */
public class MappingConfig {
	private String formName;
	private String formKey;
	private Integer startGridRow;
 	private Map<String, String> columns;
 	private Map<String, String> columnsType;
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	public Integer getStartGridRow() {
		return startGridRow;
	}
	public void setStartGridRow(Integer startGridRow) {
		this.startGridRow = startGridRow;
	}
	public Map<String, String> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}
	public Map<String, String> getColumnsType() {
		return columnsType;
	}
	public void setColumnsType(Map<String, String> columnsType) {
		this.columnsType = columnsType;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

}
