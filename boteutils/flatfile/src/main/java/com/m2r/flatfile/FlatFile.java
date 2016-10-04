package com.m2r.flatfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;
import com.m2r.flatfile.enums.FlatFileMessageEnum;
import com.m2r.flatfile.exception.FlatFileException;

public class FlatFile {

	private static Logger logger = Logger.getLogger(FlatFile.class.getSimpleName());

	private static ResourceBundle resourceBundle;

	private Class<?> readedClass;
	private Field readefField;

	private BufferedReader buffer;
	private String line;
	private boolean isEnd;

	private List<Class<?>> flatFields;

	public FlatFile() {
		this.flatFields = new ArrayList<Class<?>>();
		this.reset();
	}

	public void registerRecord(Class<?> flatField) {

		this.flatFields.add(flatField);
	}

	public void load(Reader reader) throws FlatFileException {

		this.buffer = new BufferedReader(reader);
		this.isEnd = false;
		this.readLine();
	}

	public boolean hasNextRecord() {
		if (this.line == null) {
			if (!this.isEnd) {
				this.reset();
			}
		}
		return !this.isEnd;
	}

	public Object nextRecord() throws FlatFileException {
		try {
			Class<?> clazz = this.getFlatFieldClass();
			Object object = null;
			if (clazz != null) {
				this.readedClass = clazz;
				logger.info("Reading record: " + this.readedClass.getSimpleName());
				object = this.readFlatFileObject(clazz);
			}
			this.readLine();
			return object;
		}
		catch (Exception e) {
			this.reset();
			throw this.erro(FlatFileMessageEnum.ERRO_NEXT_RECORD, e, (this.readefField != null ? this.readefField.getName() : ""), (this.readedClass != null ? this.readedClass.getSimpleName() : ""));
		}
	}

	protected void readLine() throws FlatFileException {
		try {
			this.line = this.buffer.readLine();
		} catch (Exception e) {
			throw new FlatFileException(e);
		}
	}

	protected void reset() {
		try {
			if (this.buffer != null) {
				this.buffer.close();
				this.buffer = null;
			}
			this.isEnd = true;
			this.line = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Class<?> getFlatFieldClass() {

		FlatField ff = null;
		String id = null;
		int flagId, flagMatch = 0;
		for (Class<?> clazz : this.flatFields) {
			flagId = 0;
			flagMatch = 0;
			for (Field field : clazz.getDeclaredFields()) {
				ff = field.getAnnotation(FlatField.class);
				if ((ff != null) && !ff.id().equals("")) {
					flagId++;
					id = this.line.substring(ff.begin(), ff.end());
					if (ff.id().endsWith(id)) {
						flagMatch++;
					}
				}
			}
			if ((flagId > 0) && (flagId == flagMatch)) {
				return clazz;
			}
		}
		return null;
	}

	private Object readFlatFileObject(Class<?> clazz) throws InstantiationException, IllegalAccessException {

		FlatField ff = null;
		FlatFieldTypeEnum ffe = null;
		Object value = null;
		Object object = clazz.newInstance();
		for (Field field : clazz.getDeclaredFields()) {
			this.readefField = field;
			ff = field.getAnnotation(FlatField.class);
			if (ff != null) {
				ffe = ff.converter();
				value = ffe.convert(field.getType(), this.line.substring(ff.begin(), ff.end()), ff.pattern());
				field.setAccessible(true);
				field.set(object, value);
				field.setAccessible(false);
			}
		}
		return object;
	}

	private FlatFileException erro(FlatFileMessageEnum msg, Throwable cause, Object ... params) throws FlatFileException {
		String text = MessageFormat.format(getResourceBundle().getString(msg.getKey()), params);
		logger.log(Level.SEVERE, text, cause);
		return new FlatFileException(text, cause);
	}

	public static ResourceBundle getResourceBundle() {
		if (resourceBundle == null) {
			resourceBundle = ResourceBundle.getBundle("FlatfileMessages");
		}
		return resourceBundle;
	}

}
