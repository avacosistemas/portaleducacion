package ar.com.avaco.codegenerator.dto;

public class SearchExcelConfig {
	private String fileName;
	private String dirUrl;
	private String outputFileName;
	private MappingConfig mappingConfig;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDirUrl() {
		return dirUrl;
	}
	public void setDirUrl(String dirUrl) {
		this.dirUrl = dirUrl;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	public MappingConfig getMappingConfig() {
		return mappingConfig;
	}
	public void setMappingConfig(MappingConfig mappingConfig) {
		this.mappingConfig = mappingConfig;
	}
}
