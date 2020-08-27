package ar.com.avaco.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.FileCopyUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.avaco.commons.domain.ConfigEntityDef;
import ar.com.avaco.commons.domain.EntityDef;
import ar.com.avaco.commons.domain.FieldDef;
import ar.com.avaco.commons.domain.GridDef;
import ar.com.avaco.commons.domain.I18nDef;


public class GenerateCrudDefMain {
	private static final String ISO_8859_1 = "ISO-8859-1";
	static String json_file = "src/main/resources/config/def.json";
	public static void main(String[] args) throws IOException {
		System.out.println("Init");
		String inputDir = "src/main/resources/config/entity-def/";
		String outputDir = "src/main/resources/config/output-data/";
		if(args.length > 2) {	
			inputDir = args[0];
			outputDir = args[1];
			json_file = args[2];
			
		}
		final String outputDirFinal = outputDir;
		File fdir = new File(inputDir);
		if(fdir.isDirectory()) {
			List<File> files = Arrays.asList(fdir.listFiles());
			files.stream().forEach(current -> {
				if(current.isFile()) {				
					FileReader fr;
					try {
						fr = new FileReader(current);
						Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
						ConfigEntityDef entityDef = gson.fromJson(fr, ConfigEntityDef.class);
						String functionalityName = entityDef.getKey().toLowerCase();
						String dirName = outputDirFinal + entityDef.getKey().toLowerCase();
						generateOutputDir(dirName);
						Map<String, String> keyValue = new LinkedHashMap<>();
						if(entityDef.getPageTitle() != null) {
							keyValue.put("page_title", entityDef.getPageTitle());
						}else {
							keyValue.put("page_title", "page_title");
						}
						generateNavigation(dirName, functionalityName, entityDef, keyValue);
						generateForms(dirName, functionalityName, entityDef, keyValue);
//						generateEntity("src/java/resources/config/output-data/", current)
						generateGrid(dirName, functionalityName, entityDef);
						entityDef.getFields().forEach(f -> {
							putKeyValueField(keyValue, f);	
						});
						generateSecurity(dirName, functionalityName, entityDef);
						generateI18n(dirName, functionalityName, keyValue);
						generateFunctionalityDef(dirName, functionalityName, entityDef);
//						generateScss("src/java/resources/config/output-data/", current);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
		}
			
	}

	private static void putKeyValueField(Map<String, String> keyValue, FieldDef f) {
		String key = f.getLabelKey().toLowerCase();
		String value = key;
		if(f.getLabel() != null) {
			value = f.getLabel();
		}
		keyValue.put(key, value);
	}

	private static void generateFunctionalityDef(String dirName, String functionalityName, ConfigEntityDef entityDef) {
		try {
			String fileName = functionalityName + ".def.ts";
			VelocityContext context = new VelocityContext();
			context.put("functionalityNameUppercase", functionalityName.toUpperCase());
			context.put("functionalityNameLowercase", functionalityName.toLowerCase());
			context.put("functionalityUrl", entityDef.getWs());
			VelocityEngine ve = new VelocityEngine();
		    ve.setProperty("resource.loader", "file");
		    ve.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		    ve.setProperty("file.resource.loader.path", "");
			ve.init();
			File input = new File(json_file);
			File output = new File(dirName + "/" + fileName);
			FileCopyUtils.copy(input, output);
			Template template = ve.getTemplate(output.getAbsolutePath(), ISO_8859_1);
		    File f = new File(dirName + "/" + fileName);
		    if(!f.exists()) {
				f.createNewFile();
		    }
			FileWriter writer = new FileWriter(f);
			template.merge(context, writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void generateNavigation(String dirName, String functionalityName, ConfigEntityDef config, Map<String, String> keyValue) {
		dirName = dirName + "/navigation";
		String fileName = functionalityName + ".nav.ts";
		String functionlityPrefix = functionalityName.toUpperCase() + "_NAV_DEF";
		String functionlityPrefixLowerCase = functionlityPrefix.toLowerCase();
		if(config.getNavTitle() != null) {			
			keyValue.put(functionlityPrefixLowerCase, config.getNavTitle());
		}else {
			keyValue.put(functionlityPrefixLowerCase, functionlityPrefixLowerCase);
		}
		Map<String, String> navigationMap = new LinkedHashMap<>();
		navigationMap.put("id", functionalityName.toUpperCase());
		navigationMap.put("translateKey", functionlityPrefixLowerCase);
		if(!"/".equals(String.valueOf(config.getRoute().charAt(0)))) {
			config.setRoute("/" + config.getRoute());
		}
		navigationMap.put("url", config.getRoute());
		createFile(dirName, fileName, functionlityPrefix, navigationMap);
	}

	private static void generateSecurity(String dirName, String functionalityName, EntityDef entityDef) {
		dirName = dirName + "/security";
		String fileName = functionalityName + ".security.ts";
		String functionlityPrefix = functionalityName.toUpperCase() + "_SECURITY_DEF";
		Map<String, String> securityMap = new LinkedHashMap<>();
		securityMap.put("readAccess", functionalityName.toUpperCase() + "_READ");
		securityMap.put("updateAccess", functionalityName.toUpperCase() + "_UPDATE");
		securityMap.put("createAccess", functionalityName.toUpperCase() + "_CREATE");
		securityMap.put("deleteAccess", functionalityName.toUpperCase() + "_DELETE");
		createFile(dirName, fileName, functionlityPrefix, securityMap);
	}

	private static void generateI18n(String dirName, String functionalityName, Map<String, String> keyValue) {
		dirName = dirName + "/i18n";
		String fileName = functionalityName + ".i18n.ts";
		String functionlityPrefix = functionalityName.toUpperCase() + "_I18N_DEF";
		I18nDef i18nDef = new I18nDef(functionlityPrefix, keyValue);
		createFile(dirName, fileName, functionlityPrefix, i18nDef);
	}

	private static void generateGrid(String dirName, String functionalityName, EntityDef entityDef) {
		List<FieldDef> fields = entityDef.getFields();
		dirName = dirName + "/grid";
		String fileName = functionalityName + ".grid.ts";
		String functionlityPrefix = functionalityName.toUpperCase() + "_GRID_DEF";
		setI18nFields(functionlityPrefix.toLowerCase() + "_column", fields);
		GridDef gridDef = new GridDef(entityDef);
		createFile(dirName, fileName, functionlityPrefix, gridDef);
	}

	private static void generateForms(String dirName, String  functionalityName, EntityDef entityDef,Map<String, String> keyValue) {
		List<FieldDef> fields = entityDef.getFields();
		// Generico
		fields.forEach(f -> {
			// Seteo de tipo
			String type = f.getType().toUpperCase();
			if(FieldDef.TYPE_NUMBER.equals(type)) {
				f.setControlType("number");
			}else if (FieldDef.TYPE_BOOLEAN.equals(type)){
				f.setControlType("checkbox");
			}else {
				if(f.getMaxLength() == null || f.getMaxLength() < 100) {
					f.setControlType("textbox");
				}else {
					f.setControlType("textarea");
				}
			}
		});
		dirName = dirName + "/form";
		// Detalles de formulario create
		String fileName = functionalityName + ".create.fields.ts";
		String functionlityPrefix = functionalityName.toUpperCase() + "_CREATE_FORM_FIELDS_DEF";
		setI18nFields(functionlityPrefix.toLowerCase() + "_field", fields);
		entityDef.getFields().forEach(f -> putKeyValueField(keyValue, f));
		createFile(dirName, fileName, functionlityPrefix, fields);
		// Detalles de formulario update
		fileName = functionalityName + ".update.fields.ts";
		functionlityPrefix = functionalityName.toUpperCase() + "_UPDATE_FORM_FIELDS_DEF";
		setI18nFields(functionlityPrefix + "_FIELD", fields);
		entityDef.getFields().forEach(f -> putKeyValueField(keyValue, f));
		createFile(dirName, fileName, functionlityPrefix, fields);
		// Detalles de formulario read
		fileName = functionalityName + ".read.fields.ts";
		functionlityPrefix = functionalityName.toUpperCase() + "_READ_FORM_FIELDS_DEF";
		fields.forEach(f -> f.setDisabled(true));
		setI18nFields(functionlityPrefix + "_FIELD", fields);
		entityDef.getFields().forEach(f -> putKeyValueField(keyValue, f));
		createFile(dirName, fileName, functionlityPrefix, fields);
		// Detalles de formulario filter
		fileName = functionalityName + ".filter.fields.ts";
		functionlityPrefix = functionalityName.toUpperCase() + "_FILTER_FORM_FIELDS_DEF";
		fields.forEach(f -> { 
			f.setDisabled(null);
			f.setMinLength(null);
			f.setRequired(null);
			f.setValidations(null);
		});
		setI18nFields(functionlityPrefix + "_FIELD", fields);
		entityDef.getFields().forEach(f -> putKeyValueField(keyValue, f));
		createFile(dirName, fileName, functionlityPrefix, fields);
	}

	private static void createFile(String dirName, String fileName, String functionlityPrefix, Object obj) {
		try {
			Pattern p = Pattern.compile("\"{1}([a-zA-Z_]*)\"{1}:");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String code = gson.toJson(obj);
			Matcher m = p.matcher(code);
			if (m.find()) {
				String template = "export const {0} = {1};\n";
				code = m.replaceAll("$1:").replaceAll("\"", "'");
			    code = MessageFormat.format(template, functionlityPrefix, code);
			    File dir = new File(dirName);
			    if(!dir.exists()) {
			    	dir.mkdirs();
			    }
			    File f = new File(dirName + "/" + fileName);
			    if(!f.exists()) {
					f.createNewFile();
			    }
			    System.out.println(f.getName());
			    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			    writer.write(code);
			    writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private static void setI18nFields(String prefix, List<FieldDef> fields) {
		fields.forEach(f ->{
			f.setLabelKey(prefix + "_" + f.getKey().toLowerCase());
		});
	}

	private static void generateOutputDir(String dirName) {
		File f = new File(dirName);
		if(!f.exists()) {
			f.mkdirs();
		}
	}
}
