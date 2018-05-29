package com.m2r.scaffolding.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;

public class ScriptLoader {

	public static void load(File file, Map<String, String> properties) throws MojoExecutionException {
        int newId = 0;
        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            String readLine = "";
            String modelName = b.readLine();
            properties.put("MODEL_NAME", modelName);
            properties.put("MODEL_TABLE", NameUtils.modelPropertyToEnum(NameUtils.modelNameToModelProperty(modelName)));
            properties.put("MODEL_LABEL", NameUtils.modelPropertyToLabelValue(NameUtils.modelNameToModelProperty(modelName)));
            properties.put("MODEL_ICON", "fa-file-o");
            while ((readLine = b.readLine()) != null) {
                ParserScript.parser(readLine, properties, newId++);
            }
            b.close();
        } 
        catch (Exception e) {
        	throw new MojoExecutionException("Erro line " + (newId + 2) + ": " + e.getMessage());
        }		
	}
	
}
