package ar.com.avaco.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.avaco.codegenerator.service.UmlByEntityDefService;
import ar.com.avaco.commons.domain.ConfigEntityDef;
import ar.com.avaco.commons.domain.EntityDef;
import ar.com.avaco.commons.domain.FieldDef;
import ar.com.avaco.commons.domain.GridDef;
import ar.com.avaco.commons.domain.I18nDef;
//import commons.filters.ExtentionFileFilter;
//import commons.utils.file.FileUtils;


public class GenerateUmlByEntityDefMain {
	private static final String ISO_8859_1 = "ISO-8859-1";
	static String json_file = "src/main/resources/config/def.json";
	public static void main(String[] args) throws IOException {
//		System.out.println("Init");
//		String inputDir = "src/main/resources/bbdd/";
//		if(args.length > 2) {	
//			inputDir = args[0];
//			json_file = args[2];
//			
//		}
//		List<File> files = FileUtils.getRecursiveFileFromPath(inputDir, Arrays.asList(new ExtentionFileFilter(".json")));
//		List<EntityDef> entitiesDef = new ArrayList<>();
//		if(files != null && files.size() > 0) {
//			files.stream().forEach(current -> {
//				System.out.println(current.getPath());
//				   try (FileReader fr = new FileReader(current)){
//						Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
//						EntityDef entityDef = gson.fromJson(fr, EntityDef.class);
//						entitiesDef.add(entityDef);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//			});
//		}
//		UmlByEntityDefService umlService = new UmlByEntityDefService();
//		String umlString = umlService.umlByEntitiesDef(entitiesDef);
//		System.out.println(umlString);			
	}
}
