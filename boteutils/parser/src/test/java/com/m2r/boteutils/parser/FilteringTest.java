package com.m2r.boteutils.parser;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceReader;
import org.junit.Test;

public class FilteringTest {

	@Test
	public void test() throws Exception {
		InputStream in = getClass().getResourceAsStream("filtering.properties");
	    byte[] buffer = IOUtils.toByteArray(in);
	    Reader reader = new CharSequenceReader(new String(buffer, StandardCharsets.ISO_8859_1));
	    StringWriter writer = new StringWriter();
		FilteringParser.parse(reader, writer, (id) -> {
			if (id.equals("project.group")) {
				return "COM.M2R.PARSER";
			}
			else if (id.equals("project.name")) {
				return "PARSER-TESTE";
			}
			else {
				return id;
			}
		});
	    reader.close();		
	    reader = new CharSequenceReader(writer.getBuffer());
		Properties properties = new Properties();
		properties.load(reader);
	    reader.close();		
		writer.close();
		assertEquals(properties.getProperty("Group"), "COM.M2R.PARSER");
		assertEquals(properties.getProperty("Name"), "PARSER-TESTE");
	}
	
}
