/**
 * 
 */
package ar.com.avaco.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.avaco.commons.domain.FormDef;
import ar.com.avaco.commons.service.FormDefService;
import ar.com.avaco.service.FormDefInitService;

/**
 * @author avaco
 *
 */

@Transactional
@Service("formDefInitService")
public class FormDefInitServiceImpl implements FormDefInitService{
	
	private String directory;
	
	@Resource
	private FormDefService formDefService;
	
	@Override
	public void init() {
		ClassPathResource cpr = new ClassPathResource(directory);
		try {
			File fdir = cpr.getFile();
			List<FormDef> formsDef = new ArrayList<>();
			if(fdir.isDirectory()) {
				List<File> files = Arrays.asList(fdir.listFiles());
				files.stream().forEach(current -> {
					if(current.isFile()) {				
						FileReader fr;
						try {
							fr = new FileReader(current);
							Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
							FormDef formDef = gson.fromJson(fr, FormDef.class);
							formsDef.add(formDef);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				});	
			}
			this.formDefService.saveFieldsDef(formsDef);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	
}

