/**
 * 
 */
package ar.com.avaco.codegenerator.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.avaco.codegenerator.dto.SearchExcelConfig;
import ar.com.avaco.commons.domain.FieldDef;
import ar.com.avaco.sheetapi.ExcelSheetReader;
import ar.com.avaco.sheetapi.SheetReader;
import ar.com.avaco.sheetapi.SheetRowNotFoundException;

/**
 * @author Claudio
 *
 */
public class JsSearchCodeGenerator implements CodeGenerator{
	private SearchExcelConfig sec;
	
	public JsSearchCodeGenerator(SearchExcelConfig sec) {
		super();
		this.sec = sec;
	}

	/* (non-Javadoc)
	 * @see ar.com.avaco.codegenerator.CodeGenerator#generate()
	 */
	@Override
	public void generate() {
		ObjectMapper mapper = getObjectMapper();
		List<File> sheetFiles = getFiles(sec.getDirUrl(), sec.getFileName());
		sheetFiles.forEach(file -> {
			try {
				process(mapper, file);
			} catch (IOException | SheetRowNotFoundException e) {
				e.printStackTrace();
			}
		});
	}

	private ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	private void process(ObjectMapper mapper, File file) throws IOException, SheetRowNotFoundException {
		SheetReader reader = new ExcelSheetReader(file.getAbsolutePath());
		List<FieldDef> fields = new ArrayList<>();
		reader.nextRowControlCut(row ->{ 
			return !row.getStringValueByColumn("A").isEmpty();
		});
		
		String formKey = reader.getStringValueByRowAndColumn(sec.getMappingConfig().getFormKey());
		reader.posRow(sec.getMappingConfig().getStartGridRow());
		do{
			Map<String, Object> data = new HashMap<>(); 
			sec.getMappingConfig().getColumns().forEach((fieldKey, columnName) -> {
				String val = reader.getStringValueByColumn(columnName); 
				Object obj = null;
				if(val != null && !val.isEmpty()) {	
					//System.out.println(MessageFormat.format("{0} - {1} - {0}", fieldKey, sec.getMappingConfig().getColumnsType().get(fieldKey), val));
					obj = transformVal(fieldKey, val);						
					data.put(fieldKey, obj);
				}
			});
						
			FieldDef field = mapper.convertValue(data, FieldDef.class);
			fields.add(format(field, mapper));
		}while(reader.hasNext() && reader.nextRow() > 0);
		
		Pattern p = Pattern.compile("\"{1}([a-zA-Z]*)\"{1}:");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String code = gson.toJson(fields);
		Matcher m = p.matcher(code);
		if (m.find()) {
			String template = "export const {0}_SEARCH_FIELDS = {1};\n";
		    code = MessageFormat.format(template,formKey.toUpperCase(), m.replaceAll("$1:").replaceAll("\"", "'"));
		    File f = new File(MessageFormat.format(sec.getOutputFileName(),formKey.toLowerCase()));
		    if(!f.exists()) {
		    	File dirs = new File(f.getAbsolutePath().replace(f.getName(), ""));
		    	if(!dirs.exists()) {
		    		dirs.mkdirs();
		    	}
		    	f.createNewFile();
		    }
		    System.out.println(f.getName());
		    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		    writer.write(code);
		    writer.close();
		}
	}

	private List<File> getFiles(String path, String fileName) {
		File file = new File(path);
		List<File> files = new ArrayList<>();
		if(file.exists()) {
			if(file.isDirectory()) {
				for(File f : file.listFiles()) {
					files.addAll(this.getFiles(f.getAbsolutePath(), fileName));
				}
			}else {
				if (file.getName().equalsIgnoreCase(fileName)) {

					files.add(file);
					return files;
				}
			}
		}
		return files;
	}

	@SuppressWarnings("unchecked")
	private FieldDef format(FieldDef field, ObjectMapper mapper) throws JsonParseException, JsonMappingException, IOException {
		if(field.getOptions() != null) {
		/*	Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
			HashMap<String, Object> map = gson.fromJson((String)field.getOptions(), HashMap.class);
			field.setOptions(map);*/
		}
		field.setControlType(field.getControlType().toLowerCase());
		field.setFilterType(field.getFilterType().toLowerCase());
		return field;
	}

	private Object transformVal(String fieldKey, String val) {
		Object obj = null;
		val = val.trim();
		switch(sec.getMappingConfig().getColumnsType().get(fieldKey)) {
			case "boolean": obj = getBoolean(val); break;
			case "number": obj = new Double(val).longValue();break;
			default: obj = val;
		}
		return obj;
	}

	private Object getBoolean(String val) {
		return "YES".equalsIgnoreCase(val) || 
				"SI".equalsIgnoreCase(val) || 
				"TRUE".equalsIgnoreCase(val);
	}
}
